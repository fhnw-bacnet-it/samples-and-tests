
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

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.lib.deviceobjects.Service;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.Segmentation;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.unconfirmed.IAmRequest;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.NetworkPriority;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding.UnsignedInteger31;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;

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
