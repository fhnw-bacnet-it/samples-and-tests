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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class TimeSynchronizationRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = -7891171877308926353L;

    public static final byte TYPE_ID = 6;

    private final DateTime time;

    public TimeSynchronizationRequest(final DateTime time) {
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

    TimeSynchronizationRequest(final ByteQueue queue) throws BACnetException {
        time = read(queue, DateTime.class);
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) {
    // try {
    // ServicesSupported servicesSupported = (ServicesSupported)
    // localDevice.getConfiguration().getProperty(
    // PropertyIdentifier.protocolServicesSupported);
    // if (servicesSupported.isTimeSynchronization())
    // localDevice.getEventHandler().synchronizeTime(time, false);
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
        final TimeSynchronizationRequest other = (TimeSynchronizationRequest) obj;
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
