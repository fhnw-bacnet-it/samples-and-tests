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

public class ProgramRequest extends Enumerated {
    private static final long serialVersionUID = 8388693192499087156L;

    public static final ProgramRequest ready = new ProgramRequest(0);

    public static final ProgramRequest load = new ProgramRequest(1);

    public static final ProgramRequest run = new ProgramRequest(2);

    public static final ProgramRequest halt = new ProgramRequest(3);

    public static final ProgramRequest restart = new ProgramRequest(4);

    public static final ProgramRequest unload = new ProgramRequest(5);

    public static final ProgramRequest[] ALL = { ready, load, run, halt,
            restart, unload, };

    public ProgramRequest(final int value) {
        super(value);
    }

    public ProgramRequest(final ByteQueue queue) {
        super(queue);
    }
}
