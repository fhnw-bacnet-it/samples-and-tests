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
package ch.fhnw.bacnetit.lib.service.confirmed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.MessagePriority;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ConfirmedTextMessageRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 2831753442676777033L;

    public static final byte TYPE_ID = 19;

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

    public ConfirmedTextMessageRequest(
            final BACnetObjectIdentifier textMessageSourceDevice,
            final UnsignedInteger messageClass,
            final MessagePriority messagePriority,
            final CharacterString message) {
        this.textMessageSourceDevice = textMessageSourceDevice;
        this.messageClass = new Choice(0, messageClass);
        this.messagePriority = messagePriority;
        this.message = message;
    }

    public ConfirmedTextMessageRequest(
            final BACnetObjectIdentifier textMessageSourceDevice,
            final CharacterString messageClass,
            final MessagePriority messagePriority,
            final CharacterString message) {
        this.textMessageSourceDevice = textMessageSourceDevice;
        this.messageClass = new Choice(0, messageClass);
        this.messagePriority = messagePriority;
        this.message = message;
    }

    public ConfirmedTextMessageRequest(
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
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService) {
    // localDevice.getEventHandler().fireTextMessage(
    // localDevice.getRemoteDeviceCreate(textMessageSourceDevice.getInstanceNumber(),
    // from, linkService),
    // messageClass, messagePriority, message);
    // return null;
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, textMessageSourceDevice, 0);
        writeOptional(queue, messageClass, 1);
        write(queue, messagePriority, 2);
        write(queue, message, 3);
    }

    ConfirmedTextMessageRequest(final ByteQueue queue) throws BACnetException {
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
        final ConfirmedTextMessageRequest other = (ConfirmedTextMessageRequest) obj;
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
