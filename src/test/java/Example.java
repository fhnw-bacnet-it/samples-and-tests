import java.net.URI;
import java.net.URISyntaxException;
import ch.fhnw.bacnetit.binding.ws.incoming.WSConnectionServerFactory;
import ch.fhnw.bacnetit.binding.ws.outgoing.WSConnectionClientFactory;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.lib.service.confirmed.ReadPropertyRequest;
import ch.fhnw.bacnetit.stack.application.BACnetEntityListener;
import ch.fhnw.bacnetit.stack.application.NetworkPortObj;
import ch.fhnw.bacnetit.stack.application.configuration.DiscoveryConfig;
import ch.fhnw.bacnetit.stack.application.transaction.Channel;
import ch.fhnw.bacnetit.stack.application.transaction.ChannelListener;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.encoding.TPDU;
import ch.fhnw.bacnetit.stack.encoding.T_UnitDataIndication;
import ch.fhnw.bacnetit.stack.encoding.T_UnitDataRequest;
import ch.fhnw.bacnetit.stack.network.directory.DirectoryBindingType;
import ch.fhnw.bacnetit.stack.network.directory.DirectoryService;
import ch.fhnw.bacnetit.stack.network.transport.ConnectionFactory;
import io.netty.channel.ChannelHandlerContext;

public class Example {


    public static void main(final String[] args) throws URISyntaxException {
        ConnectionFactory connectionFactory1 = new ConnectionFactory();

        int port1 = 8080;
        connectionFactory1.addConnectionClient("ws", new WSConnectionClientFactory());
        connectionFactory1.addConnectionServer("ws", new WSConnectionServerFactory(port1));
        Channel channel1 = new Channel();


        BACnetEID device1inStack1 = new BACnetEID(1001);
        BACnetEID device2inStack1 = new BACnetEID(1002);
        
        NetworkPortObj npo1 = new NetworkPortObj("ws", 8080, null);

        
        channel1.registerChannelListener(new ChannelListener(device1inStack1) {
            @Override
            public void onIndication(
                    final T_UnitDataIndication tUnitDataIndication,
                    final ChannelHandlerContext ctx) {
                System.out.println(this.eid.getIdentifierAsString()
                        + " got an indication" + tUnitDataIndication.getData());
            }

            @Override
            public void onError(final String cause) {
                System.err.println(cause);
            }

            @Override
            public URI getURIfromNPO() {
                return npo1.getUri();
            }
        });

         channel1.registerChannelListener(new ChannelListener(device2inStack1) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo1.getUri();
                    }
        });


        BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {

                    @Override
                    public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                        DirectoryService.getInstance().register(eid, remoteUri, false, true);
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
        channel1.setEntityListener(bacNetEntityHandler);

        channel1.initializeAndStart(connectionFactory1);

        ConnectionFactory connectionFactory2 = new ConnectionFactory();

        int port2 = 9090;
        connectionFactory2.addConnectionClient("ws", new WSConnectionClientFactory());
        connectionFactory2.addConnectionServer("ws", new WSConnectionServerFactory(port2));

        Channel channel2 = new Channel();

        BACnetEID device1inStack2 = new BACnetEID(2001);
        BACnetEID device2inStack2 = new BACnetEID(2002);
        NetworkPortObj npo2 = new NetworkPortObj("ws", 9090,null);

        channel2.registerChannelListener(new ChannelListener(device1inStack2) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo2.getUri();
                    }
        });

         channel2.registerChannelListener(new ChannelListener(device2inStack2) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo2.getUri();
                    }
        });

        BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener() {

                    @Override
                    public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                        DirectoryService.getInstance().register(eid, remoteUri, false, true);
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
        channel2.setEntityListener(bacNetEntityHandler2);

        channel2.initializeAndStart(connectionFactory2);


        final DiscoveryConfig ds = new DiscoveryConfig(
                    DirectoryBindingType.DNSSD.name(), "86.119.39.127",
                    "itb.bacnet.ch.", "bds._sub._bacnet._tcp.",
                    "dev._sub._bacnet._tcp.", "obj._sub._bacnet._tcp.", false);

            try {
                DirectoryService.init();
                DirectoryService.getInstance().setDns(ds);

            } catch (final Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            

        ReadPropertyRequest readRequest = new ReadPropertyRequest(
            new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                                        BACnetPropertyIdentifier.presentValue
                                        );
                                                
                ByteQueue byteQueue = new ByteQueue();
          readRequest.write(byteQueue);
        TPDU tpdu = new TPDU(device1inStack1, device1inStack2, byteQueue.popAll());

        T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(new URI("ws://localhost:9090"), tpdu, 1, true, null);

        channel1.doRequest(unitDataRequest);
    }

}
