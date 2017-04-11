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
package ch.fhnw.bacnetit.lib.encoding.asdu;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.error.BaseError;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

/**
 * The BACnet-Error-PDU is used to convey the information contained in a service
 * response primitive ('Result(-)') that indicates the reason why a previous
 * confirmed service request failed in its entirety.
 */
public class BACnetErrorAckASDU extends AckASDU {
    /**
     * This parameter shall contain the value of the
     * BACnetConfirmedServiceChoice corresponding to the service contained in
     * the previous BACnet-Confirmed-Service-Request that has resulted in this
     * acknowledgment.
     */
    private final int serviceAckChoice;

    private static final long serialVersionUID = 1062847933272895140L;

    public static final byte TYPE_ID = 4;

    /**
     * This parameter, of type BACnet-Error, indicates the reason the indicated
     * service request could not be carried out. This parameter shall be encoded
     * according to the rules of 20.2.
     */
    private final BaseError error;

    public BACnetErrorAckASDU(final ByteQueue queue) throws BACnetException {
        queue.pop(); // no news here
        serviceAckChoice = (queue.pop() & 0xf0) >> 4;
        if (serviceAckChoice >= 15) {
            queue.pop();
        }
        error = new BaseError((byte) serviceAckChoice, queue);
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
        queue.push(getShiftedTypeId(TYPE_ID));
        queue.push(originalInvokeId);
        error.write(queue);
    }

    @Override
    public String toString() {
        return "ErrorAPDU(" + error + ")";
    }

    public BaseError getError() {
        return error;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((error == null) ? 0 : error.hashCode());
        result = PRIME * result + originalInvokeId;
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
        final BACnetErrorAckASDU other = (BACnetErrorAckASDU) obj;
        if (error == null) {
            if (other.error != null) {
                return false;
            }
        } else if (!error.equals(other.error)) {
            return false;
        }
        if (originalInvokeId != other.originalInvokeId) {
            return false;
        }
        return true;
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
