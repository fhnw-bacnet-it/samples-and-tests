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
package ch.fhnw.bacnetit.samplesandtests.api.service.confirmed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ReadAccessSpecification;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
