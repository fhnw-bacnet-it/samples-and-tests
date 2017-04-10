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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
