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
