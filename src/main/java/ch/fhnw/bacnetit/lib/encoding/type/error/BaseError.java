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
package ch.fhnw.bacnetit.lib.encoding.type.error;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BaseError extends BaseType {
    private static final long serialVersionUID = 8363160647986011176L;

    public static BaseError createBaseError(final ByteQueue queue)
            throws BACnetException {
        final byte choice = queue.pop();

        switch (choice) {
        case 8:
        case 9:
            return new ChangeListError(choice, queue);
        case 10:
            return new CreateObjectError(choice, queue);
        case 16:
            return new WritePropertyMultipleError(choice, queue);
        case 18:
            return new ConfirmedPrivateTransferError(choice, queue);
        case 22:
            return new VTCloseError(choice, queue);
        }
        return new BaseError(choice, queue);
    }

    protected byte choice;

    protected BACnetError error;

    public BaseError(final byte choice, final BACnetError error) {
        this.choice = choice;
        this.error = error;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error);
    }

    public BaseError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        this.choice = choice;
        error = read(queue, BACnetError.class);
    }

    public BaseError(final byte choice, final ByteQueue queue,
            final int contextId) throws BACnetException {
        this.choice = choice;
        error = read(queue, BACnetError.class, contextId);
    }

    @Override
    public String toString() {
        return "choice=" + (choice & 0xff) + ", " + error;
    }

    public BACnetError getError() {
        return error;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + choice;
        result = PRIME * result + ((error == null) ? 0 : error.hashCode());
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
        final BaseError other = (BaseError) obj;
        if (choice != other.choice) {
            return false;
        }
        if (error == null) {
            if (other.error != null) {
                return false;
            }
        } else if (!error.equals(other.error)) {
            return false;
        }
        return true;
    }
}
