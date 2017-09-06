package demoapp;

// Make sure to import the following classes
// Import Java components
import java.net.URI;

import ch.fhnw.bacnetit.ase.application.service.api.ApplicationService;
import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ComplexACK;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.UnconfirmedRequest;
import ch.fhnw.bacnetit.samplesandtests.api.service.unconfirmed.WhoIsRequest;

/**
 * Simulating a BACnet/IT application using the ApplicationService interface
 * (component of ASE) to send messages to other devices.
 *
 * @author IMVS, FHNW
 *
 */
public class Application1 extends AbstractApplication {

    /**
     * Constructor of class Application1
     *
     * @param applicationService,
     *            the applicationService gets passed from the Configurator.
     */
    public Application1(final ApplicationService applicationService) {
        super(applicationService);

        // To simulate a device within the application, a ChannelListener needs
        // to get implemented.
        final ChannelListener bacnetDevice1001 = new ChannelListener(
                new BACnetEID(1001)) {

            /**
             * Handles incoming message errors from the ASE.
             */
            @Override
            public void onError(final String arg0) {
                // TODO Auto-generated method stub

            }

            /**
             * Handles incoming messages from the ASE.
             */
            @Override
            public void onIndication(final T_UnitDataIndication arg0,
                    final Object context) {
                System.out.println("Application1 got an indication");
                // Parse the incoming message
                final ASDU incoming = getServiceFromBody(
                        arg0.getData().getBody());

                // Dummy Handling of a ReadPropertyAck
                if (incoming instanceof ComplexACK) {
                    System.out.println(
                            "Application1 got an indication - ReadPropertyAck");
                    System.out.println("************\nReceived Value: "
                            + ((ComplexACK) incoming).getService().toString()
                                    .split("\\(")[1].split("\\)")[0]
                            + "\n************");
                }
                // Dummy Handling of a WhoIsRequest
                else if (incoming instanceof UnconfirmedRequest
                        && ((UnconfirmedRequest) incoming)
                                .getService() instanceof WhoIsRequest) {
                    System.out.println(
                            "Application1 got an indication - WhoIsRequest");

                    // Represent an IAmRequest as byte array
                    final byte[] iAmRequest = new byte[] { (byte) 0x1E,
                            (byte) 0x0E, (byte) 0xC4, (byte) 0x02, (byte) 0x00,
                            (byte) 0x00, (byte) 0x00, (byte) 0x21, (byte) 0x01,
                            (byte) 0x91, (byte) 0x00, (byte) 0x21, (byte) 0x01,
                            (byte) 0x0F, (byte) 0x1F };

                    try {
                        System.out.println(
                                "Application1 sends an IAmRequest to Application2");
                        sendBACnetMessage(new URI("ws://localhost:9090"),
                                new BACnetEID(1001), new BACnetEID(2001),
                                iAmRequest);
                    } catch (final Exception e) {
                    }

                }

            }
        };

        // To simulate a device within the application, a ChannelListener needs
        // to get implemented.
        final ChannelListener bacnetDevice1002 = new ChannelListener(
                new BACnetEID(1002)) {

            /**
             * Handles incoming message errors from the ASE.
             */
            @Override
            public void onError(final String arg0) {
                // TODO Auto-generated method stub
            }

            /**
             * Handles incoming messages from the ASE.
             */
            @Override
            public void onIndication(final T_UnitDataIndication arg0,
                    final Object context) {
                // TODO Auto-generated method stub
            }
        };

        // Add the two bacnetDevices to the device list of application1.
        devices.add(bacnetDevice1001);
        devices.add(bacnetDevice1002);
    }

}
