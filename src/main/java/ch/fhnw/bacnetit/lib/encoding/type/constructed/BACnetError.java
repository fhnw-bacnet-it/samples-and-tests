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
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BACnetError extends BaseType {
    private static final long serialVersionUID = -2894184233450540796L;

    private final ErrorClass errorClass;

    private final ErrorCode errorCode;

    public BACnetError(final ErrorClass errorClass, final ErrorCode errorCode) {
        this.errorClass = errorClass;
        this.errorCode = errorCode;
    }

    public BACnetError(final BACnetServiceException e) {
        this.errorClass = e.getErrorClass();
        this.errorCode = e.getErrorCode();
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, errorClass);
        write(queue, errorCode);
    }

    public BACnetError(final ByteQueue queue) throws BACnetException {
        errorClass = read(queue, ErrorClass.class);
        errorCode = read(queue, ErrorCode.class);
    }

    public ErrorClass getErrorClass() {
        return errorClass;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public boolean equals(final ErrorClass errorClass,
            final ErrorCode errorCode) {
        return this.errorClass.equals(errorClass)
                && this.errorCode.equals(errorCode);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((errorClass == null) ? 0 : errorClass.hashCode());
        result = PRIME * result
                + ((errorCode == null) ? 0 : errorCode.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "errorClass=" + errorClass + ", errorCode=" + errorCode;
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
        final BACnetError other = (BACnetError) obj;
        if (errorClass == null) {
            if (other.errorClass != null) {
                return false;
            }
        } else if (!errorClass.equals(other.errorClass)) {
            return false;
        }
        if (errorCode == null) {
            if (other.errorCode != null) {
                return false;
            }
        } else if (!errorCode.equals(other.errorCode)) {
            return false;
        }
        return true;
    }
}
