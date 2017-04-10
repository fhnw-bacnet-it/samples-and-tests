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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.Address;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class RequestKeyRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 6550270345275044608L;

    public static final byte TYPE_ID = 25;

    private final BACnetObjectIdentifier requestingDeviceIdentifier;

    private final Address requestingDeviceAddress;

    private final BACnetObjectIdentifier remoteDeviceIdentifier;

    private final Address remoteDeviceAddress;

    public RequestKeyRequest(
            final BACnetObjectIdentifier requestingDeviceIdentifier,
            final Address requestingDeviceAddress,
            final BACnetObjectIdentifier remoteDeviceIdentifier,
            final Address remoteDeviceAddress) {
        this.requestingDeviceIdentifier = requestingDeviceIdentifier;
        this.requestingDeviceAddress = requestingDeviceAddress;
        this.remoteDeviceIdentifier = remoteDeviceIdentifier;
        this.remoteDeviceAddress = remoteDeviceAddress;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, requestingDeviceIdentifier);
        write(queue, requestingDeviceAddress);
        write(queue, remoteDeviceIdentifier);
        write(queue, remoteDeviceAddress);
    }

    RequestKeyRequest(final ByteQueue queue) throws BACnetException {
        requestingDeviceIdentifier = read(queue, BACnetObjectIdentifier.class);
        requestingDeviceAddress = read(queue, Address.class);
        remoteDeviceIdentifier = read(queue, BACnetObjectIdentifier.class);
        remoteDeviceAddress = read(queue, Address.class);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((remoteDeviceAddress == null) ? 0
                : remoteDeviceAddress.hashCode());
        result = PRIME * result + ((remoteDeviceIdentifier == null) ? 0
                : remoteDeviceIdentifier.hashCode());
        result = PRIME * result + ((requestingDeviceAddress == null) ? 0
                : requestingDeviceAddress.hashCode());
        result = PRIME * result + ((requestingDeviceIdentifier == null) ? 0
                : requestingDeviceIdentifier.hashCode());
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
        final RequestKeyRequest other = (RequestKeyRequest) obj;
        if (remoteDeviceAddress == null) {
            if (other.remoteDeviceAddress != null) {
                return false;
            }
        } else if (!remoteDeviceAddress.equals(other.remoteDeviceAddress)) {
            return false;
        }
        if (remoteDeviceIdentifier == null) {
            if (other.remoteDeviceIdentifier != null) {
                return false;
            }
        } else if (!remoteDeviceIdentifier
                .equals(other.remoteDeviceIdentifier)) {
            return false;
        }
        if (requestingDeviceAddress == null) {
            if (other.requestingDeviceAddress != null) {
                return false;
            }
        } else if (!requestingDeviceAddress
                .equals(other.requestingDeviceAddress)) {
            return false;
        }
        if (requestingDeviceIdentifier == null) {
            if (other.requestingDeviceIdentifier != null) {
                return false;
            }
        } else if (!requestingDeviceIdentifier
                .equals(other.requestingDeviceIdentifier)) {
            return false;
        }
        return true;
    }
}
