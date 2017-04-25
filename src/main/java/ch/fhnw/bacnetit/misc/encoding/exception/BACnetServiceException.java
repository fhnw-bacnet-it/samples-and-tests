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
package ch.fhnw.bacnetit.misc.encoding.exception;

import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorCode;

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
