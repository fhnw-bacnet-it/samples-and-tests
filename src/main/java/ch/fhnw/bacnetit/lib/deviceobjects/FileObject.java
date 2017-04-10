/*******************************************************************************
 * Copyright (C) 2016 The Java BACnetITB Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.fhnw.bacnetit.lib.deviceobjects;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetRuntimeException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.FileAccessMethod;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;

/**
 * @author Matthew Lohbihler
 */
public class FileObject extends BACnetObject {
    private static final long serialVersionUID = 1089963077847602732L;

    /**
     * The actual file that this object represents.
     */
    private final File file;

    public FileObject(final BACnetObjectIdentifier oid, final File file,
            final FileAccessMethod fileAccessMethod) {
        super(oid);
        this.file = file;

        if (file.isDirectory()) {
            throw new BACnetRuntimeException("File is a directory");
        }

        updateProperties();

        try {
            setProperty(BACnetPropertyIdentifier.fileAccessMethod,
                    fileAccessMethod);
        } catch (final BACnetServiceException e) {
            // Should never happen, but wrap in an unchecked just in case.
            throw new BACnetRuntimeException(e);
        }
    }

    public void updateProperties() {
        try {
            // TODO this is only a snapshot. Property read methods need to be
            // overridden to report real time values.
            setProperty(BACnetPropertyIdentifier.fileSize, new UnsignedInteger(
                    new BigInteger(Long.toString(length()))));
            setProperty(BACnetPropertyIdentifier.modificationDate,
                    new DateTime(file.lastModified()));
            setProperty(BACnetPropertyIdentifier.readOnly,
                    new Boolean(!file.canWrite()));
        } catch (final BACnetServiceException e) {
            // Should never happen, but wrap in an unchecked just in case.
            throw new BACnetRuntimeException(e);
        }
    }

    public long length() {
        return file.length();
    }

    public OctetString readData(long start, final long length)
            throws IOException {
        final FileInputStream in = new FileInputStream(file);
        try {
            while (start > 0) {
                final long result = in.skip(start);
                if (result == -1) {
                    // EOF
                    break;
                }
                start -= result;
            }
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            // StreamUtils.transfer(in, out, length);
            return new OctetString(out.toByteArray());
        } finally {
            in.close();
        }
    }

    public void writeData(final long start, final OctetString fileData)
            throws IOException {
        final RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            final byte data[] = fileData.getBytes();
            final long newLength = start + data.length;
            raf.seek(start);
            raf.write(data);
            raf.setLength(newLength);
        } finally {
            raf.close();
        }

        updateProperties();
    }

    //
    // public SequenceOf<OctetString> readRecords(int start, int length) throws
    // IOException {
    //
    // }
}
