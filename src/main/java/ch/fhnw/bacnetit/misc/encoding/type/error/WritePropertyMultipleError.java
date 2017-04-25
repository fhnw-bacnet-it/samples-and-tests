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
package ch.fhnw.bacnetit.misc.encoding.type.error;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.ObjectPropertyReference;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class WritePropertyMultipleError extends BaseError {
    private static final long serialVersionUID = 7893783677289456368L;

    private final ObjectPropertyReference firstFailedWriteAttempt;

    public WritePropertyMultipleError(final byte choice,
            final BACnetError error,
            final ObjectPropertyReference firstFailedWriteAttempt) {
        super(choice, error);
        this.firstFailedWriteAttempt = firstFailedWriteAttempt;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error, 0);
        firstFailedWriteAttempt.write(queue, 1);
    }

    WritePropertyMultipleError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        super(choice, queue, 0);
        firstFailedWriteAttempt = read(queue, ObjectPropertyReference.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + ((firstFailedWriteAttempt == null) ? 0
                : firstFailedWriteAttempt.hashCode());
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
        final WritePropertyMultipleError other = (WritePropertyMultipleError) obj;
        if (firstFailedWriteAttempt == null) {
            if (other.firstFailedWriteAttempt != null) {
                return false;
            }
        } else if (!firstFailedWriteAttempt
                .equals(other.firstFailedWriteAttempt)) {
            return false;
        }
        return true;
    }
}
