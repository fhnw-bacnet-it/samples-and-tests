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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;
import ch.fhnw.bacnetit.stack.encoding.UnsignedInteger8;

public class TBAPDUTest {

    @Test
    public void TBAPDUEncodableTest1() throws Exception {

        // Create tbapdu1
        final ByteQueue q = new ByteQueue();
        new WhoIsRequest().write(q);
        final TPDU t1 = new TPDU(null, new BACnetEID(12345),
                new BACnetEID(78945), new UnsignedInteger8(44), new UnsignedInteger8(33),
                null, q.popAll());

        // Create tbapdu2 with bytestream from tbapdu1
        final _ByteQueue queue = new _ByteQueue();
        t1.write(queue);
        final TPDU t2 = new TPDU(queue.popAll());

        assertEquals(t1.getRevision(), t2.getRevision());
        assertEquals(t1.getSourceEID().getIdentifier(),
                t2.getSourceEID().getIdentifier());
        assertEquals(t1.getDestinationEID().getIdentifier(),
                t2.getDestinationEID().getIdentifier());
        assertEquals(t1.getInvokeId(), t2.getInvokeId());
        assertEquals(t1.getForwards(), t2.getForwards());

    }

}
