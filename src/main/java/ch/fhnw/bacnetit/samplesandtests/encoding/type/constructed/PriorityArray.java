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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class PriorityArray extends SequenceOf<PriorityValue> {
    private static final long serialVersionUID = 8292702351986751796L;

    private static final int LENGTH = 16;

    public PriorityArray() {
        super(new ArrayList<PriorityValue>());
        ensureLength();
    }

    public PriorityArray(final List<PriorityValue> priorityValues) {
        super(priorityValues);
        ensureLength();
    }

    public PriorityArray(final ByteQueue queue, final int contextId)
            throws BACnetException {
        super(queue, PriorityValue.class, contextId);
        ensureLength();
    }

    private void ensureLength() {
        while (getCount() < LENGTH) {
            super.add(new PriorityValue(new Null()));
        }
        while (getCount() > LENGTH) {
            super.remove(getCount());
        }
    }

    @Override
    public void set(final int indexBase1, PriorityValue value) {
        if (indexBase1 < 1 || indexBase1 > LENGTH) {
            throw new RuntimeException("Invalid priority value");
        }
        if (value == null) {
            value = new PriorityValue(new Null());
        }
        super.set(indexBase1, value);
    }

    @Override
    public void add(final PriorityValue value) {
        throw new RuntimeException("Use set method instead");
    }

    @Override
    public void remove(final int indexBase1) {
        throw new RuntimeException("Use set method instead");
    }

    @Override
    public void remove(final PriorityValue value) {
        throw new RuntimeException("Use set method instead");
    }

    @Override
    public void removeAll(final PriorityValue value) {
        throw new RuntimeException("Use set method instead");
    }
}
