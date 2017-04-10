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
