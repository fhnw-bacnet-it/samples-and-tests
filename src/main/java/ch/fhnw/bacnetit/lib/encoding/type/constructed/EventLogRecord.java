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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
