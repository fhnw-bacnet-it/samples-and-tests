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
package ch.fhnw.bacnetit.misc.service.acknowledgment;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.ReadAccessResult;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class ReadPropertyConditionalAck extends AcknowledgementService {
    private static final long serialVersionUID = 2007538802044831429L;

    public static final byte TYPE_ID = 13;

    private final SequenceOf<ReadAccessResult> listOfReadAccessResults;

    public ReadPropertyConditionalAck(
            final SequenceOf<ReadAccessResult> listOfReadAccessResults) {
        this.listOfReadAccessResults = listOfReadAccessResults;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, listOfReadAccessResults);
    }

    ReadPropertyConditionalAck(final ByteQueue queue) throws BACnetException {
        listOfReadAccessResults = readSequenceOf(queue, ReadAccessResult.class);
    }

    public SequenceOf<ReadAccessResult> getListOfReadAccessResults() {
        return listOfReadAccessResults;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfReadAccessResults == null) ? 0
                : listOfReadAccessResults.hashCode());
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
        final ReadPropertyConditionalAck other = (ReadPropertyConditionalAck) obj;
        if (listOfReadAccessResults == null) {
            if (other.listOfReadAccessResults != null) {
                return false;
            }
        } else if (!listOfReadAccessResults
                .equals(other.listOfReadAccessResults)) {
            return false;
        }
        return true;
    }
}
