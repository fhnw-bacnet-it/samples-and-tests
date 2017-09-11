
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.fhnw.bacnetit.ase.application.api.NetworkPortObj;
import ch.fhnw.bacnetit.ase.application.configuration.api.DiscoveryConfig;
import ch.fhnw.bacnetit.ase.application.configuration.api.KeystoreConfig;
import ch.fhnw.bacnetit.ase.application.configuration.api.TruststoreConfig;
import ch.fhnw.bacnetit.ase.application.service.api.ApplicationService;
import ch.fhnw.bacnetit.ase.application.service.api.BACnetEntityListener;
import ch.fhnw.bacnetit.ase.application.service.api.ChannelConfiguration;
import ch.fhnw.bacnetit.ase.application.service.api.ChannelFactory;
import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataRequest;
import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
import ch.fhnw.bacnetit.ase.transportbinding.service.api.ASEService;
import ch.fhnw.bacnetit.directorybinding.dnssd.api.DNSSD;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PropertyReference;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.TimeStamp;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LifeSafetyOperation;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.NotifyType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.AcknowledgeAlarmRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ConfirmedCovNotificationRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ConfirmedEventNotificationRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.LifeSafetyOperationRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.SubscribeCOVPropertyRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.SubscribeCOVRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.WritePropertyRequest;
import ch.fhnw.bacnetit.transportbinding.api.BindingConfiguration;
import ch.fhnw.bacnetit.transportbinding.api.ConnectionFactory;
import ch.fhnw.bacnetit.transportbinding.api.TransportBindingInitializer;
import ch.fhnw.bacnetit.transportbinding.ws.incoming.api.WSConnectionServerFactory;
import ch.fhnw.bacnetit.transportbinding.ws.incoming.tls.api.WSSConnectionServerFactory;
import ch.fhnw.bacnetit.transportbinding.ws.outgoing.api.WSConnectionClientFactory;
import ch.fhnw.bacnetit.transportbinding.ws.outgoing.tls.api.WSSConnectionClientFactory;

/**
 * @author IMVS, FHNW
 *
 */
public class TestLocalSpecService {

    // Define some variables
    private static URI hostLocal1Tls = null, hostLocal1NoTls = null,
            hostLocal2Tls = null, hostLocal2NoTls = null;
    private static int portLocal1Tls = 0, portLocal1NoTls = 0,
            portLocal2Tls = 0, portLocal2NoTls = 0;

    // Two BACnet devices for host 1
    final BACnetEID devLocal11 = new BACnetEID(1001);
    final BACnetEID devLocal12 = new BACnetEID(1002);

    // Two BACnet devices for host 2
    final BACnetEID devLocal21 = new BACnetEID(2001);
    final BACnetEID devLocal22 = new BACnetEID(2002);

    private ApplicationService appService1;
    private ApplicationService appService2;
    private BindingConfiguration bindingConfiguration1;
    private BindingConfiguration bindingConfiguration2;
    private DiscoveryConfig ds;

    static volatile T_UnitDataIndication indicationTodevLocal11 = null;
    static volatile T_UnitDataIndication indicationTodevLocal12 = null;
    static volatile T_UnitDataIndication indicationTodevLocal21 = null;
    static volatile T_UnitDataIndication indicationTodevLocal22 = null;

    // Define key- and truststore
    final KeystoreConfig keystoreConfig = new KeystoreConfig(
            "dummyKeystores/keyStoreDev1.jks", "123456", "operationaldevcert");
    final TruststoreConfig truststoreConfig = new TruststoreConfig(
            "dummyKeystores/trustStore.jks", "123456", "installer.ch");

    private void setupHost1() throws URISyntaxException {

        portLocal1Tls = 38080;
        portLocal1NoTls = 38081;
        // Define URI for host 1
        hostLocal1Tls = new URI("wss://localhost:" + portLocal1Tls);
        hostLocal1NoTls = new URI("ws://localhost:" + portLocal1NoTls);

        // Build the connection factory
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        // TLS on, Client
        connectionFactory.addConnectionClient("wss",
                new WSSConnectionClientFactory(keystoreConfig,
                        truststoreConfig));
        // TLS on, Server
        connectionFactory.addConnectionServer("wss",
                new WSSConnectionServerFactory(portLocal1Tls, keystoreConfig,
                        truststoreConfig));

        // TLS off, Client
        connectionFactory.addConnectionClient("ws",
                new WSConnectionClientFactory());
        // TLS off, Server
        connectionFactory.addConnectionServer("ws",
                new WSConnectionServerFactory(portLocal1NoTls));

        // Run the channel
        appService1 = ChannelFactory.getInstance();
        bindingConfiguration1 = new TransportBindingInitializer();
        ((ChannelConfiguration) appService1)
                .setASEService((ASEService) bindingConfiguration1);
        ;
        bindingConfiguration1.initializeAndStart(connectionFactory);

        /*
         * Implement and set BACnetEntityHandler (to handle received control
         * messages)
         */
        final BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid,
                    final URI remoteUri) {
                // TODO Auto-generated method stub
                DirectoryService.getInstance().register(eid, remoteUri, false,
                        true);
                // LOG.debug("Register local: " + eid.getIdentifierAsString()+ "
                // points to " + remoteUri);
            }

            @Override
            public void onRemoteRemove(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLocalRequested(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }

        };
        // Register a BACnetEntityListener in the Channel.
        ((ChannelConfiguration) appService1)
                .setEntityListener(bacNetEntityHandler);

        /*
         * Update the monitor about host 1
         */

        // Create a Network Port Object for host 1
        final NetworkPortObj npo = new NetworkPortObj("wss", 8001,
                keystoreConfig);

        /*
         * Channel Listener is the interface between the stack and the
         * application. A simple BACnet Device gets simulated by an anonymous
         * class implementing ChannelListener. Add two devices to each host and
         * notify the monitor.
         */
        ((ChannelConfiguration) appService1)
                .registerChannelListener(new ChannelListener(devLocal11) {

                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {
                        indicationTodevLocal11 = tUnitDataIndication;
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

        ((ChannelConfiguration) appService1)
                .registerChannelListener(new ChannelListener(devLocal12) {

                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {
                        indicationTodevLocal12 = tUnitDataIndication;
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

    }

    private void setupHost2() throws URISyntaxException {
        portLocal2Tls = 9090;
        portLocal2NoTls = 9091;
        // Define URI for host 2
        hostLocal2Tls = new URI("wss://localhost:" + portLocal2Tls);
        hostLocal2NoTls = new URI("ws://localhost:" + portLocal2NoTls);

        // Build the connection factory
        final ConnectionFactory connectionFactory2 = new ConnectionFactory();
        // TLS on, Client
        connectionFactory2.addConnectionClient("wss",
                new WSSConnectionClientFactory(keystoreConfig,
                        truststoreConfig));
        // TLS on, Server
        connectionFactory2.addConnectionServer("wss",
                new WSSConnectionServerFactory(portLocal2Tls, keystoreConfig,
                        truststoreConfig));

        // TLS off, Client
        connectionFactory2.addConnectionClient("ws",
                new WSConnectionClientFactory());
        // TLS off, Server
        connectionFactory2.addConnectionServer("ws",
                new WSConnectionServerFactory(portLocal2NoTls));

        // Run the channel
        appService2 = ChannelFactory.getInstance();
        bindingConfiguration2 = new TransportBindingInitializer();
        ((ChannelConfiguration) appService2)
                .setASEService((ASEService) bindingConfiguration2);
        ;
        bindingConfiguration2.initializeAndStart(connectionFactory2);

        /*
         * Implement and set BACnetEntityHandler (to handle received control
         * messages)
         */
        final BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid,
                    final URI remoteUri) {
                // TODO Auto-generated method stub
                DirectoryService.getInstance().register(eid, remoteUri, false,
                        true);
                // LOG.debug("Register local: " + eid.getIdentifierAsString()+ "
                // points to " + remoteUri);
            }

            @Override
            public void onRemoteRemove(final BACnetEID eid) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLocalRequested(final BACnetEID eid) {
                // TODO Auto-generated method stub

            }

        };
        // Register a BACnetEntityListener in the Channel.
        ((ChannelConfiguration) appService2)
                .setEntityListener(bacNetEntityHandler2);

        // Create a Network Port Object for host 2
        final NetworkPortObj npo2 = new NetworkPortObj("wss", 9001,
                keystoreConfig);

        /*
         * Channel Listener is the interface between the stack and the
         * application. A simple BACnet Device gets simulated by an anonymous
         * class implementing ChannelListener. Add two devices to each host and
         * notify the monitor.
         */
        ((ChannelConfiguration) appService2)
                .registerChannelListener(new ChannelListener(devLocal21) {

                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {
                        indicationTodevLocal21 = tUnitDataIndication;
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

        ((ChannelConfiguration) appService2)
                .registerChannelListener(new ChannelListener(devLocal22) {

                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {

                        indicationTodevLocal22 = tUnitDataIndication;
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

    }

    private void setupDiscovery() {
        ds = new DiscoveryConfig("DNSSD", "86.119.39.127", "itb.bacnet.ch.",
                "bds._sub._bacnet._tcp.", "dev._sub._bacnet._tcp.",
                "obj._sub._bacnet._tcp.", false);

        try {
            DirectoryService.init();
            DirectoryService.getInstance().setDNSBinding(new DNSSD(ds));

        } catch (final Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Before
    public void setup() throws URISyntaxException {
        setupHost1();
        setupHost2();
        setupDiscovery();
    }

    @After
    public void teardown() {

        // channel1.shutdown();
        // channel2.shutdown();
        // channel1 = null;
        // channel2 = null;
        bindingConfiguration1.shutdown();
        bindingConfiguration2.shutdown();
        appService1 = null;
        appService2 = null;
        ds = null;
        indicationTodevLocal11 = null;
        indicationTodevLocal12 = null;
        indicationTodevLocal21 = null;
        indicationTodevLocal22 = null;
    }

    @Test
    public void TestSubscribeCOVRequest() {
        final SubscribeCOVRequest scr = new SubscribeCOVRequest(
                new UnsignedInteger(13),
                new BACnetObjectIdentifier(BACnetObjectType.binaryInput, 1),
                new Boolean(true), new UnsignedInteger(17));
        final ByteQueue b = new ByteQueue();
        scr.write(b);

        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);
        while (indicationTodevLocal21 == null) {
        }

        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));
    }

    @Test
    public void TestSubscribeCOVPropertyRequest() {
        final SubscribeCOVPropertyRequest scpr = new SubscribeCOVPropertyRequest(
                new UnsignedInteger(10),
                new BACnetObjectIdentifier(BACnetObjectType.notificationClass,
                        1),
                new Boolean(false), new UnsignedInteger(14),
                new PropertyReference(BACnetPropertyIdentifier.activeText,
                        new UnsignedInteger(99)),
                new Real(30));
        final ByteQueue b = new ByteQueue();
        scpr.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);
        while (indicationTodevLocal21 == null) {
        }

        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));

    }

    @Test
    public void TestLifeSafetyOperationRequest() {
        final LifeSafetyOperationRequest lsor = new LifeSafetyOperationRequest(
                new UnsignedInteger(10), new CharacterString("abc"),
                LifeSafetyOperation.unsilenceAudible,
                new BACnetObjectIdentifier(BACnetObjectType.binaryInput, 1));
        final ByteQueue b = new ByteQueue();
        lsor.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);

        while (indicationTodevLocal21 == null) {
        }

        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));
    }

    @Test
    public void TestConfirmedCovNotificationRequest() {
        final SequenceOf<PropertyValue> seq = new SequenceOf<PropertyValue>();
        seq.add(new PropertyValue(BACnetPropertyIdentifier.activeText,
                new UnsignedInteger(10)));
        seq.add(new PropertyValue(BACnetPropertyIdentifier.deviceAddressBinding,
                new UnsignedInteger(17)));
        final ConfirmedCovNotificationRequest ccnr = new ConfirmedCovNotificationRequest(
                new UnsignedInteger(17),
                new BACnetObjectIdentifier(BACnetObjectType.binaryInput, 1),
                new BACnetObjectIdentifier(BACnetObjectType.notificationClass,
                        1),
                new UnsignedInteger(70), seq

        );
        final ByteQueue b = new ByteQueue();
        seq.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);

        while (indicationTodevLocal21 == null) {
        }

        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));
    }

    @Test
    public void TestConfirmedEventNotificationRequest() {
        final ConfirmedEventNotificationRequest cenr = new ConfirmedEventNotificationRequest(
                new UnsignedInteger(77),
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                new BACnetObjectIdentifier(BACnetObjectType.averaging, 1),
                new TimeStamp(new DateTime()), new UnsignedInteger(7),
                new UnsignedInteger(11), EventType.unsignedRange,
                new CharacterString("test123"), NotifyType.ackNotification,
                new Boolean(true), EventState.highLimit, EventState.lowLimit,
                null

        );
        final ByteQueue b = new ByteQueue();
        cenr.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);
        while (indicationTodevLocal21 == null) {
        }
        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));
    }

    @Test
    public void TestAcknowledgeAlarmRequest() {
        // AcknowledgeAlarmRequest
        final AcknowledgeAlarmRequest aar = new AcknowledgeAlarmRequest(
                new UnsignedInteger(1),
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                EventState.highLimit, new TimeStamp(new DateTime()),
                new CharacterString("test"), new TimeStamp(new DateTime()));
        final ByteQueue b = new ByteQueue();
        aar.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal21, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal2NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();
        appService1.doRequest(dDataRequest);
        while (indicationTodevLocal21 == null) {
        }
        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal21.getData().getBody()));
    }

    @Test
    public void TestReadPropertyRequest() {
        final ReadPropertyRequest asdu = new ReadPropertyRequest(
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                BACnetPropertyIdentifier.presentValue);
        final ByteQueue b = new ByteQueue();
        asdu.write(b);
        final TPDU tpdu = new TPDU(devLocal11, devLocal12, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal1NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();

        appService2.doRequest(dDataRequest);
        while (indicationTodevLocal12 == null) {
        }
        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal12.getData().getBody()));
    }

    @Test
    public void TestWritePropertyRequest() {
        final WritePropertyRequest asdu = new WritePropertyRequest(
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                BACnetPropertyIdentifier.presentValue, new UnsignedInteger(55),
                new Real(5), new UnsignedInteger(1));
        final ByteQueue b = new ByteQueue();
        asdu.write(b);
        final TPDU tpdu = new TPDU(devLocal21, devLocal11, b.popAll());
        final T_UnitDataRequest dDataRequest = new T_UnitDataRequest(
                hostLocal1NoTls, tpdu, 1, null);
        final byte[] before = dDataRequest.getData().getBody();

        appService2.doRequest(dDataRequest);
        while (indicationTodevLocal11 == null) {
        }
        assertEquals(Arrays.toString(before),
                Arrays.toString(indicationTodevLocal11.getData().getBody()));

    }

}
