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
package ch.fhnw.bacnetit.lib.deviceobjects;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Primitive;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BACnetObjectIdentifier extends Primitive {
    private static final long serialVersionUID = 4171263406246161971L;

    public static final byte TYPE_ID = 12;

    private BACnetObjectType objectType;

    private int instanceNumber;

    public BACnetObjectIdentifier(final BACnetObjectType objectType,
            final int instanceNumber) {
        setValues(objectType, instanceNumber);
    }

    private void setValues(final BACnetObjectType objectType,
            final int instanceNumber) {
        if (instanceNumber < 0 || instanceNumber > 0x3FFFFF) {
            throw new IllegalArgumentException(
                    "Illegal instance number: " + instanceNumber);
        }

        this.objectType = objectType;
        this.instanceNumber = instanceNumber;
    }

    public BACnetObjectType getObjectType() {
        return objectType;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

    @Override
    public String toString() {
        return objectType.toString() + " " + instanceNumber;
    }

    //
    // Reading and writing
    //
    public BACnetObjectIdentifier(final ByteQueue queue) {
        readTag(queue);

        int objectType = queue.popU1B() << 2;
        final int i = queue.popU1B();
        objectType |= i >> 6;

        this.objectType = new BACnetObjectType(objectType);

        instanceNumber = (i & 0x3f) << 16;
        instanceNumber |= queue.popU1B() << 8;
        instanceNumber |= queue.popU1B();
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        final int objectType = this.objectType.intValue();
        queue.push(objectType >> 2);
        queue.push(((objectType & 3) << 6) | (instanceNumber >> 16));
        queue.push(instanceNumber >> 8);
        queue.push(instanceNumber);
    }

    @Override
    protected long getLength() {
        return 4;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + instanceNumber;
        result = PRIME * result
                + ((objectType == null) ? 0 : objectType.hashCode());
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
        final BACnetObjectIdentifier other = (BACnetObjectIdentifier) obj;
        if (instanceNumber != other.instanceNumber) {
            return false;
        }
        if (objectType == null) {
            if (other.objectType != null) {
                return false;
            }
        } else if (!objectType.equals(other.objectType)) {
            return false;
        }
        return true;
    }
}
