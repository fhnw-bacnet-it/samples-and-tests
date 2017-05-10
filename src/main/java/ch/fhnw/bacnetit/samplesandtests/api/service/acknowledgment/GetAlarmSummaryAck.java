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
package ch.fhnw.bacnetit.samplesandtests.api.service.acknowledgment;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.EventTransitionBits;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class GetAlarmSummaryAck extends AcknowledgementService {
    private static final long serialVersionUID = 6838220512669552863L;

    public static final byte TYPE_ID = 3;

    private final SequenceOf<AlarmSummary> values;

    public GetAlarmSummaryAck(final SequenceOf<AlarmSummary> values) {
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

    GetAlarmSummaryAck(final ByteQueue queue) throws BACnetException {
        values = readSequenceOf(queue, AlarmSummary.class);
    }

    public SequenceOf<AlarmSummary> getValues() {
        return values;
    }

    public static class AlarmSummary extends BaseType {
        private static final long serialVersionUID = -3442490797564872769L;

        private final BACnetObjectIdentifier objectIdentifier;

        private final EventState alarmState;

        private final EventTransitionBits acknowledgedTransitions;

        public AlarmSummary(final BACnetObjectIdentifier objectIdentifier,
                final EventState alarmState,
                final EventTransitionBits acknowledgedTransitions) {
            this.objectIdentifier = objectIdentifier;
            this.alarmState = alarmState;
            this.acknowledgedTransitions = acknowledgedTransitions;
        }

        @Override
        public void write(final ByteQueue queue) {
            objectIdentifier.write(queue);
            alarmState.write(queue);
            acknowledgedTransitions.write(queue);
        }

        public AlarmSummary(final ByteQueue queue) throws BACnetException {
            objectIdentifier = read(queue, BACnetObjectIdentifier.class);
            alarmState = read(queue, EventState.class);
            acknowledgedTransitions = read(queue, EventTransitionBits.class);
        }

        public BACnetObjectIdentifier getObjectIdentifier() {
            return objectIdentifier;
        }

        public EventState getAlarmState() {
            return alarmState;
        }

        public EventTransitionBits getAcknowledgedTransitions() {
            return acknowledgedTransitions;
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((acknowledgedTransitions == null) ? 0
                    : acknowledgedTransitions.hashCode());
            result = PRIME * result
                    + ((alarmState == null) ? 0 : alarmState.hashCode());
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
            final AlarmSummary other = (AlarmSummary) obj;
            if (acknowledgedTransitions == null) {
                if (other.acknowledgedTransitions != null) {
                    return false;
                }
            } else if (!acknowledgedTransitions
                    .equals(other.acknowledgedTransitions)) {
                return false;
            }
            if (alarmState == null) {
                if (other.alarmState != null) {
                    return false;
                }
            } else if (!alarmState.equals(other.alarmState)) {
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
        final GetAlarmSummaryAck other = (GetAlarmSummaryAck) obj;
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
