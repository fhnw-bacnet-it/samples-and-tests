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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.AmbiguousValue;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class CommandFailure extends NotificationParameters {
    private static final long serialVersionUID = 5727410398456093753L;

    public static final byte TYPE_ID = 3;

    private final Encodable commandValue;

    private final StatusFlags statusFlags;

    private final Encodable feedbackValue;

    public CommandFailure(final Encodable commandValue,
            final StatusFlags statusFlags, final Encodable feedbackValue) {
        this.commandValue = commandValue;
        this.statusFlags = statusFlags;
        this.feedbackValue = feedbackValue;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        writeEncodable(queue, commandValue, 0);
        write(queue, statusFlags, 1);
        writeEncodable(queue, feedbackValue, 2);
    }

    public CommandFailure(final ByteQueue queue) throws BACnetException {
        commandValue = new AmbiguousValue(queue, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
        feedbackValue = new AmbiguousValue(queue, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public Encodable getCommandValue() {
        return commandValue;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public Encodable getFeedbackValue() {
        return feedbackValue;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((commandValue == null) ? 0 : commandValue.hashCode());
        result = PRIME * result
                + ((feedbackValue == null) ? 0 : feedbackValue.hashCode());
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
        final CommandFailure other = (CommandFailure) obj;
        if (commandValue == null) {
            if (other.commandValue != null) {
                return false;
            }
        } else if (!commandValue.equals(other.commandValue)) {
            return false;
        }
        if (feedbackValue == null) {
            if (other.feedbackValue != null) {
                return false;
            }
        } else if (!feedbackValue.equals(other.feedbackValue)) {
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
