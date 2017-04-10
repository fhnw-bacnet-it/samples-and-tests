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

import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;

public class BACnetServiceException extends Exception {
    private static final long serialVersionUID = -1;

    private final ErrorClass errorClass;

    private final ErrorCode errorCode;

    public BACnetServiceException(final ErrorClass errorClass,
            final ErrorCode errorCode) {
        this.errorClass = errorClass;
        this.errorCode = errorCode;
    }

    public BACnetServiceException(final ErrorClass errorClass,
            final ErrorCode errorCode, final String message) {
        super(message);
        this.errorClass = errorClass;
        this.errorCode = errorCode;
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
    public String getMessage() {
        String message = "class=" + errorClass + ", code=" + errorCode;
        final String userDesc = super.getMessage();
        if (userDesc != null) {
            message += ", message=" + userDesc;
        }
        return message;
    }
}
