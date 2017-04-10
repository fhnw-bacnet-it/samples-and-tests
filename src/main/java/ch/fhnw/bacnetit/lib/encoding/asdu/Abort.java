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
package ch.fhnw.bacnetit.lib.encoding.asdu;

import ch.fhnw.bacnetit.lib.encoding.type.enumerated.AbortReason;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
