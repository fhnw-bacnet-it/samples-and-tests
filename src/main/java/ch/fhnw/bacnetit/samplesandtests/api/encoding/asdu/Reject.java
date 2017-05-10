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

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.RejectReason;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

/**
 * The BACnet-Reject-PDU is used to reject a received confirmed request PDU
 * based on syntactical flaws or other protocol errors that prevent the PDU from
 * being interpreted or the requested service from being provided. Only
 * confirmed request PDUs may be rejected.
 */
public class Reject extends AckASDU {
    private static final long serialVersionUID = 3800544859107653762L;

    public static final byte TYPE_ID = 6;

    /**
     * This parameter, of type BACnetRejectReason, contains the reason the PDU
     * with the indicated 'invokeID' is being rejected.
     */
    private final RejectReason rejectReason;

    public Reject(final byte originalInvokeId,
            final RejectReason rejectReason) {
        this.originalInvokeId = originalInvokeId;
        this.rejectReason = rejectReason;
    }

    @Override
    public byte getPduType() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(getShiftedTypeId(TYPE_ID));
        queue.push(originalInvokeId);
        queue.push(rejectReason.byteValue());
    }

    Reject(final ByteQueue queue) {
        queue.pop(); // Ignore the first byte. No news there.
        originalInvokeId = queue.pop();
        rejectReason = new RejectReason(queue.popU1B());
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + originalInvokeId;
        result = PRIME * result
                + ((rejectReason == null) ? 0 : rejectReason.hashCode());
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
        final Reject other = (Reject) obj;
        if (originalInvokeId != other.originalInvokeId) {
            return false;
        }
        if (rejectReason == null) {
            if (other.rejectReason != null) {
                return false;
            }
        } else if (!rejectReason.equals(other.rejectReason)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reject(originalInvokeId=" + originalInvokeId + ", rejectReason="
                + rejectReason + ")";
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
