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
package ch.fhnw.bacnetit.misc.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.type.Encodable;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class ShedLevel extends BaseType {
    private static final long serialVersionUID = 8550443800962401306L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(UnsignedInteger.class);
        classes.add(UnsignedInteger.class);
        classes.add(Real.class);
    }

    private final Choice choice;

    public ShedLevel(final UnsignedInteger datum, final boolean percent) {
        if (percent) {
            choice = new Choice(0, datum);
        } else {
            choice = new Choice(1, datum);
        }
    }

    public ShedLevel(final Real amount) {
        choice = new Choice(2, amount);
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, choice);
    }

    public UnsignedInteger getPercent() {
        return (UnsignedInteger) choice.getDatum();
    }

    public UnsignedInteger getLevel() {
        return (UnsignedInteger) choice.getDatum();
    }

    public Real getAmount() {
        return (Real) choice.getDatum();
    }

    public int getChoiceType() {
        return choice.getContextId();
    }

    public ShedLevel(final ByteQueue queue) throws BACnetException {
        choice = new Choice(queue, classes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((choice == null) ? 0 : choice.hashCode());
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
        final ShedLevel other = (ShedLevel) obj;
        if (choice == null) {
            if (other.choice != null) {
                return false;
            }
        } else if (!choice.equals(other.choice)) {
            return false;
        }
        return true;
    }
}
