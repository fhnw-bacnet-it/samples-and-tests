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
package ch.fhnw.bacnetit.misc.service.unconfirmed;

import ch.fhnw.bacnetit.misc.deviceobjects.Service;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

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
