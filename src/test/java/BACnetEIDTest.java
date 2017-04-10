
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

import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID.BACnetEIDOption;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;

public class BACnetEIDTest {

    @Test
    public void EIDEncodableTestDevice() throws Exception {
        // Create eid1
        final BACnetEID eid1 = new BACnetEID(0,
                BACnetEID.BACnetEIDOption.DEVICE);

        final _ByteQueue q = new _ByteQueue();
        eid1.write(q);

        // Create eid2 with byte stream from eid1
        final BACnetEID eid2 = new BACnetEID(q);

        assertEquals(eid1.getIdentifier(), eid2.getIdentifier());
        assertEquals(eid1.getChoice(), eid2.getChoice());
    }

    @Test
    public void EIDEncodableTestNotAssigned() throws Exception {
        // Create eid1
        final BACnetEID eid1 = new BACnetEID(1, BACnetEIDOption.NOTASSIGNED);

        final _ByteQueue q = new _ByteQueue();
        eid1.write(q);

        // Create eid2 with byte stream from eid1
        final BACnetEID eid2 = new BACnetEID(q);
        assertEquals(eid1.getIdentifier(), eid2.getIdentifier());
        assertEquals(eid1.getChoice(), eid2.getChoice());
    }

    @Test
    public void EIDEncodableTestGroup() throws Exception {
        // Create eid1
        final BACnetEID eid1 = new BACnetEID(123456,
                BACnetEID.BACnetEIDOption.GROUP);

        final _ByteQueue q = new _ByteQueue();
        eid1.write(q);

        // Create eid2 with byte stream from eid1
        final BACnetEID eid2 = new BACnetEID(q);
        assertEquals(eid1.getIdentifier(), eid2.getIdentifier());
        assertEquals(eid1.getChoice(), eid2.getChoice());
    }

    @Test
    public void EIDEncodableTest4() throws Exception {
        // Create eid1
        final BACnetEID eid1 = new BACnetEID(9876);

        final _ByteQueue q = new _ByteQueue();
        eid1.write(q);

        // Create eid2 with byte stream from eid1
        final BACnetEID eid2 = new BACnetEID(q);
        assertEquals(eid1.getIdentifier(), eid2.getIdentifier());
        assertEquals(eid1.getChoice(), eid2.getChoice());
    }

}
