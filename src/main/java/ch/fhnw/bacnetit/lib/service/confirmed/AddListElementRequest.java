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

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectFactory;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetProperty;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class AddListElementRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 6984164609601014611L;

    private static final InternalLogger LOG = InternalLoggerFactory
            .getInstance(AddListElementRequest.class);

    public static final byte TYPE_ID = 8;

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger propertyArrayIndex;

    private final SequenceOf<? extends Encodable> listOfElements;

    public AddListElementRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex,
            final SequenceOf<CharacterString> uriChars) {
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.listOfElements = uriChars;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    public SequenceOf<? extends Encodable> getListOfElements() {
        return this.listOfElements;
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // BACnetObject obj = localDevice.getObject(objectIdentifier);
    // if (obj == null)
    // throw createException(ErrorClass.property, ErrorCode.writeAccessDenied,
    // new UnsignedInteger(1));
    //
    // Encodable e;
    // try {
    // e = obj.getProperty(propertyIdentifier, propertyArrayIndex);
    // }
    // catch (BACnetServiceException ex) {
    // throw createException(ErrorClass.property, ErrorCode.invalidArrayIndex,
    // new UnsignedInteger(1));
    // }
    // if (!(e instanceof SequenceOf<?>))
    // throw createException(ErrorClass.property,
    // ErrorCode.propertyIsNotAnArray, new UnsignedInteger(1));
    //
    // SequenceOf<Encodable> propList = (SequenceOf<Encodable>) e;
    //
    // PropertyValue pv = new PropertyValue(propertyIdentifier,
    // propertyArrayIndex, listOfElements, null);
    // if (localDevice.getEventHandler().checkAllowPropertyWrite(obj, pv)) {
    // for (Encodable pr : listOfElements) {
    // if (!propList.contains(pr))
    // propList.add(pr);
    // }
    //
    // localDevice.getEventHandler().propertyWritten(obj, pv);
    // }
    // else
    // throw createException(ErrorClass.property, ErrorCode.writeAccessDenied,
    // new UnsignedInteger(1));
    //
    // return null;
    // }

    // private BACnetErrorException createException(ErrorClass errorClass,
    // ErrorCode errorCode,
    // UnsignedInteger firstFailedElementNumber) {
    // return new BACnetErrorException(new ChangeListError(getChoiceId(), new
    // BACnetError(errorClass, errorCode),
    // firstFailedElementNumber));
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeContextTag(queue, SERVICE_ID, true);
        writeContextTag(queue, TYPE_ID, true);
        write(queue, objectIdentifier, 0);
        write(queue, propertyIdentifier, 1);
        writeOptional(queue, propertyArrayIndex, 2);
        writeEncodable(queue, listOfElements, 3);
        writeContextTag(queue, TYPE_ID, false);
        writeContextTag(queue, SERVICE_ID, false);
    }

    public AddListElementRequest(final ByteQueue queue) throws BACnetException {
        // queue.pop();

        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);

        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 1);

        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 2);
        final BACnetProperty def = BACnetObjectFactory
                .getPropertyTypeDefinition(objectIdentifier.getObjectType(),
                        propertyIdentifier);

        listOfElements = readSequenceOf(queue, def.getClazz(), 3);
        // listOfElements = readEncodable(queue,
        // objectIdentifier.getObjectType(), propertyIdentifier,
        // propertyArrayIndex, 3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result
                + ((listOfElements == null) ? 0 : listOfElements.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
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
        final AddListElementRequest other = (AddListElementRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (listOfElements == null) {
            if (other.listOfElements != null) {
                return false;
            }
        } else if (!listOfElements.equals(other.listOfElements)) {
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
        return true;
    }
}
