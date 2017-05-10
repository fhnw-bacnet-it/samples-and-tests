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

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.AbortReason;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

/**
 * The BACnet-Abort-PDU is used to terminate a transaction between two peers.
 *
 * @author mlohbihler
 */
public class Abort extends AckASDU {
    private static final long serialVersionUID = 2521232315250724579L;

    public static final byte TYPE_ID = 7;

    /**
     * This parameter shall be TRUE when the Abort PDU is sent by a server. This
     * parameter shall be FALSE when the Abort PDU is sent by a client.
     */
    private final boolean server;

    /**
     * This parameter, of type BACnetAbortReason, contains the reason the
     * transaction with the indicated invoke ID is being aborted.
     */
    private final int abortReason;

    public Abort(final boolean server, final byte originalInvokeId,
            final int abortReason) {
        this.server = server;
        this.originalInvokeId = originalInvokeId;
        this.abortReason = abortReason;
    }

    @Override
    public byte getPduType() {
        return TYPE_ID;
    }

    @Override
    public boolean isServer() {
        return server;
    }

    public int getAbortReason() {
        return abortReason;
    }

    @Override
    public void write(final ByteQueue queue) {
        final int data = getShiftedTypeId(TYPE_ID) | (server ? 1 : 0);
        queue.push(data);
        queue.push(originalInvokeId);
        queue.push(abortReason);
    }

    Abort(final ByteQueue queue) {
        server = (queue.popU1B() & 1) == 1;
        originalInvokeId = queue.pop();
        abortReason = queue.popU1B();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + abortReason;
        result = PRIME * result + originalInvokeId;
        result = PRIME * result + (server ? 1231 : 1237);
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
        final Abort other = (Abort) obj;
        if (abortReason != other.abortReason) {
            return false;
        }
        if (originalInvokeId != other.originalInvokeId) {
            return false;
        }
        if (server != other.server) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Abort(server=" + server + ", originalInvokeId="
                + originalInvokeId + ", abortReason="
                + new AbortReason(abortReason) + ")";
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
