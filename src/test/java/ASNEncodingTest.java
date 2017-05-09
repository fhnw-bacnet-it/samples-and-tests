
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

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.enumerated.Segmentation;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.service.confirmed.ConfirmedRequestService;
import ch.fhnw.bacnetit.samplesandtests.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.IAmRequest;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.UnconfirmedRequestService;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.samplesandtests.util.BytesUtil;

public class ASNEncodingTest {

    /*
     * Confirmed ReadProperty Request on Presen_Value property of Analog Input
     * with instance 5.
     */
    @Test
    public void testReadPropertyRequestForAnalogInputAndPresentValue() {
        final byte[] expected = BytesUtil
                .hexStringToByteArray("0ECE0C000000051955CF0F");
        final BACnetObjectIdentifier oid = new BACnetObjectIdentifier(
                BACnetObjectType.analogInput, 5);
        final ConfirmedRequestService service = new ReadPropertyRequest(oid,
                BACnetPropertyIdentifier.presentValue);
        final ByteQueue serviceData = new ByteQueue();
        service.write(serviceData);
        final byte[] bytes = serviceData.peekAll();
        Assert.assertTrue(Arrays.equals(expected, bytes));
    }

    /*
     * Unconfirmed Who-Is Request with no filter parameters
     */
    @Test
    public void testWhoIsRequestWithNoFilter() {
        final byte[] expected = BytesUtil.hexStringToByteArray("1E8E8F1F");
        final UnconfirmedRequestService service = new WhoIsRequest();
        final ByteQueue serviceData = new ByteQueue();
        service.write(serviceData);
        final byte[] bytes = serviceData.peekAll();
        Assert.assertTrue(Arrays.equals(expected, bytes));
    }

    /*
     * Unconfirmed Who-Is Request with instance filter parameter (1000 <=
     * instance <= 2000)
     */
    @Test
    public void testWhoIsRequestWithFilterAndDirectedBroadcast() {
        final byte[] expected = BytesUtil
                .hexStringToByteArray("1E8E0A03E81A07D08F1F");
        final UnsignedInteger lowLimit = new UnsignedInteger(1000);
        final UnsignedInteger highLimit = new UnsignedInteger(2000);
        final UnconfirmedRequestService service = new WhoIsRequest(lowLimit,
                highLimit);
        final ByteQueue serviceData = new ByteQueue();
        service.write(serviceData);
        final byte[] bytes = serviceData.peekAll();
        Assert.assertTrue(Arrays.equals(expected, bytes));
    }

    /*
     * Unconfirmed I-Am Request unicast to a single device from device with
     * Device ID 12345.
     */
    @Test
    public void testIAmRequestWithUnicast() {
        final byte[] expected = BytesUtil
                .hexStringToByteArray("1E0EC402003039220400910321070F1F");
        final BACnetObjectIdentifier oid = new BACnetObjectIdentifier(
                BACnetObjectType.device, 12345);
        final UnsignedInteger maxAPDULength = new UnsignedInteger(1024);
        final UnsignedInteger vendorId = new UnsignedInteger(7);
        final UnconfirmedRequestService service = new IAmRequest(oid,
                maxAPDULength, Segmentation.noSegmentation, vendorId);
        final ByteQueue serviceData = new ByteQueue();
        service.write(serviceData);
        final byte[] bytes = serviceData.peekAll();
        Assert.assertTrue(Arrays.equals(expected, bytes));
    }
}
