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
package ch.fhnw.bacnetit.lib.encoding.asdu;

import java.io.Serializable;

import ch.fhnw.bacnetit.lib.encoding.enums.Tag;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

abstract public class ASDU implements Serializable {
    private static final long serialVersionUID = -5844093063653180470L;
    private static final InternalLogger LOG = InternalLoggerFactory
            .getInstance(ASDU.class);

    public static ASDU createASDU(final ServicesSupported services,
            final ByteQueue queue) throws BACnetException {
        byte type = queue.peek(0);
        final Tag tag = Tag.valueOf((byte) (type & 0x0f));
        if (tag == Tag.OPENING_TAG) {
            // Message is encoded as "constructed data" (see spec 20.2.1.3.2)
            type = (byte) ((type & 0xff) >> 4);
            ASDU asdu = null;
            switch (type) {
            case ConfirmedRequest.TYPE_ID:
                asdu = new ConfirmedRequest(services, queue);
                break;
            case UnconfirmedRequest.TYPE_ID:
                asdu = new UnconfirmedRequest(services, queue);
                break;
            case SimpleACK.TYPE_ID:
                asdu = new SimpleACK(queue);
                break;
            case ComplexACK.TYPE_ID:
                asdu = new ComplexACK(queue);
                break;
            case BACnetErrorAckASDU.TYPE_ID:
                asdu = new BACnetErrorAckASDU(queue);
                break;
            case BACnetMsgErrorAckASDU.TYPE_ID:
                asdu = new BACnetMsgErrorAckASDU(queue);
                break;
            case Reject.TYPE_ID:
                asdu = new Reject(queue);
                break;
            case Abort.TYPE_ID:
                asdu = new Abort(queue);
                break;
            default:
                throw new BACnetException(Byte.toString(type));
            }
            // Check if request is closed
            final Tag closingTag = Tag.valueOf((byte) (queue.pop() & 0x0f));
            if (closingTag == Tag.CLOSING_TAG) {
                return asdu;
            } else {
                throw new BACnetException("Missing Closing Tag!");
            }
        } else {
            // Message is encoded as "primitive data" (see spec 20.2.1.3.1)
            type = (byte) ((type & 0xff) >> 4);
            ASDU asdu = null;
            switch (type) {
            case SimpleACK.TYPE_ID:
                asdu = new SimpleACK(queue);
                break;
            default:
                throw new BACnetException(Byte.toString(type));
            }
            return asdu;
        }
    }

    abstract public byte getPduType();

    abstract public void write(ByteQueue queue);

    protected int getShiftedTypeId(final byte typeId) {
        return typeId << 4;
    }

    abstract public boolean expectsReply();
}
