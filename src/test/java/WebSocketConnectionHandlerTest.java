
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
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import ch.fhnw.bacnetit.transportbinding.ws.ControlMessageHandler;
import ch.fhnw.bacnetit.transportbinding.ws.WSBinaryFrameHandler;
import ch.fhnw.bacnetit.transportbinding.ws.WSConnectionHandler;
import ch.fhnw.bacnetit.transportbinding.ws.WSEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

public class WebSocketConnectionHandlerTest {

    @Test
    public void webSocketUpgradeTest() throws URISyntaxException {
        final WebSocketClientHandshaker testHandshaker = new WebSocketClientHandshaker(
                new URI(""), WebSocketVersion.V13, "", new DefaultHttpHeaders(),
                65536) {

            @Override
            protected void verify(final FullHttpResponse response) {
            }

            @Override
            protected WebSocketFrameDecoder newWebsocketDecoder() {
                return new WebSocket13FrameDecoder(true, true, 65536);
            }

            @Override
            protected WebSocketFrameEncoder newWebSocketEncoder() {
                return null;
            }

            @Override
            protected FullHttpRequest newHandshakeRequest() {
                return null;
            }

            @Override
            public boolean isHandshakeComplete() {
                return false;
            }
        };

        final WSConnectionHandler handlerUnderTest = new WSConnectionHandler(
                testHandshaker);

        final EmbeddedChannel channel = new EmbeddedChannel();

        channel.pipeline().addFirst(WSConnectionHandler.class.getSimpleName(),
                handlerUnderTest);
        channel.pipeline().addFirst(HttpObjectAggregator.class.getSimpleName(),
                new HttpObjectAggregator(8129));
        channel.pipeline().addFirst(HttpClientCodec.class.getSimpleName(),
                new HttpClientCodec());

        final ChannelHandlerContext mockContext = mock(
                ChannelHandlerContext.class);
        when(mockContext.channel()).thenReturn(channel);
        when(mockContext.pipeline()).thenReturn(channel.pipeline());

        try {
            handlerUnderTest.channelRead0(mockContext,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.SWITCHING_PROTOCOLS));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        verify(mockContext, times(0)).fireChannelRead(any());
        // TODO: check order of handlers
        assertNull("WebSocketConnectionHandler has not been removed.",
                channel.pipeline().get(WSConnectionHandler.class));
        assertNotNull("WebSocketEncoder has not been added.",
                channel.pipeline().get(WSEncoder.class));
        assertNotNull("WebSocketBinaryFrameHandler has not been added.",
                channel.pipeline().get(WSBinaryFrameHandler.class));
        assertNotNull("Control MessageHandler has not been added.",
                channel.pipeline().get(ControlMessageHandler.class));
    }

    @Test
    public void httpRequestTestHandshakeComplete() throws URISyntaxException {
        final WebSocketClientHandshaker mock = mock(
                WebSocketClientHandshaker.class);
        when(mock.isHandshakeComplete()).thenReturn(true);

        try {
            final EmbeddedChannel channel = new EmbeddedChannel();
            final WSConnectionHandler webSocketConnectionHandler = new WSConnectionHandler(
                    mock);

            channel.pipeline().addFirst(
                    WSConnectionHandler.class.getSimpleName(),
                    webSocketConnectionHandler);

            channel.writeInbound(new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.GET, "127.0.0.1"));
            // System.out.println(channel.readOutbound());
            // System.err.println(channel.readInbound());

        } catch (final IllegalStateException e) {

        }
    }

    @Test
    public void httpRequestTestHandshakeIncomplete() throws URISyntaxException {
        final WebSocketClientHandshaker mock = mock(
                WebSocketClientHandshaker.class);
        when(mock.isHandshakeComplete()).thenReturn(false);

        try {
            final EmbeddedChannel channel = new EmbeddedChannel();
            channel.pipeline().addFirst(
                    WSConnectionHandler.class.getSimpleName(),
                    new WSConnectionHandler(mock));

            channel.writeInbound(new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.GET, "127.0.0.1"));
        } catch (final IllegalStateException e) {

        }
    }

    @Test
    public void msgForwardingTest() throws URISyntaxException {
        final WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory
                .newHandshaker(new URI("ws://127.0.0.1"), WebSocketVersion.V13,
                        "", false, new DefaultHttpHeaders());

        final EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addFirst(WSConnectionHandler.class.getSimpleName(),
                new WSConnectionHandler(handshaker));

        channel.writeInbound(new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET, "127.0.0.1"));
    }

    @Test
    public void contextCloseOnExceptionFinishedPromise() {
        final ChannelHandlerContext mockContext = mock(
                ChannelHandlerContext.class);
        final EmbeddedChannel channel = new EmbeddedChannel();
        final ChannelPromise mockPromise = new DefaultChannelPromise(channel);
        mockPromise.setSuccess();
        when(mockContext.newPromise()).thenReturn(mockPromise);

        final WSConnectionHandler handlerUnderTest = new WSConnectionHandler(
                mock(WebSocketClientHandshaker.class));
        handlerUnderTest.handlerAdded(mockContext);
        handlerUnderTest.exceptionCaught(mockContext,
                new Exception("MockException"));

        verify(mockContext).close();
    }

    @Test
    public void contextCloseOnExceptionUnfinishedPromise() {
        final ChannelHandlerContext mockContext = mock(
                ChannelHandlerContext.class);
        final EmbeddedChannel channel = new EmbeddedChannel();
        final ChannelPromise mockPromise = new DefaultChannelPromise(channel);
        when(mockContext.newPromise()).thenReturn(mockPromise);

        final Throwable testException = new Exception("Test Exception");

        final WSConnectionHandler handlerUnderTest = new WSConnectionHandler(
                mock(WebSocketClientHandshaker.class));
        handlerUnderTest.handlerAdded(mockContext);
        handlerUnderTest.exceptionCaught(mockContext, testException);

        assertNotNull("Failure not set on exception", mockPromise.cause());
        assertEquals("Not same exception is thrown", mockPromise.cause(),
                testException);

        verify(mockContext).close();
    }
}
