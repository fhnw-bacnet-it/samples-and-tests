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
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.Service;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

abstract public class UnconfirmedRequestService extends Service {
    private static final long serialVersionUID = 8962921362279665295L;

    public static final byte SERVICE_ID = 1;

    public static UnconfirmedRequestService createUnconfirmedRequestService(
            final ServicesSupported services, final byte type,
            final ByteQueue queue) throws BACnetException {
        if (type == IAmRequest.TYPE_ID) {
            if (services.isIAm()) {
                return new IAmRequest(queue);
            }
            return null;
        }

        if (type == IHaveRequest.TYPE_ID) {
            if (services.isIHave()) {
                return new IHaveRequest(queue);
            }
            return null;
        }

        if (type == UnconfirmedCovNotificationRequest.TYPE_ID) {
            if (services.isUnconfirmedCovNotification()) {
                return new UnconfirmedCovNotificationRequest(queue);
            }
            return null;
        }

        if (type == UnconfirmedEventNotificationRequest.TYPE_ID) {
            if (services.isUnconfirmedEventNotification()) {
                return new UnconfirmedEventNotificationRequest(queue);
            }
            return null;
        }

        if (type == UnconfirmedPrivateTransferRequest.TYPE_ID) {
            if (services.isUnconfirmedPrivateTransfer()) {
                return new UnconfirmedPrivateTransferRequest(queue);
            }
            return null;
        }

        if (type == UnconfirmedTextMessageRequest.TYPE_ID) {
            if (services.isUnconfirmedTextMessage()) {
                return new UnconfirmedTextMessageRequest(queue);
            }
            return null;
        }

        if (type == TimeSynchronizationRequest.TYPE_ID) {
            if (services.isTimeSynchronization()) {
                return new TimeSynchronizationRequest(queue);
            }
            return null;
        }

        if (type == WhoHasRequest.TYPE_ID) {
            if (services.isWhoHas()) {
                return new WhoHasRequest(queue);
            }
            return null;
        }

        if (type == WhoIsRequest.TYPE_ID) {
            if (services.isWhoIs()) {
                return new WhoIsRequest(queue);
            }
            return null;
        }

        if (type == UTCTimeSynchronizationRequest.TYPE_ID) {
            if (services.isUtcTimeSynchronization()) {
                return new UTCTimeSynchronizationRequest(queue);
            }
            return null;
        }

        throw new BACnetException(
                "Unsupported unconfirmed service: " + (type & 0xff));
    }

}
