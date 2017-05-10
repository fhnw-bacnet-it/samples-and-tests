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

import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
