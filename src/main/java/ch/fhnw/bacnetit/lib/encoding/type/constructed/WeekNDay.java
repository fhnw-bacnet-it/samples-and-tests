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

import ch.fhnw.bacnetit.lib.encoding.enums.DayOfWeek;
import ch.fhnw.bacnetit.lib.encoding.enums.Month;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
