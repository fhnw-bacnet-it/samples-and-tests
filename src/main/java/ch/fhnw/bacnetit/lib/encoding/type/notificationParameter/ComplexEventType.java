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
package ch.fhnw.bacnetit.lib.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
