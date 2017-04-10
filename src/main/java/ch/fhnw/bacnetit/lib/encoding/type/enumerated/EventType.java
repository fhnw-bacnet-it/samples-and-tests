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
