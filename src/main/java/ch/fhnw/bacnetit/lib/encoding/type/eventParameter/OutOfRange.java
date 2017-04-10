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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class OutOfRange extends EventParameter {
    private static final long serialVersionUID = 6279370879628323322L;

    public static final byte TYPE_ID = 5;

    private final UnsignedInteger timeDelay;

    private final Real lowLimit;

    private final Real highLimit;

    private final Real deadband;

    public OutOfRange(final UnsignedInteger timeDelay, final Real lowLimit,
            final Real highLimit, final Real deadband) {
        this.timeDelay = timeDelay;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.deadband = deadband;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, lowLimit, 1);
        write(queue, highLimit, 2);
        write(queue, deadband, 3);
    }

    public OutOfRange(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        lowLimit = read(queue, Real.class, 1);
        highLimit = read(queue, Real.class, 2);
        deadband = read(queue, Real.class, 3);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public Real getLowLimit() {
        return lowLimit;
    }

    public Real getHighLimit() {
        return highLimit;
    }

    public Real getDeadband() {
        return deadband;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((deadband == null) ? 0 : deadband.hashCode());
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
        final OutOfRange other = (OutOfRange) obj;
        if (deadband == null) {
            if (other.deadband != null) {
                return false;
            }
        } else if (!deadband.equals(other.deadband)) {
            return false;
        }
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
