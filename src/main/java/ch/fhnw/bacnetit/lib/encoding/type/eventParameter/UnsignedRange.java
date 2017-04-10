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
package ch.fhnw.bacnetit.lib.encoding.type.eventParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class UnsignedRange extends EventParameter {
    private static final long serialVersionUID = 8057621493864254453L;

    public static final byte TYPE_ID = 11;

    private final UnsignedInteger timeDelay;

    private final UnsignedInteger lowLimit;

    private final UnsignedInteger highLimit;

    public UnsignedRange(final UnsignedInteger timeDelay,
            final UnsignedInteger lowLimit, final UnsignedInteger highLimit) {
        this.timeDelay = timeDelay;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, lowLimit, 1);
        write(queue, highLimit, 2);
    }

    public UnsignedRange(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        lowLimit = read(queue, UnsignedInteger.class, 1);
        highLimit = read(queue, UnsignedInteger.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public UnsignedInteger getLowLimit() {
        return lowLimit;
    }

    public UnsignedInteger getHighLimit() {
        return highLimit;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((highLimit == null) ? 0 : highLimit.hashCode());
        result = PRIME * result
                + ((lowLimit == null) ? 0 : lowLimit.hashCode());
        result = PRIME * result
                + ((timeDelay == null) ? 0 : timeDelay.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnsignedRange other = (UnsignedRange) obj;
        if (highLimit == null) {
            if (other.highLimit != null) {
                return false;
            }
        } else if (!highLimit.equals(other.highLimit)) {
            return false;
        }
        if (lowLimit == null) {
            if (other.lowLimit != null) {
                return false;
            }
        } else if (!lowLimit.equals(other.lowLimit)) {
            return false;
        }
        if (timeDelay == null) {
            if (other.timeDelay != null) {
                return false;
            }
        } else if (!timeDelay.equals(other.timeDelay)) {
            return false;
        }
        return true;
    }
}
