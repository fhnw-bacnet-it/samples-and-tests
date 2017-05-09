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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.eventParameter;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class ChangeOfBitString extends EventParameter {
    private static final long serialVersionUID = -3812561432266744183L;

    public static final byte TYPE_ID = 0;

    private final UnsignedInteger timeDelay;

    private final BitString bitMask;

    private final SequenceOf<BitString> listOfBitstringValues;

    public ChangeOfBitString(final UnsignedInteger timeDelay,
            final BitString bitMask,
            final SequenceOf<BitString> listOfBitstringValues) {
        this.timeDelay = timeDelay;
        this.bitMask = bitMask;
        this.listOfBitstringValues = listOfBitstringValues;
    }

    public ChangeOfBitString(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        bitMask = read(queue, BitString.class, 1);
        listOfBitstringValues = readSequenceOf(queue, BitString.class, 2);
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, bitMask, 1);
        write(queue, listOfBitstringValues, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public BitString getBitMask() {
        return bitMask;
    }

    public SequenceOf<BitString> getListOfBitstringValues() {
        return listOfBitstringValues;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((bitMask == null) ? 0 : bitMask.hashCode());
        result = PRIME * result + ((listOfBitstringValues == null) ? 0
                : listOfBitstringValues.hashCode());
        result = PRIME * result
                + ((timeDelay == null) ? 0 : timeDelay.hashCode());
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
        final ChangeOfBitString other = (ChangeOfBitString) obj;
        if (bitMask == null) {
            if (other.bitMask != null) {
                return false;
            }
        } else if (!bitMask.equals(other.bitMask)) {
            return false;
        }
        if (listOfBitstringValues == null) {
            if (other.listOfBitstringValues != null) {
                return false;
            }
        } else if (!listOfBitstringValues.equals(other.listOfBitstringValues)) {
            return false;
        }
        if (timeDelay == null) {
            if (other.timeDelay != null) {
                return false;
            }
        } else if (!timeDelay.equals(other.timeDelay)) {
            return false;
        }
        return true;
    }
}
