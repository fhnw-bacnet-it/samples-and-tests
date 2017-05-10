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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
