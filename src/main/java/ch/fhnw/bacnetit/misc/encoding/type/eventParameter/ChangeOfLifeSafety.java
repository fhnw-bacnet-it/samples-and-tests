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

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.misc.encoding.type.enumerated.LifeSafetyState;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class ChangeOfLifeSafety extends EventParameter {
    private static final long serialVersionUID = 2568744221261483561L;

    public static final byte TYPE_ID = 8;

    private final UnsignedInteger timeDelay;

    private final SequenceOf<LifeSafetyState> listOfLifeSafetyAlarmValues;

    private final SequenceOf<LifeSafetyState> listOfAlarmValues;

    private final DeviceObjectPropertyReference modePropertyReference;

    public ChangeOfLifeSafety(final UnsignedInteger timeDelay,
            final SequenceOf<LifeSafetyState> listOfLifeSafetyAlarmValues,
            final SequenceOf<LifeSafetyState> listOfAlarmValues,
            final DeviceObjectPropertyReference modePropertyReference) {
        this.timeDelay = timeDelay;
        this.listOfLifeSafetyAlarmValues = listOfLifeSafetyAlarmValues;
        this.listOfAlarmValues = listOfAlarmValues;
        this.modePropertyReference = modePropertyReference;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, listOfLifeSafetyAlarmValues, 1);
        write(queue, listOfAlarmValues, 2);
        write(queue, modePropertyReference, 3);
    }

    public ChangeOfLifeSafety(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        listOfLifeSafetyAlarmValues = readSequenceOf(queue,
                LifeSafetyState.class, 1);
        listOfAlarmValues = readSequenceOf(queue, LifeSafetyState.class, 2);
        modePropertyReference = read(queue, DeviceObjectPropertyReference.class,
                3);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public SequenceOf<LifeSafetyState> getListOfLifeSafetyAlarmValues() {
        return listOfLifeSafetyAlarmValues;
    }

    public SequenceOf<LifeSafetyState> getListOfAlarmValues() {
        return listOfAlarmValues;
    }

    public DeviceObjectPropertyReference getModePropertyReference() {
        return modePropertyReference;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfAlarmValues == null) ? 0
                : listOfAlarmValues.hashCode());
        result = PRIME * result + ((listOfLifeSafetyAlarmValues == null) ? 0
                : listOfLifeSafetyAlarmValues.hashCode());
        result = PRIME * result + ((modePropertyReference == null) ? 0
                : modePropertyReference.hashCode());
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
        final ChangeOfLifeSafety other = (ChangeOfLifeSafety) obj;
        if (listOfAlarmValues == null) {
            if (other.listOfAlarmValues != null) {
                return false;
            }
        } else if (!listOfAlarmValues.equals(other.listOfAlarmValues)) {
            return false;
        }
        if (listOfLifeSafetyAlarmValues == null) {
            if (other.listOfLifeSafetyAlarmValues != null) {
                return false;
            }
        } else if (!listOfLifeSafetyAlarmValues
                .equals(other.listOfLifeSafetyAlarmValues)) {
            return false;
        }
        if (modePropertyReference == null) {
            if (other.modePropertyReference != null) {
                return false;
            }
        } else if (!modePropertyReference.equals(other.modePropertyReference)) {
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
