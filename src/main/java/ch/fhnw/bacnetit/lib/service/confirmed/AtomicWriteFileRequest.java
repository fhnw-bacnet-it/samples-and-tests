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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AtomicWriteFileRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 2426317127743066428L;

    public static final byte TYPE_ID = 7;

    private final BACnetObjectIdentifier fileIdentifier;

    private SignedInteger fileStart;

    private OctetString fileData;

    private UnsignedInteger recordCount;

    private SequenceOf<OctetString> fileRecordData;

    public AtomicWriteFileRequest(final BACnetObjectIdentifier fileIdentifier,
            final SignedInteger fileStart, final OctetString fileData) {
        super();
        this.fileIdentifier = fileIdentifier;
        this.fileStart = fileStart;
        this.fileData = fileData;
    }

    public AtomicWriteFileRequest(final BACnetObjectIdentifier fileIdentifier,
            final SignedInteger fileStart, final UnsignedInteger recordCount,
            final SequenceOf<OctetString> fileRecordData) {
        super();
        this.fileIdentifier = fileIdentifier;
        this.fileStart = fileStart;
        this.recordCount = recordCount;
        this.fileRecordData = fileRecordData;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // AtomicWriteFileAck response;
    //
    // BACnetObject obj;
    // FileObject file;
    // try {
    // // Find the file.
    // obj = localDevice.getObjectRequired(fileIdentifier);
    // if (!(obj instanceof FileObject))
    // throw new BACnetServiceException(ErrorClass.object,
    // ErrorCode.rejectInconsistentParameters);
    // file = (FileObject) obj;
    //
    // // Validation.
    // FileAccessMethod fileAccessMethod = (FileAccessMethod) file
    // .getProperty(PropertyIdentifier.fileAccessMethod);
    // if (fileData == null &&
    // fileAccessMethod.equals(FileAccessMethod.streamAccess) || fileData !=
    // null
    // && fileAccessMethod.equals(FileAccessMethod.recordAccess))
    // throw new BACnetErrorException(getChoiceId(), ErrorClass.object,
    // ErrorCode.invalidFileAccessMethod);
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(getChoiceId(), e);
    // }
    //
    // if (fileData == null) {
    // throw new NotImplementedException();
    // }
    //
    // long start = fileStart.longValue();
    //
    // if (start > file.length())
    // throw new BACnetErrorException(getChoiceId(), ErrorClass.object,
    // ErrorCode.invalidFileStartPosition);
    //
    // try {
    // file.writeData(start, fileData);
    // response = new AtomicWriteFileAck(fileData == null, fileStart);
    // }
    // catch (IOException e) {
    // throw new BACnetErrorException(getChoiceId(), ErrorClass.object,
    // ErrorCode.fileAccessDenied);
    // }
    //
    // return response;
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, fileIdentifier);
        if (fileData != null) {
            writeContextTag(queue, 0, true);
            write(queue, fileStart);
            write(queue, fileData);
            writeContextTag(queue, 0, false);
        } else {
            writeContextTag(queue, 1, true);
            write(queue, fileStart);
            write(queue, recordCount);
            write(queue, fileRecordData);
            writeContextTag(queue, 1, false);
        }
    }

    AtomicWriteFileRequest(final ByteQueue queue) throws BACnetException {
        fileIdentifier = read(queue, BACnetObjectIdentifier.class);
        if (popStart(queue) == 0) {
            fileStart = read(queue, SignedInteger.class);
            fileData = read(queue, OctetString.class);
            popEnd(queue, 0);
        } else {
            fileStart = read(queue, SignedInteger.class);
            recordCount = read(queue, UnsignedInteger.class);
            fileRecordData = readSequenceOf(queue, recordCount.intValue(),
                    OctetString.class);
            popEnd(queue, 1);
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((fileData == null) ? 0 : fileData.hashCode());
        result = PRIME * result
                + ((fileIdentifier == null) ? 0 : fileIdentifier.hashCode());
        result = PRIME * result
                + ((fileRecordData == null) ? 0 : fileRecordData.hashCode());
        result = PRIME * result
                + ((fileStart == null) ? 0 : fileStart.hashCode());
        result = PRIME * result
                + ((recordCount == null) ? 0 : recordCount.hashCode());
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
        final AtomicWriteFileRequest other = (AtomicWriteFileRequest) obj;
        if (fileData == null) {
            if (other.fileData != null) {
                return false;
            }
        } else if (!fileData.equals(other.fileData)) {
            return false;
        }
        if (fileIdentifier == null) {
            if (other.fileIdentifier != null) {
                return false;
            }
        } else if (!fileIdentifier.equals(other.fileIdentifier)) {
            return false;
        }
        if (fileRecordData == null) {
            if (other.fileRecordData != null) {
                return false;
            }
        } else if (!fileRecordData.equals(other.fileRecordData)) {
            return false;
        }
        if (fileStart == null) {
            if (other.fileStart != null) {
                return false;
            }
        } else if (!fileStart.equals(other.fileStart)) {
            return false;
        }
        if (recordCount == null) {
            if (other.recordCount != null) {
                return false;
            }
        } else if (!recordCount.equals(other.recordCount)) {
            return false;
        }
        return true;
    }
}
