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
package ch.fhnw.bacnetit.samplesandtests.api.service.unconfirmed;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class IHaveRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = 3369038797505147152L;

    public static final byte TYPE_ID = 1;

    private final BACnetObjectIdentifier deviceIdentifier;

    private final BACnetObjectIdentifier objectIdentifier;

    private final CharacterString objectName;

    public IHaveRequest(final BACnetObjectIdentifier deviceIdentifier,
            final BACnetObjectIdentifier objectIdentifier,
            final CharacterString objectName) {
        super();
        this.deviceIdentifier = deviceIdentifier;
        this.objectIdentifier = objectIdentifier;
        this.objectName = objectName;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) {
    // RemoteDevice d =
    // localDevice.getRemoteDeviceCreate(deviceIdentifier.getInstanceNumber(),
    // from, linkService);
    // RemoteObject o = new RemoteObject(objectIdentifier);
    // o.setObjectName(objectName.toString());
    // d.setObject(o);
    //
    // localDevice.getEventHandler().fireIHaveReceived(d, o);
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, deviceIdentifier);
        write(queue, objectIdentifier);
        write(queue, objectName);
    }

    IHaveRequest(final ByteQueue queue) throws BACnetException {
        deviceIdentifier = read(queue, BACnetObjectIdentifier.class);
        objectIdentifier = read(queue, BACnetObjectIdentifier.class);
        objectName = read(queue, CharacterString.class);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((deviceIdentifier == null) ? 0
                : deviceIdentifier.hashCode());
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result
                + ((objectName == null) ? 0 : objectName.hashCode());
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
        final IHaveRequest other = (IHaveRequest) obj;
        if (deviceIdentifier == null) {
            if (other.deviceIdentifier != null) {
                return false;
            }
        } else if (!deviceIdentifier.equals(other.deviceIdentifier)) {
            return false;
        }
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (objectName == null) {
            if (other.objectName != null) {
                return false;
            }
        } else if (!objectName.equals(other.objectName)) {
            return false;
        }
        return true;
    }
}
