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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class LimitEnable extends BitString {
    private static final long serialVersionUID = -8754983228968085042L;

    public LimitEnable(final boolean lowLimitEnable,
            final boolean highLimitEnable) {
        super(new boolean[] { lowLimitEnable, highLimitEnable });
    }

    public LimitEnable(final ByteQueue queue) {
        super(queue);
    }

    public boolean isLowLimitEnable() {
        return getValue()[0];
    }

    public boolean isHighLimitEnable() {
        return getValue()[1];
    }
}
