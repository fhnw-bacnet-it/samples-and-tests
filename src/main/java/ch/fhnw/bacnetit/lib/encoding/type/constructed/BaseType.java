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

import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

abstract public class BaseType extends Encodable {
    private static final long serialVersionUID = -2536344211247711774L;

    @Override
    public void write(final ByteQueue queue, final int contextId) {
        // Write a start tag
        writeContextTag(queue, contextId, true);
        write(queue);
        // Write an end tag
        writeContextTag(queue, contextId, false);
    }
}
