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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 667<br>
 * BACnetDailySchedule ::= SEQUENCE {<br>
 * day-schedule [0] SEQUENCE OF BACnetTimeValue }<br>
 */
public class DailySchedule extends BaseType {
    private static final long serialVersionUID = -8539541069909649459L;

    private final SequenceOf<TimeValue> daySchedule;

    public DailySchedule(final SequenceOf<TimeValue> daySchedule) {
        this.daySchedule = daySchedule;
    }

    public SequenceOf<TimeValue> getDaySchedule() {
        return daySchedule;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, daySchedule, 0);
    }

    public DailySchedule(final ByteQueue queue) throws BACnetException {
        daySchedule = readSequenceOf(queue, TimeValue.class, 0);
    }
}
