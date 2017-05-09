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

import ch.fhnw.bacnetit.samplesandtests.encoding.enums.DayOfWeek;
import ch.fhnw.bacnetit.samplesandtests.encoding.enums.Month;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 713<br>
 * BACnetWeekNDay ::= OCTET STRING (SIZE (3))<br>
 * -- first octet month(1..14) 1 = January<br>
 * -- 13 = odd months<br>
 * -- 14 = even months<br>
 * -- 255 = X'FF' = any month<br>
 * -- second octet weekOfMonth where: 1 = days numbered 1-7<br>
 * -- 2 = days numbered 8-14<br>
 * -- 3 = days numbered 15-21<br>
 * -- 4 = days numbered 22-28<br>
 * -- 5 = days numbered 29-31<br>
 * -- 6 = last 7 days of this month<br>
 * -- 255 =X'FF' = any week of this month<br>
 * -- third octet dayOfWeek where: 1 = Monday<br>
 * -- 7 = Sunday<br>
 * -- 255 = X'FF' = any day of week<br>
 */
public class WeekNDay extends OctetString {
    private static final long serialVersionUID = -2836161294089567458L;

    public static class WeekOfMonth extends Enumerated {
        private static final long serialVersionUID = 1951617360223950570L;

        public static final WeekOfMonth days1to7 = new WeekOfMonth(1);

        public static final WeekOfMonth days8to14 = new WeekOfMonth(2);

        public static final WeekOfMonth days15to21 = new WeekOfMonth(3);

        public static final WeekOfMonth days22to28 = new WeekOfMonth(4);

        public static final WeekOfMonth days29to31 = new WeekOfMonth(5);

        public static final WeekOfMonth last7Days = new WeekOfMonth(6);

        public static final WeekOfMonth any = new WeekOfMonth(255);

        public static WeekOfMonth valueOf(final byte b) {
            switch (b) {
            case 1:
                return days1to7;
            case 2:
                return days8to14;
            case 3:
                return days15to21;
            case 4:
                return days22to28;
            case 5:
                return days29to31;
            case 6:
                return last7Days;
            default:
                return any;
            }
        }

        private WeekOfMonth(final int value) {
            super(value);
        }

        public WeekOfMonth(final ByteQueue queue) {
            super(queue);
        }
    }

    public WeekNDay(final Month month, final WeekOfMonth weekOfMonth,
            final DayOfWeek dayOfWeek) {
        super(new byte[] { month.getId(), weekOfMonth.byteValue(),
                dayOfWeek.getId() });
    }

    public Month getMonth() {
        return Month.valueOf(getBytes()[0]);
    }

    public WeekOfMonth getWeekOfMonth() {
        return WeekOfMonth.valueOf(getBytes()[1]);
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.valueOf(getBytes()[2]);
    }

    public WeekNDay(final ByteQueue queue) {
        super(queue);
    }
}
