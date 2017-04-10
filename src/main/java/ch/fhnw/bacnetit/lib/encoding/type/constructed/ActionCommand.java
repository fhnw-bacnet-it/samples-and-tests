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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ActionCommand extends BaseType {
    private static final long serialVersionUID = 6627972998457216719L;

    private final BACnetObjectIdentifier deviceIdentifier;

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger propertyArrayIndex;

    private final Encodable propertyValue;

    private final UnsignedInteger priority;

    private final UnsignedInteger postDelay;

    private final Boolean quitOnFailure;

    private final Boolean writeSuccessful;

    public ActionCommand(final BACnetObjectIdentifier deviceIdentifier,
            final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex,
            final Encodable propertyValue, final UnsignedInteger priority,
            final UnsignedInteger postDelay, final Boolean quitOnFailure,
            final Boolean writeSuccessful) {
        this.deviceIdentifier = deviceIdentifier;
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.propertyValue = propertyValue;
        this.priority = priority;
        this.postDelay = postDelay;
        this.quitOnFailure = quitOnFailure;
        this.writeSuccessful = writeSuccessful;
    }

    @Override
    public void write(final ByteQueue queue) {
        writeOptional(queue, deviceIdentifier, 0);
        write(queue, objectIdentifier, 1);
        write(queue, propertyIdentifier, 2);
        writeOptional(queue, propertyArrayIndex, 3);
        write(queue, propertyValue, 4);
        writeOptional(queue, priority, 5);
        writeOptional(queue, postDelay, 6);
        write(queue, quitOnFailure, 7);
        write(queue, writeSuccessful, 8);
    }

    public ActionCommand(final ByteQueue queue) throws BACnetException {
        deviceIdentifier = readOptional(queue, BACnetObjectIdentifier.class, 0);
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 1);
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 2);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 3);
        propertyValue = readEncodable(queue, objectIdentifier.getObjectType(),
                propertyIdentifier, propertyArrayIndex, 4);
        priority = readOptional(queue, UnsignedInteger.class, 5);
        postDelay = readOptional(queue, UnsignedInteger.class, 6);
        quitOnFailure = read(queue, Boolean.class, 7);
        writeSuccessful = read(queue, Boolean.class, 8);
    }

    public BACnetObjectIdentifier getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    public UnsignedInteger getPropertyArrayIndex() {
        return propertyArrayIndex;
    }

    public Encodable getPropertyValue() {
        return propertyValue;
    }

    public UnsignedInteger getPriority() {
        return priority;
    }

    public UnsignedInteger getPostDelay() {
        return postDelay;
    }

    public Boolean getQuitOnFailure() {
        return quitOnFailure;
    }

    public Boolean getWriteSuccessful() {
        return writeSuccessful;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((deviceIdentifier == null) ? 0
                : deviceIdentifier.hashCode());
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result
                + ((postDelay == null) ? 0 : postDelay.hashCode());
        result = PRIME * result
                + ((priority == null) ? 0 : priority.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
        result = PRIME * result
                + ((propertyValue == null) ? 0 : propertyValue.hashCode());
        result = PRIME * result
                + ((quitOnFailure == null) ? 0 : quitOnFailure.hashCode());
        result = PRIME * result
                + ((writeSuccessful == null) ? 0 : writeSuccessful.hashCode());
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
        final ActionCommand other = (ActionCommand) obj;
        if (deviceIdentifier == null) {
            if (other.deviceIdentifier != null) {
                return false;
            }
        } else if (!deviceIdentifier.equals(other.deviceIdentifier)) {
            return false;
        }
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (postDelay == null) {
            if (other.postDelay != null) {
                return false;
            }
        } else if (!postDelay.equals(other.postDelay)) {
            return false;
        }
        if (priority == null) {
            if (other.priority != null) {
                return false;
            }
        } else if (!priority.equals(other.priority)) {
            return false;
        }
        if (propertyArrayIndex == null) {
            if (other.propertyArrayIndex != null) {
                return false;
            }
        } else if (!propertyArrayIndex.equals(other.propertyArrayIndex)) {
            return false;
        }
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null) {
                return false;
            }
        } else if (!propertyIdentifier.equals(other.propertyIdentifier)) {
            return false;
        }
        if (propertyValue == null) {
            if (other.propertyValue != null) {
                return false;
            }
        } else if (!propertyValue.equals(other.propertyValue)) {
            return false;
        }
        if (quitOnFailure == null) {
            if (other.quitOnFailure != null) {
                return false;
            }
        } else if (!quitOnFailure.equals(other.quitOnFailure)) {
            return false;
        }
        if (writeSuccessful == null) {
            if (other.writeSuccessful != null) {
                return false;
            }
        } else if (!writeSuccessful.equals(other.writeSuccessful)) {
            return false;
        }
        return true;
    }
}
