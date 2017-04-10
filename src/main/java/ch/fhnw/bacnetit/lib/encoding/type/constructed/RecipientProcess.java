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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
