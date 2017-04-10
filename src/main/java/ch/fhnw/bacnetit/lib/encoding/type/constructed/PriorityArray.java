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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
