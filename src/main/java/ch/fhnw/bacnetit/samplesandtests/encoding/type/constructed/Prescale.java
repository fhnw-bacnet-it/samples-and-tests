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

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

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
