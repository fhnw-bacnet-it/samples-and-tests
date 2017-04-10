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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class VtDataRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 5285787585416136977L;

    public static final byte TYPE_ID = 23;

    private final UnsignedInteger vtSessionIdentifier;

    private final OctetString vtNewData;

    private final UnsignedInteger vtDataFlag;

    public VtDataRequest(final UnsignedInteger vtSessionIdentifier,
            final OctetString vtNewData, final UnsignedInteger vtDataFlag) {
        this.vtSessionIdentifier = vtSessionIdentifier;
        this.vtNewData = vtNewData;
        this.vtDataFlag = vtDataFlag;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, vtSessionIdentifier);
        write(queue, vtNewData);
        write(queue, vtDataFlag);
    }

    VtDataRequest(final ByteQueue queue) throws BACnetException {
        vtSessionIdentifier = read(queue, UnsignedInteger.class);
        vtNewData = read(queue, OctetString.class);
        vtDataFlag = read(queue, UnsignedInteger.class);
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
        result = PRIME * result
                + ((vtDataFlag == null) ? 0 : vtDataFlag.hashCode());
        result = PRIME * result
                + ((vtNewData == null) ? 0 : vtNewData.hashCode());
        result = PRIME * result + ((vtSessionIdentifier == null) ? 0
                : vtSessionIdentifier.hashCode());
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
        final VtDataRequest other = (VtDataRequest) obj;
        if (vtDataFlag == null) {
            if (other.vtDataFlag != null) {
                return false;
            }
        } else if (!vtDataFlag.equals(other.vtDataFlag)) {
            return false;
        }
        if (vtNewData == null) {
            if (other.vtNewData != null) {
                return false;
            }
        } else if (!vtNewData.equals(other.vtNewData)) {
            return false;
        }
        if (vtSessionIdentifier == null) {
            if (other.vtSessionIdentifier != null) {
                return false;
            }
        } else if (!vtSessionIdentifier.equals(other.vtSessionIdentifier)) {
            return false;
        }
        return true;
    }
}
