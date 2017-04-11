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

import ch.fhnw.bacnetit.lib.encoding.exception.TransportError.TransportErrorType;

public abstract class TransportErrorException extends RuntimeException {

    private static final long serialVersionUID = 4899436753445332038L;
    private final TransportError transportError;

    public TransportErrorException(final String message,
            final TransportError transportError) {
        super(message);
        this.transportError = (transportError == null)
                ? new TransportError(TransportErrorType.Undefined, 0)
                : transportError;
    }

    public TransportError getTransportError() {
        return this.transportError;
    }
}
