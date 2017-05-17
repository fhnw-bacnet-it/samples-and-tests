//
/// *******************************************************************************
// *
// ============================================================================
// * GNU General Public License
// *
// ============================================================================
// *
// * Copyright (C) 2017 University of Applied Sciences and Arts,
// * Northwestern Switzerland FHNW,
// * Institute of Mobile and Distributed Systems.
// * All rights reserved.
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
// * GNU General Public License for more details.
// * You should have received a copy of the GNU General Public License
// * along with this program. If not, see http://www.gnu.orglicenses.
// *******************************************************************************/
// import java.net.URI;
// import java.net.URISyntaxException;
//
// import ch.fhnw.bacnetit.ase.application.api.BACnetEntityListener;
// import ch.fhnw.bacnetit.ase.application.api.NetworkPortObj;
// import ch.fhnw.bacnetit.ase.application.configuration.api.DiscoveryConfig;
// import ch.fhnw.bacnetit.ase.application.configuration.api.KeystoreConfig;
// import ch.fhnw.bacnetit.ase.application.transaction.ASEChannel;
// import ch.fhnw.bacnetit.ase.application.transaction.api.ApplicationService;
// import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelConfiguration;
// import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelFactory;
// import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
// import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
// import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
// import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
// import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataRequest;
// import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
// import ch.fhnw.bacnetit.ase.network.transport.api.ConnectionFactory;
// import ch.fhnw.bacnetit.directorybinding.dnssd.api.DNSSD;
// import
// ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
// import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
// import
// ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
// import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
// import
// ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;
// import
// ch.fhnw.bacnetit.transportbinding.ws.incoming.api.WSConnectionServerFactory;
// import
// ch.fhnw.bacnetit.transportbinding.ws.outgoing.api.WSConnectionClientFactory;
// import io.netty.channel.ChannelHandlerContext;
//
// public class HandsOn1 {
//
//
// public static void main(final String[] args) throws URISyntaxException {
// final ConnectionFactory connectionFactory1 = new ConnectionFactory();
//
// final int port1 = 8080;
// connectionFactory1.addConnectionClient("ws",
// new WSConnectionClientFactory());
// connectionFactory1.addConnectionServer("ws",
// new WSConnectionServerFactory(port1));
//
//
// final ch.fhnw.bacnetit.ase.application.transaction.api.Channel channel =
// ChannelFactory.getInstance();
// final ChannelConfiguration channelConfiguration1 = channel;
// final ApplicationService applicationService1 = channel;
//
//
// final BACnetEID device1inStack1 = new BACnetEID(1001);
// final BACnetEID device2inStack1 = new BACnetEID(1002);
// final KeystoreConfig keystoreConfig1 = new
// KeystoreConfig("dummyKeystores/keyStoreDev1.jks","123456",
// "operationaldevcert");
//
// final NetworkPortObj npo1 = new NetworkPortObj("ws", 8080, keystoreConfig1);
//
// channelConfiguration1.registerChannelListener(new
// ChannelListener(device1inStack1) {
// @Override
// public void onIndication(
// final T_UnitDataIndication tUnitDataIndication,
// final ChannelHandlerContext ctx) {
// System.out.println(this.getEID().getIdentifierAsString()
// + " got an indication" + tUnitDataIndication.getData());
// }
//
// @Override
// public void onError(final String cause) {
// System.err.println(cause);
// }
//
//
// });
//
// channelConfiguration1.registerChannelListener(new
// ChannelListener(device2inStack1) {
// @Override
// public void onIndication(
// final T_UnitDataIndication tUnitDataIndication,
// final ChannelHandlerContext ctx) {
// System.out.println(this.getEID().getIdentifierAsString()
// + " got an indication" + tUnitDataIndication.getData());
// }
//
// @Override
// public void onError(final String cause) {
// System.err.println(cause);
// }
//
//
// });
//
// final BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {
//
// @Override
// public void onRemoteAdded(final BACnetEID eid,
// final URI remoteUri) {
// DirectoryService.getInstance().register(eid, remoteUri, false,
// true);
// }
//
// @Override
// public void onRemoteRemove(final BACnetEID eid) {
// // TODO Auto-generated method stub
// }
//
// @Override
// public void onLocalRequested(final BACnetEID eid) {
// // TODO Auto-generated method stub
// }
//
// };
// channelConfiguration1.setEntityListener(bacNetEntityHandler);
//
// channelConfiguration1.initializeAndStart(connectionFactory1);
//
// final ConnectionFactory connectionFactory2 = new ConnectionFactory();
//
// final int port2 = 9090;
// connectionFactory2.addConnectionClient("ws",
// new WSConnectionClientFactory());
// connectionFactory2.addConnectionServer("ws",
// new WSConnectionServerFactory(port2));
//
// final ch.fhnw.bacnetit.ase.application.transaction.api.Channel channel2 =
// ChannelFactory.getInstance();
// final ChannelConfiguration channelConfiguration2 = channel2;
// final ApplicationService applicationService2 = channel2;
//
//
// final BACnetEID device1inStack2 = new BACnetEID(2001);
// final BACnetEID device2inStack2 = new BACnetEID(2002);
// final KeystoreConfig keystoreConfig2 = new
// KeystoreConfig("dummyKeystores/keyStoreDev1.jks","123456",
// "operationaldevcert");
//
// final NetworkPortObj npo2 = new NetworkPortObj("ws", 9090, keystoreConfig2);
//
// channelConfiguration2.registerChannelListener(new
// ChannelListener(device1inStack2) {
// @Override
// public void onIndication(
// final T_UnitDataIndication tUnitDataIndication,
// final ChannelHandlerContext ctx) {
// System.out.println(this.getEID().getIdentifierAsString()
// + " got an indication" + tUnitDataIndication.getData());
// }
//
// @Override
// public void onError(final String cause) {
// System.err.println(cause);
// }
//
//
// });
//
// channelConfiguration2.registerChannelListener(new
// ChannelListener(device2inStack2) {
// @Override
// public void onIndication(
// final T_UnitDataIndication tUnitDataIndication,
// final ChannelHandlerContext ctx) {
// System.out.println(this.getEID().getIdentifierAsString()
// + " got an indication" + tUnitDataIndication.getData());
// }
//
// @Override
// public void onError(final String cause) {
// System.err.println(cause);
// }
//
//
// });
//
// final BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener()
// {
//
// @Override
// public void onRemoteAdded(final BACnetEID eid,
// final URI remoteUri) {
// DirectoryService.getInstance().register(eid, remoteUri, false,
// true);
// }
//
// @Override
// public void onRemoteRemove(final BACnetEID eid) {
// // TODO Auto-generated method stub
// }
//
// @Override
// public void onLocalRequested(final BACnetEID eid) {
// // TODO Auto-generated method stub
// }
//
// };
// channelConfiguration2.setEntityListener(bacNetEntityHandler2);
//
// channelConfiguration2.initializeAndStart(connectionFactory2);
//
// final DiscoveryConfig ds = new DiscoveryConfig(
// "DNSSD", "86.119.39.127",
// "itb.bacnet.ch.", "bds._sub._bacnet._tcp.",
// "dev._sub._bacnet._tcp.", "obj._sub._bacnet._tcp.", false);
//
// try {
// DirectoryService.init();
// DirectoryService.getInstance().setDNSBinding(new DNSSD(ds));
//
// } catch (final Exception e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
//
// final ReadPropertyRequest readRequest = new ReadPropertyRequest(
// new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
// BACnetPropertyIdentifier.presentValue);
//
//
// final ByteQueue byteQueue = new ByteQueue();
// readRequest.write(byteQueue);
// final TPDU tpdu = new TPDU(device1inStack1, device1inStack2,
// byteQueue.popAll());
//
// final T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(
// new URI("ws://localhost:9090"), tpdu, 1, true, null);
//
// applicationService1.doRequest(unitDataRequest);
// }
//
// }
