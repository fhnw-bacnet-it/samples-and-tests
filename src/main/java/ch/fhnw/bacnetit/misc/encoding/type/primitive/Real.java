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
package ch.fhnw.bacnetit.misc.encoding.type.primitive;

import ch.fhnw.bacnetit.misc.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class Real extends Primitive {
    private static final long serialVersionUID = -165304995181723832L;

    public static final byte TYPE_ID = 4;

    private final float value;

    public Real(final float value) {
        this.value = value;
    }

    public float floatValue() {
        return value;
    }

    //
    // Reading and writing
    //
    public Real(final ByteQueue queue) {
        readTag(queue);
        value = Float.intBitsToFloat(BACnetUtils.popInt(queue));
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        BACnetUtils.pushInt(queue, Float.floatToIntBits(value));
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
        result = PRIME * result + Float.floatToIntBits(value);
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
        final Real other = (Real) obj;
        if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }
}
