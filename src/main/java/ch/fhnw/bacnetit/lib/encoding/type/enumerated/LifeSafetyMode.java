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
