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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive;

import java.math.BigInteger;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class SignedInteger extends Primitive {
    private static final long serialVersionUID = 3344404709705407437L;

    public static final byte TYPE_ID = 3;

    private int smallValue;

    private BigInteger bigValue;

    public SignedInteger(final int value) {
        smallValue = value;
    }

    public SignedInteger(final long value) {
        bigValue = BigInteger.valueOf(value);
    }

    public SignedInteger(final BigInteger value) {
        bigValue = value;
    }

    public int intValue() {
        if (bigValue == null) {
            return smallValue;
        }
        return bigValue.intValue();
    }

    public long longValue() {
        if (bigValue == null) {
            return smallValue;
        }
        return bigValue.longValue();
    }

    public BigInteger bigIntegerValue() {
        if (bigValue == null) {
            return BigInteger.valueOf(smallValue);
        }
        return bigValue;
    }

    //
    // Reading and writing
    //
    public SignedInteger(final ByteQueue queue) {
        // Read the data length value.
        final int length = (int) readTag(queue);

        final byte[] bytes = new byte[length];
        queue.pop(bytes);
        final BigInteger bi = new BigInteger(bytes);

        if (length < 5) {
            smallValue = bi.intValue();
        } else {
            bigValue = bi;
        }
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        if (bigValue == null) {
            long length = getLength();
            while (length > 0) {
                queue.push(smallValue >> (--length * 8));
            }
        } else {
            queue.push(bigValue.toByteArray());
        }
    }

    @Override
    protected long getLength() {
        if (bigValue == null) {
            int length;
            if (smallValue < Byte.MAX_VALUE && smallValue > Byte.MIN_VALUE) {
                length = 1;
            }
            // TODO, get answer from BACnet4j author
            else if (smallValue < Short.MAX_VALUE
                    && smallValue > Short.MAX_VALUE) {
                length = 2;
            } else if (smallValue < 8388607 && smallValue > -8388608) {
                length = 3;
            } else {
                length = 4;
            }
            return length;
        }
        return bigValue.toByteArray().length;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((bigValue == null) ? 0 : bigValue.hashCode());
        result = PRIME * result + smallValue;
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
        final SignedInteger other = (SignedInteger) obj;
        return bigIntegerValue().equals(other.bigIntegerValue());
    }

    @Override
    public String toString() {
        if (bigValue == null) {
            return Integer.toString(smallValue);
        }
        return bigValue.toString();
    }
}
