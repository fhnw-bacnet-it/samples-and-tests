/*******************************************************************************
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2017 University of Applied Sciences and Arts,
 * Northwestern Switzerland FHNW,
 * Institute of Mobile and Distributed Systems.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.orglicenses.
 *******************************************************************************/
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class FloatingLimit extends NotificationParameters {
    private static final long serialVersionUID = 7176330185332756654L;

    public static final byte TYPE_ID = 4;

    private final Real referenceValue;

    private final StatusFlags statusFlags;

    private final Real setpointValue;

    private final Real errorLimit;

    public FloatingLimit(final Real referenceValue,
            final StatusFlags statusFlags, final Real setpointValue,
            final Real errorLimit) {
        this.referenceValue = referenceValue;
        this.statusFlags = statusFlags;
        this.setpointValue = setpointValue;
        this.errorLimit = errorLimit;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, referenceValue, 0);
        write(queue, statusFlags, 1);
        write(queue, setpointValue, 2);
        write(queue, errorLimit, 3);
    }

    public FloatingLimit(final ByteQueue queue) throws BACnetException {
        referenceValue = read(queue, Real.class, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
        setpointValue = read(queue, Real.class, 2);
        errorLimit = read(queue, Real.class, 3);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public Real getReferenceValue() {
        return referenceValue;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public Real getSetpointValue() {
        return setpointValue;
    }

    public Real getErrorLimit() {
        return errorLimit;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((errorLimit == null) ? 0 : errorLimit.hashCode());
        result = PRIME * result
                + ((referenceValue == null) ? 0 : referenceValue.hashCode());
        result = PRIME * result
                + ((setpointValue == null) ? 0 : setpointValue.hashCode());
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
        final FloatingLimit other = (FloatingLimit) obj;
        if (errorLimit == null) {
            if (other.errorLimit != null) {
                return false;
            }
        } else if (!errorLimit.equals(other.errorLimit)) {
            return false;
        }
        if (referenceValue == null) {
            if (other.referenceValue != null) {
                return false;
            }
        } else if (!referenceValue.equals(other.referenceValue)) {
            return false;
        }
        if (setpointValue == null) {
            if (other.setpointValue != null) {
                return false;
            }
        } else if (!setpointValue.equals(other.setpointValue)) {
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
