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
package ch.fhnw.bacnetit.misc.service.acknowledgment;

import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

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
