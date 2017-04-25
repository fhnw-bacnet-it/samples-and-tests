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
package ch.fhnw.bacnetit.misc.service.confirmed;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class VtCloseRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -7857063472279095665L;

    public static final byte TYPE_ID = 22;

    private final SequenceOf<UnsignedInteger> listOfRemoteVTSessionIdentifiers;

    public VtCloseRequest(
            final SequenceOf<UnsignedInteger> listOfRemoteVTSessionIdentifiers) {
        this.listOfRemoteVTSessionIdentifiers = listOfRemoteVTSessionIdentifiers;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, listOfRemoteVTSessionIdentifiers);
    }

    VtCloseRequest(final ByteQueue queue) throws BACnetException {
        listOfRemoteVTSessionIdentifiers = readSequenceOf(queue,
                UnsignedInteger.class);
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
        result = PRIME * result + ((listOfRemoteVTSessionIdentifiers == null)
                ? 0 : listOfRemoteVTSessionIdentifiers.hashCode());
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
        final VtCloseRequest other = (VtCloseRequest) obj;
        if (listOfRemoteVTSessionIdentifiers == null) {
            if (other.listOfRemoteVTSessionIdentifiers != null) {
                return false;
            }
        } else if (!listOfRemoteVTSessionIdentifiers
                .equals(other.listOfRemoteVTSessionIdentifiers)) {
            return false;
        }
        return true;
    }
}
