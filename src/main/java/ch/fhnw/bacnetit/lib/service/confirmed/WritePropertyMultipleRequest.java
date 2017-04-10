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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.WriteAccessSpecification;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WritePropertyMultipleRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 4702397545138383955L;

    public static final byte TYPE_ID = 16;

    private final SequenceOf<WriteAccessSpecification> listOfWriteAccessSpecifications;

    public WritePropertyMultipleRequest(
            final SequenceOf<WriteAccessSpecification> listOfWriteAccessSpecifications) {
        this.listOfWriteAccessSpecifications = listOfWriteAccessSpecifications;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, listOfWriteAccessSpecifications);
    }

    WritePropertyMultipleRequest(final ByteQueue queue) throws BACnetException {
        listOfWriteAccessSpecifications = readSequenceOf(queue,
                WriteAccessSpecification.class);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // BACnetObject obj;
    // for (WriteAccessSpecification spec : listOfWriteAccessSpecifications) {
    // obj = localDevice.getObject(spec.getObjectIdentifier());
    // if (obj == null)
    // throw createException(ErrorClass.property, ErrorCode.writeAccessDenied,
    // spec, null);
    //
    // for (PropertyValue pv : spec.getListOfProperties()) {
    // try {
    // if (localDevice.getEventHandler().checkAllowPropertyWrite(obj, pv)) {
    // obj.setProperty(pv);
    // localDevice.getEventHandler().propertyWritten(obj, pv);
    // }
    // else
    // throw createException(ErrorClass.property, ErrorCode.writeAccessDenied,
    // spec, pv);
    // }
    // catch (BACnetServiceException e) {
    // throw createException(e.getErrorClass(), e.getErrorCode(), spec, pv);
    // }
    // }
    // }
    //
    // return null;
    // }

    // private BACnetErrorException createException(ErrorClass errorClass,
    // ErrorCode errorCode,
    // WriteAccessSpecification spec, PropertyValue pv) {
    // if (pv == null)
    // pv = spec.getListOfProperties().get(1);
    // return new BACnetErrorException(new
    // WritePropertyMultipleError(getChoiceId(), new BACnetError(errorClass,
    // errorCode), new ObjectPropertyReference(spec.getObjectIdentifier(),
    // pv.getPropertyIdentifier(),
    // pv.getPropertyArrayIndex())));
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfWriteAccessSpecifications == null) ? 0
                : listOfWriteAccessSpecifications.hashCode());
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
        final WritePropertyMultipleRequest other = (WritePropertyMultipleRequest) obj;
        if (listOfWriteAccessSpecifications == null) {
            if (other.listOfWriteAccessSpecifications != null) {
                return false;
            }
        } else if (!listOfWriteAccessSpecifications
                .equals(other.listOfWriteAccessSpecifications)) {
            return false;
        }
        return true;
    }
}
