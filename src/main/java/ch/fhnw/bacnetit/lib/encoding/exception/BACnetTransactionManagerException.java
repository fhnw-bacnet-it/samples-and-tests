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
