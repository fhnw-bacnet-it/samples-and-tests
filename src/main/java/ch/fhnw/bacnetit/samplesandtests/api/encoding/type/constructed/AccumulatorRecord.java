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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class AccumulatorRecord extends BaseType {
    private static final long serialVersionUID = 6871737486792988550L;

    private final DateTime timestamp;

    private final UnsignedInteger presentValue;

    private final UnsignedInteger accumulatedValue;

    private final AccumulatorStatus accumulatorStatus;

    public AccumulatorRecord(final DateTime timestamp,
            final UnsignedInteger presentValue,
            final UnsignedInteger accumulatedValue,
            final AccumulatorStatus accumulatorStatus) {
        this.timestamp = timestamp;
        this.presentValue = presentValue;
        this.accumulatedValue = accumulatedValue;
        this.accumulatorStatus = accumulatorStatus;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, timestamp, 0);
        write(queue, presentValue, 1);
        write(queue, accumulatedValue, 2);
        write(queue, accumulatorStatus, 3);
    }

    public AccumulatorRecord(final ByteQueue queue) throws BACnetException {
        timestamp = read(queue, DateTime.class, 0);
        presentValue = read(queue, UnsignedInteger.class, 1);
        accumulatedValue = read(queue, UnsignedInteger.class, 2);
        accumulatorStatus = read(queue, AccumulatorStatus.class, 3);
    }

    public static class AccumulatorStatus extends Enumerated {
        private static final long serialVersionUID = -2613363966788524923L;

        public static final AccumulatorStatus normal = new AccumulatorStatus(0);

        public static final AccumulatorStatus starting = new AccumulatorStatus(
                1);

        public static final AccumulatorStatus recovered = new AccumulatorStatus(
                2);

        public static final AccumulatorStatus abnormal = new AccumulatorStatus(
                3);

        public static final AccumulatorStatus failed = new AccumulatorStatus(4);

        public AccumulatorStatus(final int value) {
            super(value);
        }

        public AccumulatorStatus(final ByteQueue queue) {
            super(queue);
        }
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public UnsignedInteger getPresentValue() {
        return presentValue;
    }

    public UnsignedInteger getAccumulatedValue() {
        return accumulatedValue;
    }

    public AccumulatorStatus getAccumulatorStatus() {
        return accumulatorStatus;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((accumulatedValue == null) ? 0
                : accumulatedValue.hashCode());
        result = PRIME * result + ((accumulatorStatus == null) ? 0
                : accumulatorStatus.hashCode());
        result = PRIME * result
                + ((presentValue == null) ? 0 : presentValue.hashCode());
        result = PRIME * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        final AccumulatorRecord other = (AccumulatorRecord) obj;
        if (accumulatedValue == null) {
            if (other.accumulatedValue != null) {
                return false;
            }
        } else if (!accumulatedValue.equals(other.accumulatedValue)) {
            return false;
        }
        if (accumulatorStatus == null) {
            if (other.accumulatorStatus != null) {
                return false;
            }
        } else if (!accumulatorStatus.equals(other.accumulatorStatus)) {
            return false;
        }
        if (presentValue == null) {
            if (other.presentValue != null) {
                return false;
            }
        } else if (!presentValue.equals(other.presentValue)) {
            return false;
        }
        if (timestamp == null) {
            if (other.timestamp != null) {
                return false;
            }
        } else if (!timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }
}
