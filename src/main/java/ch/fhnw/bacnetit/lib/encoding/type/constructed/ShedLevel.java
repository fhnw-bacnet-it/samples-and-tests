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
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
