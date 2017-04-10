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

import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;

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
