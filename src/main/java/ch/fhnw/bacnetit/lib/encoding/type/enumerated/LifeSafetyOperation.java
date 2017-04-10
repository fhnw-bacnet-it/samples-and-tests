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

public class LifeSafetyOperation extends Enumerated {
    private static final long serialVersionUID = -8453182789389518551L;

    public static final LifeSafetyOperation none = new LifeSafetyOperation(0);

    public static final LifeSafetyOperation silence = new LifeSafetyOperation(
            1);

    public static final LifeSafetyOperation silenceAudible = new LifeSafetyOperation(
            2);

    public static final LifeSafetyOperation silenceVisual = new LifeSafetyOperation(
            3);

    public static final LifeSafetyOperation reset = new LifeSafetyOperation(4);

    public static final LifeSafetyOperation resetAlarm = new LifeSafetyOperation(
            5);

    public static final LifeSafetyOperation resetFault = new LifeSafetyOperation(
            6);

    public static final LifeSafetyOperation unsilence = new LifeSafetyOperation(
            7);

    public static final LifeSafetyOperation unsilenceAudible = new LifeSafetyOperation(
            8);

    public static final LifeSafetyOperation unsilenceVisual = new LifeSafetyOperation(
            9);

    public static final LifeSafetyOperation[] ALL = { none, silence,
            silenceAudible, silenceVisual, reset, resetAlarm, resetFault,
            unsilence, unsilenceAudible, unsilenceVisual, };

    public LifeSafetyOperation(final int value) {
        super(value);
    }

    public LifeSafetyOperation(final ByteQueue queue) {
        super(queue);
    }
}
