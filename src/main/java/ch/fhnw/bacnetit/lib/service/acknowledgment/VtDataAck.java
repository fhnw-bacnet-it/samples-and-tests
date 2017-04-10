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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class VtDataAck extends AcknowledgementService {
    private static final long serialVersionUID = -178402574862840705L;

    public static final byte TYPE_ID = 23;

    private final Boolean allNewDataAccepted;

    private final UnsignedInteger acceptedOctetCount;

    public VtDataAck(final Boolean allNewDataAccepted,
            final UnsignedInteger acceptedOctetCount) {
        this.allNewDataAccepted = allNewDataAccepted;
        this.acceptedOctetCount = acceptedOctetCount;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, allNewDataAccepted, 0);
        writeOptional(queue, acceptedOctetCount, 1);
    }

    VtDataAck(final ByteQueue queue) throws BACnetException {
        allNewDataAccepted = read(queue, Boolean.class, 0);
        acceptedOctetCount = readOptional(queue, UnsignedInteger.class, 1);
    }

    public Boolean getAllNewDataAccepted() {
        return allNewDataAccepted;
    }

    public UnsignedInteger getAcceptedOctetCount() {
        return acceptedOctetCount;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((acceptedOctetCount == null) ? 0
                : acceptedOctetCount.hashCode());
        result = PRIME * result + ((allNewDataAccepted == null) ? 0
                : allNewDataAccepted.hashCode());
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
        final VtDataAck other = (VtDataAck) obj;
        if (acceptedOctetCount == null) {
            if (other.acceptedOctetCount != null) {
                return false;
            }
        } else if (!acceptedOctetCount.equals(other.acceptedOctetCount)) {
            return false;
        }
        if (allNewDataAccepted == null) {
            if (other.allNewDataAccepted != null) {
                return false;
            }
        } else if (!allNewDataAccepted.equals(other.allNewDataAccepted)) {
            return false;
        }
        return true;
    }
}
