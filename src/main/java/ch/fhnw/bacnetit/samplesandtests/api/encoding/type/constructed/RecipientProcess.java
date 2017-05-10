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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class RecipientProcess extends BaseType {
    private static final long serialVersionUID = -3615223495651827907L;

    private final Recipient recipient;

    private final UnsignedInteger processIdentifier;

    public RecipientProcess(final Recipient recipient,
            final UnsignedInteger processIdentifier) {
        this.recipient = recipient;
        this.processIdentifier = processIdentifier;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, recipient, 0);
        write(queue, processIdentifier, 1);
    }

    public RecipientProcess(final ByteQueue queue) throws BACnetException {
        recipient = read(queue, Recipient.class, 0);
        processIdentifier = read(queue, UnsignedInteger.class, 1);
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public UnsignedInteger getProcessIdentifier() {
        return processIdentifier;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((processIdentifier == null) ? 0
                : processIdentifier.hashCode());
        result = PRIME * result
                + ((recipient == null) ? 0 : recipient.hashCode());
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
        final RecipientProcess other = (RecipientProcess) obj;
        if (processIdentifier == null) {
            if (other.processIdentifier != null) {
                return false;
            }
        } else if (!processIdentifier.equals(other.processIdentifier)) {
            return false;
        }
        if (recipient == null) {
            if (other.recipient != null) {
                return false;
            }
        } else if (!recipient.equals(other.recipient)) {
            return false;
        }
        return true;
    }
}
