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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class DoorAlarmState extends Enumerated {
    private static final long serialVersionUID = -4016268027739544828L;

    public static final DoorAlarmState normal = new DoorAlarmState(0);

    public static final DoorAlarmState alarm = new DoorAlarmState(1);

    public static final DoorAlarmState doorOpenTooLong = new DoorAlarmState(2);

    public static final DoorAlarmState forcedOpen = new DoorAlarmState(3);

    public static final DoorAlarmState tamper = new DoorAlarmState(4);

    public static final DoorAlarmState doorFault = new DoorAlarmState(5);

    public static final DoorAlarmState lockDown = new DoorAlarmState(6);

    public static final DoorAlarmState freeAccess = new DoorAlarmState(7);

    public static final DoorAlarmState egressOpen = new DoorAlarmState(8);

    public static final DoorAlarmState[] ALL = { normal, alarm, doorOpenTooLong,
            forcedOpen, tamper, doorFault, lockDown, freeAccess, egressOpen, };

    public DoorAlarmState(final int value) {
        super(value);
    }

    public DoorAlarmState(final ByteQueue queue) {
        super(queue);
    }
}
