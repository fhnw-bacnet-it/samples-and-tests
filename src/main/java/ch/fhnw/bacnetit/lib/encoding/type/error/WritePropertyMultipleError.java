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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ObjectPropertyReference;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class WritePropertyMultipleError extends BaseError {
    private static final long serialVersionUID = 7893783677289456368L;

    private final ObjectPropertyReference firstFailedWriteAttempt;

    public WritePropertyMultipleError(final byte choice,
            final BACnetError error,
            final ObjectPropertyReference firstFailedWriteAttempt) {
        super(choice, error);
        this.firstFailedWriteAttempt = firstFailedWriteAttempt;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error, 0);
        firstFailedWriteAttempt.write(queue, 1);
    }

    WritePropertyMultipleError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        super(choice, queue, 0);
        firstFailedWriteAttempt = read(queue, ObjectPropertyReference.class, 1);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + ((firstFailedWriteAttempt == null) ? 0
                : firstFailedWriteAttempt.hashCode());
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
        final WritePropertyMultipleError other = (WritePropertyMultipleError) obj;
        if (firstFailedWriteAttempt == null) {
            if (other.firstFailedWriteAttempt != null) {
                return false;
            }
        } else if (!firstFailedWriteAttempt
                .equals(other.firstFailedWriteAttempt)) {
            return false;
        }
        return true;
    }
}
