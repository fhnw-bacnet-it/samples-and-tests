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
package ch.fhnw.bacnetit.lib.encoding.type.eventParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

abstract public class EventParameter extends BaseType {
    private static final long serialVersionUID = -8202182792179896645L;

    public static EventParameter createEventParameter(final ByteQueue queue)
            throws BACnetException {
        // Get the first byte. It will tell us what the parameter type is.
        final int type = popStart(queue);

        EventParameter result;
        if (type == ChangeOfBitString.TYPE_ID) {
            result = new ChangeOfBitString(queue);
        } else if (type == ChangeOfState.TYPE_ID) {
            result = new ChangeOfState(queue);
        } else if (type == ChangeOfValue.TYPE_ID) {
            result = new ChangeOfValue(queue);
        } else if (type == CommandFailure.TYPE_ID) {
            result = new CommandFailure(queue);
        } else if (type == FloatingLimit.TYPE_ID) {
            result = new FloatingLimit(queue);
        } else if (type == OutOfRange.TYPE_ID) {
            result = new OutOfRange(queue);
        } else if (type == ChangeOfLifeSafety.TYPE_ID) {
            result = new ChangeOfLifeSafety(queue);
        } else if (type == Extended.TYPE_ID) {
            result = new Extended(queue);
        } else if (type == BufferReady.TYPE_ID) {
            result = new BufferReady(queue);
        } else if (type == UnsignedRange.TYPE_ID) {
            result = new UnsignedRange(queue);
        } else {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.invalidParameterDataType);
        }

        popEnd(queue, type);
        return result;
    }

    @Override
    final public void write(final ByteQueue queue) {
        writeContextTag(queue, getTypeId(), true);
        writeImpl(queue);
        writeContextTag(queue, getTypeId(), false);
    }

    abstract protected int getTypeId();

    abstract protected void writeImpl(ByteQueue queue);
}
