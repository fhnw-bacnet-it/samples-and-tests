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

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
