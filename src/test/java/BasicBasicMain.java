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
//
// import ch.fhnw.bacnetit.ase.application.api.BACnetEntityListener;
// import ch.fhnw.bacnetit.ase.application.api.NetworkPortObj;
// import ch.fhnw.bacnetit.ase.application.configuration.api.DiscoveryConfig;
// import ch.fhnw.bacnetit.ase.application.configuration.api.KeystoreConfig;
// import ch.fhnw.bacnetit.ase.application.configuration.api.TruststoreConfig;
// import ch.fhnw.bacnetit.ase.application.transaction.ASEChannel;
// import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
// import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
// import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
// import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
// import ch.fhnw.bacnetit.ase.network.transport.api.ConnectionFactory;
// import ch.fhnw.bacnetit.directorybinding.dnssd.api.DNSSD;
// import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
// import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
// import
// ch.fhnw.bacnetit.transportbinding.ws.incoming.api.WSConnectionServerFactory;
// import
// ch.fhnw.bacnetit.transportbinding.ws.outgoing.api.WSConnectionClientFactory;
// import io.netty.channel.ChannelHandlerContext;
//
// public class BasicBasicMain {
//
// private static String PW = "123456";
// private static String KS_PATH = System.getProperty("user.home")
// + "/git/bacnet-it-tools/stores/siemens_agent01/keyStoreDev1.jks";
// private static String TS_PATH = System.getProperty("user.home")
// + "/git/bacnet-it-tools/stores/siemens_agent01/trustStore.jks";
//
// public static void main(final String[] args) throws Exception {
// DirectoryService.init();
// DirectoryService.getInstance()
// .setDNSBinding(new DNSSD(new DiscoveryConfig("DNSSD",
// "86.119.39.127", "itb.bacnet.ch.",
// "bds._sub._bacnet._tcp.", "dev._sub._bacnet._tcp.",
// "obj._sub._bacnet._tcp.", false)));
//
// final KeystoreConfig keystoreConfig = new KeystoreConfig(KS_PATH, PW,
// "operationaldevcert");
// final TruststoreConfig truststoreConfig = new TruststoreConfig(TS_PATH,
// PW, "trust");
// final NetworkPortObj npo = new NetworkPortObj("ws", 8080,
// keystoreConfig);
//
// final ASEChannel channel = new ASEChannel();
//
// final ChannelListener channelListener = new ChannelListener(
// new BACnetEID(2000)) {
// @Override
// public void onIndication(
// final T_UnitDataIndication tUnitDataIndication,
// final ChannelHandlerContext ctx) {
// final Real real = new Real(
// new ByteQueue(tUnitDataIndication.getData().getBody()));
// System.out.println("Received value: " + real.floatValue());
// }
//
// @Override
// public void onError(final String cause) {
// }
//
//
// };
// channel.registerChannelListener(channelListener);
//
// final BACnetEntityListener bacNetEntityListener = new BACnetEntityListener()
// {
// @Override
// public void onRemoteRemove(final BACnetEID eid) {
// }
//
// @Override
// public void onRemoteAdded(final BACnetEID eid, final URI uri) {
// DirectoryService.getInstance().register(eid, uri, false, true);
// }
//
// @Override
// public void onLocalRequested(final BACnetEID eid) {
// }
// };
// channel.setEntityListener(bacNetEntityListener);
//
// final ConnectionFactory connectionFactory = new ConnectionFactory();
// connectionFactory.addConnectionClient("ws",
// new WSConnectionClientFactory());
// connectionFactory.addConnectionServer("ws",
// new WSConnectionServerFactory(8080));
// channel.initializeAndStart(connectionFactory);
// }
//
// }
