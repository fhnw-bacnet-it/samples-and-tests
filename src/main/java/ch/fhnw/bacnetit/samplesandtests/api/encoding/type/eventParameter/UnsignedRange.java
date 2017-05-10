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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.eventParameter;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class UnsignedRange extends EventParameter {
    private static final long serialVersionUID = 8057621493864254453L;

    public static final byte TYPE_ID = 11;

    private final UnsignedInteger timeDelay;

    private final UnsignedInteger lowLimit;

    private final UnsignedInteger highLimit;

    public UnsignedRange(final UnsignedInteger timeDelay,
            final UnsignedInteger lowLimit, final UnsignedInteger highLimit) {
        this.timeDelay = timeDelay;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, lowLimit, 1);
        write(queue, highLimit, 2);
    }

    public UnsignedRange(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        lowLimit = read(queue, UnsignedInteger.class, 1);
        highLimit = read(queue, UnsignedInteger.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public UnsignedInteger getLowLimit() {
        return lowLimit;
    }

    public UnsignedInteger getHighLimit() {
        return highLimit;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((highLimit == null) ? 0 : highLimit.hashCode());
        result = PRIME * result
                + ((lowLimit == null) ? 0 : lowLimit.hashCode());
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
        final UnsignedRange other = (UnsignedRange) obj;
        if (highLimit == null) {
            if (other.highLimit != null) {
                return false;
            }
        } else if (!highLimit.equals(other.highLimit)) {
            return false;
        }
        if (lowLimit == null) {
            if (other.lowLimit != null) {
                return false;
            }
        } else if (!lowLimit.equals(other.lowLimit)) {
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
