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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 712 <br>
 * BACnetSpecialEvent ::= SEQUENCE {<br>
 * period CHOICE {<br>
 * calendarEntry [0] BACnetCalendarEntry<br>
 * calendarReference [1] BACnetObjectIdentifier <br>
 * },<br>
 * listOfTimeValues [2] SEQUENCE OF BACnetTimeValue,<br>
 * eventPriority [3] Unsigned (1..16)<br>
 * }
 */
public class SpecialEvent extends BaseType {
    private static final long serialVersionUID = -5828791384033258372L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(CalendarEntry.class);
        classes.add(BACnetObjectIdentifier.class);
    }

    private final Choice calendar;

    private final SequenceOf<TimeValue> listOfTimeValues;

    private final UnsignedInteger eventPriority;

    public SpecialEvent(final CalendarEntry calendarEntry,
            final SequenceOf<TimeValue> listOfTimeValues,
            final UnsignedInteger eventPriority) {
        calendar = new Choice(0, calendarEntry);
        this.listOfTimeValues = listOfTimeValues;
        this.eventPriority = eventPriority;
    }

    public SpecialEvent(final BACnetObjectIdentifier calendarReference,
            final SequenceOf<TimeValue> listOfTimeValues,
            final UnsignedInteger eventPriority) {
        calendar = new Choice(1, calendarReference);
        this.listOfTimeValues = listOfTimeValues;
        this.eventPriority = eventPriority;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, calendar);
        write(queue, listOfTimeValues, 2);
        write(queue, eventPriority, 3);
    }

    public boolean isCalendarReference() {
        return calendar.getContextId() == 1;
    }

    public CalendarEntry getCalendarEntry() {
        return (CalendarEntry) calendar.getDatum();
    }

    public BACnetObjectIdentifier getCalendarReference() {
        return (BACnetObjectIdentifier) calendar.getDatum();
    }

    public SequenceOf<TimeValue> getListOfTimeValues() {
        return listOfTimeValues;
    }

    public UnsignedInteger getEventPriority() {
        return eventPriority;
    }

    public SpecialEvent(final ByteQueue queue) throws BACnetException {
        calendar = new Choice(queue, classes);
        listOfTimeValues = readSequenceOf(queue, TimeValue.class, 2);
        eventPriority = read(queue, UnsignedInteger.class, 3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((calendar == null) ? 0 : calendar.hashCode());
        result = PRIME * result
                + ((eventPriority == null) ? 0 : eventPriority.hashCode());
        result = PRIME * result + ((listOfTimeValues == null) ? 0
                : listOfTimeValues.hashCode());
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
        final SpecialEvent other = (SpecialEvent) obj;
        if (calendar == null) {
            if (other.calendar != null) {
                return false;
            }
        } else if (!calendar.equals(other.calendar)) {
            return false;
        }
        if (eventPriority == null) {
            if (other.eventPriority != null) {
                return false;
            }
        } else if (!eventPriority.equals(other.eventPriority)) {
            return false;
        }
        if (listOfTimeValues == null) {
            if (other.listOfTimeValues != null) {
                return false;
            }
        } else if (!listOfTimeValues.equals(other.listOfTimeValues)) {
            return false;
        }
        return true;
    }
}
