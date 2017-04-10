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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
