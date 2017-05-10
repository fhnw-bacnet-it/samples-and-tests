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
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.NotifyType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.notificationParameter.NotificationParameters;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class ConfirmedEventNotificationRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 1213823870240071042L;

    public static final byte TYPE_ID = 2;

    private final UnsignedInteger processIdentifier; // 0

    private final BACnetObjectIdentifier initiatingDeviceIdentifier; // 1

    private final BACnetObjectIdentifier eventObjectIdentifier; // 2

    private final TimeStamp timeStamp; // 3

    private final UnsignedInteger notificationClass; // 4

    private final UnsignedInteger priority; // 5

    private final EventType eventType; // 6

    private final CharacterString messageText; // 7 optional

    private final NotifyType notifyType; // 8

    private final Boolean ackRequired; // 9 optional

    private final EventState fromState; // 10 optional

    private final EventState toState; // 11

    private final NotificationParameters eventValues; // 12 optional

    public ConfirmedEventNotificationRequest(
            final UnsignedInteger processIdentifier,
            final BACnetObjectIdentifier initiatingDeviceIdentifier,
            final BACnetObjectIdentifier eventObjectIdentifier,
            final TimeStamp timeStamp, final UnsignedInteger notificationClass,
            final UnsignedInteger priority, final EventType eventType,
            final CharacterString messageText, final NotifyType notifyType,
            final Boolean ackRequired, final EventState fromState,
            final EventState toState,
            final NotificationParameters eventValues) {
        this.processIdentifier = processIdentifier;
        this.initiatingDeviceIdentifier = initiatingDeviceIdentifier;
        this.eventObjectIdentifier = eventObjectIdentifier;
        this.timeStamp = timeStamp;
        this.notificationClass = notificationClass;
        this.priority = priority;
        this.eventType = eventType;
        this.messageText = messageText;
        this.notifyType = notifyType;
        this.ackRequired = ackRequired;
        this.fromState = fromState;
        this.toState = toState;
        this.eventValues = eventValues;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService) {
    // localDevice.getEventHandler().fireEventNotification(processIdentifier,
    // localDevice.getRemoteDeviceCreate(initiatingDeviceIdentifier.getInstanceNumber(),
    // from, linkService),
    // eventObjectIdentifier, timeStamp, notificationClass, priority, eventType,
    // messageText, notifyType,
    // ackRequired, fromState, toState, eventValues);
    // return null;
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, processIdentifier, 0);
        write(queue, initiatingDeviceIdentifier, 1);
        write(queue, eventObjectIdentifier, 2);
        write(queue, timeStamp, 3);
        write(queue, notificationClass, 4);
        write(queue, priority, 5);
        write(queue, eventType, 6);
        writeOptional(queue, messageText, 7);
        write(queue, notifyType, 8);
        writeOptional(queue, ackRequired, 9);
        writeOptional(queue, fromState, 10);
        write(queue, toState, 11);
        writeOptional(queue, eventValues, 12);
    }

    ConfirmedEventNotificationRequest(final ByteQueue queue)
            throws BACnetException {
        processIdentifier = read(queue, UnsignedInteger.class, 0);
        initiatingDeviceIdentifier = read(queue, BACnetObjectIdentifier.class,
                1);
        eventObjectIdentifier = read(queue, BACnetObjectIdentifier.class, 2);
        timeStamp = read(queue, TimeStamp.class, 3);
        notificationClass = read(queue, UnsignedInteger.class, 4);
        priority = read(queue, UnsignedInteger.class, 5);
        eventType = read(queue, EventType.class, 6);
        messageText = readOptional(queue, CharacterString.class, 7);
        notifyType = read(queue, NotifyType.class, 8);
        ackRequired = readOptional(queue, Boolean.class, 9);
        fromState = readOptional(queue, EventState.class, 10);
        toState = read(queue, EventState.class, 11);
        eventValues = NotificationParameters
                .createNotificationParametersOptional(queue, 12);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((ackRequired == null) ? 0 : ackRequired.hashCode());
        result = PRIME * result + ((eventObjectIdentifier == null) ? 0
                : eventObjectIdentifier.hashCode());
        result = PRIME * result
                + ((eventType == null) ? 0 : eventType.hashCode());
        result = PRIME * result
                + ((eventValues == null) ? 0 : eventValues.hashCode());
        result = PRIME * result
                + ((fromState == null) ? 0 : fromState.hashCode());
        result = PRIME * result + ((initiatingDeviceIdentifier == null) ? 0
                : initiatingDeviceIdentifier.hashCode());
        result = PRIME * result
                + ((messageText == null) ? 0 : messageText.hashCode());
        result = PRIME * result + ((notificationClass == null) ? 0
                : notificationClass.hashCode());
        result = PRIME * result
                + ((notifyType == null) ? 0 : notifyType.hashCode());
        result = PRIME * result
                + ((priority == null) ? 0 : priority.hashCode());
        result = PRIME * result + ((processIdentifier == null) ? 0
                : processIdentifier.hashCode());
        result = PRIME * result
                + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        result = PRIME * result + ((toState == null) ? 0 : toState.hashCode());
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
        final ConfirmedEventNotificationRequest other = (ConfirmedEventNotificationRequest) obj;
        if (ackRequired == null) {
            if (other.ackRequired != null) {
                return false;
            }
        } else if (!ackRequired.equals(other.ackRequired)) {
            return false;
        }
        if (eventObjectIdentifier == null) {
            if (other.eventObjectIdentifier != null) {
                return false;
            }
        } else if (!eventObjectIdentifier.equals(other.eventObjectIdentifier)) {
            return false;
        }
        if (eventType == null) {
            if (other.eventType != null) {
                return false;
            }
        } else if (!eventType.equals(other.eventType)) {
            return false;
        }
        if (eventValues == null) {
            if (other.eventValues != null) {
                return false;
            }
        } else if (!eventValues.equals(other.eventValues)) {
            return false;
        }
        if (fromState == null) {
            if (other.fromState != null) {
                return false;
            }
        } else if (!fromState.equals(other.fromState)) {
            return false;
        }
        if (initiatingDeviceIdentifier == null) {
            if (other.initiatingDeviceIdentifier != null) {
                return false;
            }
        } else if (!initiatingDeviceIdentifier
                .equals(other.initiatingDeviceIdentifier)) {
            return false;
        }
        if (messageText == null) {
            if (other.messageText != null) {
                return false;
            }
        } else if (!messageText.equals(other.messageText)) {
            return false;
        }
        if (notificationClass == null) {
            if (other.notificationClass != null) {
                return false;
            }
        } else if (!notificationClass.equals(other.notificationClass)) {
            return false;
        }
        if (notifyType == null) {
            if (other.notifyType != null) {
                return false;
            }
        } else if (!notifyType.equals(other.notifyType)) {
            return false;
        }
        if (priority == null) {
            if (other.priority != null) {
                return false;
            }
        } else if (!priority.equals(other.priority)) {
            return false;
        }
        if (processIdentifier == null) {
            if (other.processIdentifier != null) {
                return false;
            }
        } else if (!processIdentifier.equals(other.processIdentifier)) {
            return false;
        }
        if (timeStamp == null) {
            if (other.timeStamp != null) {
                return false;
            }
        } else if (!timeStamp.equals(other.timeStamp)) {
            return false;
        }
        if (toState == null) {
            if (other.toState != null) {
                return false;
            }
        } else if (!toState.equals(other.toState)) {
            return false;
        }
        return true;
    }
}
