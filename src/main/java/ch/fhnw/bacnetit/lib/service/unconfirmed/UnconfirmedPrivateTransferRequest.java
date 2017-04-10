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
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.bacnetit.lib.deviceobjects.VendorServiceKey;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.SequenceDefinition;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class UnconfirmedPrivateTransferRequest
        extends UnconfirmedRequestService {
    private static final long serialVersionUID = 2084345165686680966L;

    public static final Map<VendorServiceKey, SequenceDefinition> vendorServiceResolutions = new HashMap<VendorServiceKey, SequenceDefinition>();

    public static final byte TYPE_ID = 4;

    private final UnsignedInteger vendorId;

    private final UnsignedInteger serviceNumber;

    private final Encodable serviceParameters;

    public UnconfirmedPrivateTransferRequest(final UnsignedInteger vendorId,
            final UnsignedInteger serviceNumber,
            final Encodable serviceParameters) {
        this.vendorId = vendorId;
        this.serviceNumber = serviceNumber;
        this.serviceParameters = serviceParameters;
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) {
    // localDevice.getEventHandler().firePrivateTransfer(vendorId,
    // serviceNumber, serviceParameters);
    // }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, vendorId, 0);
        write(queue, serviceNumber, 1);
        writeOptional(queue, serviceParameters, 2);
    }

    UnconfirmedPrivateTransferRequest(final ByteQueue queue)
            throws BACnetException {
        vendorId = read(queue, UnsignedInteger.class, 0);
        serviceNumber = read(queue, UnsignedInteger.class, 1);
        serviceParameters = readVendorSpecific(queue, vendorId, serviceNumber,
                vendorServiceResolutions, 2);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((serviceNumber == null) ? 0 : serviceNumber.hashCode());
        result = PRIME * result + ((serviceParameters == null) ? 0
                : serviceParameters.hashCode());
        result = PRIME * result
                + ((vendorId == null) ? 0 : vendorId.hashCode());
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
        final UnconfirmedPrivateTransferRequest other = (UnconfirmedPrivateTransferRequest) obj;
        if (serviceNumber == null) {
            if (other.serviceNumber != null) {
                return false;
            }
        } else if (!serviceNumber.equals(other.serviceNumber)) {
            return false;
        }
        if (serviceParameters == null) {
            if (other.serviceParameters != null) {
                return false;
            }
        } else if (!serviceParameters.equals(other.serviceParameters)) {
            return false;
        }
        if (vendorId == null) {
            if (other.vendorId != null) {
                return false;
            }
        } else if (!vendorId.equals(other.vendorId)) {
            return false;
        }
        return true;
    }
}
