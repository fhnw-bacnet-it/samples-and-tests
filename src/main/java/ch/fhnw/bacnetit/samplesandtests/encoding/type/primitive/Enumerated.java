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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive;

import java.math.BigInteger;

import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class Enumerated extends UnsignedInteger {
    private static final long serialVersionUID = 2462119559912570064L;

    public static final byte TYPE_ID = 9;

    public Enumerated(final int value) {
        super(value);
    }

    public Enumerated(final BigInteger value) {
        super(value);
    }

    public byte byteValue() {
        return (byte) intValue();
    }

    public boolean equals(final Enumerated that) {
        return intValue() == that.intValue();
    }

    //
    // Reading and writing
    //
    public Enumerated(final ByteQueue queue) {
        super(queue);
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }
}
