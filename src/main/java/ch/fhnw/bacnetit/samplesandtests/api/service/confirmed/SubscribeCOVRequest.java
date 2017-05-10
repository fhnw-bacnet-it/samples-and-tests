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
package ch.fhnw.bacnetit.samplesandtests.api.service.confirmed;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class SubscribeCOVRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -4264911944887357155L;

    public static final byte TYPE_ID = 5;

    private final UnsignedInteger subscriberProcessIdentifier;

    private final BACnetObjectIdentifier monitoredObjectIdentifier;

    private final Boolean issueConfirmedNotifications;

    private final UnsignedInteger lifetime;

    public SubscribeCOVRequest(
            final UnsignedInteger subscriberProcessIdentifier,
            final BACnetObjectIdentifier monitoredObjectIdentifier,
            final Boolean issueConfirmedNotifications,
            final UnsignedInteger lifetime) {
        this.subscriberProcessIdentifier = subscriberProcessIdentifier;
        this.monitoredObjectIdentifier = monitoredObjectIdentifier;
        this.issueConfirmedNotifications = issueConfirmedNotifications;
        this.lifetime = lifetime;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, subscriberProcessIdentifier, 0);
        write(queue, monitoredObjectIdentifier, 1);
        writeOptional(queue, issueConfirmedNotifications, 2);
        writeOptional(queue, lifetime, 3);
    }

    SubscribeCOVRequest(final ByteQueue queue) throws BACnetException {
        subscriberProcessIdentifier = read(queue, UnsignedInteger.class, 0);
        monitoredObjectIdentifier = read(queue, BACnetObjectIdentifier.class,
                1);
        issueConfirmedNotifications = readOptional(queue, Boolean.class, 2);
        lifetime = readOptional(queue, UnsignedInteger.class, 3);
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // try {
    // BACnetObject obj =
    // localDevice.getObjectRequired(monitoredObjectIdentifier);
    // if (issueConfirmedNotifications == null && lifetime == null)
    // obj.removeCovSubscription(from, subscriberProcessIdentifier);
    // else
    // obj.addCovSubscription(from, linkService, subscriberProcessIdentifier,
    // issueConfirmedNotifications,
    // lifetime);
    // return null;
    // }
    // catch (BACnetServiceException e) {
    // throw new BACnetErrorException(getChoiceId(), e);
    // }
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((issueConfirmedNotifications == null) ? 0
                : issueConfirmedNotifications.hashCode());
        result = PRIME * result
                + ((lifetime == null) ? 0 : lifetime.hashCode());
        result = PRIME * result + ((monitoredObjectIdentifier == null) ? 0
                : monitoredObjectIdentifier.hashCode());
        result = PRIME * result + ((subscriberProcessIdentifier == null) ? 0
                : subscriberProcessIdentifier.hashCode());
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
        final SubscribeCOVRequest other = (SubscribeCOVRequest) obj;
        if (issueConfirmedNotifications == null) {
            if (other.issueConfirmedNotifications != null) {
                return false;
            }
        } else if (!issueConfirmedNotifications
                .equals(other.issueConfirmedNotifications)) {
            return false;
        }
        if (lifetime == null) {
            if (other.lifetime != null) {
                return false;
            }
        } else if (!lifetime.equals(other.lifetime)) {
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
        return true;
    }
}
