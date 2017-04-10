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
package ch.fhnw.bacnetit.lib.encoding.type.primitive;

import java.math.BigInteger;

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class UnsignedInteger extends Primitive {
    private static final long serialVersionUID = -3350034351888356100L;

    public static final byte TYPE_ID = 2;

    private int smallValue;

    private BigInteger bigValue;

    public UnsignedInteger(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "Value cannot be less than zero");
        }
        smallValue = value;
    }

    public UnsignedInteger(final long value) {
        bigValue = BigInteger.valueOf(value);
    }

    public UnsignedInteger(final BigInteger value) {
        if (value.signum() == -1) {
            throw new IllegalArgumentException(
                    "Value cannot be less than zero");
        }
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
    public UnsignedInteger(final ByteQueue queue) {
        int length = (int) readTag(queue);
        if (length < 4) {
            while (length > 0) {
                smallValue |= (queue.pop() & 0xff) << (--length * 8);
            }
        } else {
            final byte[] bytes = new byte[length + 1];
            queue.pop(bytes, 1, length);
            bigValue = new BigInteger(bytes);
        }
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        int length = (int) getLength();
        if (bigValue == null) {
            while (length > 0) {
                queue.push(smallValue >> (--length * 8));
            }
        } else {
            final byte[] bytes = new byte[length];

            for (int i = 0; i < bigValue.bitLength(); i++) {
                if (bigValue.testBit(i)) {
                    bytes[length - i / 8 - 1] |= 1 << (i % 8);
                }
            }

            queue.push(bytes);
        }
    }

    @Override
    public long getLength() {
        if (bigValue == null) {
            int length;
            if (smallValue < 0x100) {
                length = 1;
            } else if (smallValue < 0x10000) {
                length = 2;
            } else if (smallValue < 0x1000000) {
                length = 3;
            } else {
                length = 4;
            }

            return length;
        }

        if (bigValue.intValue() == 0) {
            return 1;
        }
        return (bigValue.bitLength() + 7) / 8;
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
        final UnsignedInteger other = (UnsignedInteger) obj;
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
