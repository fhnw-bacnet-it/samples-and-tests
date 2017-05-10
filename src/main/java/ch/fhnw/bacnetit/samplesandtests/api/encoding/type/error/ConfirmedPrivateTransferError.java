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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.error;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.VendorServiceKey;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.SequenceDefinition;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class ConfirmedPrivateTransferError extends BaseError {
    private static final long serialVersionUID = -4736829685989649711L;

    public static final Map<VendorServiceKey, SequenceDefinition> vendorServiceResolutions = new HashMap<VendorServiceKey, SequenceDefinition>();

    private final UnsignedInteger vendorId;

    private final UnsignedInteger serviceNumber;

    private final Encodable errorParameters;

    public ConfirmedPrivateTransferError(final byte choice,
            final BACnetError error, final UnsignedInteger vendorId,
            final UnsignedInteger serviceNumber,
            final BaseType errorParameters) {
        super(choice, error);
        this.vendorId = vendorId;
        this.serviceNumber = serviceNumber;
        this.errorParameters = errorParameters;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error, 0);
        write(queue, vendorId, 1);
        write(queue, serviceNumber, 2);
        writeOptional(queue, errorParameters, 3);
    }

    ConfirmedPrivateTransferError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        super(choice, queue, 0);
        vendorId = read(queue, UnsignedInteger.class, 1);
        serviceNumber = read(queue, UnsignedInteger.class, 2);
        errorParameters = readVendorSpecific(queue, vendorId, serviceNumber,
                vendorServiceResolutions, 3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result
                + ((errorParameters == null) ? 0 : errorParameters.hashCode());
        result = PRIME * result
                + ((serviceNumber == null) ? 0 : serviceNumber.hashCode());
        result = PRIME * result
                + ((vendorId == null) ? 0 : vendorId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfirmedPrivateTransferError other = (ConfirmedPrivateTransferError) obj;
        if (errorParameters == null) {
            if (other.errorParameters != null) {
                return false;
            }
        } else if (!errorParameters.equals(other.errorParameters)) {
            return false;
        }
        if (serviceNumber == null) {
            if (other.serviceNumber != null) {
                return false;
            }
        } else if (!serviceNumber.equals(other.serviceNumber)) {
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
