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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class Scale extends BaseType {
    private static final long serialVersionUID = 5351842576726308269L;

    private final Choice scale;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Real.class);
        classes.add(SignedInteger.class);
    }

    public Scale(final Real scale) {
        this.scale = new Choice(0, scale);
    }

    public Scale(final SignedInteger scale) {
        this.scale = new Choice(1, scale);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, scale);
    }

    public Scale(final ByteQueue queue) throws BACnetException {
        scale = new Choice(queue, classes);
    }

    public boolean isReal() {
        return scale.getContextId() == 0;
    }

    public Real getReal() {
        return (Real) scale.getDatum();
    }

    public SignedInteger getSignedInteger() {
        return (SignedInteger) scale.getDatum();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((scale == null) ? 0 : scale.hashCode());
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
        final Scale other = (Scale) obj;
        if (scale == null) {
            if (other.scale != null) {
                return false;
            }
        } else if (!scale.equals(other.scale)) {
            return false;
        }
        return true;
    }
}
