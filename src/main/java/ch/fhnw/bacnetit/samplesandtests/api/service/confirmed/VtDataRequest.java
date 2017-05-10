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
package ch.fhnw.bacnetit.samplesandtests.api.service.confirmed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
