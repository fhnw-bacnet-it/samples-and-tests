import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.bacnetit.binding.ws.WSBinaryFrameHandler;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetTransactionManagerException;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
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

    @Test
    public void testInvalidDecoding() {
        buffer.writeBytes(INVALID_SIMPLE_ACK);

        channel = new EmbeddedChannel(new WSBinaryFrameHandler());
        channel.writeInbound(new BinaryWebSocketFrame(buffer));

        assertNull(
                "Nothing should be forwarded when a BACnet message could not be decoded.",
                channel.readInbound());
        final Object response = channel.readOutbound();
        assertNotNull("Client must get a response on invalid message.",
                response);
        assertTrue("Valid error message must be sent to the client.",
                response instanceof BACnetTransactionManagerException);
        // TODO decide on reject reason
        // assertTrue("Error message must contain a valid reject reason.",
        // ((BACnetTransactionManagerException)
        // response).getMessage().equals("asdf"));
    }

}
