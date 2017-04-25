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

import ch.fhnw.bacnetit.misc.deviceobjects.Service;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

abstract public class AcknowledgementService extends Service {
    private static final long serialVersionUID = 3837098889443642001L;

    public static AcknowledgementService createAcknowledgementService(
            final byte type, final ByteQueue queue) throws BACnetException {

        if (type == GetAlarmSummaryAck.TYPE_ID) {
            return new GetAlarmSummaryAck(queue);
        }
        if (type == GetEnrollmentSummaryAck.TYPE_ID) {
            return new GetEnrollmentSummaryAck(queue);
        }
        if (type == AtomicReadFileAck.TYPE_ID) {
            return new AtomicReadFileAck(queue);
        }
        if (type == AtomicWriteFileAck.TYPE_ID) {
            return new AtomicWriteFileAck(queue);
        }
        if (type == CreateObjectAck.TYPE_ID) {
            return new CreateObjectAck(queue);
        }
        if (type == ReadPropertyAck.TYPE_ID) {
            return new ReadPropertyAck(queue);
        }
        if (type == ReadPropertyConditionalAck.TYPE_ID) {
            return new ReadPropertyConditionalAck(queue);
        }
        if (type == ReadPropertyMultipleAck.TYPE_ID) {
            return new ReadPropertyMultipleAck(queue);
        }
        if (type == ConfirmedPrivateTransferAck.TYPE_ID) {
            return new ConfirmedPrivateTransferAck(queue);
        }
        if (type == VtOpenAck.TYPE_ID) {
            return new VtOpenAck(queue);
        }
        if (type == VtDataAck.TYPE_ID) {
            return new VtDataAck(queue);
        }
        if (type == AuthenticateAck.TYPE_ID) {
            return new AuthenticateAck(queue);
        }
        if (type == ReadRangeAck.TYPE_ID) {
            return new ReadRangeAck(queue);
        }
        if (type == GetEventInformationAck.TYPE_ID) {
            return new GetEventInformationAck(queue);
        }

        throw new BACnetException(
                "Unsupported service acknowledgement: " + (type & 0xff));
    }

}
