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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.SequenceDefinition;
import ch.fhnw.bacnetit.lib.encoding.type.SequenceDefinition.ElementSpecification;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Sequence extends BaseType {
    private static final long serialVersionUID = -6678747788242195805L;

    private final SequenceDefinition definition;

    private final Map<String, Encodable> values;

    public Sequence(final SequenceDefinition definition,
            final Map<String, Encodable> values) {
        this.definition = definition;
        this.values = values;
    }

    public Sequence(final SequenceDefinition definition, final ByteQueue queue,
            final int contextId) throws BACnetException {
        this(definition, popStart0(queue, contextId));
        Encodable.popEnd(queue, contextId);
    }

    // The constructor call must be the first statement in the constructor (a
    // nuisance of a rule), so this static
    // method is required as a workaround. Ugly, but it works.
    private static ByteQueue popStart0(final ByteQueue queue,
            final int contextId) throws BACnetException {
        Encodable.popStart(queue, contextId);
        return queue;
    }

    public Sequence(final SequenceDefinition definition, final ByteQueue queue)
            throws BACnetException {
        this.definition = definition;
        values = new HashMap<String, Encodable>();
        for (final ElementSpecification spec : definition.getElements()) {
            if (spec.isSequenceOf()) {
                if (spec.isOptional()) {
                    values.put(spec.getId(), readOptionalSequenceOf(queue,
                            spec.getClazz(), spec.getContextId()));
                } else {
                    if (spec.hasContextId()) {
                        values.put(spec.getId(), readSequenceOf(queue,
                                spec.getClazz(), spec.getContextId()));
                    } else {
                        values.put(spec.getId(),
                                readSequenceOf(queue, spec.getClazz()));
                    }
                }
            } else if (spec.isOptional()) {
                values.put(spec.getId(), readOptional(queue, spec.getClazz(),
                        spec.getContextId()));
            } else if (spec.hasContextId()) {
                values.put(spec.getId(),
                        read(queue, spec.getClazz(), spec.getContextId()));
            } else {
                values.put(spec.getId(), read(queue, spec.getClazz()));
            }
        }
    }

    @Override
    public void write(final ByteQueue queue) {
        final List<ElementSpecification> specs = definition.getElements();
        for (final ElementSpecification spec : specs) {
            if (spec.isOptional()) {
                if (spec.hasContextId()) {
                    writeOptional(queue, values.get(spec.getId()),
                            spec.getContextId());
                } else {
                    writeOptional(queue, values.get(spec.getId()));
                }
            } else {
                if (spec.hasContextId()) {
                    write(queue, values.get(spec.getId()), spec.getContextId());
                } else {
                    write(queue, values.get(spec.getId()));
                }
            }
        }
    }

    public SequenceDefinition getDefinition() {
        return definition;
    }

    public Map<String, Encodable> getValues() {
        return values;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((values == null) ? 0 : values.hashCode());
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
        final Sequence other = (Sequence) obj;
        if (values == null) {
            if (other.values != null) {
                return false;
            }
        } else if (!values.equals(other.values)) {
            return false;
        }
        return true;
    }
}
