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
package ch.fhnw.bacnetit.lib.encoding.type.primitive;

import ch.fhnw.bacnetit.lib.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Double extends Primitive {
    private static final long serialVersionUID = -8758433354411016404L;

    public static final byte TYPE_ID = 5;

    private final double value;

    public Double(final double value) {
        this.value = value;
    }

    public double doubleValue() {
        return value;
    }

    //
    // Reading and writing
    //
    public Double(final ByteQueue queue) {
        readTag(queue);
        value = java.lang.Double.longBitsToDouble(BACnetUtils.popLong(queue));
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        BACnetUtils.pushLong(queue, java.lang.Double.doubleToLongBits(value));
    }

    @Override
    protected long getLength() {
        return 8;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        long temp;
        temp = java.lang.Double.doubleToLongBits(value);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
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
        final Double other = (Double) obj;
        if (java.lang.Double.doubleToLongBits(value) != java.lang.Double
                .doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return java.lang.Double.toString(value);
    }
}
