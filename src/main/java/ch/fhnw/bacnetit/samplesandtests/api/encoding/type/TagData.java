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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type;

public class TagData {
    public int tagNumber;

    public boolean contextSpecific;

    public long length;

    public int tagLength;

    public int getTotalLength() {
        return (int) (length + tagLength);
    }

    public boolean isStartTag() {
        return contextSpecific && ((length & 6) == 6);
    }

    public boolean isStartTag(final int contextId) {
        return isStartTag() && tagNumber == contextId;
    }

    public boolean isEndTag() {
        return contextSpecific && ((length & 7) == 7);
    }

    public boolean isEndTag(final int contextId) {
        return isEndTag() && tagNumber == contextId;
    }
}
