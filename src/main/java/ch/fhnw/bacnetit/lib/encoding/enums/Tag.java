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
package ch.fhnw.bacnetit.lib.encoding.enums;

public enum Tag {
    OPENING_TAG((byte) 0xe), CLOSING_TAG((byte) 0xf), UNSPECIFIED((byte) 0x0);

    private byte id;

    Tag(final byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public static Tag valueOf(final byte id) {
        if (id == OPENING_TAG.id) {
            return OPENING_TAG;
        }
        if (id == CLOSING_TAG.id) {
            return CLOSING_TAG;
        }

        return UNSPECIFIED;
    }

    public boolean isContextTag() {
        return false;
    }
}
