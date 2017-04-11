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
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class UTCTimeSynchronizationRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = 495223700432185701L;

    public static final byte TYPE_ID = 9;

    private final DateTime time;

    public UTCTimeSynchronizationRequest(final DateTime time) {
        this.time = time;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, time);
    }

    UTCTimeSynchronizationRequest(final ByteQueue queue)
            throws BACnetException {
        time = read(queue, DateTime.class);
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) {
    // try {
    // ServicesSupported servicesSupported = (ServicesSupported)
    // localDevice.getConfiguration().getProperty(
    // PropertyIdentifier.protocolServicesSupported);
    // if (servicesSupported.isUtcTimeSynchronization())
    // localDevice.getEventHandler().synchronizeTime(time, true);
    // }
    // catch (BACnetServiceException e) {
    // // no op
    // }
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UTCTimeSynchronizationRequest other = (UTCTimeSynchronizationRequest) obj;
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        return true;
    }
}
