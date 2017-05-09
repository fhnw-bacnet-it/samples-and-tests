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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive;

import java.io.Serializable;
import java.util.LinkedList;

import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.Encodable;

public class ObjectConfig implements Serializable {
    private static final long serialVersionUID = -8627210334469107678L;
    public final static int ID = 0, VALUE = 1;
    public final BACnetObjectType type;
    public final int instanceNr;
    private final LinkedList<Encodable[]> properties = new LinkedList<>();

    public ObjectConfig(final int instanceNr, final BACnetObjectType type) {
        this.instanceNr = instanceNr;
        this.type = type;
    }

    public void addProperty(final BACnetPropertyIdentifier identifier,
            final Encodable value) {
        properties.add(new Encodable[] { identifier, value });
    }

    /**
     * Returns a copied list of properties.
     *
     * @return List of properties
     */
    public LinkedList<Encodable[]> getProperties() {
        return new LinkedList<>(properties);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                "\tObject, Type: " + type + " number " + instanceNr + "\n");
        for (final Object[] property : properties) {
            builder.append(
                    "ID: " + property[0] + " value: " + property[1] + "\n");
        }
        return builder.toString();
    }

}
