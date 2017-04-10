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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WritePropertyRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -6047767959151824679L;

    public static final byte TYPE_ID = 15;

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger propertyArrayIndex;

    private final Encodable propertyValue;

    private final UnsignedInteger priority;

    public WritePropertyRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex,
            final Encodable propertyValue, final UnsignedInteger priority) {
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.propertyValue = propertyValue;
        this.priority = priority;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    public BACnetPropertyIdentifier getPropertyIdentifier() {
        return propertyIdentifier;
    }

    public Encodable getPropertyValue() {
        return propertyValue;
    }

    @Override
    public void write(final ByteQueue queue) {
        writeContextTag(queue, SERVICE_ID, true);
        writeContextTag(queue, TYPE_ID, true);
        write(queue, objectIdentifier, 0);
        write(queue, propertyIdentifier, 1);
        writeOptional(queue, propertyArrayIndex, 2);
        writeEncodable(queue, propertyValue, 3);
        writeOptional(queue, priority, 4);
        writeContextTag(queue, TYPE_ID, false);
        writeContextTag(queue, SERVICE_ID, false);
    }

    public WritePropertyRequest(final ByteQueue queue) throws BACnetException {
        queue.pop();
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 1);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 2);
        propertyValue = readEncodable(queue, objectIdentifier.getObjectType(),
                propertyIdentifier, propertyArrayIndex, 3);
        priority = readOptional(queue, UnsignedInteger.class, 4);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetErrorException {
    // BACnetObject obj = localDevice.getObject(objectIdentifier);
    // if (obj == null)
    // throw new BACnetErrorException(getChoiceId(), ErrorClass.object,
    // ErrorCode.unknownObject);
    //
    // PropertyValue pv = new PropertyValue(propertyIdentifier,
    // propertyArrayIndex, propertyValue, priority);
    // try {
    // if (localDevice.getEventHandler().checkAllowPropertyWrite(obj, pv)) {
    // obj.setProperty(pv);
    // localDevice.getEventHandler().propertyWritten(obj, pv);
    // }
    // else
    // throw new BACnetServiceException(ErrorClass.property,
    // ErrorCode.writeAccessDenied);
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(getChoiceId(), e);
    // }
    //
    // return null;
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result
                + ((priority == null) ? 0 : priority.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
        result = PRIME * result
                + ((propertyValue == null) ? 0 : propertyValue.hashCode());
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
        final WritePropertyRequest other = (WritePropertyRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (priority == null) {
            if (other.priority != null) {
                return false;
            }
        } else if (!priority.equals(other.priority)) {
            return false;
        }
        if (propertyArrayIndex == null) {
            if (other.propertyArrayIndex != null) {
                return false;
            }
        } else if (!propertyArrayIndex.equals(other.propertyArrayIndex)) {
            return false;
        }
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null) {
                return false;
            }
        } else if (!propertyIdentifier.equals(other.propertyIdentifier)) {
            return false;
        }
        if (propertyValue == null) {
            if (other.propertyValue != null) {
                return false;
            }
        } else if (!propertyValue.equals(other.propertyValue)) {
            return false;
        }
        return true;
    }
}
