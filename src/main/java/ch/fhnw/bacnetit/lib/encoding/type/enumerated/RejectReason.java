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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class RejectReason extends Enumerated {
    private static final long serialVersionUID = 3672606740809550085L;

    public static final RejectReason other = new RejectReason(0);

    public static final RejectReason bufferOverflow = new RejectReason(1);

    public static final RejectReason inconsistentParameters = new RejectReason(
            2);

    public static final RejectReason invalidParameterDataType = new RejectReason(
            3);

    public static final RejectReason invalidTag = new RejectReason(4);

    public static final RejectReason missingRequiredParameter = new RejectReason(
            5);

    public static final RejectReason parameterOutOfRange = new RejectReason(6);

    public static final RejectReason tooManyArguments = new RejectReason(7);

    public static final RejectReason undefinedEnumeration = new RejectReason(8);

    public static final RejectReason unrecognizedService = new RejectReason(9);

    public static final RejectReason[] ALL = { other, bufferOverflow,
            inconsistentParameters, invalidParameterDataType, invalidTag,
            missingRequiredParameter, parameterOutOfRange, tooManyArguments,
            undefinedEnumeration, unrecognizedService, };

    public RejectReason(final int value) {
        super(value);
    }

    public RejectReason(final ByteQueue queue) {
        super(queue);
    }
}
