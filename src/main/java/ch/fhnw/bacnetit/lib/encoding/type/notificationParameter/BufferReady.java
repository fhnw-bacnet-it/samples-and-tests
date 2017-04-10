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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BufferReady extends NotificationParameters {
    private static final long serialVersionUID = 1210370718867995350L;

    public static final byte TYPE_ID = 10;

    private final DeviceObjectPropertyReference bufferProperty;

    private final UnsignedInteger previousNotification;

    private final UnsignedInteger currentNotification;

    public BufferReady(final DeviceObjectPropertyReference bufferProperty,
            final UnsignedInteger previousNotification,
            final UnsignedInteger currentNotification) {
        this.bufferProperty = bufferProperty;
        this.previousNotification = previousNotification;
        this.currentNotification = currentNotification;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, bufferProperty, 0);
        write(queue, previousNotification, 1);
        write(queue, currentNotification, 2);
    }

    public BufferReady(final ByteQueue queue) throws BACnetException {
        bufferProperty = read(queue, DeviceObjectPropertyReference.class, 0);
        previousNotification = read(queue, UnsignedInteger.class, 1);
        currentNotification = read(queue, UnsignedInteger.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public DeviceObjectPropertyReference getBufferProperty() {
        return bufferProperty;
    }

    public UnsignedInteger getPreviousNotification() {
        return previousNotification;
    }

    public UnsignedInteger getCurrentNotification() {
        return currentNotification;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((bufferProperty == null) ? 0 : bufferProperty.hashCode());
        result = PRIME * result + ((currentNotification == null) ? 0
                : currentNotification.hashCode());
        result = PRIME * result + ((previousNotification == null) ? 0
                : previousNotification.hashCode());
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
        final BufferReady other = (BufferReady) obj;
        if (bufferProperty == null) {
            if (other.bufferProperty != null) {
                return false;
            }
        } else if (!bufferProperty.equals(other.bufferProperty)) {
            return false;
        }
        if (currentNotification == null) {
            if (other.currentNotification != null) {
                return false;
            }
        } else if (!currentNotification.equals(other.currentNotification)) {
            return false;
        }
        if (previousNotification == null) {
            if (other.previousNotification != null) {
                return false;
            }
        } else if (!previousNotification.equals(other.previousNotification)) {
            return false;
        }
        return true;
    }
}
