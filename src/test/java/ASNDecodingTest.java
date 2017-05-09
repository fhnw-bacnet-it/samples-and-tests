
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

import org.junit.Assert;
import org.junit.Test;

import ch.fhnw.bacnetit.samplesandtests.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.samplesandtests.encoding.asdu.SimpleACK;
import ch.fhnw.bacnetit.samplesandtests.encoding.asdu.UnconfirmedRequest;
import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.service.confirmed.WritePropertyRequest;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.IAmRequest;
import ch.fhnw.bacnetit.samplesandtests.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.samplesandtests.util.BytesUtil;

public class ASNDecodingTest {

    /*
     * Unconfirmed Who-Is Request with no filter parameters
     */
    @Test
    public void testDecodeWhoIsRequestWithNoFilter() throws BACnetException {
        final byte[] message = BytesUtil.hexStringToByteArray("1E8E8F1F");
        final ByteQueue queue = new ByteQueue(message);
        final ServicesSupported servicesSupported = new ServicesSupported();
        servicesSupported.setAll(true);
        final IncomingRequestParser parser = new IncomingRequestParser(
                servicesSupported, queue);
        final ASDU request = parser.parse();
        Assert.assertTrue(request instanceof UnconfirmedRequest);
        final UnconfirmedRequest unconfirmedRequest = (UnconfirmedRequest) request;
        Assert.assertTrue(
                unconfirmedRequest.getService() instanceof WhoIsRequest);
    }

    @Test
    public void testDecodeIAmRequestWithUnicast() throws BACnetException {
        final byte[] message = BytesUtil
                .hexStringToByteArray("1E0EC402003039220400910321070F1F");
        final ByteQueue queue = new ByteQueue(message);
        final ServicesSupported servicesSupported = new ServicesSupported();
        servicesSupported.setAll(true);
        final IncomingRequestParser parser = new IncomingRequestParser(
                servicesSupported, queue);
        final ASDU request = parser.parse();
        Assert.assertTrue(request instanceof UnconfirmedRequest);
        final UnconfirmedRequest unconfirmedRequest = (UnconfirmedRequest) request;
        Assert.assertTrue(
                unconfirmedRequest.getService() instanceof IAmRequest);
    }

    @Test(expected = BACnetException.class)
    public void testDecodeOpeningTagException() throws BACnetException {
        final byte[] message = BytesUtil.hexStringToByteArray("290F");
        final ByteQueue queue = new ByteQueue(message);
        final ServicesSupported servicesSupported = new ServicesSupported();
        servicesSupported.setAll(true);
        final IncomingRequestParser parser = new IncomingRequestParser(
                servicesSupported, queue);
        final ASDU request = parser.parse();
        Assert.assertTrue(request instanceof SimpleACK);
        final SimpleACK simpleACK = (SimpleACK) request;
        Assert.assertEquals(WritePropertyRequest.TYPE_ID,
                simpleACK.getServiceAckChoice());
    }

}
