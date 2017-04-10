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

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.ThreadLocalObjectTypeStack;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class PropertyValue extends BaseType {
    private static final long serialVersionUID = -2781078772918097137L;

    private final BACnetPropertyIdentifier propertyIdentifier; // 0

    private final UnsignedInteger propertyArrayIndex; // 1 optional

    private Encodable value; // 2

    private final UnsignedInteger priority; // 3 optional

    public PropertyValue(final BACnetPropertyIdentifier propertyIdentifier,
            final Encodable value) {
        this(propertyIdentifier, null, value, null);
    }

    public PropertyValue(final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex, final Encodable value,
            final UnsignedInteger priority) {
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.value = value;
        this.priority = priority;
    }

    public UnsignedInteger getPriority() {
        return priority;
    }

    public UnsignedInteger getPropertyArrayIndex() {
        return propertyArrayIndex;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    public Encodable getValue() {
        return value;
    }

    public void setValue(final Encodable value) {
        this.value = value;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, propertyIdentifier, 0);
        writeOptional(queue, propertyArrayIndex, 1);
        writeEncodable(queue, value, 2);
        writeOptional(queue, priority, 3);
    }

    public PropertyValue(final ByteQueue queue) throws BACnetException {
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 0);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 1);
        value = readEncodable(queue, ThreadLocalObjectTypeStack.get(),
                propertyIdentifier, propertyArrayIndex, 2);
        priority = readOptional(queue, UnsignedInteger.class, 3);
    }

    @Override
    public String toString() {
        return "PropertyValue(propertyIdentifier=" + propertyIdentifier
                + ", propertyArrayIndex=" + propertyArrayIndex + ", value="
                + value + ", priority=" + priority + ")";
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
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
        final PropertyValue other = (PropertyValue) obj;
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
