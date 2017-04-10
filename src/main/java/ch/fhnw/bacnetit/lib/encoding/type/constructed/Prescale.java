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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Prescale extends BaseType {
    private static final long serialVersionUID = -4499669866775224187L;

    private final UnsignedInteger multiplier;

    private final UnsignedInteger moduloDivide;

    public Prescale(final UnsignedInteger multiplier,
            final UnsignedInteger moduloDivide) {
        this.multiplier = multiplier;
        this.moduloDivide = moduloDivide;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, multiplier, 0);
        write(queue, moduloDivide, 1);
    }

    public Prescale(final ByteQueue queue) throws BACnetException {
        multiplier = read(queue, UnsignedInteger.class, 0);
        moduloDivide = read(queue, UnsignedInteger.class, 1);
    }

    public UnsignedInteger getMultiplier() {
        return multiplier;
    }

    public UnsignedInteger getModuloDivide() {
        return moduloDivide;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((moduloDivide == null) ? 0 : moduloDivide.hashCode());
        result = PRIME * result
                + ((multiplier == null) ? 0 : multiplier.hashCode());
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
        final Prescale other = (Prescale) obj;
        if (moduloDivide == null) {
            if (other.moduloDivide != null) {
                return false;
            }
        } else if (!moduloDivide.equals(other.moduloDivide)) {
            return false;
        }
        if (multiplier == null) {
            if (other.multiplier != null) {
                return false;
            }
        } else if (!multiplier.equals(other.multiplier)) {
            return false;
        }
        return true;
    }
}
