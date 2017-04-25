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

import ch.fhnw.bacnetit.misc.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class PropertyReference extends BaseType {
    private static final long serialVersionUID = 1375764258364665118L;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private UnsignedInteger propertyArrayIndex;

    public PropertyReference(final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex) {
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
    }

    public PropertyReference(
            final BACnetPropertyIdentifier propertyIdentifier) {
        this.propertyIdentifier = propertyIdentifier;
    }

    public UnsignedInteger getPropertyArrayIndex() {
        return propertyArrayIndex;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, propertyIdentifier, 0);
        writeOptional(queue, propertyArrayIndex, 1);
    }

    public PropertyReference(final ByteQueue queue) throws BACnetException {
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 0);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
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
        final PropertyReference other = (PropertyReference) obj;
        if (propertyArrayIndex == null) {
            if (other.propertyArrayIndex != null) {
                return false;
            }
        } else if (!propertyArrayIndex.equals(other.propertyArrayIndex)) {
            return false;
        }
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null) {
                return false;
            }
        } else if (!propertyIdentifier.equals(other.propertyIdentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PropertyReference [propertyIdentifier=" + propertyIdentifier
                + ", propertyArrayIndex=" + propertyArrayIndex + "]";
    }
}
