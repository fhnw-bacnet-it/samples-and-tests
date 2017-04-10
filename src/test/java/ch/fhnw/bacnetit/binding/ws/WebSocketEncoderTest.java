package ch.fhnw.bacnetit.binding.ws;

import java.io.IOException;

import org.junit.Test;

import ch.fhnw.bacnetit.stack.application.transaction.Channel;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding._ByteQueue;
import ch.fhnw.bacnetit.stack.encoding.UnsignedInteger8;
import ch.fhnw.bacnetit.stack.network.transport.util.ByteBufLogger;
import ch.fhnw.bacnetit.stack.network.transport.util.MessageLogger;
import ch.fhnw.bacnetit.stack.network.transport.util.PipelineLogger;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;

public class WebSocketEncoderTest {

    /*
     * @Test public void testMessageEncoding() { final EmbeddedChannel channel =
     * new EmbeddedChannel(new WSEncoder()); final byte[] validSimpleAck = {
     * 0x01, 0x00, 0x0E, 0x09, 0x14, 0x19, 0x00, 0x2E, 0x1A, 0x03, (byte) 0xE9,
     * 0x2F, 0x3E, 0x1A, 0x03, (byte) 0xEB, 0x3F, 0x49, 0x02, 0x59, 0x01, 0x0F,
     * 0x1E, 0x39, 0x0F, 0x1F };
     *
     * try { final TPDU testTbpdu = new TPDU(validSimpleAck);
     * channel.writeOutbound(testTbpdu);
     *
     * final Object output = channel.readOutbound();
     * assertNotNull("ChannelHandler must provide an output.", output);
     * assertTrue("WebSocketEncoder must output a ByteBuf.", output instanceof
     * ByteBuf); final ByteBuf buffer = ((ByteBuf) output);
     *
     * } catch (final BACnetException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } }
     */

    @Test
    public void testLogging() throws IOException {
        final EmbeddedChannel channel = new EmbeddedChannel(new ByteBufLogger(),
                new PipelineLogger(), new WebSocket13FrameEncoder(false),
                new WebSocket13FrameDecoder(false, false, 1024),
                new ControlMessageHandler(), new WSBinaryFrameHandler(),
                new WSEncoder(), new MessageLogger(), new Channel());
        final TPDU tpdu = new TPDU(new BACnetEID(1001), new BACnetEID(1002),
                new _ByteQueue().popAll());
        tpdu.setInvokeId(new UnsignedInteger8(1));
        channel.writeOutbound(tpdu);
        final Object temp = channel.readOutbound();
        System.out.println(temp);
        channel.writeInbound(temp);
        final Object back = channel.readInbound();
        System.out.println(back);
        channel.close();
    }

    // TODO remove test
    @Test
    public void testEncoding() {
        System.out.println(getTBPDU(new byte[] { -126, 26, 1, 0, 14, 9, 2, 25,
                0, 46, 26, 3, -23, 47, 62, 26, 3, -22, 63, 73, 1, 15, 30, 30,
                -114, -113, 31, 31 }));
    }

    private static TPDU getTBPDU(final byte[] bytes) {
        TPDU tbapdu = null;
        _ByteQueue q = new _ByteQueue(bytes);
        final byte head = q.pop();
        if (head == (byte) 0x82) { // FIN & binary frame
            final byte temp = q.pop(); // websocket params
            final boolean isMasked = (temp & 0x80) == 0x80 ? true : false; // check
                                                                           // 1st
                                                                           // bit
            final int length = temp & 0x7F; // check remaining 7 bits

            if (isMasked) {
                final byte[] mask = { q.pop(), q.pop(), q.pop(), q.pop() }; // 4-byte
                                                                            // mask
                final _ByteQueue decodedData = new _ByteQueue();
                for (int i = 0; i < length; i++) {
                    decodedData.push(q.pop() ^ mask[i % 4]);
                }
                q = decodedData;
            }
            q.pop(); // skip version
            final WSPayloadControl.PayloadType payloadType = WSPayloadControl
                    .getType(q.pop());
            if (payloadType == WSPayloadControl.PayloadType.TBPDU) {
                try {
                    tbapdu = new TPDU(q);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            } else if (payloadType == WSPayloadControl.PayloadType.CONTROLMESSAGE) {

            }
        } else {
            // LOG.info("Not a WebSocket packet"); TODO TMI
        }
        return tbapdu;
    }
}
