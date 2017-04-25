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
package ch.fhnw.bacnetit.misc.encoding.type.constructed;

import ch.fhnw.bacnetit.misc.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.misc.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.Encodable;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class DeviceObjectPropertyValue extends BaseType {
    private static final long serialVersionUID = 7014807902238765395L;

    private final BACnetObjectIdentifier deviceIdentifier;

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger arrayIndex;

    private final Encodable value;

    public DeviceObjectPropertyValue(
            final BACnetObjectIdentifier deviceIdentifier,
            final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger arrayIndex, final Encodable value) {
        this.deviceIdentifier = deviceIdentifier;
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.arrayIndex = arrayIndex;
        this.value = value;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, deviceIdentifier, 0);
        write(queue, objectIdentifier, 1);
        write(queue, propertyIdentifier, 2);
        writeOptional(queue, arrayIndex, 3);
        write(queue, value, 4);
    }

    public DeviceObjectPropertyValue(final ByteQueue queue)
            throws BACnetException {
        deviceIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 1);
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 2);
        arrayIndex = readOptional(queue, UnsignedInteger.class, 3);
        value = readEncodable(queue, objectIdentifier.getObjectType(),
                propertyIdentifier, null, 4);
    }

    public BACnetObjectIdentifier getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    public UnsignedInteger getArrayIndex() {
        return arrayIndex;
    }

    public Encodable getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((arrayIndex == null) ? 0 : arrayIndex.hashCode());
        result = PRIME * result + ((deviceIdentifier == null) ? 0
                : deviceIdentifier.hashCode());
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
        result = PRIME * result + ((value == null) ? 0 : value.hashCode());
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
        final DeviceObjectPropertyValue other = (DeviceObjectPropertyValue) obj;
        if (arrayIndex == null) {
            if (other.arrayIndex != null) {
                return false;
            }
        } else if (!arrayIndex.equals(other.arrayIndex)) {
            return false;
        }
        if (deviceIdentifier == null) {
            if (other.deviceIdentifier != null) {
                return false;
            }
        } else if (!deviceIdentifier.equals(other.deviceIdentifier)) {
            return false;
        }
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null) {
                return false;
            }
        } else if (!propertyIdentifier.equals(other.propertyIdentifier)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
