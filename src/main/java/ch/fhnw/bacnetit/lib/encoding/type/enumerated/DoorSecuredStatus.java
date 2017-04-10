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
public class DoorSecuredStatus extends Enumerated {
    private static final long serialVersionUID = 7337105893343734773L;

    public static final DoorSecuredStatus secured = new DoorSecuredStatus(0);

    public static final DoorSecuredStatus unsecured = new DoorSecuredStatus(1);

    public static final DoorSecuredStatus unknown = new DoorSecuredStatus(2);

    public static final DoorSecuredStatus[] ALL = { secured, unsecured,
            unknown, };

    public DoorSecuredStatus(final int value) {
        super(value);
    }

    public DoorSecuredStatus(final ByteQueue queue) {
        super(queue);
    }
}
