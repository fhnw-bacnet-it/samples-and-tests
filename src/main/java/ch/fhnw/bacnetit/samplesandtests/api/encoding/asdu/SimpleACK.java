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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class SimpleACK extends AckASDU {
    private static final long serialVersionUID = -4585349985375271438L;

    public static final byte TYPE_ID = 3;

    /**
     * This parameter shall contain the value of the
     * BACnetConfirmedServiceChoice corresponding to the service contained in
     * the previous BACnet-Confirmed-Service-Request that has resulted in this
     * acknowledgment.
     */
    private final int serviceAckChoice;

    public SimpleACK(final int serviceAckChoice) {
        this.serviceAckChoice = serviceAckChoice;
    }

    public int getServiceAckChoice() {
        return serviceAckChoice;
    }

    @Override
    public byte getPduType() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        /*
         * +9, because: X30 (0011 0000) contains no type and length value So add
         * X09 (0000 1001) to get X39 (0011 1001)
         */
        queue.push(getShiftedTypeId(TYPE_ID) + 9);
        // queue.push(originalInvokeId);
        queue.push(serviceAckChoice);
    }

    public SimpleACK(final ByteQueue queue) {
        queue.pop(); // no news here
        // originalInvokeId = queue.pop();
        serviceAckChoice = queue.popU1B();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        // result = PRIME * result + originalInvokeId;
        result = PRIME * result + serviceAckChoice;
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
        final SimpleACK other = (SimpleACK) obj;
        if (originalInvokeId != other.originalInvokeId) {
            return false;
        }
        if (serviceAckChoice != other.serviceAckChoice) {
            return false;
        }
        return true;
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
