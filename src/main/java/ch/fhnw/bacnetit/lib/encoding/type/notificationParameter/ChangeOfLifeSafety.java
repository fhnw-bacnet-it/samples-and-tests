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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyMode;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyOperation;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyState;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ChangeOfLifeSafety extends NotificationParameters {
    private static final long serialVersionUID = -3145779272869271053L;

    public static final byte TYPE_ID = 8;

    private final LifeSafetyState newState;

    private final LifeSafetyMode newMode;

    private final StatusFlags statusFlags;

    private final LifeSafetyOperation operationExpected;

    public ChangeOfLifeSafety(final LifeSafetyState newState,
            final LifeSafetyMode newMode, final StatusFlags statusFlags,
            final LifeSafetyOperation operationExpected) {
        this.newState = newState;
        this.newMode = newMode;
        this.statusFlags = statusFlags;
        this.operationExpected = operationExpected;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, newState, 0);
        write(queue, statusFlags, 1);
        write(queue, newMode, 2);
        write(queue, operationExpected, 3);
    }

    public ChangeOfLifeSafety(final ByteQueue queue) throws BACnetException {
        newState = read(queue, LifeSafetyState.class, 0);
        newMode = read(queue, LifeSafetyMode.class, 1);
        statusFlags = read(queue, StatusFlags.class, 2);
        operationExpected = read(queue, LifeSafetyOperation.class, 3);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public LifeSafetyState getNewState() {
        return newState;
    }

    public LifeSafetyMode getNewMode() {
        return newMode;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public LifeSafetyOperation getOperationExpected() {
        return operationExpected;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((newMode == null) ? 0 : newMode.hashCode());
        result = PRIME * result
                + ((newState == null) ? 0 : newState.hashCode());
        result = PRIME * result + ((operationExpected == null) ? 0
                : operationExpected.hashCode());
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
        final ChangeOfLifeSafety other = (ChangeOfLifeSafety) obj;
        if (newMode == null) {
            if (other.newMode != null) {
                return false;
            }
        } else if (!newMode.equals(other.newMode)) {
            return false;
        }
        if (newState == null) {
            if (other.newState != null) {
                return false;
            }
        } else if (!newState.equals(other.newState)) {
            return false;
        }
        if (operationExpected == null) {
            if (other.operationExpected != null) {
                return false;
            }
        } else if (!operationExpected.equals(other.operationExpected)) {
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
