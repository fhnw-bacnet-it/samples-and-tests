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
package ch.fhnw.bacnetit.lib.deviceobjects;

import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PriorityArray;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PriorityValue;

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
