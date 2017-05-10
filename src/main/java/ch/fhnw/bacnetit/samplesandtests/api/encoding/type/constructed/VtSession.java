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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class VtSession extends BaseType {
    private static final long serialVersionUID = 3655659977689484183L;

    private final UnsignedInteger localVtSessionId;

    private final UnsignedInteger remoteVtSessionId;

    private final Address remoteVtAddress;

    public VtSession(final UnsignedInteger localVtSessionId,
            final UnsignedInteger remoteVtSessionId,
            final Address remoteVtAddress) {
        this.localVtSessionId = localVtSessionId;
        this.remoteVtSessionId = remoteVtSessionId;
        this.remoteVtAddress = remoteVtAddress;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, localVtSessionId);
        write(queue, remoteVtSessionId);
        write(queue, remoteVtAddress);
    }

    public VtSession(final ByteQueue queue) throws BACnetException {
        localVtSessionId = read(queue, UnsignedInteger.class);
        remoteVtSessionId = read(queue, UnsignedInteger.class);
        remoteVtAddress = read(queue, Address.class);
    }

    public UnsignedInteger getLocalVtSessionId() {
        return localVtSessionId;
    }

    public UnsignedInteger getRemoteVtSessionId() {
        return remoteVtSessionId;
    }

    public Address getRemoteVtAddress() {
        return remoteVtAddress;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((localVtSessionId == null) ? 0
                : localVtSessionId.hashCode());
        result = PRIME * result
                + ((remoteVtAddress == null) ? 0 : remoteVtAddress.hashCode());
        result = PRIME * result + ((remoteVtSessionId == null) ? 0
                : remoteVtSessionId.hashCode());
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
        final VtSession other = (VtSession) obj;
        if (localVtSessionId == null) {
            if (other.localVtSessionId != null) {
                return false;
            }
        } else if (!localVtSessionId.equals(other.localVtSessionId)) {
            return false;
        }
        if (remoteVtAddress == null) {
            if (other.remoteVtAddress != null) {
                return false;
            }
        } else if (!remoteVtAddress.equals(other.remoteVtAddress)) {
            return false;
        }
        if (remoteVtSessionId == null) {
            if (other.remoteVtSessionId != null) {
                return false;
            }
        } else if (!remoteVtSessionId.equals(other.remoteVtSessionId)) {
            return false;
        }
        return true;
    }
}
