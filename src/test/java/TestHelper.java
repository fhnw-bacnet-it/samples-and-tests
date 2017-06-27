
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
import java.io.IOException;

import ch.fhnw.bacnetit.ase.application.configuration.api.ConnectionConfig;
import ch.fhnw.bacnetit.ase.application.service.ASEChannel;

public class TestHelper {

    public static void clearDirectory() throws IOException {

        /*
         * // New directory file if
         * (DirectoryServiceFactory.getInstance().getDirectoryServiceType() ==
         * DirectoryServiceType.DUMMYFILE) { ((DummyFileDirectoryService)
         * DirectoryServiceFactory.getInstance().getDirectoryService()).
         * clearEntries(); }
         */
    }

    public static ASEChannel createTestingTransactionChannel(
            final int serverport) throws Exception {
        final ConnectionConfig connectionConfig = new ConnectionConfig("test",
                "ws", 8080, 1);

        final ASEChannel channel = new ASEChannel();
        // channel.setConnectionFactory(new
        // ConnectionFactory(connectionConfig));
        // channel.initializeAndStart();
        return channel;
    }

    /*
     * public static BACnetDevice createTestingDevice(DeviceConfig devConfig,
     * boolean bds, Channel transactionChannel) throws Exception { BACnetDevice
     * bacnetDevice = new BACnetDevice(transactionChannel, devConfig, new
     * DiscoveryConfig(DirectoryBindingType.MDNS.name(), "127.0.0.1", "test",
     * "test", "test", "test", false)); // NetworkPortObj npo = new
     * NetworkPortObj(chConfig) // bacnetDevice.setNetworkPortObject(npo);
     * transactionChannel.registerListener(bacnetDevice);
     * bacnetDevice.removeAllListener(); bacnetDevice.addListener(new
     * CommunicationTest().new TestingDeviceListener()); if (bds == true) { //
     * Register as BDS //
     * bacnetDevice.getDirectoryServerObj().setDirectoryServerType(
     * DirectoryServiceObjectType.BDS);
     * bacnetDevice.getDirectoryServerObj().registerBDSandEntity(bacnetDevice);
     * } return bacnetDevice;
     *
     * }
     */
}
