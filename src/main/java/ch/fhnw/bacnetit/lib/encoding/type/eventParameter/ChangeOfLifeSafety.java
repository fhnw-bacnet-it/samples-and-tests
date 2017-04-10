/*******************************************************************************
 * Copyright (C) 2016 The Java BACnetITB Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.fhnw.bacnetit.lib.encoding.type.eventParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyState;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
