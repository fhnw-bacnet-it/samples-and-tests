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
