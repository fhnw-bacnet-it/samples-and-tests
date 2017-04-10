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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.VtClass;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
