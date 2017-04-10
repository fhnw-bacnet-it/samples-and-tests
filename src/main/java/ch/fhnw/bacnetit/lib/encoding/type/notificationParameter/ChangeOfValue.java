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
package ch.fhnw.bacnetit.lib.encoding.type.notificationParameter;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ChangeOfValue extends NotificationParameters {
    private static final long serialVersionUID = -5744561310526587892L;

    public static final byte TYPE_ID = 2;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(BitString.class);
        classes.add(Real.class);
    }

    private final Choice newValue;

    private final StatusFlags statusFlags;

    public ChangeOfValue(final BitString newValue,
            final StatusFlags statusFlags) {
        this.newValue = new Choice(0, newValue);
        this.statusFlags = statusFlags;
    }

    public ChangeOfValue(final Real newValue, final StatusFlags statusFlags) {
        this.newValue = new Choice(1, newValue);
        this.statusFlags = statusFlags;
    }

    @Override
    protected void writeImpl(final ByteQueue queue) {
        write(queue, newValue, 0);
        write(queue, statusFlags, 1);
    }

    public ChangeOfValue(final ByteQueue queue) throws BACnetException {
        newValue = new Choice(queue, classes, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public Choice getNewValue() {
        return newValue;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((newValue == null) ? 0 : newValue.hashCode());
        result = PRIME * result
                + ((statusFlags == null) ? 0 : statusFlags.hashCode());
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
        final ChangeOfValue other = (ChangeOfValue) obj;
        if (newValue == null) {
            if (other.newValue != null) {
                return false;
            }
        } else if (!newValue.equals(other.newValue)) {
            return false;
        }
        if (statusFlags == null) {
            if (other.statusFlags != null) {
                return false;
            }
        } else if (!statusFlags.equals(other.statusFlags)) {
            return false;
        }
        return true;
    }
}
