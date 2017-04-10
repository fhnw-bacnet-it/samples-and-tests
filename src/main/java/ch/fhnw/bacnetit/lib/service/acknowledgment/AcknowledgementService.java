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

import ch.fhnw.bacnetit.lib.deviceobjects.Service;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
