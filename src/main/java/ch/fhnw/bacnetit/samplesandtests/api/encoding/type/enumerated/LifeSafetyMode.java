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

public class LifeSafetyMode extends Enumerated {
    private static final long serialVersionUID = -4939675355903263402L;

    public static final LifeSafetyMode off = new LifeSafetyMode(0);

    public static final LifeSafetyMode on = new LifeSafetyMode(1);

    public static final LifeSafetyMode test = new LifeSafetyMode(2);

    public static final LifeSafetyMode manned = new LifeSafetyMode(3);

    public static final LifeSafetyMode unmanned = new LifeSafetyMode(4);

    public static final LifeSafetyMode armed = new LifeSafetyMode(5);

    public static final LifeSafetyMode disarmed = new LifeSafetyMode(6);

    public static final LifeSafetyMode prearmed = new LifeSafetyMode(7);

    public static final LifeSafetyMode slow = new LifeSafetyMode(8);

    public static final LifeSafetyMode fast = new LifeSafetyMode(9);

    public static final LifeSafetyMode disconnected = new LifeSafetyMode(10);

    public static final LifeSafetyMode enabled = new LifeSafetyMode(11);

    public static final LifeSafetyMode disabled = new LifeSafetyMode(12);

    public static final LifeSafetyMode automaticReleaseDisabled = new LifeSafetyMode(
            13);

    public static final LifeSafetyMode defaultMode = new LifeSafetyMode(14);

    public static final LifeSafetyMode[] ALL = { off, on, test, manned,
            unmanned, armed, disarmed, prearmed, slow, fast, disconnected,
            enabled, disabled, automaticReleaseDisabled, defaultMode, };

    public LifeSafetyMode(final int value) {
        super(value);
    }

    public LifeSafetyMode(final ByteQueue queue) {
        super(queue);
    }
}
