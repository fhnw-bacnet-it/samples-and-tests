/*******************************************************************************
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2017 University of Applied Sciences and Arts,
 * Northwestern Switzerland FHNW,
 * Institute of Mobile and Distributed Systems.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.orglicenses.
 *******************************************************************************/
package ch.fhnw.bacnetit.samplesandtests.api.deviceobjects;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetRuntimeException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.FileAccessMethod;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;

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
