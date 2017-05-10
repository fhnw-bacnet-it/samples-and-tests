
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.BACnetErrorAckASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.acknowledgment.BACnetErrorAck;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.samplesandtests.api.util.BytesUtil;

public class BACnetErrorAckASDUTest {

    @Test
    public void testSerialize() {
        final byte[] expected = BytesUtil
                .hexStringToByteArray("4ECE9101911FCF4F");
        final byte serviceId = ReadPropertyRequest.TYPE_ID;
        final BACnetErrorAck errorACK = new BACnetErrorAck(ErrorClass.object,
                ErrorCode.unknownObject, serviceId);
        final ByteQueue queue = new ByteQueue();
        errorACK.write(queue);
        final byte[] result = queue.popAll();
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void testDeserialize() throws BACnetException {
        final byte[] expected = BytesUtil
                .hexStringToByteArray("4ECE9101911FCF4F");
        final ByteQueue queue = new ByteQueue(expected);
        final ServicesSupported servicesSupported = new ServicesSupported();
        servicesSupported.setAll(true);
        final IncomingRequestParser parser = new IncomingRequestParser(
                servicesSupported, queue);
        final ASDU request = parser.parse();
        assertNotNull(request);
        assertTrue(request instanceof BACnetErrorAckASDU);

        final BACnetErrorAckASDU error = (BACnetErrorAckASDU) request;
        assertEquals(ReadPropertyRequest.TYPE_ID, error.getServiceAckChoice());
        assertEquals(ErrorClass.object,
                error.getError().getError().getErrorClass());
        assertEquals(ErrorCode.unknownObject,
                error.getError().getError().getErrorCode());
    }

}
