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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive;

import java.io.UnsupportedEncodingException;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetRuntimeException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class CharacterString extends Primitive {
    private static final long serialVersionUID = -3146333907363025078L;

    public static final byte TYPE_ID = 7;

    public interface Encodings {
        byte ANSI_X3_4 = 0;

        byte IBM_MS_DBCS = 1;

        byte JIS_C_6226 = 2;

        byte ISO_10646_UCS_4 = 3;

        byte ISO_10646_UCS_2 = 4;

        byte ISO_8859_1 = 5;
    }

    private final byte encoding;

    private final String value;

    public CharacterString(final String value) {
        encoding = Encodings.ANSI_X3_4;
        this.value = value;
    }

    public CharacterString(final byte encoding, final String value) {
        try {
            validateEncoding();
        } catch (final BACnetErrorException e) {
            // This is an API constructor, so it doesn't need to throw checked
            // exceptions. Convert to runtime.
            throw new BACnetRuntimeException(e);
        }
        this.encoding = encoding;
        this.value = value;
    }

    public byte getEncoding() {
        return encoding;
    }

    public String getValue() {
        return value;
    }

    //
    // Reading and writing
    //
    public CharacterString(final ByteQueue queue) throws BACnetErrorException {
        final int length = (int) readTag(queue);

        encoding = queue.pop();
        validateEncoding();

        final byte[] bytes = new byte[length - 1];
        queue.pop(bytes);

        value = decode(encoding, bytes);
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        queue.push(encoding);
        queue.push(encode(encoding, value));
    }

    @Override
    protected long getLength() {
        return encode(encoding, value).length + 1;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    private static byte[] encode(final byte encoding, final String value) {
        try {
            switch (encoding) {
            case Encodings.ANSI_X3_4:
                return value.getBytes("UTF-8");
            case Encodings.ISO_10646_UCS_2:
                return value.getBytes("UTF-16");
            case Encodings.ISO_8859_1:
                return value.getBytes("ISO-8859-1");
            }
        } catch (final UnsupportedEncodingException e) {
            // Should never happen, so convert to a runtime exception.
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String decode(final byte encoding, final byte[] bytes) {
        try {
            switch (encoding) {
            case Encodings.ANSI_X3_4:
                return new String(bytes, "UTF-8");
            case Encodings.ISO_10646_UCS_2:
                return new String(bytes, "UTF-16");
            case Encodings.ISO_8859_1:
                return new String(bytes, "ISO-8859-1");
            }
        } catch (final UnsupportedEncodingException e) {
            // Should never happen, so convert to a runtime exception.
            throw new RuntimeException(e);
        }
        return null;
    }

    private void validateEncoding() throws BACnetErrorException {
        if (encoding != Encodings.ANSI_X3_4
                && encoding != Encodings.ISO_10646_UCS_2
                && encoding != Encodings.ISO_8859_1) {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.characterSetNotSupported,
                    Byte.toString(encoding));
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((value == null) ? 0 : value.hashCode());
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
        final CharacterString other = (CharacterString) obj;
        if (encoding != other.encoding) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }
}
