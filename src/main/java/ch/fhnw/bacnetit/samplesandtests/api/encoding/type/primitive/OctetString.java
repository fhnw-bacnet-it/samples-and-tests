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

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class OctetString extends Primitive {
    private static final long serialVersionUID = -3557657941142811228L;

    public static final byte TYPE_ID = 6;

    private final byte[] value;

    public OctetString(final byte[] value) {
        this.value = value;
    }

    public OctetString(final String dottedString) {
        // this(dottedString, UDPIpNetwork.DEFAULT_PORT);
        this(dottedString, 8007);
    }

    public OctetString(String dottedString, final int defaultPort) {
        dottedString = dottedString.trim();
        final int colon = dottedString.indexOf(":");
        if (colon == -1) {
            final byte[] b = BACnetUtils.dottedStringToBytes(dottedString);
            if (b.length == 4) {
                value = toBytes(b, defaultPort);
            } else {
                value = b;
            }
        } else {
            final byte[] ip = BACnetUtils
                    .dottedStringToBytes(dottedString.substring(0, colon));
            final int port = Integer
                    .parseInt(dottedString.substring(colon + 1));
            value = toBytes(ip, port);
        }
    }

    /**
     * Convenience constructor for MS/TP addresses local to this network.
     *
     * @param station
     *            the station id
     */
    public OctetString(final byte station) {
        value = new byte[] { station };
    }

    /**
     * Convenience constructor for IP addresses local to this network.
     *
     * @param ipAddress
     * @param port
     */
    public OctetString(final byte[] ipAddress, final int port) {
        value = toBytes(ipAddress, port);
    }

    public OctetString(final InetSocketAddress addr) {
        this(addr.getAddress().getAddress(), addr.getPort());
    }

    public byte[] getBytes() {
        return value;
    }

    private static byte[] toBytes(final byte[] ipAddress, final int port) {
        if (ipAddress.length != 4) {
            throw new IllegalArgumentException(
                    "IP address must have 4 parts, not " + ipAddress.length);
        }

        final byte[] b = new byte[6];
        System.arraycopy(ipAddress, 0, b, 0, ipAddress.length);
        b[ipAddress.length] = (byte) (port >> 8);
        b[ipAddress.length + 1] = (byte) port;
        return b;
    }

    //
    //
    // I/P convenience
    //
    public String getMacAddressDottedString() {
        return BACnetUtils.bytesToDottedString(value);
    }

    public InetAddress getInetAddress() {
        try {
            return InetAddress.getByAddress(getIpBytes());
        } catch (final UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    // public InetSocketAddress getInetSocketAddress() {
    // return InetAddrCache.get(getInetAddress(), getPort());
    // }

    public int getPort() {
        if (value.length == 6) {
            return ((value[4] & 0xff) << 8) | (value[5] & 0xff);
        }
        return -1;
    }

    // public String toIpString() {
    // return IpAddressUtils.toIpString(getIpBytes());
    // }

    // public String toIpPortString() {
    // return toIpString() + ":" + getPort();
    // }

    public byte[] getIpBytes() {
        if (value.length == 4) {
            return value;
        }

        final byte[] b = new byte[4];
        System.arraycopy(value, 0, b, 0, 4);
        return b;
    }

    //
    //
    // MS/TP convenience
    //
    public byte getMstpAddress() {
        return value[0];
    }

    //
    // Reading and writing
    //
    public OctetString(final ByteQueue queue) {
        final int length = (int) readTag(queue);
        value = new byte[length];
        queue.pop(value);
    }

    @Override
    public void writeImpl(final ByteQueue queue) {
        queue.push(value);
    }

    @Override
    public long getLength() {
        return value.length;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Arrays.hashCode(value);
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
        final OctetString other = (OctetString) obj;
        if (!Arrays.equals(value, other.value)) {
            return false;
        }
        return true;
    }

    // @Override
    // public String toString() {
    // return ArrayUtils.toHexString(value);
    // }

    public String getDescription() {
        final StringBuilder sb = new StringBuilder();
        if (value.length == 1) {
            // Assume an MS/TP address
            sb.append(getMstpAddress() & 0xff);
            // else if (value.length == 6)
            // // Assume an I/P address
            // sb.append(toIpPortString());
        } else {
            sb.append(toString());
        }
        return sb.toString();
    }
}
