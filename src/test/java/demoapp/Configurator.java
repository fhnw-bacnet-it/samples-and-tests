package demoapp;

// Make sure to import the following classes
// Import Java components
import java.net.URI;

// Import packages from the BACnet/IT opensource projects
// By convention just classes within an api package should be used
import ch.fhnw.bacnetit.ase.application.api.BACnetEntityListener;
import ch.fhnw.bacnetit.ase.application.service.ASEService;
import ch.fhnw.bacnetit.ase.application.transaction.api.*;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
import ch.fhnw.bacnetit.ase.network.transport.api.*;
import ch.fhnw.bacnetit.transportbinding.ws.BindingConfiguration;
import ch.fhnw.bacnetit.transportbinding.ws.BindingInitializer;
import ch.fhnw.bacnetit.transportbinding.ws.ConnectionFactory;
import ch.fhnw.bacnetit.transportbinding.ws.incoming.api.*;
import ch.fhnw.bacnetit.transportbinding.ws.outgoing.api.*;

public class Configurator {

    public static void main(String[] args) {
        
        int wsServerPort1 = 8080;
        int wsServerPort2 = 9090;
        

        /*
         *********************** SETUP ASE 1 ***********************
         */
        final ASEServices channel1 = ChannelFactory.getInstance();
        

        // Configure BACnetEntity Listener to handle Control Messages
        final BACnetEntityListener bacNetEntityHandler1 = new BACnetEntityListener() {

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

        ((ChannelConfiguration)channel1).setEntityListener(bacNetEntityHandler1);

        // Configure the transport binding
        final ConnectionFactory connectionFactory1 = new ConnectionFactory();
        connectionFactory1.addConnectionClient("ws",
                new WSConnectionClientFactory());
 
        connectionFactory1.addConnectionServer("ws",
                new WSConnectionServerFactory(wsServerPort1));
        
        BindingConfiguration bindingConfiguration = new BindingInitializer();
        ((ChannelConfiguration)channel1).addBinding((ASEService)bindingConfiguration);
        bindingConfiguration.initializeAndStart(connectionFactory1);
   
      

        /*
         *********************** SETUP ASE 2 ***********************
         */
        final ASEServices channel2 = ChannelFactory.getInstance();

        // Configure BACnetEntity Listener to handle Control Messages
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

        ((ChannelConfiguration)channel2).setEntityListener(bacNetEntityHandler2);

        // Configure the transport binding
        final ConnectionFactory connectionFactory2 = new ConnectionFactory();
        connectionFactory2.addConnectionClient("ws",
                new WSConnectionClientFactory());

        connectionFactory2.addConnectionServer("ws",
                new WSConnectionServerFactory(wsServerPort2));
        
        BindingConfiguration bindingConfiguration2 = new BindingInitializer();
        ((ChannelConfiguration)channel2).addBinding((ASEService)bindingConfiguration2);
        bindingConfiguration2.initializeAndStart(connectionFactory2);
 
        /*
         *********************** Register BACnet devices from application 1 in ASE 1 ***********************
         */
        AbstractApplication application1 = new Application1((ApplicationService)channel1);
        for (ChannelListener device : application1.devices) {
            ((ChannelConfiguration)channel1).registerChannelListener(device);
        }

        /*
         *********************** Register BACnet devices from application 2 in ASE 2 ***********************
         */
        AbstractApplication application2 = new Application2((ApplicationService)channel2);
        for (ChannelListener device : application2.devices) {
            ((ChannelConfiguration)channel2).registerChannelListener(device);
        }

        DirectoryService.init();
       
        /*
         *********************** Enforce Application1 to send a ReadPropertyRequest to Application2.
         * Application2 answers with its "value". To represent the
         * ReadPropertyRequest and the ReadPropertyAck BACnet4J is
         * used.***********************
         */
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        try {
            System.out.println("Application1 sends a ReadPropRequest to Application2");
            application1.sendReadPropertyRequestUsingBACnet4j(
                    new URI("ws://localhost:"+wsServerPort2), new BACnetEID(1001),
                    new BACnetEID(2001));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print(e);
        }

        // Wait until close
        try{
            System.in.read();
        }catch(Exception e){}
    }
}