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

import java.util.Arrays;

import ch.fhnw.bacnetit.lib.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
