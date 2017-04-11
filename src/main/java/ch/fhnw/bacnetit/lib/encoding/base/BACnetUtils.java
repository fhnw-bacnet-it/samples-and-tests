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
package ch.fhnw.bacnetit.lib.encoding.base;

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BACnetUtils {
    public static void pushShort(final ByteQueue queue, final long value) {
        queue.push((byte) (0xff & (value >> 8)));
        queue.push((byte) (0xff & value));
    }

    public static void pushInt(final ByteQueue queue, final long value) {
        queue.push((byte) (0xff & (value >> 24)));
        queue.push((byte) (0xff & (value >> 16)));
        queue.push((byte) (0xff & (value >> 8)));
        queue.push((byte) (0xff & value));
    }

    public static void pushLong(final ByteQueue queue, final long value) {
        queue.push((byte) (0xff & (value >> 56)));
        queue.push((byte) (0xff & (value >> 48)));
        queue.push((byte) (0xff & (value >> 40)));
        queue.push((byte) (0xff & (value >> 32)));
        queue.push((byte) (0xff & (value >> 24)));
        queue.push((byte) (0xff & (value >> 16)));
        queue.push((byte) (0xff & (value >> 8)));
        queue.push((byte) (0xff & value));
    }

    public static int popShort(final ByteQueue queue) {
        return (short) ((toInt(queue.pop()) << 8) | toInt(queue.pop()));
    }

    public static int popInt(final ByteQueue queue) {
        return (toInt(queue.pop()) << 24) | (toInt(queue.pop()) << 16)
                | (toInt(queue.pop()) << 8) | toInt(queue.pop());
    }

    public static long popLong(final ByteQueue queue) {
        return (toLong(queue.pop()) << 56) | (toLong(queue.pop()) << 48)
                | (toLong(queue.pop()) << 40) | (toLong(queue.pop()) << 32)
                | (toLong(queue.pop()) << 24) | (toLong(queue.pop()) << 16)
                | (toLong(queue.pop()) << 8) | toLong(queue.pop());
    }

    public static int toInt(final byte b) {
        return b & 0xff;
    }

    public static long toLong(final byte b) {
        return (b & 0xff);
    }

    public static byte[] convertToBytes(final boolean[] bdata) {
        final int byteCount = (bdata.length + 7) / 8;
        final byte[] data = new byte[byteCount];
        for (int i = 0; i < bdata.length; i++) {
            data[i / 8] |= (bdata[i] ? 1 : 0) << (7 - (i % 8));
        }
        return data;
    }

    public static boolean[] convertToBooleans(final byte[] data,
            final int length) {
        final boolean[] bdata = new boolean[length];
        for (int i = 0; i < bdata.length; i++) {
            bdata[i] = ((data[i / 8] >> (7 - (i % 8))) & 0x1) == 1;
        }
        return bdata;
    }

    public static byte[] dottedStringToBytes(final String s)
            throws NumberFormatException {
        final String[] parts = s.split("\\.");
        final byte[] b = new byte[parts.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(parts[i]);
        }
        return b;
    }

    public static String bytesToDottedString(final byte[] b) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append(0xff & b[i]);
        }
        return sb.toString();
    }
}
