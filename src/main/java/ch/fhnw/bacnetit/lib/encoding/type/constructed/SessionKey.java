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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
