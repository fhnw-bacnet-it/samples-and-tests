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
