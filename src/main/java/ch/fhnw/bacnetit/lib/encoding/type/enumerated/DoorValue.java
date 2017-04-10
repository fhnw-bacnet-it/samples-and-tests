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

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
