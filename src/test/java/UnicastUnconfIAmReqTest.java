
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

import ch.fhnw.bacnetit.ase.encoding.NetworkPriority;
import ch.fhnw.bacnetit.ase.encoding.UnsignedInteger31;
import ch.fhnw.bacnetit.ase.encoding._ByteQueue;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.Service;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Segmentation;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.unconfirmed.IAmRequest;

public class UnicastUnconfIAmReqTest {

    @Test
    public void UnicastUnconfIAmReq() {
        // Setup I Am Request
        final UnsignedInteger31 priority = new UnsignedInteger31(
                NetworkPriority.NORMAL);
        final BACnetEID source = new BACnetEID(12345,
                BACnetEID.BACnetEIDOption.DEVICE);
        final BACnetEID destination = new BACnetEID(9876,
                BACnetEID.BACnetEIDOption.DEVICE);
        final BACnetObjectIdentifier oi = new BACnetObjectIdentifier(
                BACnetObjectType.device, source.getIdentifier());
        final UnsignedInteger maxLength = new UnsignedInteger(1024);
        final Segmentation segmentation = Segmentation.noSegmentation;
        final UnsignedInteger vendorId = new UnsignedInteger(7);
        final Service service = new IAmRequest(oi, maxLength, segmentation,
                vendorId);
        final ByteQueue b = new ByteQueue();
        service.write(b);
        final TPDU apdu = new TPDU(priority, source, destination, null, null,
                null, b.popAll());

        final _ByteQueue queue = new _ByteQueue();
        apdu.write(queue);

        final ByteQueue expected = new ByteQueue();
        // Entered from BACnet IT Encoding Example v0.3
        final byte[] expectedBytes = new byte[] { (byte) 0X0E, (byte) 0X09,
                (byte) 0X2, (byte) 0X19, (byte) 0X00, (byte) 0X2E, (byte) 0X1A,
                (byte) 0X30, (byte) 0X39, (byte) 0X2F, (byte) 0X3E, (byte) 0X1A,
                (byte) 0X26, (byte) 0X94, (byte) 0X3F, (byte) 0X0F, (byte) 0X1E,
                (byte) 0X1E, (byte) 0X0E, (byte) 0XC4, (byte) 0X02, (byte) 0X00,
                (byte) 0X30, (byte) 0X39, (byte) 0X22, (byte) 0X04, (byte) 0X00,
                (byte) 0X91, (byte) 0X03, (byte) 0X21, (byte) 0X07, (byte) 0X0F,
                (byte) 0X1F, (byte) 0X1F };
        expected.push(expectedBytes);

        System.out.println(queue);
        System.out.println(expected);
        assertTrue(queue.toString().equals(expected.toString()));
    }
}
