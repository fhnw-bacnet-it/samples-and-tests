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

public class ProgramError extends Enumerated {
    private static final long serialVersionUID = 4478176770591341682L;

    public static final ProgramError normal = new ProgramError(0);

    public static final ProgramError loadFailed = new ProgramError(1);

    public static final ProgramError internal = new ProgramError(2);

    public static final ProgramError program = new ProgramError(3);

    public static final ProgramError other = new ProgramError(4);

    public static final ProgramError[] ALL = { normal, loadFailed, internal,
            program, other, };

    public ProgramError(final int value) {
        super(value);
    }

    public ProgramError(final ByteQueue queue) {
        super(queue);
    }
}
