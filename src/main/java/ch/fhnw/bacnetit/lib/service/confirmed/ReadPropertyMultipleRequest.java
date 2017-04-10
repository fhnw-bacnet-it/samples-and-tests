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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ReadAccessSpecification;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ReadPropertyMultipleRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 1994873785772969841L;

    public static final byte TYPE_ID = 14;

    private final SequenceOf<ReadAccessSpecification> listOfReadAccessSpecs;

    public ReadPropertyMultipleRequest(
            final SequenceOf<ReadAccessSpecification> listOfReadAccessSpecs) {
        this.listOfReadAccessSpecs = listOfReadAccessSpecs;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, listOfReadAccessSpecs);
    }

    ReadPropertyMultipleRequest(final ByteQueue queue) throws BACnetException {
        listOfReadAccessSpecs = readSequenceOf(queue,
                ReadAccessSpecification.class);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // BACnetObject obj;
    // ObjectIdentifier oid;
    // List<ReadAccessResult> readAccessResults = new
    // ArrayList<ReadAccessResult>();
    // List<Result> results;
    //
    // try {
    // for (ReadAccessSpecification req : listOfReadAccessSpecs) {
    // results = new ArrayList<Result>();
    // oid = req.getObjectIdentifier();
    // obj = localDevice.getObjectRequired(oid);
    //
    // for (PropertyReference propRef : req.getListOfPropertyReferences())
    // addProperty(obj, results, propRef.getPropertyIdentifier(),
    // propRef.getPropertyArrayIndex());
    //
    // readAccessResults.add(new ReadAccessResult(oid, new
    // SequenceOf<Result>(results)));
    // }
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(getChoiceId(), e);
    // }
    //
    // return new ReadPropertyMultipleAck(new
    // SequenceOf<ReadAccessResult>(readAccessResults));
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfReadAccessSpecs == null) ? 0
                : listOfReadAccessSpecs.hashCode());
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
        final ReadPropertyMultipleRequest other = (ReadPropertyMultipleRequest) obj;
        if (listOfReadAccessSpecs == null) {
            if (other.listOfReadAccessSpecs != null) {
                return false;
            }
        } else if (!listOfReadAccessSpecs.equals(other.listOfReadAccessSpecs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReadPropertyMultipleRequest [listOfReadAccessSpecs="
                + listOfReadAccessSpecs + "]";
    }

    // private void addProperty(BACnetObject obj, List<Result> results,
    // PropertyIdentifier pid, UnsignedInteger pin) {
    // if (pid.intValue() == PropertyIdentifier.all.intValue()) {
    // for (PropertyTypeDefinition def :
    // ObjectProperties.getPropertyTypeDefinitions(obj.getId().getObjectType()))
    // addProperty(obj, results, def.getPropertyIdentifier(), pin);
    // }
    // else if (pid.intValue() == PropertyIdentifier.required.intValue()) {
    // for (PropertyTypeDefinition def :
    // ObjectProperties.getRequiredPropertyTypeDefinitions(obj.getId()
    // .getObjectType()))
    // addProperty(obj, results, def.getPropertyIdentifier(), pin);
    // }
    // else if (pid.intValue() == PropertyIdentifier.optional.intValue()) {
    // for (PropertyTypeDefinition def :
    // ObjectProperties.getOptionalPropertyTypeDefinitions(obj.getId()
    // .getObjectType()))
    // addProperty(obj, results, def.getPropertyIdentifier(), pin);
    // }
    // else {
    // // Get the specified property.
    // try {
    // results.add(new Result(pid, pin, obj.getPropertyRequired(pid, pin)));
    // }
    // catch (BACnetServiceException e) {
    // results.add(new Result(pid, pin, new BACnetError(e.getErrorClass(),
    // e.getErrorCode())));
    // }
    // }
    // }
}
