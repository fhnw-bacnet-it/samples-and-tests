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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class ReadRangeRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 7357639261721076121L;

    public static final byte TYPE_ID = 26;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Encodable.class);
        classes.add(Encodable.class);
        classes.add(Encodable.class);
        classes.add(ByPosition.class);
        classes.add(Encodable.class);
        classes.add(Encodable.class);
        classes.add(BySequenceNumber.class);
        classes.add(ByTime.class);
    }

    private final BACnetObjectIdentifier objectIdentifier;

    private final BACnetPropertyIdentifier propertyIdentifier;

    private final UnsignedInteger propertyArrayIndex;

    private Choice range;

    private ReadRangeRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex) {
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
    }

    public ReadRangeRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex, final ByPosition range) {
        this(objectIdentifier, propertyIdentifier, propertyArrayIndex);
        this.range = new Choice(3, range);
    }

    public ReadRangeRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex,
            final BySequenceNumber range) {
        this(objectIdentifier, propertyIdentifier, propertyArrayIndex);
        this.range = new Choice(6, range);
    }

    public ReadRangeRequest(final BACnetObjectIdentifier objectIdentifier,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex, final ByTime range) {
        this(objectIdentifier, propertyIdentifier, propertyArrayIndex);
        this.range = new Choice(7, range);
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        write(queue, propertyIdentifier, 1);
        writeOptional(queue, propertyArrayIndex, 2);
        writeOptional(queue, range);
    }

    ReadRangeRequest(final ByteQueue queue) throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 1);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 2);
        if (peekTagNumber(queue) != -1) {
            range = new Choice(queue, classes);
        }
    }

    abstract public static class Range extends BaseType {
        private static final long serialVersionUID = 7079223629723797175L;

        protected SignedInteger count;

        public Range(final SignedInteger count) {
            this.count = count;
        }

        Range() {
            // no op
        }
    }

    public static class ByPosition extends Range {
        private static final long serialVersionUID = 4251374814674312091L;

        private final UnsignedInteger referenceIndex;

        public ByPosition(final UnsignedInteger referenceIndex,
                final SignedInteger count) {
            super(count);
            this.referenceIndex = referenceIndex;
        }

        @Override
        public void write(final ByteQueue queue) {
            write(queue, referenceIndex);
            write(queue, count);
        }

        ByPosition(final ByteQueue queue) throws BACnetException {
            referenceIndex = read(queue, UnsignedInteger.class);
            count = read(queue, SignedInteger.class);
        }
    }

    public static class BySequenceNumber extends Range {
        private static final long serialVersionUID = 4218886072119156278L;

        private final UnsignedInteger referenceIndex;

        public BySequenceNumber(final UnsignedInteger referenceIndex,
                final SignedInteger count) {
            super(count);
            this.referenceIndex = referenceIndex;
        }

        @Override
        public void write(final ByteQueue queue) {
            write(queue, referenceIndex);
            write(queue, count);
        }

        BySequenceNumber(final ByteQueue queue) throws BACnetException {
            referenceIndex = read(queue, UnsignedInteger.class);
            count = read(queue, SignedInteger.class);
        }
    }

    public static class ByTime extends Range {
        private static final long serialVersionUID = 6322007580214441250L;

        private final DateTime referenceTime;

        public ByTime(final DateTime referenceTime, final SignedInteger count) {
            super(count);
            this.referenceTime = referenceTime;
        }

        @Override
        public void write(final ByteQueue queue) {
            write(queue, referenceTime);
            write(queue, count);
        }

        ByTime(final ByteQueue queue) throws BACnetException {
            referenceTime = read(queue, DateTime.class);
            count = read(queue, SignedInteger.class);
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0
                : objectIdentifier.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0
                : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0
                : propertyIdentifier.hashCode());
        result = PRIME * result + ((range == null) ? 0 : range.hashCode());
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
        final ReadRangeRequest other = (ReadRangeRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null) {
                return false;
            }
        } else if (!objectIdentifier.equals(other.objectIdentifier)) {
            return false;
        }
        if (propertyArrayIndex == null) {
            if (other.propertyArrayIndex != null) {
                return false;
            }
        } else if (!propertyArrayIndex.equals(other.propertyArrayIndex)) {
            return false;
        }
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null) {
                return false;
            }
        } else if (!propertyIdentifier.equals(other.propertyIdentifier)) {
            return false;
        }
        if (range == null) {
            if (other.range != null) {
                return false;
            }
        } else if (!range.equals(other.range)) {
            return false;
        }
        return true;
    }
}
