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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.unconfirmed.UnconfirmedRequestService;

public class UnconfirmedRequest extends ASDU {
    private static final long serialVersionUID = 1606568334137370062L;

    public static final byte TYPE_ID = 1;

    /**
     * This parameter shall contain the parameters of the specific service that
     * is being requested, encoded according to the rules of 20.2. These
     * parameters are defined in the individual service descriptions in this
     * standard and are represented in Clause 21 in accordance with the rules of
     * ASN.1.
     */
    private final UnconfirmedRequestService service;

    public UnconfirmedRequest(final UnconfirmedRequestService service) {
        this.service = service;
    }

    @Override
    public byte getPduType() {
        return TYPE_ID;
    }

    public UnconfirmedRequestService getService() {
        return service;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(getShiftedTypeId(TYPE_ID));
        queue.push(service.getChoiceId());
        service.write(queue);
    }

    public UnconfirmedRequest(final ServicesSupported services,
            final ByteQueue queue) throws BACnetException {
        queue.pop();
        byte openTagChoiceId = queue.pop();
        openTagChoiceId = (byte) ((openTagChoiceId & 0xff) >> 4);
        service = UnconfirmedRequestService.createUnconfirmedRequestService(
                services, openTagChoiceId, queue);
        // byte closeTagChoiceId = queue.pop();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((service == null) ? 0 : service.hashCode());
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
        final UnconfirmedRequest other = (UnconfirmedRequest) obj;
        if (service == null) {
            if (other.service != null) {
                return false;
            }
        } else if (!service.equals(other.service)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
