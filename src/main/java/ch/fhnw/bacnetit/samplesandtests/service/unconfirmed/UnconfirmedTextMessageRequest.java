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
package ch.fhnw.bacnetit.samplesandtests.service.unconfirmed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.enumerated.MessagePriority;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class UnconfirmedTextMessageRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = 1555323189158421374L;

    public static final byte TYPE_ID = 5;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(UnsignedInteger.class);
        classes.add(CharacterString.class);
    }

    private final BACnetObjectIdentifier textMessageSourceDevice;

    private Choice messageClass;

    private final MessagePriority messagePriority;

    private final CharacterString message;

    public UnconfirmedTextMessageRequest(
            final BACnetObjectIdentifier textMessageSourceDevice,
            final UnsignedInteger messageClass,
            final MessagePriority messagePriority,
            final CharacterString message) {
        this.textMessageSourceDevice = textMessageSourceDevice;
        this.messageClass = new Choice(0, messageClass);
        this.messagePriority = messagePriority;
        this.message = message;
    }

    public UnconfirmedTextMessageRequest(
            final BACnetObjectIdentifier textMessageSourceDevice,
            final CharacterString messageClass,
            final MessagePriority messagePriority,
            final CharacterString message) {
        this.textMessageSourceDevice = textMessageSourceDevice;
        this.messageClass = new Choice(0, messageClass);
        this.messagePriority = messagePriority;
        this.message = message;
    }

    public UnconfirmedTextMessageRequest(
            final BACnetObjectIdentifier textMessageSourceDevice,
            final MessagePriority messagePriority,
            final CharacterString message) {
        this.textMessageSourceDevice = textMessageSourceDevice;
        this.messagePriority = messagePriority;
        this.message = message;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public void handle(LocalDevice localDevice, Address from, OctetString
    // linkService) {
    // localDevice.getEventHandler().fireTextMessage(
    // localDevice.getRemoteDeviceCreate(textMessageSourceDevice.getInstanceNumber(),
    // from, linkService),
    // messageClass, messagePriority, message);
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, textMessageSourceDevice, 0);
        writeOptional(queue, messageClass, 1);
        write(queue, messagePriority, 2);
        write(queue, message, 3);
    }

    UnconfirmedTextMessageRequest(final ByteQueue queue)
            throws BACnetException {
        textMessageSourceDevice = read(queue, BACnetObjectIdentifier.class, 0);
        if (readStart(queue) == 1) {
            messageClass = new Choice(queue, classes);
        }
        messagePriority = read(queue, MessagePriority.class, 2);
        message = read(queue, CharacterString.class, 3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((message == null) ? 0 : message.hashCode());
        result = PRIME * result
                + ((messageClass == null) ? 0 : messageClass.hashCode());
        result = PRIME * result
                + ((messagePriority == null) ? 0 : messagePriority.hashCode());
        result = PRIME * result + ((textMessageSourceDevice == null) ? 0
                : textMessageSourceDevice.hashCode());
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
        final UnconfirmedTextMessageRequest other = (UnconfirmedTextMessageRequest) obj;
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (messageClass == null) {
            if (other.messageClass != null) {
                return false;
            }
        } else if (!messageClass.equals(other.messageClass)) {
            return false;
        }
        if (messagePriority == null) {
            if (other.messagePriority != null) {
                return false;
            }
        } else if (!messagePriority.equals(other.messagePriority)) {
            return false;
        }
        if (textMessageSourceDevice == null) {
            if (other.textMessageSourceDevice != null) {
                return false;
            }
        } else if (!textMessageSourceDevice
                .equals(other.textMessageSourceDevice)) {
            return false;
        }
        return true;
    }
}
