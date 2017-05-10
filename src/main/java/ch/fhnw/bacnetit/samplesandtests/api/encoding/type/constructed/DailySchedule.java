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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
