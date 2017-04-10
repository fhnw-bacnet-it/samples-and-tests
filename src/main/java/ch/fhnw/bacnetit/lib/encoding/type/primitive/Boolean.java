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

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Boolean extends Primitive {
    private static final long serialVersionUID = -161562645674050036L;

    public static final byte TYPE_ID = 1;

    protected boolean value;

    public Boolean(final boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return value;
    }

    public Boolean(final ByteQueue queue) {
        final long length = readTag(queue);
        if (contextSpecific) {
            value = queue.pop() == 1;
        } else {
            value = length == 1;
        }
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        if (contextSpecific) {
            queue.push((byte) (value ? 1 : 0));
        }
    }

    @Override
    protected long getLength() {
        if (contextSpecific) {
            return 1;
        }
        return (byte) (value ? 1 : 0);
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (value ? 1231 : 1237);
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
        final Boolean other = (Boolean) obj;
        if (value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return java.lang.Boolean.toString(value);
    }
}
