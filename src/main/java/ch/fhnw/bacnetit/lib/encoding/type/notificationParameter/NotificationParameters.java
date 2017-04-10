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
package ch.fhnw.bacnetit.lib.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

abstract public class NotificationParameters extends BaseType {
    private static final long serialVersionUID = 7902337844295486044L;

    public static NotificationParameters createNotificationParameters(
            final ByteQueue queue, final int contextId) throws BACnetException {
        popStart(queue, contextId);
        final NotificationParameters result = createNotificationParameters(
                queue);
        popEnd(queue, contextId);
        return result;
    }

    public static NotificationParameters createNotificationParametersOptional(
            final ByteQueue queue, final int contextId) throws BACnetException {
        if (readStart(queue) != contextId) {
            return null;
        }
        return createNotificationParameters(queue, contextId);
    }

    public static NotificationParameters createNotificationParameters(
            final ByteQueue queue) throws BACnetException {
        // Get the first byte. It will tell us what the service type is.
        final int type = popStart(queue);

        NotificationParameters result;
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
        } else if (type == ComplexEventType.TYPE_ID) {
            result = new ComplexEventType(queue);
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
