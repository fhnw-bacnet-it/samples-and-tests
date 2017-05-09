
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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.fhnw.bacnetit.ase.encoding.UnsignedInteger8;
import ch.fhnw.bacnetit.ase.encoding._ByteQueue;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.samplesandtests.deviceobjects.Service;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.WhoIsRequest;

//3.1 Unicast Unconfirmed Who-Is Request with No Filters
public class UnicastUnconfWhoIsTest {

    @Test
    public void UnicastUnconfWhoIs() {
        final BACnetEID source = new BACnetEID(9876,
                BACnetEID.BACnetEIDOption.DEVICE);
        final BACnetEID destination = new BACnetEID(12345,
                BACnetEID.BACnetEIDOption.DEVICE);
        final UnsignedInteger8 forwards = new UnsignedInteger8(10);
        final Service service = new WhoIsRequest();
        final ByteQueue q = new ByteQueue();
        service.write(q);
        final TPDU apdu = new TPDU(null, source, destination, null, forwards,
                null, q.popAll());

        final _ByteQueue queue = new _ByteQueue();
        apdu.write(queue);

        final ByteQueue expected = new ByteQueue();
        // Entered from BACnet IT Encoding Example v0.3
        final byte[] expectedBytes = new byte[] { (byte) 0x0E, (byte) 0x09,
                (byte) 0x2, (byte) 0x2E, (byte) 0x1A, (byte) 0x26, (byte) 0x94,
                (byte) 0x2F, (byte) 0x3E, (byte) 0x1A, (byte) 0x30, (byte) 0x39,
                (byte) 0x3F, (byte) 0x59, (byte) 0x0A, (byte) 0x0F, (byte) 0x1E,
                (byte) 0x1E, (byte) 0x8E, (byte) 0x8F, (byte) 0x1F,
                (byte) 0x1F };
        expected.push(expectedBytes);

        System.out.println(queue);
        System.out.println(expected);
        assertTrue(queue.toString().equals(expected.toString()));

    }

}
