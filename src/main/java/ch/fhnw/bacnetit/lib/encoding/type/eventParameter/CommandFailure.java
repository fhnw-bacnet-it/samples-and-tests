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
package ch.fhnw.bacnetit.lib.encoding.type.eventParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class CommandFailure extends EventParameter {
    private static final long serialVersionUID = 6903279389504770474L;

    public static final byte TYPE_ID = 3;

    private final UnsignedInteger timeDelay;

    private final DeviceObjectPropertyReference feedbackPropertyReference;

    public CommandFailure(final UnsignedInteger timeDelay,
            final DeviceObjectPropertyReference feedbackPropertyReference) {
        this.timeDelay = timeDelay;
        this.feedbackPropertyReference = feedbackPropertyReference;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, feedbackPropertyReference, 1);
    }

    public CommandFailure(final ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        feedbackPropertyReference = read(queue,
                DeviceObjectPropertyReference.class, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getTimeDelay() {
        return timeDelay;
    }

    public DeviceObjectPropertyReference getFeedbackPropertyReference() {
        return feedbackPropertyReference;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((feedbackPropertyReference == null) ? 0
                : feedbackPropertyReference.hashCode());
        result = PRIME * result
                + ((timeDelay == null) ? 0 : timeDelay.hashCode());
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
        if (feedbackPropertyReference == null) {
            if (other.feedbackPropertyReference != null) {
                return false;
            }
        } else if (!feedbackPropertyReference
                .equals(other.feedbackPropertyReference)) {
            return false;
        }
        if (timeDelay == null) {
            if (other.timeDelay != null) {
                return false;
            }
        } else if (!timeDelay.equals(other.timeDelay)) {
            return false;
        }
        return true;
    }
}
