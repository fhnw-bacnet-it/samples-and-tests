
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

import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.unconfirmed.WhoIsRequest;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding.UnsignedInteger8;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;

public class TBAPDUTest {

    @Test
    public void TBAPDUEncodableTest1() throws Exception {

        // Create tbapdu1
        final ByteQueue q = new ByteQueue();
        new WhoIsRequest().write(q);
        final TPDU t1 = new TPDU(null, new BACnetEID(12345),
                new BACnetEID(78945), new UnsignedInteger8(44),
                new UnsignedInteger8(33), null, q.popAll());

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
