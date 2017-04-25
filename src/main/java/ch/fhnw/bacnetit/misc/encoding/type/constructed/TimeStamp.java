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
package ch.fhnw.bacnetit.misc.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.Encodable;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.Time;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class TimeStamp extends BaseType {
    private static final long serialVersionUID = 728644269380254714L;

    private final Choice choice;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Time.class);
        classes.add(UnsignedInteger.class);
        classes.add(DateTime.class);
    }

    public TimeStamp(final Time time) {
        choice = new Choice(0, time);
    }

    public TimeStamp(final UnsignedInteger sequenceNumber) {
        choice = new Choice(1, sequenceNumber);
    }

    public TimeStamp(final DateTime dateTime) {
        choice = new Choice(2, dateTime);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, choice);
    }

    public TimeStamp(final ByteQueue queue) throws BACnetException {
        choice = new Choice(queue, classes);
    }

    public boolean isTime() {
        return choice.getContextId() == 0;
    }

    public Time getTime() {
        return (Time) choice.getDatum();
    }

    public boolean isSequenceNumber() {
        return choice.getContextId() == 1;
    }

    public UnsignedInteger getSequenceNumber() {
        return (UnsignedInteger) choice.getDatum();
    }

    public boolean isDateTime() {
        return choice.getContextId() == 2;
    }

    public DateTime getDateTime() {
        return (DateTime) choice.getDatum();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((choice == null) ? 0 : choice.hashCode());
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
        final TimeStamp other = (TimeStamp) obj;
        if (choice == null) {
            if (other.choice != null) {
                return false;
            }
        } else if (!choice.equals(other.choice)) {
            return false;
        }
        return true;
    }

}
