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
package ch.fhnw.bacnetit.samplesandtests.service.acknowledgment;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class VtOpenAck extends AcknowledgementService {
    private static final long serialVersionUID = 6959632953423207763L;

    public static final byte TYPE_ID = 21;

    private final UnsignedInteger remoteVTSessionIdentifier;

    public VtOpenAck(final UnsignedInteger remoteVTSessionIdentifier) {
        this.remoteVTSessionIdentifier = remoteVTSessionIdentifier;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, remoteVTSessionIdentifier);
    }

    VtOpenAck(final ByteQueue queue) throws BACnetException {
        remoteVTSessionIdentifier = read(queue, UnsignedInteger.class);
    }

    public UnsignedInteger getRemoteVTSessionIdentifier() {
        return remoteVTSessionIdentifier;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((remoteVTSessionIdentifier == null) ? 0
                : remoteVTSessionIdentifier.hashCode());
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
        final VtOpenAck other = (VtOpenAck) obj;
        if (remoteVTSessionIdentifier == null) {
            if (other.remoteVTSessionIdentifier != null) {
                return false;
            }
        } else if (!remoteVTSessionIdentifier
                .equals(other.remoteVTSessionIdentifier)) {
            return false;
        }
        return true;
    }
}
