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
