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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Time;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
