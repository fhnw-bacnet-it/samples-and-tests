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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AddressBinding extends BaseType {
    private static final long serialVersionUID = -3619507415957976531L;

    private final BACnetObjectIdentifier deviceObjectIdentifier;

    private final Address deviceAddress;

    public AddressBinding(final BACnetObjectIdentifier deviceObjectIdentifier,
            final Address deviceAddress) {
        this.deviceObjectIdentifier = deviceObjectIdentifier;
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, deviceObjectIdentifier);
        write(queue, deviceAddress);
    }

    public AddressBinding(final ByteQueue queue) throws BACnetException {
        deviceObjectIdentifier = read(queue, BACnetObjectIdentifier.class);
        deviceAddress = read(queue, Address.class);
    }

    public BACnetObjectIdentifier getDeviceObjectIdentifier() {
        return deviceObjectIdentifier;
    }

    public Address getDeviceAddress() {
        return deviceAddress;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((deviceAddress == null) ? 0 : deviceAddress.hashCode());
        result = PRIME * result + ((deviceObjectIdentifier == null) ? 0
                : deviceObjectIdentifier.hashCode());
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
        final AddressBinding other = (AddressBinding) obj;
        if (deviceAddress == null) {
            if (other.deviceAddress != null) {
                return false;
            }
        } else if (!deviceAddress.equals(other.deviceAddress)) {
            return false;
        }
        if (deviceObjectIdentifier == null) {
            if (other.deviceObjectIdentifier != null) {
                return false;
            }
        } else if (!deviceObjectIdentifier
                .equals(other.deviceObjectIdentifier)) {
            return false;
        }
        return true;
    }
}
