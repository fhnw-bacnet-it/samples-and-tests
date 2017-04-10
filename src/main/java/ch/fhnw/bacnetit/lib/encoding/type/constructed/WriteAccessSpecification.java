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
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.ThreadLocalObjectTypeStack;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WriteAccessSpecification extends BaseType {
    private static final long serialVersionUID = -676251352183146270L;

    private final BACnetObjectIdentifier objectIdentifier;

    private final SequenceOf<PropertyValue> listOfProperties;

    public WriteAccessSpecification(
            final BACnetObjectIdentifier objectIdentifier,
            final SequenceOf<PropertyValue> listOfProperties) {
        this.objectIdentifier = objectIdentifier;
        this.listOfProperties = listOfProperties;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        write(queue, listOfProperties, 1);
    }

    public WriteAccessSpecification(final ByteQueue queue)
            throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        try {
            ThreadLocalObjectTypeStack.set(objectIdentifier.getObjectType());
            listOfProperties = readSequenceOf(queue, PropertyValue.class, 1);
        } finally {
            ThreadLocalObjectTypeStack.remove();
        }
    }

    public SequenceOf<PropertyValue> getListOfProperties() {
        return listOfProperties;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfProperties == null) ? 0
                : listOfProperties.hashCode());
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
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
        final WriteAccessSpecification other = (WriteAccessSpecification) obj;
        if (listOfProperties == null) {
            if (other.listOfProperties != null) {
                return false;
            }
        } else if (!listOfProperties.equals(other.listOfProperties)) {
            return false;
        }
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        return true;
    }
}
