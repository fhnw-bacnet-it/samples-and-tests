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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.ThreadLocalObjectTypeStack;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class CreateObjectRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -610206284148696878L;

    public static final byte TYPE_ID = 10;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(BACnetObjectType.class);
        classes.add(BACnetObjectIdentifier.class);
    }

    private final Choice objectSpecifier;

    private final SequenceOf<PropertyValue> listOfInitialValues;

    public CreateObjectRequest(final BACnetObjectType objectType,
            final SequenceOf<PropertyValue> listOfInitialValues) {
        objectSpecifier = new Choice(0, objectType);
        this.listOfInitialValues = listOfInitialValues;
    }

    public CreateObjectRequest(final BACnetObjectIdentifier objectIdentifier,
            final SequenceOf<PropertyValue> listOfInitialValues) {
        objectSpecifier = new Choice(1, objectIdentifier);
        this.listOfInitialValues = listOfInitialValues;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetErrorException {
    // final ObjectIdentifier id;
    // if (objectSpecifier.getContextId() == 0) {
    // ObjectType type = (ObjectType) objectSpecifier.getDatum();
    // id = localDevice.getNextInstanceObjectIdentifier(type);
    // }
    // else
    // id = (ObjectIdentifier) objectSpecifier.getDatum();
    //
    // BACnetObject obj = new BACnetObject(localDevice, id);
    //
    // if (listOfInitialValues != null) {
    // for (int i = 0; i < listOfInitialValues.getCount(); i++) {
    // PropertyValue pv = listOfInitialValues.get(i + 1);
    // try {
    // obj.setProperty(pv);
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(new CreateObjectError(getChoiceId(), e,
    // new UnsignedInteger(i + 1)));
    // }
    // }
    // }
    //
    // try {
    // localDevice.addObject(obj);
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(new CreateObjectError(getChoiceId(), e,
    // null));
    // }
    //
    // // Return a create object ack.
    // return new CreateObjectAck(id);
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectSpecifier, 0);
        writeOptional(queue, listOfInitialValues, 1);
    }

    CreateObjectRequest(final ByteQueue queue) throws BACnetException {
        popStart(queue, 0);
        objectSpecifier = new Choice(queue, classes);
        popEnd(queue, 0);

        try {
            if (objectSpecifier.getContextId() == 0) {
                ThreadLocalObjectTypeStack
                        .set((BACnetObjectType) objectSpecifier.getDatum());
            } else {
                ThreadLocalObjectTypeStack.set(
                        ((BACnetObjectIdentifier) objectSpecifier.getDatum())
                                .getObjectType());
            }
            listOfInitialValues = readOptionalSequenceOf(queue,
                    PropertyValue.class, 1);
        } finally {
            ThreadLocalObjectTypeStack.remove();
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfInitialValues == null) ? 0
                : listOfInitialValues.hashCode());
        result = PRIME * result
                + ((objectSpecifier == null) ? 0 : objectSpecifier.hashCode());
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
        final CreateObjectRequest other = (CreateObjectRequest) obj;
        if (listOfInitialValues == null) {
            if (other.listOfInitialValues != null) {
                return false;
            }
        } else if (!listOfInitialValues.equals(other.listOfInitialValues)) {
            return false;
        }
        if (objectSpecifier == null) {
            if (other.objectSpecifier != null) {
                return false;
            }
        } else if (!objectSpecifier.equals(other.objectSpecifier)) {
            return false;
        }
        return true;
    }
}
