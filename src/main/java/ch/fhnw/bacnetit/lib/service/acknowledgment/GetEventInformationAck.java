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
package ch.fhnw.bacnetit.lib.service.acknowledgment;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.EventTransitionBits;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.TimeStamp;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.NotifyType;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class GetEventInformationAck extends AcknowledgementService {
    private static final long serialVersionUID = -1017673519290270616L;

    public static final byte TYPE_ID = 29;

    private final SequenceOf<EventSummary> listOfEventSummaries;

    private final Boolean moreEvents;

    public GetEventInformationAck(
            final SequenceOf<EventSummary> listOfEventSummaries,
            final Boolean moreEvents) {
        this.listOfEventSummaries = listOfEventSummaries;
        this.moreEvents = moreEvents;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, listOfEventSummaries, 0);
        write(queue, moreEvents, 1);
    }

    GetEventInformationAck(final ByteQueue queue) throws BACnetException {
        listOfEventSummaries = readSequenceOf(queue, EventSummary.class, 0);
        moreEvents = read(queue, Boolean.class, 1);
    }

    public SequenceOf<EventSummary> getListOfEventSummaries() {
        return listOfEventSummaries;
    }

    public Boolean getMoreEvents() {
        return moreEvents;
    }

    public static class EventSummary extends BaseType {
        private static final long serialVersionUID = -8125602915002238614L;

        private final BACnetObjectIdentifier objectIdentifier;

        private final EventState eventState;

        private final EventTransitionBits acknowledgedTransitions;

        private final TimeStamp eventTimeStamp1;

        private final TimeStamp eventTimeStamp2;

        private final TimeStamp eventTimeStamp3;

        private final NotifyType notifyType;

        private final EventTransitionBits eventEnable;

        private final UnsignedInteger eventPriorities1;

        private final UnsignedInteger eventPriorities2;

        private final UnsignedInteger eventPriorities3;

        public EventSummary(final BACnetObjectIdentifier objectIdentifier,
                final EventState eventState,
                final EventTransitionBits acknowledgedTransitions,
                final TimeStamp eventTimeStamp1,
                final TimeStamp eventTimeStamp2,
                final TimeStamp eventTimeStamp3, final NotifyType notifyType,
                final EventTransitionBits eventEnable,
                final UnsignedInteger eventPriorities1,
                final UnsignedInteger eventPriorities2,
                final UnsignedInteger eventPriorities3) {
            this.objectIdentifier = objectIdentifier;
            this.eventState = eventState;
            this.acknowledgedTransitions = acknowledgedTransitions;
            this.eventTimeStamp1 = eventTimeStamp1;
            this.eventTimeStamp2 = eventTimeStamp2;
            this.eventTimeStamp3 = eventTimeStamp3;
            this.notifyType = notifyType;
            this.eventEnable = eventEnable;
            this.eventPriorities1 = eventPriorities1;
            this.eventPriorities2 = eventPriorities2;
            this.eventPriorities3 = eventPriorities3;
        }

        @Override
        public void write(final ByteQueue queue) {
            objectIdentifier.write(queue, 0);
            eventState.write(queue, 1);
            acknowledgedTransitions.write(queue, 2);
            writeContextTag(queue, 3, true);
            eventTimeStamp1.write(queue);
            eventTimeStamp2.write(queue);
            eventTimeStamp3.write(queue);
            writeContextTag(queue, 3, false);
            notifyType.write(queue, 4);
            eventEnable.write(queue, 5);
            writeContextTag(queue, 6, true);
            eventPriorities1.write(queue);
            eventPriorities2.write(queue);
            eventPriorities3.write(queue);
            writeContextTag(queue, 6, false);
        }

        public EventSummary(final ByteQueue queue) throws BACnetException {
            objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
            eventState = read(queue, EventState.class, 1);
            acknowledgedTransitions = read(queue, EventTransitionBits.class, 2);
            popStart(queue, 3);
            eventTimeStamp1 = read(queue, TimeStamp.class);
            eventTimeStamp2 = read(queue, TimeStamp.class);
            eventTimeStamp3 = read(queue, TimeStamp.class);
            popEnd(queue, 3);
            notifyType = read(queue, NotifyType.class, 4);
            eventEnable = read(queue, EventTransitionBits.class, 5);
            popStart(queue, 6);
            eventPriorities1 = read(queue, UnsignedInteger.class);
            eventPriorities2 = read(queue, UnsignedInteger.class);
            eventPriorities3 = read(queue, UnsignedInteger.class);
            popEnd(queue, 6);
        }

        public BACnetObjectIdentifier getObjectIdentifier() {
            return objectIdentifier;
        }

        public EventState getEventState() {
            return eventState;
        }

        public EventTransitionBits getAcknowledgedTransitions() {
            return acknowledgedTransitions;
        }

        public TimeStamp getEventTimeStamp1() {
            return eventTimeStamp1;
        }

        public TimeStamp getEventTimeStamp2() {
            return eventTimeStamp2;
        }

        public TimeStamp getEventTimeStamp3() {
            return eventTimeStamp3;
        }

        public NotifyType getNotifyType() {
            return notifyType;
        }

        public EventTransitionBits getEventEnable() {
            return eventEnable;
        }

        public UnsignedInteger getEventPriorities1() {
            return eventPriorities1;
        }

        public UnsignedInteger getEventPriorities2() {
            return eventPriorities2;
        }

        public UnsignedInteger getEventPriorities3() {
            return eventPriorities3;
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((acknowledgedTransitions == null) ? 0
                    : acknowledgedTransitions.hashCode());
            result = PRIME * result
                    + ((eventEnable == null) ? 0 : eventEnable.hashCode());
            result = PRIME * result + ((eventPriorities1 == null) ? 0
                    : eventPriorities1.hashCode());
            result = PRIME * result + ((eventPriorities2 == null) ? 0
                    : eventPriorities2.hashCode());
            result = PRIME * result + ((eventPriorities3 == null) ? 0
                    : eventPriorities3.hashCode());
            result = PRIME * result
                    + ((eventState == null) ? 0 : eventState.hashCode());
            result = PRIME * result + ((eventTimeStamp1 == null) ? 0
                    : eventTimeStamp1.hashCode());
            result = PRIME * result + ((eventTimeStamp2 == null) ? 0
                    : eventTimeStamp2.hashCode());
            result = PRIME * result + ((eventTimeStamp3 == null) ? 0
                    : eventTimeStamp3.hashCode());
            result = PRIME * result
                    + ((notifyType == null) ? 0 : notifyType.hashCode());
            result = PRIME * result + ((objectIdentifier == null) ? 0
                    : objectIdentifier.hashCode());
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
            final EventSummary other = (EventSummary) obj;
            if (acknowledgedTransitions == null) {
                if (other.acknowledgedTransitions != null) {
                    return false;
                }
            } else if (!acknowledgedTransitions
                    .equals(other.acknowledgedTransitions)) {
                return false;
            }
            if (eventEnable == null) {
                if (other.eventEnable != null) {
                    return false;
                }
            } else if (!eventEnable.equals(other.eventEnable)) {
                return false;
            }
            if (eventPriorities1 == null) {
                if (other.eventPriorities1 != null) {
                    return false;
                }
            } else if (!eventPriorities1.equals(other.eventPriorities1)) {
                return false;
            }
            if (eventPriorities2 == null) {
                if (other.eventPriorities2 != null) {
                    return false;
                }
            } else if (!eventPriorities2.equals(other.eventPriorities2)) {
                return false;
            }
            if (eventPriorities3 == null) {
                if (other.eventPriorities3 != null) {
                    return false;
                }
            } else if (!eventPriorities3.equals(other.eventPriorities3)) {
                return false;
            }
            if (eventState == null) {
                if (other.eventState != null) {
                    return false;
                }
            } else if (!eventState.equals(other.eventState)) {
                return false;
            }
            if (eventTimeStamp1 == null) {
                if (other.eventTimeStamp1 != null) {
                    return false;
                }
            } else if (!eventTimeStamp1.equals(other.eventTimeStamp1)) {
                return false;
            }
            if (eventTimeStamp2 == null) {
                if (other.eventTimeStamp2 != null) {
                    return false;
                }
            } else if (!eventTimeStamp2.equals(other.eventTimeStamp2)) {
                return false;
            }
            if (eventTimeStamp3 == null) {
                if (other.eventTimeStamp3 != null) {
                    return false;
                }
            } else if (!eventTimeStamp3.equals(other.eventTimeStamp3)) {
                return false;
            }
            if (notifyType == null) {
                if (other.notifyType != null) {
                    return false;
                }
            } else if (!notifyType.equals(other.notifyType)) {
                return false;
            }
            if (objectIdentifier == null) {
                if (other.objectIdentifier != null) {
                    return false;
                }
            } else if (!objectIdentifier.equals(other.objectIdentifier)) {
                return false;
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfEventSummaries == null) ? 0
                : listOfEventSummaries.hashCode());
        result = PRIME * result
                + ((moreEvents == null) ? 0 : moreEvents.hashCode());
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
        final GetEventInformationAck other = (GetEventInformationAck) obj;
        if (listOfEventSummaries == null) {
            if (other.listOfEventSummaries != null) {
                return false;
            }
        } else if (!listOfEventSummaries.equals(other.listOfEventSummaries)) {
            return false;
        }
        if (moreEvents == null) {
            if (other.moreEvents != null) {
                return false;
            }
        } else if (!moreEvents.equals(other.moreEvents)) {
            return false;
        }
        return true;
    }
}
