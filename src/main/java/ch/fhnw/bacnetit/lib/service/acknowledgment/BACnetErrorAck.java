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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BACnetErrorAck extends AcknowledgementService {
    private static final long serialVersionUID = 6298176515532865809L;

    public static final byte TYPE_ID = 4;

    private final ErrorClass errorClass;

    private final ErrorCode errorCode;

    private final int serviceId;

    public BACnetErrorAck(final ErrorClass errorClass,
            final ErrorCode errorCode, final byte serviceId) {
        this.errorClass = errorClass;
        this.errorCode = errorCode;
        this.serviceId = serviceId;
    }

    @Override
    public void write(final ByteQueue queue) {
        writeContextTag(queue, TYPE_ID, true);
        writeContextTag(queue, serviceId, true);
        errorClass.write(queue);
        errorCode.write(queue);
        writeContextTag(queue, serviceId, false);
        writeContextTag(queue, TYPE_ID, false);
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

}
