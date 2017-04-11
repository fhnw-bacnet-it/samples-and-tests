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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.AmbiguousValue;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.BinaryPV;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class PriorityValue extends BaseType {
    private static final long serialVersionUID = 213834169635261132L;

    private Null nullValue;

    private Real realValue;

    private BinaryPV binaryValue;

    private UnsignedInteger integerValue;

    private Encodable constructedValue;

    public PriorityValue(final Null nullValue) {
        this.nullValue = nullValue;
    }

    public PriorityValue(final Real realValue) {
        this.realValue = realValue;
    }

    public PriorityValue(final BinaryPV binaryValue) {
        this.binaryValue = binaryValue;
    }

    public PriorityValue(final UnsignedInteger integerValue) {
        this.integerValue = integerValue;
    }

    public PriorityValue(final BaseType constructedValue) {
        this.constructedValue = constructedValue;
    }

    public Null getNullValue() {
        return nullValue;
    }

    public Real getRealValue() {
        return realValue;
    }

    public BinaryPV getBinaryValue() {
        return binaryValue;
    }

    public UnsignedInteger getIntegerValue() {
        return integerValue;
    }

    public Encodable getConstructedValue() {
        return constructedValue;
    }

    public boolean isNull() {
        return nullValue != null;
    }

    public Encodable getValue() {
        if (nullValue != null) {
            return nullValue;
        }
        if (realValue != null) {
            return realValue;
        }
        if (binaryValue != null) {
            return binaryValue;
        }
        if (integerValue != null) {
            return integerValue;
        }
        return constructedValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PriorityValue(");
        if (nullValue != null) {
            sb.append("nullValue=").append(nullValue);
        } else if (realValue != null) {
            sb.append("realValue=").append(realValue);
        } else if (binaryValue != null) {
            sb.append("binaryValue=").append(binaryValue);
        } else if (integerValue != null) {
            sb.append("integerValue=").append(integerValue);
        } else if (constructedValue != null) {
            sb.append("constructedValue=").append(constructedValue);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void write(final ByteQueue queue) {
        if (nullValue != null) {
            nullValue.write(queue);
        } else if (realValue != null) {
            realValue.write(queue);
        } else if (binaryValue != null) {
            binaryValue.write(queue);
        } else if (integerValue != null) {
            integerValue.write(queue);
        } else {
            constructedValue.write(queue, 0);
        }
    }

    public PriorityValue(final ByteQueue queue) throws BACnetException {
        // Sweet Jesus...
        int tag = (queue.peek(0) & 0xff);
        if ((tag & 8) == 8) {
            // A class tag, so this is a constructed value.
            constructedValue = new AmbiguousValue(queue, 0);
        } else {
            // A primitive value
            tag = tag >> 4;
            if (tag == Null.TYPE_ID) {
                nullValue = new Null(queue);
            } else if (tag == Real.TYPE_ID) {
                realValue = new Real(queue);
            } else if (tag == Enumerated.TYPE_ID) {
                binaryValue = new BinaryPV(queue);
            } else if (tag == UnsignedInteger.TYPE_ID) {
                integerValue = new UnsignedInteger(queue);
            } else {
                throw new BACnetErrorException(ErrorClass.property,
                        ErrorCode.invalidDataType,
                        "Unsupported primitive id: " + tag);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((binaryValue == null) ? 0 : binaryValue.hashCode());
        result = prime * result + ((constructedValue == null) ? 0
                : constructedValue.hashCode());
        result = prime * result
                + ((integerValue == null) ? 0 : integerValue.hashCode());
        result = prime * result
                + ((nullValue == null) ? 0 : nullValue.hashCode());
        result = prime * result
                + ((realValue == null) ? 0 : realValue.hashCode());
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
        final PriorityValue other = (PriorityValue) obj;
        if (binaryValue == null) {
            if (other.binaryValue != null) {
                return false;
            }
        } else if (!binaryValue.equals(other.binaryValue)) {
            return false;
        }
        if (constructedValue == null) {
            if (other.constructedValue != null) {
                return false;
            }
        } else if (!constructedValue.equals(other.constructedValue)) {
            return false;
        }
        if (integerValue == null) {
            if (other.integerValue != null) {
                return false;
            }
        } else if (!integerValue.equals(other.integerValue)) {
            return false;
        }
        if (nullValue == null) {
            if (other.nullValue != null) {
                return false;
            }
        } else if (!nullValue.equals(other.nullValue)) {
            return false;
        }
        if (realValue == null) {
            if (other.realValue != null) {
                return false;
            }
        } else if (!realValue.equals(other.realValue)) {
            return false;
        }
        return true;
    }
}
