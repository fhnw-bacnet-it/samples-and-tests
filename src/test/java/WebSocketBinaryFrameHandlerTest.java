
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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.transportbinding.ws.WSBinaryFrameHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class WebSocketBinaryFrameHandlerTest {

    private static byte[] VALID_SIMPLE_ACK = { 0x01, 0x00, 0x0E, 0x09, 0x14,
            0x19, 0x00, 0x2E, 0x1A, 0x03, (byte) 0xE9, 0x2F, 0x3E, 0x1A, 0x03,
            (byte) 0xEB, 0x3F, 0x49, 0x02, 0x59, 0x01, 0x0F, 0x1E, 0x39, 0x0F,
            0x1F };
    private static byte[] INVALID_SIMPLE_ACK = { 0x01, 0x00, 0x00, 0x09, 0x14,
            0x19, 0x00, 0x2E, 0x1A, 0x03, (byte) 0xE9, 0x2F, 0x3E, (byte) 0xEB,
            0x3F, 0x49, 0x02, 0x59, 0x01, 0x0F, 0x1E, 0x39, 0x0F, 0x1E };

    private EmbeddedChannel channel;
    private ByteBuf buffer;

    @Before
    public void setup() {
        channel = new EmbeddedChannel(new WSBinaryFrameHandler());
        buffer = Unpooled.directBuffer();
    }

    @Test
    public void testValidDecoding() {
        buffer.writeBytes(VALID_SIMPLE_ACK);

        channel = new EmbeddedChannel(new WSBinaryFrameHandler());
        channel.writeInbound(new BinaryWebSocketFrame(buffer));

        assertTrue("Output message must be a TBPDU",
                channel.readInbound() instanceof TPDU);
    }

}
