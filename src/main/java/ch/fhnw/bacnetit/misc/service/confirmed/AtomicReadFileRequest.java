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
package ch.fhnw.bacnetit.misc.service.confirmed;

import ch.fhnw.bacnetit.misc.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class AtomicReadFileRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -279843621668191530L;

    public static final byte TYPE_ID = 6;

    private final BACnetObjectIdentifier fileIdentifier;

    private final boolean recordAccess;

    private final SignedInteger fileStartPosition;

    private final UnsignedInteger requestedCount;

    public AtomicReadFileRequest(final BACnetObjectIdentifier fileIdentifier,
            final boolean recordAccess, final SignedInteger fileStartPosition,
            final UnsignedInteger requestedCount) {
        this.fileIdentifier = fileIdentifier;
        this.recordAccess = recordAccess;
        this.fileStartPosition = fileStartPosition;
        this.requestedCount = requestedCount;
    }

    public AtomicReadFileRequest(final BACnetObjectIdentifier fileIdentifier,
            final boolean recordAccess, final int start, final int length) {
        this(fileIdentifier, recordAccess, new SignedInteger(start),
                new UnsignedInteger(length));
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, fileIdentifier);
        writeContextTag(queue, recordAccess ? 1 : 0, true);
        write(queue, fileStartPosition);
        write(queue, requestedCount);
        writeContextTag(queue, recordAccess ? 1 : 0, false);
    }

    AtomicReadFileRequest(final ByteQueue queue) throws BACnetException {
        fileIdentifier = read(queue, BACnetObjectIdentifier.class);
        recordAccess = popStart(queue) == 1;
        fileStartPosition = read(queue, SignedInteger.class);
        requestedCount = read(queue, UnsignedInteger.class);
        popEnd(queue, recordAccess ? 1 : 0);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((fileIdentifier == null) ? 0 : fileIdentifier.hashCode());
        result = PRIME * result + ((fileStartPosition == null) ? 0
                : fileStartPosition.hashCode());
        result = PRIME * result + (recordAccess ? 1231 : 1237);
        result = PRIME * result
                + ((requestedCount == null) ? 0 : requestedCount.hashCode());
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
        final AtomicReadFileRequest other = (AtomicReadFileRequest) obj;
        if (fileIdentifier == null) {
            if (other.fileIdentifier != null) {
                return false;
            }
        } else if (!fileIdentifier.equals(other.fileIdentifier)) {
            return false;
        }
        if (fileStartPosition == null) {
            if (other.fileStartPosition != null) {
                return false;
            }
        } else if (!fileStartPosition.equals(other.fileStartPosition)) {
            return false;
        }
        if (recordAccess != other.recordAccess) {
            return false;
        }
        if (requestedCount == null) {
            if (other.requestedCount != null) {
                return false;
            }
        } else if (!requestedCount.equals(other.requestedCount)) {
            return false;
        }
        return true;
    }
}
