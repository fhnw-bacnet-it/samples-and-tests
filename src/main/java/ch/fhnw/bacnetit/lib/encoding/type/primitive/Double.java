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
