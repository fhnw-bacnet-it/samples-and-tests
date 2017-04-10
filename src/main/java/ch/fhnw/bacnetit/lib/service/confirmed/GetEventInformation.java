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
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class GetEventInformation extends ConfirmedRequestService {
    private static final long serialVersionUID = 5920365345189498832L;

    public static final byte TYPE_ID = 29;

    private final BACnetObjectIdentifier lastReceivedObjectIdentifier;

    public GetEventInformation(
            final BACnetObjectIdentifier lastReceivedObjectIdentifier) {
        this.lastReceivedObjectIdentifier = lastReceivedObjectIdentifier;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeOptional(queue, lastReceivedObjectIdentifier, 0);
    }

    GetEventInformation(final ByteQueue queue) throws BACnetException {
        lastReceivedObjectIdentifier = readOptional(queue,
                BACnetObjectIdentifier.class, 0);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((lastReceivedObjectIdentifier == null) ? 0
                : lastReceivedObjectIdentifier.hashCode());
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
        final GetEventInformation other = (GetEventInformation) obj;
        if (lastReceivedObjectIdentifier == null) {
            if (other.lastReceivedObjectIdentifier != null) {
                return false;
            }
        } else if (!lastReceivedObjectIdentifier
                .equals(other.lastReceivedObjectIdentifier)) {
            return false;
        }
        return true;
    }
}
