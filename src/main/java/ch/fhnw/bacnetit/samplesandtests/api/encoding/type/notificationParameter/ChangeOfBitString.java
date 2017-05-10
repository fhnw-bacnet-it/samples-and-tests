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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class ChangeOfBitString extends NotificationParameters {
    private static final long serialVersionUID = -3901007413758533165L;

    public static final byte TYPE_ID = 0;

    private final BitString referencedBitstring;

    private final StatusFlags statusFlags;

    public ChangeOfBitString(final BitString referencedBitstring,
            final StatusFlags statusFlags) {
        this.referencedBitstring = referencedBitstring;
        this.statusFlags = statusFlags;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, referencedBitstring, 0);
        write(queue, statusFlags, 1);
    }

    public ChangeOfBitString(final ByteQueue queue) throws BACnetException {
        referencedBitstring = read(queue, BitString.class, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public BitString getReferencedBitstring() {
        return referencedBitstring;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((referencedBitstring == null) ? 0
                : referencedBitstring.hashCode());
        result = PRIME * result
                + ((statusFlags == null) ? 0 : statusFlags.hashCode());
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
        final ChangeOfBitString other = (ChangeOfBitString) obj;
        if (referencedBitstring == null) {
            if (other.referencedBitstring != null) {
                return false;
            }
        } else if (!referencedBitstring.equals(other.referencedBitstring)) {
            return false;
        }
        if (statusFlags == null) {
            if (other.statusFlags != null) {
                return false;
            }
        } else if (!statusFlags.equals(other.statusFlags)) {
            return false;
        }
        return true;
    }
}
