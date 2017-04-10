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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AtomicWriteFileAck extends AcknowledgementService {
    private static final long serialVersionUID = -3122331020521995628L;

    public static final byte TYPE_ID = 7;

    private final boolean recordAccess;

    private final SignedInteger fileStart;

    public AtomicWriteFileAck(final boolean recordAccess,
            final SignedInteger fileStart) {
        this.recordAccess = recordAccess;
        this.fileStart = fileStart;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, fileStart, recordAccess ? 1 : 0);
    }

    AtomicWriteFileAck(final ByteQueue queue) throws BACnetException {
        recordAccess = peekTagNumber(queue) == 1;
        fileStart = read(queue, SignedInteger.class, recordAccess ? 1 : 0);
    }

    public boolean isRecordAccess() {
        return recordAccess;
    }

    public SignedInteger getFileStart() {
        return fileStart;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((fileStart == null) ? 0 : fileStart.hashCode());
        result = PRIME * result + (recordAccess ? 1231 : 1237);
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
        final AtomicWriteFileAck other = (AtomicWriteFileAck) obj;
        if (fileStart == null) {
            if (other.fileStart != null) {
                return false;
            }
        } else if (!fileStart.equals(other.fileStart)) {
            return false;
        }
        if (recordAccess != other.recordAccess) {
            return false;
        }
        return true;
    }
}
