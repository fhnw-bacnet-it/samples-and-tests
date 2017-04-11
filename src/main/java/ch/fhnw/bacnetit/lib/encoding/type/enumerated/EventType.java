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

import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.BufferReady;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.ChangeOfBitString;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.ChangeOfLifeSafety;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.ChangeOfState;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.ChangeOfValue;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.CommandFailure;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.Extended;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.FloatingLimit;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.OutOfRange;
import ch.fhnw.bacnetit.lib.encoding.type.eventParameter.UnsignedRange;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class EventType extends Enumerated {
    private static final long serialVersionUID = -3342337624733065326L;

    public static final EventType changeOfBitstring = new EventType(
            ChangeOfBitString.TYPE_ID);

    public static final EventType changeOfState = new EventType(
            ChangeOfState.TYPE_ID);

    public static final EventType changeOfValue = new EventType(
            ChangeOfValue.TYPE_ID);

    public static final EventType commandFailure = new EventType(
            CommandFailure.TYPE_ID);

    public static final EventType floatingLimit = new EventType(
            FloatingLimit.TYPE_ID);

    public static final EventType outOfRange = new EventType(
            OutOfRange.TYPE_ID);

    public static final EventType changeOfLifeSafety = new EventType(
            ChangeOfLifeSafety.TYPE_ID);

    public static final EventType extended = new EventType(Extended.TYPE_ID);

    public static final EventType bufferReady = new EventType(
            BufferReady.TYPE_ID);

    public static final EventType unsignedRange = new EventType(
            UnsignedRange.TYPE_ID);

    public static final EventType[] ALL = { changeOfBitstring, changeOfState,
            changeOfValue, commandFailure, floatingLimit, outOfRange,
            changeOfLifeSafety, extended, bufferReady, unsignedRange, };

    public EventType(final int value) {
        super(value);
    }

    public EventType(final ByteQueue queue) {
        super(queue);
    }
}
