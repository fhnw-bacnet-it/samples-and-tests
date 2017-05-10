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

public class Null extends Primitive {
    private static final long serialVersionUID = 4511984655190634429L;

    public static final byte TYPE_ID = 0;

    public Null() {
        // no op
    }

    public Null(final ByteQueue queue) {
        readTag(queue);
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        // no op
    }

    @Override
    protected long getLength() {
        return 0;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
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
        return true;
    }

    @Override
    public String toString() {
        return "Null";
    }
}
