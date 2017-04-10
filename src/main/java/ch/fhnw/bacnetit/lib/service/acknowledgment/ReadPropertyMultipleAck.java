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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ReadAccessResult;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ReadPropertyMultipleAck extends AcknowledgementService {
    private static final long serialVersionUID = 5389362813511389512L;

    public static final byte TYPE_ID = 14;

    private final SequenceOf<ReadAccessResult> listOfReadAccessResults;

    public ReadPropertyMultipleAck(
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

    ReadPropertyMultipleAck(final ByteQueue queue) throws BACnetException {
        listOfReadAccessResults = readSequenceOf(queue, ReadAccessResult.class);
    }

    @Override
    public String toString() {
        return "ReadPropertyMultipleAck(" + listOfReadAccessResults + ")";
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
        final ReadPropertyMultipleAck other = (ReadPropertyMultipleAck) obj;
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
