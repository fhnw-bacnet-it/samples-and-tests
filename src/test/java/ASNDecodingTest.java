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

import org.junit.Assert;
import org.junit.Test;

import ch.fhnw.bacnetit.lib.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.lib.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.lib.encoding.asdu.SimpleACK;
import ch.fhnw.bacnetit.lib.encoding.asdu.UnconfirmedRequest;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.confirmed.WritePropertyRequest;
import ch.fhnw.bacnetit.lib.service.unconfirmed.IAmRequest;
import ch.fhnw.bacnetit.lib.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.lib.util.BytesUtil;

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
