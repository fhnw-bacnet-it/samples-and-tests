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

import java.math.BigInteger;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class Unsigned32 extends UnsignedInteger {
    private static final long serialVersionUID = -933873380384300138L;

    private static final long MAX = 0xffffffffl - 1;

    public Unsigned32(final int value) {
        super(value);
    }

    public Unsigned32(final BigInteger value) {
        super(value);
        if (value.longValue() > MAX) {
            throw new IllegalArgumentException(
                    "Value cannot be greater than " + MAX);
        }
    }

    public Unsigned32(final ByteQueue queue) {
        super(queue);
    }
}
