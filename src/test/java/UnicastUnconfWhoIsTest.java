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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.fhnw.bacnetit.lib.deviceobjects.Service;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;
import ch.fhnw.bacnetit.stack.encoding.UnsignedInteger8;

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
