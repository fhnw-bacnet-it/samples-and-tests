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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ObjectPropertyValue extends BaseType {
    private static final long serialVersionUID = -3809960734849615419L;

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger propertyArrayIndex;

    private final Encodable value;

    private final UnsignedInteger priority;

    public ObjectPropertyValue(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex, final Encodable value,
            final UnsignedInteger priority) {
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.value = value;
        this.priority = priority;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        write(queue, propertyIdentifier, 1);
        writeOptional(queue, propertyArrayIndex, 2);
        write(queue, value, 3);
        writeOptional(queue, priority, 4);
    }

    public ObjectPropertyValue(final ByteQueue queue) throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 1);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 2);
        value = readEncodable(queue, objectIdentifier.getObjectType(),
                propertyIdentifier, propertyArrayIndex, 3);
        priority = readOptional(queue, UnsignedInteger.class, 4);
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

    public Encodable getValue() {
        return value;
    }

    public UnsignedInteger getPriority() {
        return priority;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result
                + ((priority == null) ? 0 : priority.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
        result = PRIME * result + ((value == null) ? 0 : value.hashCode());
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
        final ObjectPropertyValue other = (ObjectPropertyValue) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
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
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
