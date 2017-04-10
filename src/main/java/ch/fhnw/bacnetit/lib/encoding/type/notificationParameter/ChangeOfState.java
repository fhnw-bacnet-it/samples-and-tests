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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PropertyStates;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ChangeOfState extends NotificationParameters {
    private static final long serialVersionUID = -3581834332371728769L;

    public static final byte TYPE_ID = 1;

    private final PropertyStates newState;

    private final StatusFlags statusFlags;

    public ChangeOfState(final PropertyStates newState,
            final StatusFlags statusFlags) {
        this.newState = newState;
        this.statusFlags = statusFlags;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, newState, 0);
        write(queue, statusFlags, 1);
    }

    public ChangeOfState(final ByteQueue queue) throws BACnetException {
        newState = read(queue, PropertyStates.class, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public PropertyStates getNewState() {
        return newState;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((newState == null) ? 0 : newState.hashCode());
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
        final ChangeOfState other = (ChangeOfState) obj;
        if (newState == null) {
            if (other.newState != null) {
                return false;
            }
        } else if (!newState.equals(other.newState)) {
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
