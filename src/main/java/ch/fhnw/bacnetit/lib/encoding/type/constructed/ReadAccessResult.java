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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.ThreadLocalObjectTypeStack;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ReadAccessResult extends BaseType {
    private static final long serialVersionUID = -5046824677263394587L;

    private final BACnetObjectIdentifier objectIdentifier;

    private final SequenceOf<Result> listOfResults;

    public ReadAccessResult(final BACnetObjectIdentifier objectIdentifier,
            final SequenceOf<Result> listOfResults) {
        this.objectIdentifier = objectIdentifier;
        this.listOfResults = listOfResults;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        writeOptional(queue, listOfResults, 1);
    }

    @Override
    public String toString() {
        return "ReadAccessResult(oid=" + objectIdentifier + ", results="
                + listOfResults + ")";
    }

    public SequenceOf<Result> getListOfResults() {
        return listOfResults;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }

    public ReadAccessResult(final ByteQueue queue) throws BACnetException {
        objectIdentifier = read(queue, BACnetObjectIdentifier.class, 0);
        try {
            ThreadLocalObjectTypeStack.set(objectIdentifier.getObjectType());
            listOfResults = readOptionalSequenceOf(queue, Result.class, 1);
        } finally {
            ThreadLocalObjectTypeStack.remove();
        }
    }

    public static class Result extends BaseType {
        private static final long serialVersionUID = -2539614773155916196L;

        private final BACnetPropertyIdentifier propertyIdentifier;

        private final UnsignedInteger propertyArrayIndex;

        private Choice readResult;

        public Result(final BACnetPropertyIdentifier propertyIdentifier,
                final UnsignedInteger propertyArrayIndex,
                final Encodable readResult) {
            this.propertyIdentifier = propertyIdentifier;
            this.propertyArrayIndex = propertyArrayIndex;
            this.readResult = new Choice(4, readResult);
        }

        public Result(final BACnetPropertyIdentifier propertyIdentifier,
                final UnsignedInteger propertyArrayIndex,
                final BACnetError readResult) {
            this.propertyIdentifier = propertyIdentifier;
            this.propertyArrayIndex = propertyArrayIndex;
            this.readResult = new Choice(5, readResult);
        }

        public UnsignedInteger getPropertyArrayIndex() {
            return propertyArrayIndex;
        }

        public BACnetPropertyIdentifier getPropertyIdentifier() {
            return propertyIdentifier;
        }

        public boolean isError() {
            return readResult.getContextId() == 5;
        }

        public Choice getReadResult() {
            return readResult;
        }

        @Override
        public String toString() {
            return "Result(pid=" + propertyIdentifier
                    + (propertyArrayIndex == null ? ""
                            : ", pin=" + propertyArrayIndex)
                    + ", value=" + readResult + ")";
        }

        @Override
        public void write(final ByteQueue queue) {
            write(queue, propertyIdentifier, 2);
            writeOptional(queue, propertyArrayIndex, 3);
            if (readResult.getContextId() == 4) {
                writeEncodable(queue, readResult.getDatum(), 4);
            } else {
                write(queue, readResult.getDatum(), 5);
            }
        }

        public Result(final ByteQueue queue) throws BACnetException {
            propertyIdentifier = read(queue, BACnetPropertyIdentifier.class, 2);
            propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 3);
            final int contextId = peekTagNumber(queue);
            if (contextId == 4) {
                readResult = new Choice(4,
                        readEncodable(queue, ThreadLocalObjectTypeStack.get(),
                                propertyIdentifier, propertyArrayIndex, 4));
            } else {
                readResult = new Choice(5, read(queue, BACnetError.class, 5));
            }
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((propertyArrayIndex == null) ? 0
                    : propertyArrayIndex.hashCode());
            result = PRIME * result + ((propertyIdentifier == null) ? 0
                    : propertyIdentifier.hashCode());
            result = PRIME * result
                    + ((readResult == null) ? 0 : readResult.hashCode());
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
            final Result other = (Result) obj;
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
            if (readResult == null) {
                if (other.readResult != null) {
                    return false;
                }
            } else if (!readResult.equals(other.readResult)) {
                return false;
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((listOfResults == null) ? 0 : listOfResults.hashCode());
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
        final ReadAccessResult other = (ReadAccessResult) obj;
        if (listOfResults == null) {
            if (other.listOfResults != null) {
                return false;
            }
        } else if (!listOfResults.equals(other.listOfResults)) {
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
