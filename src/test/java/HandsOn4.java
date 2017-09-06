
/********************************************************************************
 * ============================================================================
 * *GNU General Public License*============================================================================
 * **Copyright(C)2017 University of Applied Sciences and Arts,*Northwestern Switzerland FHNW,*Institute of Mobile and Distributed Systems.*All rights reserved.**This program is free software:you can redistribute it and/or modify*it under the terms of the GNU General Public License as published by*the Free Software Foundation,either version 3 of the License,or*(at your option)any later version.**This program is distributed in the hope that it will be useful,*but WITHOUT ANY WARRANTY;without even the implied warranty of*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the*GNU General Public License for more details.*You should have received a copy of the GNU General Public License*along with this program.If not,see http://www.gnu.orglicenses.
*******************************************************************************/

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.fhnw.bacnetit.ase.application.api.NetworkPortObj;
import ch.fhnw.bacnetit.ase.application.configuration.api.KeystoreConfig;
import ch.fhnw.bacnetit.ase.application.configuration.api.TruststoreConfig;
import ch.fhnw.bacnetit.ase.application.service.api.ApplicationService;
import ch.fhnw.bacnetit.ase.application.service.api.BACnetEntityListener;
import ch.fhnw.bacnetit.ase.application.service.api.ChannelConfiguration;
import ch.fhnw.bacnetit.ase.application.service.api.ChannelFactory;
import ch.fhnw.bacnetit.ase.application.service.api.TransportBindingService;
import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataRequest;
import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryBinding;
import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ConfirmedRequest;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.SimpleACK;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.AddListElementRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.transportbinding.api.ConnectionFactory;
import ch.fhnw.bacnetit.transportbinding.ws.incoming.tls.api.WSSConnectionServerFactory;
import ch.fhnw.bacnetit.transportbinding.ws.outgoing.tls.api.WSSConnectionClientFactory;

public class HandsOn4 {

    public static void main(final String[] args) throws URISyntaxException {
        DirectoryService ds = null;
        final BACnetEID device1inStack1 = new BACnetEID(1001);
        final URI device1inStack1Uri = new URI("wss://localhost:8080");
        final BACnetEID device1inStack2 = new BACnetEID(2001);
        final URI device1inStack2Uri = new URI("wss://localhost:9090");

        final KeystoreConfig keystoreConfig = new KeystoreConfig(
                "dummyKeystores/keyStoreDev1.jks", "123456",
                "operationaldevcert");
        final TruststoreConfig truststoreConfig = new TruststoreConfig(
                "dummyKeystores/trustStore.jks", "123456", "installer.ch",
                "installer.net");

        final ConnectionFactory connectionFactory1 = new ConnectionFactory();

        connectionFactory1.addConnectionClient("wss",
                new WSSConnectionClientFactory(keystoreConfig,
                        truststoreConfig));
        connectionFactory1.addConnectionServer("wss",
                new WSSConnectionServerFactory(device1inStack1Uri.getPort(),
                        keystoreConfig, truststoreConfig));

        final ch.fhnw.bacnetit.ase.application.service.api.ASEServices channel = ChannelFactory
                .getInstance();
        final ChannelConfiguration channelConfiguration1 = channel;
        final ApplicationService applicationService1 = channel;

        final NetworkPortObj npo1 = new NetworkPortObj("wss",
                device1inStack1Uri.getPort(), keystoreConfig);

        channelConfiguration1
                .registerChannelListener(new ChannelListener(device1inStack1) {
                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {

                        final ServicesSupported servicesSupported = new ServicesSupported();
                        servicesSupported.setAll(true);
                        try {
                            final ASDU msg = new IncomingRequestParser(
                                    servicesSupported,
                                    new ByteQueue(tUnitDataIndication.getData()
                                            .getBody())).parse();
                            if (msg instanceof ConfirmedRequest
                                    && ((ConfirmedRequest) msg)
                                            .getServiceRequest() instanceof AddListElementRequest) {
                                System.out.println(
                                        "BDS got a AddListElementRequest");
                                final AddListElementRequest aler = new AddListElementRequest(
                                        ((ConfirmedRequest) msg)
                                                .getServiceData());

                                if (aler.getListOfElements().getCount() == 1) {

                                    DirectoryService.getInstance().register(
                                            tUnitDataIndication.getData()
                                                    .getSourceEID(),
                                            new URI(((CharacterString) aler
                                                    .getListOfElements().get(1))
                                                            .toString()),
                                            false, false);

                                }
                                final int serviceAckChoice = ((ConfirmedRequest) msg)
                                        .getServiceRequest().getChoiceId();
                                final SimpleACK simpleack = new SimpleACK(
                                        serviceAckChoice);
                                final ByteQueue bq = new ByteQueue();
                                simpleack.write(bq);
                                final TPDU simpleAckTpdu = new TPDU(
                                        device1inStack1, device1inStack2,
                                        bq.popAll());
                                final T_UnitDataRequest simpleAckRequest = new T_UnitDataRequest(
                                        device1inStack2Uri, simpleAckTpdu, 0,
                                        null);
                                applicationService1.doRequest(simpleAckRequest);

                            }

                        } catch (final Exception e) {

                        }

                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

        final BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid,
                    final URI remoteUri) {
                DirectoryService.getInstance().register(eid, remoteUri, false,
                        true);
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
        channelConfiguration1.setEntityListener(bacNetEntityHandler);

        final ConnectionFactory connectionFactory2 = new ConnectionFactory();

        final int port2 = 9090;

        connectionFactory2.addConnectionClient("wss",
                new WSSConnectionClientFactory(keystoreConfig,
                        truststoreConfig));
        connectionFactory2.addConnectionServer("wss",
                new WSSConnectionServerFactory(port2, keystoreConfig,
                        truststoreConfig));

        final ch.fhnw.bacnetit.ase.application.service.api.ASEServices channel2 = ChannelFactory
                .getInstance();
        final ChannelConfiguration channelConfiguration2 = channel2;
        final ApplicationService applicationService2 = channel2;

        final NetworkPortObj npo2 = new NetworkPortObj("wss", 9090,
                keystoreConfig);

        channelConfiguration2
                .registerChannelListener(new ChannelListener(device1inStack2) {
                    @Override
                    public void onIndication(
                            final T_UnitDataIndication tUnitDataIndication,
                            final Object ctx) {
                        System.out.println(this.getEID().getIdentifierAsString()
                                + " got an indication"
                                + tUnitDataIndication.getData());
                        final ServicesSupported servicesSupported = new ServicesSupported();
                        servicesSupported.setAll(true);
                        try {
                            final ASDU msg = new IncomingRequestParser(
                                    servicesSupported,
                                    new ByteQueue(tUnitDataIndication.getData()
                                            .getBody())).parse();
                            System.out.println(msg.getClass());
                        } catch (final Exception e) {
                        }
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                });

        final BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid,
                    final URI remoteUri) {
                DirectoryService.getInstance().register(eid, remoteUri, false,
                        true);
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
        channelConfiguration2.setEntityListener(bacNetEntityHandler2);

        // Implement a dummy Directory Binding using the DirectoryBinding
        // Interface
        final DirectoryBinding dummyDirectoryBinding = new DirectoryBinding() {
            BACnetEID bdsEid = null;
            Map<BACnetEID, URI> records = new HashMap<BACnetEID, URI>();

            @Override
            public List<BACnetEID> findBDS() {
                if (bdsEid == null) {
                    return null;
                }
                final List<BACnetEID> bdsList = new LinkedList<BACnetEID>();
                bdsList.add(bdsEid);
                return bdsList;

            }

            @Override
            public URI resolve(final BACnetEID eid) {
                return records.get(eid);
            }

            @Override
            public void delete(final BACnetEID eid) {
                // TODO Auto-generated method stub

            }

            @Override
            public void register(final BACnetEID eid, final URI url,
                    final boolean isBDS) {
                records.put(eid, url);
                if (isBDS) {
                    bdsEid = eid;
                }
            }

            @Override
            public void registerObject(final String instance,
                    final boolean isInstanceObjectName, final String txtvers,
                    final BACnetEID bacnetEid, final String oid_oname,
                    final int ttl, final int quality) {
                // TODO Auto-generated method stub

            }
        };

        try {
            DirectoryService.init();
            ds = DirectoryService.getInstance();
            ds.setDNSBinding(dummyDirectoryBinding);

            // Register the device from application 1 as BDS.
            // BDS registers itself directly using Directory Service.
            ds.register(

                    new BACnetEID(
                            ((TransportBindingService) channelConfiguration1)
                                    .getChannelListeners().get(0)),
                    device1inStack1Uri, true, false);

            // After registration find BDS EID using the Directoy Service
            final BACnetEID bdsEID = ds.getBds();
            System.out.println("BDS EID is: " + bdsEID.getIdentifierAsString());
            final URI bdsURI = ds.resolve(bdsEID);
            System.out.println("BDS URI is: " + bdsURI.toString());

            final SequenceOf<CharacterString> uriChars = new SequenceOf<CharacterString>();
            uriChars.add(new CharacterString(device1inStack2Uri.toString()));
            final AddListElementRequest request = new AddListElementRequest(
                    new BACnetObjectIdentifier(BACnetObjectType.multiStateInput,
                            1),
                    BACnetPropertyIdentifier.stateText, null, uriChars);

            // Send a registration request from device1inStack2 (non-BDS) to
            // device1inStack1 (BDS)
            final ByteQueue byteQueue = new ByteQueue();
            request.write(byteQueue);
            final TPDU remoteRegistrationTPDU = new TPDU(device1inStack2,
                    device1inStack1, byteQueue.popAll());
            final T_UnitDataRequest remoteRegistrationRequest = new T_UnitDataRequest(
                    bdsURI, remoteRegistrationTPDU, 0, null);
            applicationService2.doRequest(remoteRegistrationRequest);
            // BDS should register device1inStack2 in DNS using the
            // directoryBinding (dummyDirectoryBinding)

        } catch (final Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (final Exception e) {
        }

        final ReadPropertyRequest readRequest = new ReadPropertyRequest(
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                BACnetPropertyIdentifier.presentValue);

        final ByteQueue byteQueue = new ByteQueue();
        readRequest.write(byteQueue);
        final TPDU tpdu = new TPDU(device1inStack1, device1inStack2,
                byteQueue.popAll());

        // Device1inStack1 (BDS) resolves BACnetEID from Device1inStack2 using
        // the DirectoryService
        URI dev1stack2UriResolved = null;
        try {
            dev1stack2UriResolved = ds.resolve(device1inStack2);
            System.out.println("Resolved URI for Device 1 in Stack 2: "
                    + dev1stack2UriResolved.toString());
        } catch (final Exception e) {
            System.err.println("No URI found for dev1Stack2");
            System.err.println(e);

        }

        final T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(
                dev1stack2UriResolved, tpdu, 1, null);

        applicationService1.doRequest(unitDataRequest);
    }

}
