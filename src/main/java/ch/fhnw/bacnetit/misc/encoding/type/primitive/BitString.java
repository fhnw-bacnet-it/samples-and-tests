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

import java.util.Arrays;

import ch.fhnw.bacnetit.misc.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class BitString extends Primitive {
    private static final long serialVersionUID = 8795578212108935279L;

    public static final byte TYPE_ID = 8;

    private boolean[] value;

    public BitString(final boolean[] value) {
        this.value = value;
    }

    public BitString(final int size, final boolean defaultValue) {
        value = new boolean[size];
        if (defaultValue) {
            for (int i = 0; i < size; i++) {
                value[i] = true;
            }
        }
    }

    public boolean[] getValue() {
        return value;
    }

    public void setAll(final boolean value) {
        final boolean[] values = getValue();
        for (int i = 0; i < values.length; i++) {
            values[i] = value;
        }
    }

    //
    // Reading and writing
    //
    public BitString(final ByteQueue queue) {
        final int length = (int) readTag(queue) - 1;
        final int remainder = queue.popU1B();

        if (length == 0) {
            value = new boolean[0];
        } else {
            final byte[] data = new byte[length];
            queue.pop(data);
            value = BACnetUtils.convertToBooleans(data, length * 8 - remainder);
        }
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        if (value.length == 0) {
            queue.push((byte) 0);
        } else {
            int remainder = value.length % 8;
            if (remainder > 0) {
                remainder = 8 - remainder;
            }
            queue.push((byte) remainder);
            queue.push(BACnetUtils.convertToBytes(value));
        }
    }

    @Override
    protected long getLength() {
        if (value.length == 0) {
            return 1;
        }
        return (value.length - 1) / 8 + 2;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Arrays.hashCode(value);
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
        final BitString other = (BitString) obj;
        if (!Arrays.equals(value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
