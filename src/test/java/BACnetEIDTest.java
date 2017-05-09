
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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.fhnw.bacnetit.ase.encoding._ByteQueue;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID.BACnetEIDOption;

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
