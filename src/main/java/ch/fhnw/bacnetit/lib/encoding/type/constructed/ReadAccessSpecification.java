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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ReadAccessSpecification extends BaseType {
    private static final long serialVersionUID = -9098153421380700655L;

    private final BACnetObjectIdentifier objectIdentifier;

    private final SequenceOf<PropertyReference> listOfPropertyReferences;

    public ReadAccessSpecification(
            final BACnetObjectIdentifier objectIdentifier,
            final SequenceOf<PropertyReference> listOfPropertyReferences) {
        this.objectIdentifier = objectIdentifier;
        this.listOfPropertyReferences = listOfPropertyReferences;
    }

    public ReadAccessSpecification(
            final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier pid) {
        this.objectIdentifier = objectIdentifier;
        final List<PropertyReference> refs = new ArrayList<PropertyReference>(
                1);
        refs.add(new PropertyReference(pid, null));
        this.listOfPropertyReferences = new SequenceOf<PropertyReference>(refs);
    }

    public SequenceOf<PropertyReference> getListOfPropertyReferences() {
        return listOfPropertyReferences;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        write(queue, listOfPropertyReferences, 1);
    }

    public ReadAccessSpecification(final ByteQueue queue)
            throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        listOfPropertyReferences = readSequenceOf(queue,
                PropertyReference.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfPropertyReferences == null) ? 0
                : listOfPropertyReferences.hashCode());
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
        final ReadAccessSpecification other = (ReadAccessSpecification) obj;
        if (listOfPropertyReferences == null) {
            if (other.listOfPropertyReferences != null) {
                return false;
            }
        } else if (!listOfPropertyReferences
                .equals(other.listOfPropertyReferences)) {
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

    @Override
    public String toString() {
        return "ReadAccessSpecification [objectIdentifier=" + objectIdentifier
                + ", listOfPropertyReferences=" + listOfPropertyReferences
                + "]";
    }
}
