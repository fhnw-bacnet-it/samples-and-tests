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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ResultFlags extends BitString {
    private static final long serialVersionUID = 7657134249555371620L;

    public ResultFlags(final boolean firstItem, final boolean lastItem,
            final boolean moreItems) {
        super(new boolean[] { firstItem, lastItem, moreItems });
    }

    public ResultFlags(final ByteQueue queue) {
        super(queue);
    }

    public boolean isFirstItem() {
        return getValue()[0];
    }

    public boolean isLastItem() {
        return getValue()[1];
    }

    public boolean isMoreItems() {
        return getValue()[2];
    }
}
