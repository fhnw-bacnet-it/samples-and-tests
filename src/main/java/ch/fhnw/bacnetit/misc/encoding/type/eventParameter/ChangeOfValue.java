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
package ch.fhnw.bacnetit.misc.encoding.type.eventParameter;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.Encodable;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class ChangeOfValue extends EventParameter {
    private static final long serialVersionUID = 2660470709377346618L;

    public static final byte TYPE_ID = 2;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(BitString.class);
        classes.add(Real.class);
    }

    private final UnsignedInteger timeDelay;

    private final Choice newValue;

    public ChangeOfValue(final UnsignedInteger timeDelay,
            final BitString bitmask) {
        this.timeDelay = timeDelay;
        this.newValue = new Choice(0, bitmask);
    }

    public ChangeOfValue(final UnsignedInteger timeDelay,
            final Real referencedPropertyIncrement) {
        this.timeDelay = timeDelay;
        this.newValue = new Choice(1, referencedPropertyIncrement);
    }

    public ChangeOfValue(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        newValue = new Choice(queue, classes, 1);
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, newValue, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public Choice getNewValue() {
        return newValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((newValue == null) ? 0 : newValue.hashCode());
        result = prime * result
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
        final ChangeOfValue other = (ChangeOfValue) obj;
        if (newValue == null) {
            if (other.newValue != null) {
                return false;
            }
        } else if (!newValue.equals(other.newValue)) {
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
