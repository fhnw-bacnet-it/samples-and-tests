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
package ch.fhnw.bacnetit.lib.encoding.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SequenceDefinition implements Serializable {
    private static final long serialVersionUID = 6464244006575549887L;

    private final List<ElementSpecification> elements;

    public SequenceDefinition(final ElementSpecification... specs) {
        elements = new ArrayList<SequenceDefinition.ElementSpecification>();
        for (final ElementSpecification spec : specs) {
            elements.add(spec);
        }
    }

    public SequenceDefinition(final List<ElementSpecification> elements) {
        this.elements = elements;
    }

    public List<ElementSpecification> getElements() {
        return elements;
    }

    public static class ElementSpecification {
        private final String id;

        private final Class<? extends Encodable> clazz;

        private final int contextId;

        private final boolean isSequenceOf;

        private final boolean isOptional;

        public ElementSpecification(final String id,
                final Class<? extends Encodable> clazz,
                final boolean isSequenceOf, final boolean isOptional) {
            this.id = id;
            this.clazz = clazz;
            this.contextId = -1;
            this.isSequenceOf = isSequenceOf;
            this.isOptional = isOptional;
        }

        public ElementSpecification(final String id,
                final Class<? extends Encodable> clazz, final int contextId,
                final boolean isSequenceOf, final boolean isOptional) {
            this.id = id;
            this.clazz = clazz;
            this.contextId = contextId;
            this.isSequenceOf = isSequenceOf;
            this.isOptional = isOptional;
        }

        public String getId() {
            return id;
        }

        public Class<? extends Encodable> getClazz() {
            return clazz;
        }

        public int getContextId() {
            return contextId;
        }

        public boolean isOptional() {
            return isOptional;
        }

        public boolean isSequenceOf() {
            return isSequenceOf;
        }

        public boolean hasContextId() {
            return contextId != -1;
        }
    }
}
