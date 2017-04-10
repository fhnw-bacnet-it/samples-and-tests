import java.io.IOException;

import ch.fhnw.bacnetit.stack.application.configuration.ConnectionConfig;
import ch.fhnw.bacnetit.stack.application.transaction.Channel;

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

    public static Channel createTestingTransactionChannel(final int serverport)
            throws Exception {
        final ConnectionConfig connectionConfig = new ConnectionConfig("test",
                "ws", 8080, 1);

        final Channel channel = new Channel();
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
