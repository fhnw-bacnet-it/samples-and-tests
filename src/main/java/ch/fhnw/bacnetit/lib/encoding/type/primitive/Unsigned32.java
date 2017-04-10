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
package ch.fhnw.bacnetit.lib.encoding.type.primitive;

import java.math.BigInteger;

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Unsigned32 extends UnsignedInteger {
    private static final long serialVersionUID = -933873380384300138L;

    private static final long MAX = 0xffffffffl - 1;

    public Unsigned32(final int value) {
        super(value);
    }

    public Unsigned32(final BigInteger value) {
        super(value);
        if (value.longValue() > MAX) {
            throw new IllegalArgumentException(
                    "Value cannot be greater than " + MAX);
        }
    }

    public Unsigned32(final ByteQueue queue) {
        super(queue);
    }
}
