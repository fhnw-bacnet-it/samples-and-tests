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
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BaseError extends BaseType {
    private static final long serialVersionUID = 8363160647986011176L;

    public static BaseError createBaseError(final ByteQueue queue)
            throws BACnetException {
        final byte choice = queue.pop();

        switch (choice) {
        case 8:
        case 9:
            return new ChangeListError(choice, queue);
        case 10:
            return new CreateObjectError(choice, queue);
        case 16:
            return new WritePropertyMultipleError(choice, queue);
        case 18:
            return new ConfirmedPrivateTransferError(choice, queue);
        case 22:
            return new VTCloseError(choice, queue);
        }
        return new BaseError(choice, queue);
    }

    protected byte choice;

    protected BACnetError error;

    public BaseError(final byte choice, final BACnetError error) {
        this.choice = choice;
        this.error = error;
    }

    @Override
    public void write(final ByteQueue queue) {
        queue.push(choice);
        write(queue, error);
    }

    public BaseError(final byte choice, final ByteQueue queue)
            throws BACnetException {
        this.choice = choice;
        error = read(queue, BACnetError.class);
    }

    public BaseError(final byte choice, final ByteQueue queue,
            final int contextId) throws BACnetException {
        this.choice = choice;
        error = read(queue, BACnetError.class, contextId);
    }

    @Override
    public String toString() {
        return "choice=" + (choice & 0xff) + ", " + error;
    }

    public BACnetError getError() {
        return error;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + choice;
        result = PRIME * result + ((error == null) ? 0 : error.hashCode());
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
        final BaseError other = (BaseError) obj;
        if (choice != other.choice) {
            return false;
        }
        if (error == null) {
            if (other.error != null) {
                return false;
            }
        } else if (!error.equals(other.error)) {
            return false;
        }
        return true;
    }
}
