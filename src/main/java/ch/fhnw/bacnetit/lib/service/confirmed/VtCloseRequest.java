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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
