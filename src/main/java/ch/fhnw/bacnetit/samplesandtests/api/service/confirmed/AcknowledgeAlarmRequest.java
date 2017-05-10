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
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.TimeStamp;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class AcknowledgeAlarmRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -2003086447797009243L;

    public static final byte TYPE_ID = 0;

    private final UnsignedInteger acknowledgingProcessIdentifier;

    private final BACnetObjectIdentifier eventObjectIdentifier;

    private final EventState eventStateAcknowledged;

    private final TimeStamp timeStamp;

    private final CharacterString acknowledgmentSource;

    private final TimeStamp timeOfAcknowledgment;

    public AcknowledgeAlarmRequest(
            final UnsignedInteger acknowledgingProcessIdentifier,
            final BACnetObjectIdentifier eventObjectIdentifier,
            final EventState eventStateAcknowledged, final TimeStamp timeStamp,
            final CharacterString acknowledgmentSource,
            final TimeStamp timeOfAcknowledgment) {
        this.acknowledgingProcessIdentifier = acknowledgingProcessIdentifier;
        this.eventObjectIdentifier = eventObjectIdentifier;
        this.eventStateAcknowledged = eventStateAcknowledged;
        this.timeStamp = timeStamp;
        this.acknowledgmentSource = acknowledgmentSource;
        this.timeOfAcknowledgment = timeOfAcknowledgment;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public void write(final ByteQueue queue) {
        acknowledgingProcessIdentifier.write(queue, 0);
        eventObjectIdentifier.write(queue, 1);
        eventStateAcknowledged.write(queue, 2);
        timeStamp.write(queue, 3);
        acknowledgmentSource.write(queue, 4);
        timeOfAcknowledgment.write(queue, 5);
    }

    AcknowledgeAlarmRequest(final ByteQueue queue) throws BACnetException {
        acknowledgingProcessIdentifier = read(queue, UnsignedInteger.class, 0);
        eventObjectIdentifier = read(queue, BACnetObjectIdentifier.class, 1);
        eventStateAcknowledged = read(queue, EventState.class, 2);
        timeStamp = read(queue, TimeStamp.class, 3);
        acknowledgmentSource = read(queue, CharacterString.class, 4);
        timeOfAcknowledgment = read(queue, TimeStamp.class, 5);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((acknowledgingProcessIdentifier == null) ? 0
                : acknowledgingProcessIdentifier.hashCode());
        result = PRIME * result + ((acknowledgmentSource == null) ? 0
                : acknowledgmentSource.hashCode());
        result = PRIME * result + ((eventObjectIdentifier == null) ? 0
                : eventObjectIdentifier.hashCode());
        result = PRIME * result + ((eventStateAcknowledged == null) ? 0
                : eventStateAcknowledged.hashCode());
        result = PRIME * result + ((timeOfAcknowledgment == null) ? 0
                : timeOfAcknowledgment.hashCode());
        result = PRIME * result
                + ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
        final AcknowledgeAlarmRequest other = (AcknowledgeAlarmRequest) obj;
        if (acknowledgingProcessIdentifier == null) {
            if (other.acknowledgingProcessIdentifier != null) {
                return false;
            }
        } else if (!acknowledgingProcessIdentifier
                .equals(other.acknowledgingProcessIdentifier)) {
            return false;
        }
        if (acknowledgmentSource == null) {
            if (other.acknowledgmentSource != null) {
                return false;
            }
        } else if (!acknowledgmentSource.equals(other.acknowledgmentSource)) {
            return false;
        }
        if (eventObjectIdentifier == null) {
            if (other.eventObjectIdentifier != null) {
                return false;
            }
        } else if (!eventObjectIdentifier.equals(other.eventObjectIdentifier)) {
            return false;
        }
        if (eventStateAcknowledged == null) {
            if (other.eventStateAcknowledged != null) {
                return false;
            }
        } else if (!eventStateAcknowledged
                .equals(other.eventStateAcknowledged)) {
            return false;
        }
        if (timeOfAcknowledgment == null) {
            if (other.timeOfAcknowledgment != null) {
                return false;
            }
        } else if (!timeOfAcknowledgment.equals(other.timeOfAcknowledgment)) {
            return false;
        }
        if (timeStamp == null) {
            if (other.timeStamp != null) {
                return false;
            }
        } else if (!timeStamp.equals(other.timeStamp)) {
            return false;
        }
        return true;
    }
}
