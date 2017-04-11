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
package ch.fhnw.bacnetit.lib.encoding.type.notificationParameter;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Double;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Primitive;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Extended extends NotificationParameters {
    private static final long serialVersionUID = 7986979868840729311L;

    public static final byte TYPE_ID = 9;

    private final UnsignedInteger vendorId;

    private final UnsignedInteger extendedEventType;

    private final SequenceOf<Parameter> parameters;

    public Extended(final UnsignedInteger vendorId,
            final UnsignedInteger extendedEventType,
            final SequenceOf<Parameter> parameters) {
        this.vendorId = vendorId;
        this.extendedEventType = extendedEventType;
        this.parameters = parameters;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, vendorId, 0);
        write(queue, extendedEventType, 1);
        write(queue, parameters, 2);
    }

    public Extended(final ByteQueue queue) throws BACnetException {
        vendorId = read(queue, UnsignedInteger.class, 0);
        extendedEventType = read(queue, UnsignedInteger.class, 1);
        parameters = readSequenceOf(queue, Parameter.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public UnsignedInteger getVendorId() {
        return vendorId;
    }

    public UnsignedInteger getExtendedEventType() {
        return extendedEventType;
    }

    public SequenceOf<Parameter> getParameters() {
        return parameters;
    }

    public static class Parameter extends BaseType {
        private static final long serialVersionUID = 9016759459458667665L;

        private Primitive primitive;

        private DeviceObjectPropertyReference reference;

        public Parameter(final Null primitive) {
            this.primitive = primitive;
        }

        public Parameter(final Real primitive) {
            this.primitive = primitive;
        }

        public Parameter(final UnsignedInteger primitive) {
            this.primitive = primitive;
        }

        public Parameter(final Boolean primitive) {
            this.primitive = primitive;
        }

        public Parameter(final Double primitive) {
            this.primitive = primitive;
        }

        public Parameter(final OctetString primitive) {
            this.primitive = primitive;
        }

        public Parameter(final BitString primitive) {
            this.primitive = primitive;
        }

        public Parameter(final Enumerated primitive) {
            this.primitive = primitive;
        }

        public Parameter(final DeviceObjectPropertyReference reference) {
            this.reference = reference;
        }

        @Override
        public void write(final ByteQueue queue) {
            if (primitive != null) {
                primitive.write(queue);
            } else {
                reference.write(queue, 0);
            }
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((extendedEventType == null) ? 0
                : extendedEventType.hashCode());
        result = PRIME * result
                + ((parameters == null) ? 0 : parameters.hashCode());
        result = PRIME * result
                + ((vendorId == null) ? 0 : vendorId.hashCode());
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
        final Extended other = (Extended) obj;
        if (extendedEventType == null) {
            if (other.extendedEventType != null) {
                return false;
            }
        } else if (!extendedEventType.equals(other.extendedEventType)) {
            return false;
        }
        if (parameters == null) {
            if (other.parameters != null) {
                return false;
            }
        } else if (!parameters.equals(other.parameters)) {
            return false;
        }
        if (vendorId == null) {
            if (other.vendorId != null) {
                return false;
            }
        } else if (!vendorId.equals(other.vendorId)) {
            return false;
        }
        return true;
    }
}
