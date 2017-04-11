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
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WhoIsRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = 4853007370475322913L;

    public static final byte TYPE_ID = 8;

    private UnsignedInteger deviceInstanceRangeLowLimit;

    private UnsignedInteger deviceInstanceRangeHighLimit;

    public WhoIsRequest() {
        // no op
    }

    public WhoIsRequest(final UnsignedInteger deviceInstanceRangeLowLimit,
            final UnsignedInteger deviceInstanceRangeHighLimit) {
        this.deviceInstanceRangeLowLimit = deviceInstanceRangeLowLimit;
        this.deviceInstanceRangeHighLimit = deviceInstanceRangeHighLimit;
    }

    WhoIsRequest(final ByteQueue queue) throws BACnetException {
        deviceInstanceRangeLowLimit = readOptional(queue, UnsignedInteger.class,
                0);
        deviceInstanceRangeHighLimit = readOptional(queue,
                UnsignedInteger.class, 1);
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) throws BACnetException {
    // BACnetObject local = localDevice.getConfiguration();
    //
    // // Check if we're in the device id range.
    // if (deviceInstanceRangeLowLimit != null && local.getInstanceId() <
    // deviceInstanceRangeLowLimit.intValue())
    // return;
    //
    // if (deviceInstanceRangeHighLimit != null && local.getInstanceId() >
    // deviceInstanceRangeHighLimit.intValue())
    // return;
    //
    // // Return the result in a i am message.
    // IAmRequest iam = localDevice.getIAm();
    // localDevice.sendGlobalBroadcast(iam);
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeContextTag(queue, SERVICE_ID, true);
        writeContextTag(queue, TYPE_ID, true);
        writeOptional(queue, deviceInstanceRangeLowLimit, 0);
        writeOptional(queue, deviceInstanceRangeHighLimit, 1);
        writeContextTag(queue, TYPE_ID, false);
        writeContextTag(queue, SERVICE_ID, false);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((deviceInstanceRangeHighLimit == null) ? 0
                : deviceInstanceRangeHighLimit.hashCode());
        result = PRIME * result + ((deviceInstanceRangeLowLimit == null) ? 0
                : deviceInstanceRangeLowLimit.hashCode());
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
        final WhoIsRequest other = (WhoIsRequest) obj;
        if (deviceInstanceRangeHighLimit == null) {
            if (other.deviceInstanceRangeHighLimit != null) {
                return false;
            }
        } else if (!deviceInstanceRangeHighLimit
                .equals(other.deviceInstanceRangeHighLimit)) {
            return false;
        }
        if (deviceInstanceRangeLowLimit == null) {
            if (other.deviceInstanceRangeLowLimit != null) {
                return false;
            }
        } else if (!deviceInstanceRangeLowLimit
                .equals(other.deviceInstanceRangeLowLimit)) {
            return false;
        }
        return true;
    }
}
