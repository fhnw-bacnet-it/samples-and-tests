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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AtomicReadFileAck extends AcknowledgementService {
    private static final long serialVersionUID = 7850183659621947037L;

    public static final byte TYPE_ID = 6;

    private final Boolean endOfFile;

    private final SignedInteger fileStartPosition;

    private final OctetString fileData;

    private final UnsignedInteger returnedRecordCount;

    private final SequenceOf<OctetString> fileRecordData;

    public AtomicReadFileAck(final Boolean endOfFile,
            final SignedInteger fileStartPosition, final OctetString fileData) {
        super();
        this.endOfFile = endOfFile;
        this.fileStartPosition = fileStartPosition;
        this.fileData = fileData;
        returnedRecordCount = null;
        fileRecordData = null;
    }

    public AtomicReadFileAck(final Boolean endOfFile,
            final SignedInteger fileStartPosition,
            final UnsignedInteger returnedRecordCount,
            final SequenceOf<OctetString> fileRecordData) {
        super();
        this.endOfFile = endOfFile;
        this.fileStartPosition = fileStartPosition;
        fileData = null;
        this.returnedRecordCount = returnedRecordCount;
        this.fileRecordData = fileRecordData;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, endOfFile);
        if (fileData != null) {
            writeContextTag(queue, 0, true);
            write(queue, fileStartPosition);
            write(queue, fileData);
            writeContextTag(queue, 0, false);
        } else {
            writeContextTag(queue, 1, true);
            write(queue, fileStartPosition);
            write(queue, returnedRecordCount);
            write(queue, fileRecordData);
            writeContextTag(queue, 1, false);
        }
    }

    AtomicReadFileAck(final ByteQueue queue) throws BACnetException {
        endOfFile = read(queue, Boolean.class);
        if (popStart(queue) == 0) {
            fileStartPosition = read(queue, SignedInteger.class);
            fileData = read(queue, OctetString.class);
            returnedRecordCount = null;
            fileRecordData = null;
            popEnd(queue, 0);
        } else {
            fileStartPosition = read(queue, SignedInteger.class);
            returnedRecordCount = read(queue, UnsignedInteger.class);
            fileData = null;
            final List<OctetString> records = new ArrayList<OctetString>();
            for (int i = 0; i < returnedRecordCount.intValue(); i++) {
                records.add(read(queue, OctetString.class));
            }
            fileRecordData = new SequenceOf<OctetString>(records);
            popEnd(queue, 1);
        }
    }

    public Boolean getEndOfFile() {
        return endOfFile;
    }

    public SignedInteger getFileStartPosition() {
        return fileStartPosition;
    }

    public OctetString getFileData() {
        return fileData;
    }

    public UnsignedInteger getReturnedRecordCount() {
        return returnedRecordCount;
    }

    public SequenceOf<OctetString> getFileRecordData() {
        return fileRecordData;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((endOfFile == null) ? 0 : endOfFile.hashCode());
        result = PRIME * result
                + ((fileData == null) ? 0 : fileData.hashCode());
        result = PRIME * result
                + ((fileRecordData == null) ? 0 : fileRecordData.hashCode());
        result = PRIME * result + ((fileStartPosition == null) ? 0
                : fileStartPosition.hashCode());
        result = PRIME * result + ((returnedRecordCount == null) ? 0
                : returnedRecordCount.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AtomicReadFileAck other = (AtomicReadFileAck) obj;
        if (endOfFile == null) {
            if (other.endOfFile != null) {
                return false;
            }
        } else if (!endOfFile.equals(other.endOfFile)) {
            return false;
        }
        if (fileData == null) {
            if (other.fileData != null) {
                return false;
            }
        } else if (!fileData.equals(other.fileData)) {
            return false;
        }
        if (fileRecordData == null) {
            if (other.fileRecordData != null) {
                return false;
            }
        } else if (!fileRecordData.equals(other.fileRecordData)) {
            return false;
        }
        if (fileStartPosition == null) {
            if (other.fileStartPosition != null) {
                return false;
            }
        } else if (!fileStartPosition.equals(other.fileStartPosition)) {
            return false;
        }
        if (returnedRecordCount == null) {
            if (other.returnedRecordCount != null) {
                return false;
            }
        } else if (!returnedRecordCount.equals(other.returnedRecordCount)) {
            return false;
        }
        return true;
    }
}
