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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ClientCov extends BaseType {
    private static final long serialVersionUID = 654453440700433527L;

    private Real realIncrement;

    private Null defaultIncrement;

    public ClientCov(final Real realIncrement) {
        this.realIncrement = realIncrement;
    }

    public ClientCov(final Null defaultIncrement) {
        this.defaultIncrement = defaultIncrement;
    }

    @Override
    public void write(final ByteQueue queue) {
        if (realIncrement != null) {
            write(queue, realIncrement);
        } else {
            write(queue, defaultIncrement);
        }
    }

    public ClientCov(final ByteQueue queue) throws BACnetException {
        final int tag = (queue.peek(0) & 0xff) >> 4;
        if (tag == Null.TYPE_ID) {
            defaultIncrement = new Null(queue);
        } else if (tag == Real.TYPE_ID) {
            realIncrement = new Real(queue);
        } else {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.invalidParameterDataType);
        }
    }

    public Real getRealIncrement() {
        return realIncrement;
    }

    public Null getDefaultIncrement() {
        return defaultIncrement;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((defaultIncrement == null) ? 0
                : defaultIncrement.hashCode());
        result = PRIME * result
                + ((realIncrement == null) ? 0 : realIncrement.hashCode());
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
        final ClientCov other = (ClientCov) obj;
        if (defaultIncrement == null) {
            if (other.defaultIncrement != null) {
                return false;
            }
        } else if (!defaultIncrement.equals(other.defaultIncrement)) {
            return false;
        }
        if (realIncrement == null) {
            if (other.realIncrement != null) {
                return false;
            }
        } else if (!realIncrement.equals(other.realIncrement)) {
            return false;
        }
        return true;
    }
}
