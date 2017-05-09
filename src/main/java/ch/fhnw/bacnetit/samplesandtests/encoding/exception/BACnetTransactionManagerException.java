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
package ch.fhnw.bacnetit.samplesandtests.encoding.exception;

public class BACnetTransactionManagerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final BACnetErrorType errorType;

    public BACnetTransactionManagerException(final BACnetErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "ErrorCode: " + this.errorType.getCode();
        msg += System.getProperty("line.separator");
        msg += "Details: " + this.errorType.getMessage();
        return msg;
    }
}
