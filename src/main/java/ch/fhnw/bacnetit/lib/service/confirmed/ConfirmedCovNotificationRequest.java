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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.ThreadLocalObjectTypeStack;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ConfirmedCovNotificationRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 3246652613921887403L;

    public static final byte TYPE_ID = 1;

    private final UnsignedInteger subscriberProcessIdentifier;

    private final BACnetObjectIdentifier initiatingDeviceIdentifier;

    private final BACnetObjectIdentifier monitoredObjectIdentifier;

    private final UnsignedInteger timeRemaining;

    private final SequenceOf<PropertyValue> listOfValues;

    public ConfirmedCovNotificationRequest(
            final UnsignedInteger subscriberProcessIdentifier,
            final BACnetObjectIdentifier initiatingDeviceIdentifier,
            final BACnetObjectIdentifier monitoredObjectIdentifier,
            final UnsignedInteger timeRemaining,
            final SequenceOf<PropertyValue> listOfValues) {
        this.subscriberProcessIdentifier = subscriberProcessIdentifier;
        this.initiatingDeviceIdentifier = initiatingDeviceIdentifier;
        this.monitoredObjectIdentifier = monitoredObjectIdentifier;
        this.timeRemaining = timeRemaining;
        this.listOfValues = listOfValues;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService) {
    // localDevice.getEventHandler().fireCovNotification(subscriberProcessIdentifier,
    // localDevice.getRemoteDeviceCreate(initiatingDeviceIdentifier.getInstanceNumber(),
    // from, linkService),
    // monitoredObjectIdentifier, timeRemaining, listOfValues);
    // return null;
    // }

    @Override
    public void write(final ByteQueue queue) {
        subscriberProcessIdentifier.write(queue, 0);
        initiatingDeviceIdentifier.write(queue, 1);
        monitoredObjectIdentifier.write(queue, 2);
        timeRemaining.write(queue, 3);
        listOfValues.write(queue, 4);
    }

    ConfirmedCovNotificationRequest(final ByteQueue queue)
            throws BACnetException {
        subscriberProcessIdentifier = read(queue, UnsignedInteger.class, 0);
        initiatingDeviceIdentifier = read(queue, BACnetObjectIdentifier.class,
                1);
        monitoredObjectIdentifier = read(queue, BACnetObjectIdentifier.class,
                2);
        timeRemaining = read(queue, UnsignedInteger.class, 3);
        try {
            ThreadLocalObjectTypeStack
                    .set(monitoredObjectIdentifier.getObjectType());
            listOfValues = readSequenceOf(queue, PropertyValue.class, 4);
        } finally {
            ThreadLocalObjectTypeStack.remove();
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((initiatingDeviceIdentifier == null) ? 0
                : initiatingDeviceIdentifier.hashCode());
        result = PRIME * result
                + ((listOfValues == null) ? 0 : listOfValues.hashCode());
        result = PRIME * result + ((monitoredObjectIdentifier == null) ? 0
                : monitoredObjectIdentifier.hashCode());
        result = PRIME * result + ((subscriberProcessIdentifier == null) ? 0
                : subscriberProcessIdentifier.hashCode());
        result = PRIME * result
                + ((timeRemaining == null) ? 0 : timeRemaining.hashCode());
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
        final ConfirmedCovNotificationRequest other = (ConfirmedCovNotificationRequest) obj;
        if (initiatingDeviceIdentifier == null) {
            if (other.initiatingDeviceIdentifier != null) {
                return false;
            }
        } else if (!initiatingDeviceIdentifier
                .equals(other.initiatingDeviceIdentifier)) {
            return false;
        }
        if (listOfValues == null) {
            if (other.listOfValues != null) {
                return false;
            }
        } else if (!listOfValues.equals(other.listOfValues)) {
            return false;
        }
        if (monitoredObjectIdentifier == null) {
            if (other.monitoredObjectIdentifier != null) {
                return false;
            }
        } else if (!monitoredObjectIdentifier
                .equals(other.monitoredObjectIdentifier)) {
            return false;
        }
        if (subscriberProcessIdentifier == null) {
            if (other.subscriberProcessIdentifier != null) {
                return false;
            }
        } else if (!subscriberProcessIdentifier
                .equals(other.subscriberProcessIdentifier)) {
            return false;
        }
        if (timeRemaining == null) {
            if (other.timeRemaining != null) {
                return false;
            }
        } else if (!timeRemaining.equals(other.timeRemaining)) {
            return false;
        }
        return true;
    }
}
