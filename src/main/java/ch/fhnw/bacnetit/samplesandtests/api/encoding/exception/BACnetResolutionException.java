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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.exception;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;

public class BACnetResolutionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final UnsignedInteger errorCode;

    private final String details;

    public BACnetResolutionException(final UnsignedInteger errorCode,
            final String details) {
        super(details);
        this.errorCode = errorCode;
        this.details = details;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "ErrorCode: " + this.errorCode;
        msg += System.getProperty("line.separator");
        msg += "Details: " + this.details;
        return msg;
    }
}
