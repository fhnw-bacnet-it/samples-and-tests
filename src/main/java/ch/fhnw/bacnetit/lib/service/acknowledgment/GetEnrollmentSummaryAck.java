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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventType;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class GetEnrollmentSummaryAck extends AcknowledgementService {
    private static final long serialVersionUID = 415671392143018598L;

    public static final byte TYPE_ID = 4;

    private final SequenceOf<EnrollmentSummary> values;

    public GetEnrollmentSummaryAck(final SequenceOf<EnrollmentSummary> values) {
        this.values = values;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, values);
    }

    GetEnrollmentSummaryAck(final ByteQueue queue) throws BACnetException {
        values = readSequenceOf(queue, EnrollmentSummary.class);
    }

    public SequenceOf<EnrollmentSummary> getValues() {
        return values;
    }

    public static class EnrollmentSummary extends BaseType {
        private static final long serialVersionUID = 2268948228193727440L;

        private final BACnetObjectIdentifier objectIdentifier;

        private final EventType eventType;

        private final EventState eventState;

        private final UnsignedInteger priority;

        private final UnsignedInteger notificationClass; // optional

        public EnrollmentSummary(final BACnetObjectIdentifier objectIdentifier,
                final EventType eventType, final EventState eventState,
                final UnsignedInteger priority,
                final UnsignedInteger notificationClass) {
            this.objectIdentifier = objectIdentifier;
            this.eventType = eventType;
            this.eventState = eventState;
            this.priority = priority;
            this.notificationClass = notificationClass;
        }

        @Override
        public void write(final ByteQueue queue) {
            write(queue, objectIdentifier);
            write(queue, eventType);
            write(queue, eventState);
            write(queue, priority);
            writeOptional(queue, notificationClass);
        }

        public EnrollmentSummary(final ByteQueue queue) throws BACnetException {
            objectIdentifier = read(queue, BACnetObjectIdentifier.class);
            eventType = read(queue, EventType.class);
            eventState = read(queue, EventState.class);
            priority = read(queue, UnsignedInteger.class);
            if (peekTagNumber(queue) == UnsignedInteger.TYPE_ID) {
                notificationClass = read(queue, UnsignedInteger.class);
            } else {
                notificationClass = null;
            }
        }

        public BACnetObjectIdentifier getObjectIdentifier() {
            return objectIdentifier;
        }

        public EventType getEventType() {
            return eventType;
        }

        public EventState getEventState() {
            return eventState;
        }

        public UnsignedInteger getPriority() {
            return priority;
        }

        public UnsignedInteger getNotificationClass() {
            return notificationClass;
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result
                    + ((eventState == null) ? 0 : eventState.hashCode());
            result = PRIME * result
                    + ((eventType == null) ? 0 : eventType.hashCode());
            result = PRIME * result + ((notificationClass == null) ? 0
                    : notificationClass.hashCode());
            result = PRIME * result + ((objectIdentifier == null) ? 0
                    : objectIdentifier.hashCode());
            result = PRIME * result
                    + ((priority == null) ? 0 : priority.hashCode());
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
            final EnrollmentSummary other = (EnrollmentSummary) obj;
            if (eventState == null) {
                if (other.eventState != null) {
                    return false;
                }
            } else if (!eventState.equals(other.eventState)) {
                return false;
            }
            if (eventType == null) {
                if (other.eventType != null) {
                    return false;
                }
            } else if (!eventType.equals(other.eventType)) {
                return false;
            }
            if (notificationClass == null) {
                if (other.notificationClass != null) {
                    return false;
                }
            } else if (!notificationClass.equals(other.notificationClass)) {
                return false;
            }
            if (objectIdentifier == null) {
                if (other.objectIdentifier != null) {
                    return false;
                }
            } else if (!objectIdentifier.equals(other.objectIdentifier)) {
                return false;
            }
            if (priority == null) {
                if (other.priority != null) {
                    return false;
                }
            } else if (!priority.equals(other.priority)) {
                return false;
            }
            return true;
        }

    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((values == null) ? 0 : values.hashCode());
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
        final GetEnrollmentSummaryAck other = (GetEnrollmentSummaryAck) obj;
        if (values == null) {
            if (other.values != null) {
                return false;
            }
        } else if (!values.equals(other.values)) {
            return false;
        }
        return true;
    }
}
