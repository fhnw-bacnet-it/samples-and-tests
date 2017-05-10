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
public class DoorValue extends Enumerated {
    private static final long serialVersionUID = -2200245400075159155L;

    public static final DoorValue lock = new DoorValue(0);

    public static final DoorValue unlock = new DoorValue(1);

    public static final DoorValue pulseUnlock = new DoorValue(2);

    public static final DoorValue extendedPulseUnlock = new DoorValue(3);

    public static final DoorValue[] ALL = { lock, unlock, pulseUnlock,
            extendedPulseUnlock, };

    public DoorValue(final int value) {
        super(value);
    }

    public DoorValue(final ByteQueue queue) {
        super(queue);
    }
}
