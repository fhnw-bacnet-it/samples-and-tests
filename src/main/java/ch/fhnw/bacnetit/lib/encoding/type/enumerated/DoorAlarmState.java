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
