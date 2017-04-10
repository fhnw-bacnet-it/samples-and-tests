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

import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Choice extends BaseType {
    private static final long serialVersionUID = 7942157718147383894L;

    private final int contextId;

    private final Encodable datum;

    public Choice(final int contextId, final Encodable datum) {
        this.contextId = contextId;
        this.datum = datum;
    }

    public int getContextId() {
        return contextId;
    }

    public Encodable getDatum() {
        return datum;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, datum, contextId);
    }

    public Choice(final ByteQueue queue,
            final List<Class<? extends Encodable>> classes)
            throws BACnetException {
        final Choice c = read(queue, classes);
        this.contextId = c.contextId;
        this.datum = c.datum;
    }

    public Choice(final ByteQueue queue,
            final List<Class<? extends Encodable>> classes, final int contextId)
            throws BACnetException {
        popStart(queue, contextId);
        final Choice c = read(queue, classes);
        popEnd(queue, contextId);
        this.contextId = c.contextId;
        this.datum = c.datum;
    }

    private Choice read(final ByteQueue queue,
            final List<Class<? extends Encodable>> classes)
            throws BACnetException {
        final int tContextId = peekTagNumber(queue);
        final Encodable tDatum = read(queue, classes.get(tContextId),
                tContextId);
        return new Choice(tContextId, tDatum);
    }

    @Override
    public String toString() {
        return datum.toString();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + contextId;
        result = PRIME * result + ((datum == null) ? 0 : datum.hashCode());
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
        final Choice other = (Choice) obj;
        if (contextId != other.contextId) {
            return false;
        }
        if (datum == null) {
            if (other.datum != null) {
                return false;
            }
        } else if (!datum.equals(other.datum)) {
            return false;
        }
        return true;
    }
}
