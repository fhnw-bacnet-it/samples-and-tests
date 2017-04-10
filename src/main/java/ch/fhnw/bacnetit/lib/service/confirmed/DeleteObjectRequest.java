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

public class DeleteObjectRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 6629196264191258622L;

    public static final byte TYPE_ID = 11;

    private final BACnetObjectIdentifier objectIdentifier;

    public DeleteObjectRequest(final BACnetObjectIdentifier objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetErrorException {
    // try {
    // localDevice.removeObject(objectIdentifier);
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(getChoiceId(), e);
    // }
    //
    // // Returning null sends a simple ack.
    // return null;
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier);
    }

    DeleteObjectRequest(final ByteQueue queue) throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
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
        final DeleteObjectRequest other = (DeleteObjectRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        return true;
    }
}
