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
package ch.fhnw.bacnetit.lib.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class UnsignedRange extends NotificationParameters {
    private static final long serialVersionUID = 3208335506254265781L;

    public static final byte TYPE_ID = 11;

    private final UnsignedInteger exceedingValue;

    private final StatusFlags statusFlags;

    private final UnsignedInteger exceedingLimit;

    public UnsignedRange(final UnsignedInteger exceedingValue,
            final StatusFlags statusFlags,
            final UnsignedInteger exceedingLimit) {
        this.exceedingValue = exceedingValue;
        this.statusFlags = statusFlags;
        this.exceedingLimit = exceedingLimit;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, exceedingValue, 0);
        write(queue, statusFlags, 1);
        write(queue, exceedingLimit, 2);
    }

    public UnsignedRange(final ByteQueue queue) throws BACnetException {
        exceedingValue = read(queue, UnsignedInteger.class, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
        exceedingLimit = read(queue, UnsignedInteger.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getExceedingValue() {
        return exceedingValue;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public UnsignedInteger getExceedingLimit() {
        return exceedingLimit;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((exceedingLimit == null) ? 0 : exceedingLimit.hashCode());
        result = PRIME * result
                + ((exceedingValue == null) ? 0 : exceedingValue.hashCode());
        result = PRIME * result
                + ((statusFlags == null) ? 0 : statusFlags.hashCode());
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
        if (exceedingLimit == null) {
            if (other.exceedingLimit != null) {
                return false;
            }
        } else if (!exceedingLimit.equals(other.exceedingLimit)) {
            return false;
        }
        if (exceedingValue == null) {
            if (other.exceedingValue != null) {
                return false;
            }
        } else if (!exceedingValue.equals(other.exceedingValue)) {
            return false;
        }
        if (statusFlags == null) {
            if (other.statusFlags != null) {
                return false;
            }
        } else if (!statusFlags.equals(other.statusFlags)) {
            return false;
        }
        return true;
    }
}
