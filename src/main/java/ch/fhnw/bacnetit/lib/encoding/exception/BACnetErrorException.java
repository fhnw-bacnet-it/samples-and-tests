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
package ch.fhnw.bacnetit.lib.encoding.exception;

import ch.fhnw.bacnetit.lib.encoding.type.constructed.BACnetError;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.type.error.BaseError;

public class BACnetErrorException extends BACnetException {
    private static final long serialVersionUID = -1;

    private final BaseError error;

    public BACnetErrorException(final byte choice, final ErrorClass errorClass,
            final ErrorCode errorCode) {
        super(getBaseMessage(errorClass, errorCode, null));
        error = new BaseError(choice, new BACnetError(errorClass, errorCode));
    }

    public BACnetErrorException(final byte choice,
            final BACnetServiceException e) {
        super(e);
        error = new BaseError(choice,
                new BACnetError(e.getErrorClass(), e.getErrorCode()));
    }

    public BACnetErrorException(final ErrorClass errorClass,
            final ErrorCode errorCode) {
        super(getBaseMessage(errorClass, errorCode, null));
        error = new BaseError((byte) 127,
                new BACnetError(errorClass, errorCode));
    }

    public BACnetErrorException(final BACnetServiceException e) {
        super(e.getMessage());
        error = new BaseError((byte) 127,
                new BACnetError(e.getErrorClass(), e.getErrorCode()));
    }

    public BACnetErrorException(final ErrorClass errorClass,
            final ErrorCode errorCode, final String message) {
        super(getBaseMessage(errorClass, errorCode, message));
        error = new BaseError((byte) 127,
                new BACnetError(errorClass, errorCode));
    }

    public BACnetErrorException(final BaseError error) {
        this.error = error;
    }

    public BaseError getError() {
        return error;
    }

    private static String getBaseMessage(final ErrorClass errorClass,
            final ErrorCode errorCode, final String message) {
        final StringBuilder sb = new StringBuilder();
        sb.append(errorClass.toString());
        sb.append(": ");
        sb.append(errorCode.toString());
        if (message != null) {
            sb.append(" '").append(message).append("'");
        }
        return sb.toString();
    }
}
