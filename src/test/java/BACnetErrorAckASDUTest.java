
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import ch.fhnw.bacnetit.lib.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.lib.encoding.asdu.BACnetErrorAckASDU;
import ch.fhnw.bacnetit.lib.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.acknowledgment.BACnetErrorAck;
import ch.fhnw.bacnetit.lib.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.lib.util.BytesUtil;

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
