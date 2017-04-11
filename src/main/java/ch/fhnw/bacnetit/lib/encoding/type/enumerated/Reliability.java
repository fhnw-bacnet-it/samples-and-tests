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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Reliability extends Enumerated {
    private static final long serialVersionUID = 1105281466137206125L;

    public static final Reliability noFaultDetected = new Reliability(0);

    public static final Reliability noSensor = new Reliability(1);

    public static final Reliability overRange = new Reliability(2);

    public static final Reliability underRange = new Reliability(3);

    public static final Reliability openLoop = new Reliability(4);

    public static final Reliability shortedLoop = new Reliability(5);

    public static final Reliability noOutput = new Reliability(6);

    public static final Reliability unreliableOther = new Reliability(7);

    public static final Reliability processError = new Reliability(8);

    public static final Reliability multiStateFault = new Reliability(9);

    public static final Reliability configurationError = new Reliability(10);

    public static final Reliability communicationFailure = new Reliability(12);

    public static final Reliability[] ALL = { noFaultDetected, noSensor,
            overRange, underRange, openLoop, shortedLoop, noOutput,
            unreliableOther, processError, multiStateFault, configurationError,
            communicationFailure, };

    public Reliability(final int value) {
        super(value);
    }

    public Reliability(final ByteQueue queue) {
        super(queue);
    }
}
