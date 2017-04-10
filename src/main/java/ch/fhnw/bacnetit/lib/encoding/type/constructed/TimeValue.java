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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Primitive;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Time;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 712 <br>
 * BACnetTimeValue ::= SEQUENCE { <br>
 * time Time, <br>
 * value ABSTRACT-SYNTAX.&Type -- any primitive datatype; complex types cannot
 * be decoded <br>
 * }
 */
public class TimeValue extends BaseType {
    private static final long serialVersionUID = 6449737212397369712L;

    private final Time time;

    private final Primitive value;

    public TimeValue(final Time time, final Primitive value) {
        this.time = time;
        this.value = value;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, time);
        write(queue, value);
    }

    public TimeValue(final ByteQueue queue) throws BACnetException {
        time = read(queue, Time.class);
        value = read(queue, Primitive.class);
    }

    public Time getTime() {
        return time;
    }

    public Primitive getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((time == null) ? 0 : time.hashCode());
        result = PRIME * result + ((value == null) ? 0 : value.hashCode());
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
        final TimeValue other = (TimeValue) obj;
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
