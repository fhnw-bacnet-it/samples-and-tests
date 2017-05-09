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
package ch.fhnw.bacnetit.samplesandtests.encoding.asdu;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.UnconfirmedRequestService;

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
