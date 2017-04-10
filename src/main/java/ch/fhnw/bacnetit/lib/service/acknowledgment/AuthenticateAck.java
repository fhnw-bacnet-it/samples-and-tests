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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AuthenticateAck extends AcknowledgementService {
    private static final long serialVersionUID = 1433915425430939025L;

    public static final byte TYPE_ID = 24;

    private final UnsignedInteger modifiedRandomNumber;

    public AuthenticateAck(final UnsignedInteger modifiedRandomNumber) {
        this.modifiedRandomNumber = modifiedRandomNumber;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, modifiedRandomNumber);
    }

    AuthenticateAck(final ByteQueue queue) throws BACnetException {
        modifiedRandomNumber = read(queue, UnsignedInteger.class);
    }

    public UnsignedInteger getModifiedRandomNumber() {
        return modifiedRandomNumber;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((modifiedRandomNumber == null) ? 0
                : modifiedRandomNumber.hashCode());
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
        final AuthenticateAck other = (AuthenticateAck) obj;
        if (modifiedRandomNumber == null) {
            if (other.modifiedRandomNumber != null) {
                return false;
            }
        } else if (!modifiedRandomNumber.equals(other.modifiedRandomNumber)) {
            return false;
        }
        return true;
    }
}
