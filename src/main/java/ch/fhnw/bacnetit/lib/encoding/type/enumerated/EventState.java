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

public class EventState extends Enumerated {
    private static final long serialVersionUID = -8567972022145562375L;

    public static final EventState normal = new EventState(0);

    public static final EventState fault = new EventState(1);

    public static final EventState offnormal = new EventState(2);

    public static final EventState highLimit = new EventState(3);

    public static final EventState lowLimit = new EventState(4);

    public static final EventState lifeSafetyAlarm = new EventState(5);

    public static final EventState[] ALL = { normal, fault, offnormal,
            highLimit, lowLimit, lifeSafetyAlarm, };

    public EventState(final int value) {
        super(value);
    }

    public EventState(final ByteQueue queue) {
        super(queue);
    }

    @Override
    public String toString() {
        if (intValue() == 0) {
            return "normal";
        }
        if (intValue() == 1) {
            return "fault";
        }
        if (intValue() == 2) {
            return "off normal";
        }
        if (intValue() == 3) {
            return "high limit";
        }
        if (intValue() == 4) {
            return "low limit";
        }
        if (intValue() == 5) {
            return "life safety alarm";
        }
        return "Unknown";
    }
}
