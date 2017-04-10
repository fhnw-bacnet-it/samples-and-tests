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
