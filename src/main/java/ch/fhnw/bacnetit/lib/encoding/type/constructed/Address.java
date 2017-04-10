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

import java.net.InetSocketAddress;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Unsigned16;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Address extends BaseType {
    public static final int LOCAL_NETWORK = 0;

    public static final Address GLOBAL = new Address(new Unsigned16(0xFFFF),
            null);

    private static final long serialVersionUID = -3376358193474831753L;

    private final Unsigned16 networkNumber;

    private final OctetString macAddress;

    public Address(final int networkNumber, final byte[] macAddress) {
        this(new Unsigned16(networkNumber), new OctetString(macAddress));
    }

    public Address(final int networkNumber, final String dottedString) {
        this(new Unsigned16(networkNumber), new OctetString(dottedString));
    }

    public Address(final OctetString macAddress) {
        this(LOCAL_NETWORK, macAddress);
    }

    public Address(final int networkNumber, final OctetString macAddress) {
        this(new Unsigned16(networkNumber), macAddress);
    }

    public Address(final Unsigned16 networkNumber,
            final OctetString macAddress) {
        this.networkNumber = networkNumber;
        this.macAddress = macAddress;
    }

    /**
     * Convenience constructor for MS/TP addresses local to this network.
     *
     * @param station
     *            the station id
     */
    public Address(final byte station) {
        this(LOCAL_NETWORK, station);
    }

    /**
     * Convenience constructor for MS/TP addresses remote to this network.
     *
     * @param network
     * @param station
     */
    public Address(final int networkNumber, final byte station) {
        this.networkNumber = new Unsigned16(networkNumber);
        macAddress = new OctetString(new byte[] { station });
    }

    /**
     * Convenience constructor for IP addresses local to this network.
     *
     * @param ipAddress
     * @param port
     */
    public Address(final byte[] ipAddress, final int port) {
        this(LOCAL_NETWORK, ipAddress, port);
    }

    /**
     * Convenience constructor for IP addresses remote to this network.
     *
     * @param network
     * @param ipAddress
     * @param port
     */
    public Address(final int networkNumber, final byte[] ipAddress,
            final int port) {
        this.networkNumber = new Unsigned16(networkNumber);

        final byte[] ipMacAddress = new byte[ipAddress.length + 2];
        System.arraycopy(ipAddress, 0, ipMacAddress, 0, ipAddress.length);
        ipMacAddress[ipAddress.length] = (byte) (port >> 8);
        ipMacAddress[ipAddress.length + 1] = (byte) port;
        macAddress = new OctetString(ipMacAddress);
    }

    public Address(final String host, final int port) {
        this(LOCAL_NETWORK, host, port);
    }

    public Address(final int networkNumber, final String host, final int port) {
        // this(networkNumber, InetAddrCache.get(host, port));
        this(networkNumber, new InetSocketAddress(host, port));
    }

    public Address(final InetSocketAddress addr) {
        this(LOCAL_NETWORK, addr.getAddress().getAddress(), addr.getPort());
    }

    public Address(final int networkNumber, final InetSocketAddress addr) {
        this(networkNumber, addr.getAddress().getAddress(), addr.getPort());
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, networkNumber);
        write(queue, macAddress);
    }

    public Address(final ByteQueue queue) throws BACnetException {
        networkNumber = read(queue, Unsigned16.class);
        macAddress = read(queue, OctetString.class);
    }

    public OctetString getMacAddress() {
        return macAddress;
    }

    public UnsignedInteger getNetworkNumber() {
        return networkNumber;
    }

    public boolean isGlobal() {
        return networkNumber.intValue() == 0xFFFF;
    }

    //
    //
    // General convenience
    //
    public String getDescription() {
        final StringBuilder sb = new StringBuilder();
        sb.append(macAddress.getDescription());
        if (networkNumber.intValue() != 0) {
            sb.append('(').append(networkNumber).append(')');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Address [networkNumber=" + networkNumber + ", macAddress="
                + macAddress + "]";
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((macAddress == null) ? 0 : macAddress.hashCode());
        result = PRIME * result
                + ((networkNumber == null) ? 0 : networkNumber.hashCode());
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
        final Address other = (Address) obj;
        if (macAddress == null) {
            if (other.macAddress != null) {
                return false;
            }
        } else if (!macAddress.equals(other.macAddress)) {
            return false;
        }
        if (networkNumber == null) {
            if (other.networkNumber != null) {
                return false;
            }
        } else if (!networkNumber.equals(other.networkNumber)) {
            return false;
        }
        return true;
    }
}
