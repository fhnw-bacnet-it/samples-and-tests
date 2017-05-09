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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.enumerated;

import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class RestartReason extends Enumerated {
    private static final long serialVersionUID = -4199348259202899844L;

    public static final RestartReason unknown = new RestartReason(0);

    public static final RestartReason coldstart = new RestartReason(1);

    public static final RestartReason warmstart = new RestartReason(2);

    public static final RestartReason detectedPowerLost = new RestartReason(3);

    public static final RestartReason detectedPoweredOff = new RestartReason(4);

    public static final RestartReason hardwareWatchdog = new RestartReason(5);

    public static final RestartReason softwareWatchdog = new RestartReason(6);

    public static final RestartReason suspended = new RestartReason(7);

    public static final RestartReason[] ALL = { unknown, coldstart, warmstart,
            detectedPowerLost, detectedPoweredOff, hardwareWatchdog,
            softwareWatchdog, suspended, };

    public RestartReason(final int value) {
        super(value);
    }

    public RestartReason(final ByteQueue queue) {
        super(queue);
    }
}
