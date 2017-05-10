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
package ch.fhnw.bacnetit.samplesandtests.api.deviceobjects;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PriorityArray;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PriorityValue;

public class BACnetProperty {
    private final BACnetObjectType objectType;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final Class<? extends Encodable> clazz;

    private final boolean sequence;

    private final boolean required;

    private final Encodable defaultValue;

    BACnetProperty(final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final Class<? extends Encodable> clazz, final boolean sequence,
            final boolean required, final Encodable defaultValue) {
        this.objectType = objectType;
        this.propertyIdentifier = propertyIdentifier;
        this.clazz = clazz;
        this.sequence = sequence;
        this.required = required;
        this.defaultValue = defaultValue;
    }

    public BACnetObjectType getObjectType() {
        return objectType;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    public Class<? extends Encodable> getClazz() {
        return clazz;
    }

    public boolean isSequence() {
        return sequence;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isOptional() {
        return !required;
    }

    public Encodable getDefaultValue() {
        return defaultValue;
    }

    public Class<? extends Encodable> getInnerType() {
        if (clazz == PriorityArray.class) {
            return PriorityValue.class;
        }
        return null;
    }
}
