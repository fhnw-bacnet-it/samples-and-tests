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
package ch.fhnw.bacnetit.lib.encoding.type.error;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ChangeListError extends BaseError {
    private static final long serialVersionUID = -466623385798604884L;

    private final UnsignedInteger firstFailedElementNumber;

    public ChangeListError(final byte choice, final BACnetError error,
            final UnsignedInteger firstFailedElementNumber) {
        super(choice, error);
        this.firstFailedElementNumber = firstFailedElementNumber;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error, 0);
        write(queue, firstFailedElementNumber, 1);
    }

    ChangeListError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        super(choice, queue, 0);
        firstFailedElementNumber = read(queue, UnsignedInteger.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + ((firstFailedElementNumber == null) ? 0
                : firstFailedElementNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChangeListError other = (ChangeListError) obj;
        if (firstFailedElementNumber == null) {
            if (other.firstFailedElementNumber != null) {
                return false;
            }
        } else if (!firstFailedElementNumber
                .equals(other.firstFailedElementNumber)) {
            return false;
        }
        return true;
    }
}
