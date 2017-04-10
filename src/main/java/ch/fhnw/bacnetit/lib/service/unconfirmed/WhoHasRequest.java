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
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WhoHasRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = -3261764708955375488L;

    public static final byte TYPE_ID = 7;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Encodable.class);
        classes.add(Encodable.class);
        classes.add(BACnetObjectIdentifier.class);
        classes.add(CharacterString.class);
    }

    private final Limits limits;

    private final Choice object;

    public WhoHasRequest(final Limits limits,
            final BACnetObjectIdentifier identifier) {
        this.limits = limits;
        object = new Choice(2, identifier);
    }

    public WhoHasRequest(final Limits limits, final CharacterString name) {
        this.limits = limits;
        object = new Choice(3, name);
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) throws BACnetException {
    // // Check if we're in the device id range.
    // if (limits != null) {
    // int localId = localDevice.getConfiguration().getInstanceId();
    // if (localId < limits.getDeviceInstanceRangeLowLimit().intValue()
    // || localId > limits.getDeviceInstanceRangeHighLimit().intValue())
    // return;
    // }
    //
    // // Check if we have the thing being looking for.
    // BACnetObject result;
    // if (object.getContextId() == 2) {
    // ObjectIdentifier oid = (ObjectIdentifier) object.getDatum();
    // result = localDevice.getObject(oid);
    // }
    // else if (object.getContextId() == 3) {
    // String name = ((CharacterString) object.getDatum()).toString();
    // result = localDevice.getObject(name);
    // }
    // else
    // return;
    //
    // if (result != null) {
    // // Return the result in an i have message.
    // IHaveRequest response = new
    // IHaveRequest(localDevice.getConfiguration().getId(), result.getId(),
    // result.getRawObjectName());
    // localDevice.sendGlobalBroadcast(response);
    // }
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeOptional(queue, limits);
        write(queue, object);
    }

    public WhoHasRequest(final ByteQueue queue) throws BACnetException {
        final Limits l = new Limits(queue);
        limits = l.getDeviceInstanceRangeLowLimit() == null ? null : l;
        object = new Choice(queue, classes);
    }

    public static class Limits extends BaseType {
        private static final long serialVersionUID = -2736168226229323897L;

        private UnsignedInteger deviceInstanceRangeLowLimit;

        private UnsignedInteger deviceInstanceRangeHighLimit;

        @Override
        public void write(final ByteQueue queue) {
            write(queue, deviceInstanceRangeLowLimit, 0);
            write(queue, deviceInstanceRangeHighLimit, 1);
        }

        Limits(final ByteQueue queue) throws BACnetException {
            deviceInstanceRangeLowLimit = readOptional(queue,
                    UnsignedInteger.class, 0);
            deviceInstanceRangeHighLimit = readOptional(queue,
                    UnsignedInteger.class, 1);
        }

        public Limits(final UnsignedInteger deviceInstanceRangeLowLimit,
                final UnsignedInteger deviceInstanceRangeHighLimit) {
            if (deviceInstanceRangeLowLimit == null
                    || deviceInstanceRangeHighLimit == null) {
                throw new RuntimeException(
                        "Both the low and high limits must be set");
            }
            this.deviceInstanceRangeLowLimit = deviceInstanceRangeLowLimit;
            this.deviceInstanceRangeHighLimit = deviceInstanceRangeHighLimit;
        }

        public UnsignedInteger getDeviceInstanceRangeLowLimit() {
            return deviceInstanceRangeLowLimit;
        }

        public void setDeviceInstanceRangeLowLimit(
                final UnsignedInteger deviceInstanceRangeLowLimit) {
            this.deviceInstanceRangeLowLimit = deviceInstanceRangeLowLimit;
        }

        public UnsignedInteger getDeviceInstanceRangeHighLimit() {
            return deviceInstanceRangeHighLimit;
        }

        public void setDeviceInstanceRangeHighLimit(
                final UnsignedInteger deviceInstanceRangeHighLimit) {
            this.deviceInstanceRangeHighLimit = deviceInstanceRangeHighLimit;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((limits == null) ? 0 : limits.hashCode());
        result = PRIME * result + ((object == null) ? 0 : object.hashCode());
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
        final WhoHasRequest other = (WhoHasRequest) obj;
        if (limits == null) {
            if (other.limits != null) {
                return false;
            }
        } else if (!limits.equals(other.limits)) {
            return false;
        }
        if (object == null) {
            if (other.object != null) {
                return false;
            }
        } else if (!object.equals(other.object)) {
            return false;
        }
        return true;
    }
}
