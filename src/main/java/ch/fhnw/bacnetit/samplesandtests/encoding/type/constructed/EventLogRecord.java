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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class EventLogRecord extends BaseType {
    private static final long serialVersionUID = 7506599418976133752L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(LogStatus.class);
        // classes.add(ConfirmedEventNotificationRequest.class);
        classes.add(Real.class);
    }

    private final DateTime timestamp;

    private final Choice choice;

    public EventLogRecord(final DateTime timestamp, final LogStatus datum) {
        this.timestamp = timestamp;
        choice = new Choice(0, datum);
    }

    // public EventLogRecord(DateTime timestamp,
    // ConfirmedEventNotificationRequest datum) {
    // this.timestamp = timestamp;
    // choice = new Choice(1, datum);
    // }

    public EventLogRecord(final DateTime timestamp, final Real datum) {
        this.timestamp = timestamp;
        choice = new Choice(2, datum);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, timestamp, 0);
        write(queue, choice, 1);
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public LogStatus getLogStatus() {
        return (LogStatus) choice.getDatum();
    }

    // public ConfirmedEventNotificationRequest
    // getConfirmedEventNotificationRequest() {
    // return (ConfirmedEventNotificationRequest) choice.getDatum();
    // }

    public Real getReal() {
        return (Real) choice.getDatum();
    }

    public int getChoiceType() {
        return choice.getContextId();
    }

    public EventLogRecord(final ByteQueue queue) throws BACnetException {
        timestamp = read(queue, DateTime.class, 0);
        choice = new Choice(queue, classes, 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((choice == null) ? 0 : choice.hashCode());
        result = prime * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        final EventLogRecord other = (EventLogRecord) obj;
        if (choice == null) {
            if (other.choice != null) {
                return false;
            }
        } else if (!choice.equals(other.choice)) {
            return false;
        }
        if (timestamp == null) {
            if (other.timestamp != null) {
                return false;
            }
        } else if (!timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }
}
