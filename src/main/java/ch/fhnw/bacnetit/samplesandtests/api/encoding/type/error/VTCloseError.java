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

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class VTCloseError extends BaseError {
    private static final long serialVersionUID = 7817379391677615141L;

    private final SequenceOf<UnsignedInteger> listOfVTSessionIdentifiers;

    public VTCloseError(final byte choice, final BACnetError error,
            final SequenceOf<UnsignedInteger> listOfVTSessionIdentifiers) {
        super(choice, error);
        this.listOfVTSessionIdentifiers = listOfVTSessionIdentifiers;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error, 0);
        writeOptional(queue, listOfVTSessionIdentifiers, 1);
    }

    VTCloseError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        super(choice, queue, 0);
        listOfVTSessionIdentifiers = readOptionalSequenceOf(queue,
                UnsignedInteger.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + ((listOfVTSessionIdentifiers == null) ? 0
                : listOfVTSessionIdentifiers.hashCode());
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
        final VTCloseError other = (VTCloseError) obj;
        if (listOfVTSessionIdentifiers == null) {
            if (other.listOfVTSessionIdentifiers != null) {
                return false;
            }
        } else if (!listOfVTSessionIdentifiers
                .equals(other.listOfVTSessionIdentifiers)) {
            return false;
        }
        return true;
    }
}
