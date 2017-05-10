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
package ch.fhnw.bacnetit.samplesandtests.api.service.confirmed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.VtClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class VtOpenRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 6197768113884175382L;

    public static final byte TYPE_ID = 21;

    private final VtClass vtClass;

    private final UnsignedInteger localVTSessionIdentifier;

    public VtOpenRequest(final VtClass vtClass,
            final UnsignedInteger localVTSessionIdentifier) {
        this.vtClass = vtClass;
        this.localVTSessionIdentifier = localVTSessionIdentifier;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, vtClass);
        write(queue, localVTSessionIdentifier);
    }

    VtOpenRequest(final ByteQueue queue) throws BACnetException {
        vtClass = read(queue, VtClass.class);
        localVTSessionIdentifier = read(queue, UnsignedInteger.class);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((localVTSessionIdentifier == null) ? 0
                : localVTSessionIdentifier.hashCode());
        result = PRIME * result + ((vtClass == null) ? 0 : vtClass.hashCode());
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
        final VtOpenRequest other = (VtOpenRequest) obj;
        if (localVTSessionIdentifier == null) {
            if (other.localVTSessionIdentifier != null) {
                return false;
            }
        } else if (!localVTSessionIdentifier
                .equals(other.localVTSessionIdentifier)) {
            return false;
        }
        if (vtClass == null) {
            if (other.vtClass != null) {
                return false;
            }
        } else if (!vtClass.equals(other.vtClass)) {
            return false;
        }
        return true;
    }
}
