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
package ch.fhnw.bacnetit.misc.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class ComplexEventType extends NotificationParameters {
    private static final long serialVersionUID = -5125532863892322124L;

    public static final byte TYPE_ID = 6;

    private final SequenceOf<PropertyValue> values;

    public ComplexEventType(final SequenceOf<PropertyValue> values) {
        this.values = values;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, values);
    }

    public ComplexEventType(final ByteQueue queue) throws BACnetException {
        values = readSequenceOf(queue, PropertyValue.class);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public SequenceOf<PropertyValue> getValues() {
        return values;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((values == null) ? 0 : values.hashCode());
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
        final ComplexEventType other = (ComplexEventType) obj;
        if (values == null) {
            if (other.values != null) {
                return false;
            }
        } else if (!values.equals(other.values)) {
            return false;
        }
        return true;
    }
}
