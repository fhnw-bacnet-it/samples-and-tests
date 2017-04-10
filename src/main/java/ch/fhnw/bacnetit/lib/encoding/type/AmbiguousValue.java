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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AmbiguousValue extends Encodable {
    private static final long serialVersionUID = -1554703777454557893L;

    private byte[] data;

    public AmbiguousValue(final ByteQueue queue) {
        final TagData tagData = new TagData();
        peekTagData(queue, tagData);
        readAmbiguousData(queue, tagData);
    }

    public AmbiguousValue(final ByteQueue queue, final int contextId)
            throws BACnetException {
        popStart(queue, contextId);

        final TagData tagData = new TagData();
        while (true) {
            peekTagData(queue, tagData);
            if (tagData.isEndTag(contextId)) {
                break;
            }
            readAmbiguousData(queue, tagData);
        }

        popEnd(queue, contextId);
    }

    @Override
    public void write(final ByteQueue queue, final int contextId) {
        throw new RuntimeException(
                "Don't write ambigous values, convert to actual types first");
    }

    @Override
    public void write(final ByteQueue queue) {
        throw new RuntimeException(
                "Don't write ambigous values, convert to actual types first");
    }

    private void readAmbiguousData(final ByteQueue queue,
            final TagData tagData) {
        final ByteQueue data = new ByteQueue();
        readAmbiguousData(queue, tagData, data);
        this.data = data.popAll();
    }

    private void readAmbiguousData(final ByteQueue queue, final TagData tagData,
            final ByteQueue data) {
        if (!tagData.contextSpecific) {
            // Application class.
            if (tagData.tagNumber == Boolean.TYPE_ID) {
                copyData(queue, 1, data);
            } else {
                copyData(queue, tagData.getTotalLength(), data);
            }
        } else {
            // Context specific class.
            if (tagData.isStartTag()) {
                // Copy the start tag
                copyData(queue, 1, data);

                // Remember the context id
                final int contextId = tagData.tagNumber;

                // Read ambiguous data until we find the end tag.
                while (true) {
                    peekTagData(queue, tagData);
                    if (tagData.isEndTag(contextId)) {
                        break;
                    }
                    readAmbiguousData(queue, tagData);
                }

                // Copy the end tag
                copyData(queue, 1, data);
            } else {
                copyData(queue, tagData.getTotalLength(), data);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuffer byteStream = new StringBuffer();
        for (final byte b : data) {
            byteStream.append(b);
        }
        return "Ambiguous(" + byteStream.toString() + ")";
    }

    private void copyData(final ByteQueue queue, int length,
            final ByteQueue data) {
        while (length-- > 0) {
            data.push(queue.pop());
        }
    }

    public boolean isNull() {
        return data.length == 1 && data[0] == 0;
    }

    public <T extends Encodable> T convertTo(final Class<T> clazz)
            throws BACnetException {
        return read(new ByteQueue(data), clazz);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((data == null) ? 0 : data.hashCode());
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
        if (!(obj instanceof Encodable)) {
            return false;
        }
        final Encodable eobj = (Encodable) obj;

        try {
            return convertTo(eobj.getClass()).equals(obj);
        } catch (final BACnetException e) {
            return false;
        }
    }
}
