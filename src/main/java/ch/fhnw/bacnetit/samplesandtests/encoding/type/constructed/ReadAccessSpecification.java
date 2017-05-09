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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

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
