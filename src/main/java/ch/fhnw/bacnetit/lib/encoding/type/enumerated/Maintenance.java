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

public class Maintenance extends Enumerated {
    private static final long serialVersionUID = -8105102280345072070L;

    public static final Maintenance none = new Maintenance(0);

    public static final Maintenance periodicTest = new Maintenance(1);

    public static final Maintenance needServiceOperational = new Maintenance(2);

    public static final Maintenance needServiceInoperative = new Maintenance(3);

    public static final Maintenance[] ALL = { none, periodicTest,
            needServiceOperational, needServiceInoperative, };

    public Maintenance(final int value) {
        super(value);
    }

    public Maintenance(final ByteQueue queue) {
        super(queue);
    }
}
