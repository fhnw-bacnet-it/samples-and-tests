
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
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorsBasicAuth {
    final String URL = "http://0.0.0.0:8080";
    final String URLTLS = "https://0.0.0.0:8080";
    final String truststorePath = "/Users/IMVS/git/bacnet-it/stores/truststore.jks";
    final String keystorePath = "/Users/IMVS/git/bacnet-it/stores/keystoreH1.jks";
    static volatile String validSessionRuntime;

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Tests for non-encrypted communication

    @Test
    @Ignore
    public void Apreflight() throws ClientProtocolException, IOException { // Build
                                                                           // HttpOption
                                                                           // Request
        final HttpOptions r = new HttpOptions(URL);
        r.addHeader("Access-Control-Request-Headers", "authorization,vary");
        r.addHeader("Access-Control-Request-Method", "GET");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);

        // Build HttpClient
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpResponse response = client.execute(r);

        System.out.println(response);
        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 200 OK");

        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Headers",
                new String[] { "Authorization", "Vary", "Cookie",
                        "Content-Type", "Accept", "X-Requested-With" }));
        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Credentials".toLowerCase(),
                new String[] { "true" }));
        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Methods".toLowerCase(),
                new String[] { "GET", "OPTIONS" }));
        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Origin".toLowerCase(),
                new String[] { randomOrigin }));
    }

    @Test
    @Ignore
    public void BhttpAuthWrongLogin()
            throws ClientProtocolException, IOException {
        final HttpGet r = new HttpGet(URL);
        r.addHeader("Authorization", "Basic Y2hyaXM6TIzNA==");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Vary", "Origin");

        // Build HttpClient
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpResponse response = client.execute(r);

        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 403 Forbidden");
    }

    @Test
    @Ignore
    public void ChttpAuth() throws ClientProtocolException, IOException {

        final HttpGet r = new HttpGet(URL);
        r.addHeader("Authorization", "Basic Y2hyaXM6MTIzNA==");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Vary", "Origin");

        // Build HttpClient
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpResponse response = client.execute(r);

        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 200 OK");

        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Credentials", new String[] { "true" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Headers",
                new String[] { "Authorization", "Vary", "Cookie",
                        "Content-Type", "Accept", "X-Requested-With" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Methods",
                new String[] { "GET", "OPTIONS" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Origin",
                new String[] { randomOrigin }));

        // Use received cookie for websocket upgrade
        validSessionRuntime = response.getFirstHeader("Set-Cookie").getValue();
        System.err.println(validSessionRuntime);

    }

    @Test
    @Ignore
    public void DwebsocketUpgrade()
            throws ClientProtocolException, IOException {
        final HttpGet r = new HttpGet(URL);
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Upgrade", "websocket");
        r.addHeader("Connection", "keep-alive, Upgrade");
        r.addHeader("Cookie", validSessionRuntime.split(";")[0]);
        r.addHeader("Sec-WebSocket-Key", "QkWkChtTGpaZL5PkOMaRtg==");
        r.addHeader("Sec-Websocket-Origin", randomOrigin);
        r.addHeader("Sec-WebSocket-Protocol", "BACnet");
        r.addHeader("Sec-WebSocket-Version", "13");
        r.addHeader("Sec-WebSocket-Extensions", "permessage-deflate");

        // Build HttpClient
        final HttpClient client = HttpClientBuilder.create().build();
        System.err.println("before");
        final HttpResponse response = client.execute(r);
        System.err.println("after");

        Assert.assertTrue(testPresence(response, "Connection",
                new String[] { "Upgrade" }));
        Assert.assertTrue(testPresence(response, "Origin",
                new String[] { randomOrigin }));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Tests for encrypted communication

    @Test
    @Ignore
    public void EApreflight_TLS() throws Exception {
        final CloseableHttpClient httpclient = getTLSHttpClient();

        final HttpOptions r = new HttpOptions(URLTLS);
        r.addHeader("Access-Control-Request-Headers", "authorization,vary");
        r.addHeader("Access-Control-Request-Method", "GET");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);

        final HttpResponse response = httpclient.execute(r);

        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 200 OK");
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Headers",
                new String[] { "Authorization", "Vary", "Cookie",
                        "Content-Type", "Accept", "X-Requested-With" }));
        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Credentials", new String[] { "true" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Methods",
                new String[] { "GET", "OPTIONS" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Origin",
                new String[] { randomOrigin }));
    }

    @Test
    @Ignore
    public void FhttpAuthWrongLogin_TLS() throws ClientProtocolException,
            IOException, KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException {
        final CloseableHttpClient httpclient = getTLSHttpClient();
        final HttpGet r = new HttpGet(URLTLS);
        r.addHeader("Authorization", "Basic Y2hyaXM6TIzNA==");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Vary", "Origin");

        final HttpResponse response = httpclient.execute(r);

        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 403 Forbidden");
    }

    @Test
    @Ignore
    public void GhttpAuth_TLS() throws ClientProtocolException, IOException,
            KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException {
        final CloseableHttpClient httpclient = getTLSHttpClient();

        final HttpGet r = new HttpGet(URLTLS);
        r.addHeader("Authorization", "Basic Y2hyaXM6MTIzNA==");
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Vary", "Origin");

        final HttpResponse response = httpclient.execute(r);

        Assert.assertEquals(response.getStatusLine().toString(),
                "HTTP/1.1 200 OK");

        Assert.assertTrue(testPresence(response,
                "Access-Control-Allow-Credentials", new String[] { "true" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Headers",
                new String[] { "Authorization", "Vary", "Cookie",
                        "Content-Type", "Accept", "X-Requested-With" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Methods",
                new String[] { "GET", "OPTIONS" }));
        Assert.assertTrue(testPresence(response, "Access-Control-Allow-Origin",
                new String[] { randomOrigin }));

        // Use received cookie for websocket upgrade
        validSessionRuntime = response.getFirstHeader("Set-Cookie").getValue();
        System.err.println(validSessionRuntime);

    }

    @Test
    @Ignore
    public void HwebsocketUpgrade_TLS() throws ClientProtocolException,
            IOException, KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException {
        final CloseableHttpClient httpclient = getTLSHttpClient();
        final HttpGet r = new HttpGet(URLTLS);
        final String randomOrigin = "http://bacnet-c" + Math.random();
        r.addHeader("Origin", randomOrigin);
        r.addHeader("Upgrade", "websocket");
        r.addHeader("Connection", "keep-alive, Upgrade");
        r.addHeader("Cookie", validSessionRuntime.split(";")[0]);
        r.addHeader("Sec-WebSocket-Key", "QkWkChtTGpaZL5PkOMaRtg==");
        r.addHeader("Sec-Websocket-Origin", randomOrigin);
        r.addHeader("Sec-WebSocket-Protocol", "BACnet");
        r.addHeader("Sec-WebSocket-Version", "13");
        r.addHeader("Sec-WebSocket-Extensions", "permessage-deflate");

        final HttpResponse response = httpclient.execute(r);

        Assert.assertTrue(testPresence(response, "Connection",
                new String[] { "Upgrade" }));
        Assert.assertTrue(testPresence(response, "Origin",
                new String[] { randomOrigin }));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Test Utilities
    private boolean testPresence(final HttpResponse response,
            final String headerName, final String[] expected) {

        if (response == null || headerName == null || expected == null
                || expected.length == 0) {
            return false;
        }

        if (response.getHeaders(headerName) == null) {
            return false;
        }

        System.out.println("Check for: " + headerName);
        System.out.println("Amount header expected: " + expected.length);
        final Header[] headers = response.getHeaders(headerName);
        System.out.println("headers: " + Arrays.toString(headers));
        System.out.println(headers[0].getValue());
        for (final String s : Arrays.asList(expected)) {
            if (!Arrays.asList(headers).stream().map(h -> h.getValue())
                    .collect(Collectors.toList()).contains(s)) {
                System.err.println("Fails by: " + s);
                return false;
            }

        }
        return true;

    }

    private CloseableHttpClient getTLSHttpClient()
            throws KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException,
            IOException {
        // Trust own CA and all self-signed certs
        final SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new File(truststorePath),
                        "123456".toCharArray(), null)
                .loadKeyMaterial(new File(keystorePath), "123456".toCharArray(),
                        "123456".toCharArray())
                .build();

        // Allow TLSv1 protocol only
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[] { "TLSv1.1", "TLSv1.2" },
                new String[] { "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256" },
                (hostname, session) -> {
                    return URLTLS.split("//")[1].split(":")[0].equals(hostname);
                });

        final CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();
        return httpclient;

    }

}
