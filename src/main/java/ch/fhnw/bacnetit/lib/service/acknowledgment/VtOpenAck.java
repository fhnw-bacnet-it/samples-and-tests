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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
