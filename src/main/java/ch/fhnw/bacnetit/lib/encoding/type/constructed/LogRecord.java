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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class LogRecord extends BaseType {
    private static final long serialVersionUID = -158114439196293884L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(LogStatus.class);
        classes.add(Boolean.class);
        classes.add(Real.class);
        classes.add(Enumerated.class);
        classes.add(UnsignedInteger.class);
        classes.add(SignedInteger.class);
        classes.add(BitString.class);
        classes.add(Null.class);
        classes.add(BACnetError.class);
        classes.add(Real.class);
        classes.add(Encodable.class);
    }

    private final DateTime timestamp;

    private Choice choice;

    private final StatusFlags statusFlags;

    private LogRecord(final DateTime timestamp, final StatusFlags statusFlags) {
        this.timestamp = timestamp;
        this.statusFlags = statusFlags;
    }

    public LogRecord(final DateTime timestamp, final LogStatus datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(0, datum);
    }

    public LogRecord(final DateTime timestamp, final Boolean datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(1, datum);
    }

    public LogRecord(final DateTime timestamp, final boolean timeChange,
            final Real datum, final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(timeChange ? 9 : 2, datum);
    }

    public LogRecord(final DateTime timestamp, final Enumerated datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(3, datum);
    }

    public LogRecord(final DateTime timestamp, final UnsignedInteger datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(4, datum);
    }

    public LogRecord(final DateTime timestamp, final SignedInteger datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(5, datum);
    }

    public LogRecord(final DateTime timestamp, final BitString datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(6, datum);
    }

    public LogRecord(final DateTime timestamp, final Null datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(7, datum);
    }

    public LogRecord(final DateTime timestamp, final BACnetError datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(8, datum);
    }

    public LogRecord(final DateTime timestamp, final Encodable datum,
            final StatusFlags statusFlags) {
        this(timestamp, statusFlags);
        choice = new Choice(10, datum);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, timestamp, 0);
        write(queue, choice, 1);
        writeOptional(queue, statusFlags, 2);
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public LogStatus getLogStatus() {
        return (LogStatus) choice.getDatum();
    }

    public Boolean getBoolean() {
        return (Boolean) choice.getDatum();
    }

    public Real getReal() {
        return (Real) choice.getDatum();
    }

    public Real getTimeChange() {
        return (Real) choice.getDatum();
    }

    public Enumerated getEnumerated() {
        return (Enumerated) choice.getDatum();
    }

    public UnsignedInteger getUnsignedInteger() {
        return (UnsignedInteger) choice.getDatum();
    }

    public SignedInteger getSignedInteger() {
        return (SignedInteger) choice.getDatum();
    }

    public BitString getBitString() {
        return (BitString) choice.getDatum();
    }

    public Null getNull() {
        return (Null) choice.getDatum();
    }

    public BACnetError getBACnetError() {
        return (BACnetError) choice.getDatum();
    }

    public Encodable getEncodable() {
        return choice.getDatum();
    }

    public int getChoiceType() {
        return choice.getContextId();
    }

    public LogRecord(final ByteQueue queue) throws BACnetException {
        timestamp = read(queue, DateTime.class, 0);
        choice = new Choice(queue, classes, 1);
        statusFlags = readOptional(queue, StatusFlags.class, 2);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((choice == null) ? 0 : choice.hashCode());
        result = PRIME * result
                + ((statusFlags == null) ? 0 : statusFlags.hashCode());
        result = PRIME * result
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
        final LogRecord other = (LogRecord) obj;
        if (choice == null) {
            if (other.choice != null) {
                return false;
            }
        } else if (!choice.equals(other.choice)) {
            return false;
        }
        if (statusFlags == null) {
            if (other.statusFlags != null) {
                return false;
            }
        } else if (!statusFlags.equals(other.statusFlags)) {
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
