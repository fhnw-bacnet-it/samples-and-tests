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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class SessionKey extends BaseType {
    private static final long serialVersionUID = 2276895536699919255L;

    private final OctetString sessionKey;

    private final Address peerAddress;

    public SessionKey(final OctetString sessionKey, final Address peerAddress) {
        this.sessionKey = sessionKey;
        this.peerAddress = peerAddress;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, sessionKey);
        write(queue, peerAddress);
    }

    public SessionKey(final ByteQueue queue) throws BACnetException {
        sessionKey = read(queue, OctetString.class);
        peerAddress = read(queue, Address.class);
    }

    public OctetString getSessionKey() {
        return sessionKey;
    }

    public Address getPeerAddress() {
        return peerAddress;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((peerAddress == null) ? 0 : peerAddress.hashCode());
        result = PRIME * result
                + ((sessionKey == null) ? 0 : sessionKey.hashCode());
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
        final SessionKey other = (SessionKey) obj;
        if (peerAddress == null) {
            if (other.peerAddress != null) {
                return false;
            }
        } else if (!peerAddress.equals(other.peerAddress)) {
            return false;
        }
        if (sessionKey == null) {
            if (other.sessionKey != null) {
                return false;
            }
        } else if (!sessionKey.equals(other.sessionKey)) {
            return false;
        }
        return true;
    }
}
