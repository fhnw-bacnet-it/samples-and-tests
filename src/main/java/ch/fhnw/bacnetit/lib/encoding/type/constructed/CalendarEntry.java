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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Date;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 667<br>
 * BACnetCalendarEntry ::= CHOICE {<br>
 * date [0] Date,<br>
 * dateRange [1] BACnetDateRange,<br>
 * weekNDay [2] BACnetWeekNDay <br>
 * }
 */
public class CalendarEntry extends BaseType {
    private static final long serialVersionUID = -4210434764578714766L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Date.class);
        classes.add(DateRange.class);
        classes.add(WeekNDay.class);
    }

    private final Choice entry;

    public CalendarEntry(final Date date) {
        entry = new Choice(0, date);
    }

    public CalendarEntry(final DateRange dateRange) {
        entry = new Choice(1, dateRange);
    }

    public CalendarEntry(final WeekNDay weekNDay) {
        entry = new Choice(2, weekNDay);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, entry);
    }

    public CalendarEntry(final ByteQueue queue) throws BACnetException {
        entry = new Choice(queue, classes);
    }

    public boolean isDate() {
        return entry.getContextId() == 0;
    }

    public boolean isDateRange() {
        return entry.getContextId() == 1;
    }

    public boolean isWeekNDay() {
        return entry.getContextId() == 2;
    }

    public Date getDate() {
        return (Date) entry.getDatum();
    }

    public DateRange getDateRange() {
        return (DateRange) entry.getDatum();
    }

    public WeekNDay getWeekNDay() {
        return (WeekNDay) entry.getDatum();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entry == null) ? 0 : entry.hashCode());
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
        final CalendarEntry other = (CalendarEntry) obj;
        if (entry == null) {
            if (other.entry != null) {
                return false;
            }
        } else if (!entry.equals(other.entry)) {
            return false;
        }
        return true;
    }
}
