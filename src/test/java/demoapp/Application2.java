package demoapp;

// Make sure to import the following classes
// Import Java components
import java.net.URI;
import java.net.URISyntaxException;

import ch.fhnw.bacnetit.ase.application.service.api.ApplicationService;
import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataIndication;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ConfirmedRequest;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.acknowledgment.ReadPropertyAck;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;

/**
 * Simulating a BACnet/IT application using the ApplicationService interface
 * (component of ASE) to send messages to other devices.
 *
 * @author IMVS, FHNW
 *
 */
public class Application2 extends AbstractApplication {

    // A value of Application2. Application1 may request this value using a
    // ReadPropertyRequest.
    final private int value = 1000;

    /**
     * Constructor of class Application2
     *
     * @param applicationService,
     *            the applicationService gets passed from the Configurator.
     */
    public Application2(final ApplicationService applicationService) {
        super(applicationService);

        // To simulate a device within the application, a ChannelListener needs
        // to get implemented.
        final ChannelListener bacnetDevice2001 = new ChannelListener(
                new BACnetEID(2001)) {

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

                System.out.println("Application2 got an indication");
                // Parse the incoming message
                final ASDU incoming = getServiceFromBody(
                        arg0.getData().getBody());

                if (incoming instanceof ConfirmedRequest
                        && ((ConfirmedRequest) incoming)
                                .getServiceRequest() instanceof ReadPropertyRequest) {
                    System.out.println(
                            "Application2 got an indication - ReadPropertyRequest");

                    // Prepare DUMMY answer
                    final ByteQueue byteQueue = new ByteQueue();
                    new ReadPropertyAck(
                            new BACnetObjectIdentifier(
                                    BACnetObjectType.analogValue, 1),
                            BACnetPropertyIdentifier.presentValue,
                            new UnsignedInteger(1), new Real(value))
                                    .write(byteQueue);

                    // Send answer
                    try {
                        System.out.println(
                                "Application2 sends an ReadPropertyAck to Application1");
                        sendBACnetMessage(new URI("ws://localhost:8080"),
                                new BACnetEID(2001), new BACnetEID(1001),
                                byteQueue.popAll());
                    } catch (final URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } // Dummy Handling of a ReadPropertyAck
                  // else if (incoming instanceof UnconfirmedRequest &&
                  // ((UnconfirmedRequest)incoming).getService() instanceof
                  // IAmRequest) {
                  // System.out.println(
                  // "Application2 got an indication - IAmRequest ");
                  // }
                  //
            }
        };

        // To simulate a device within the application, a ChannelListener needs
        // to get implemented.
        final ChannelListener bacnetDevice2002 = new ChannelListener(
                new BACnetEID(2002)) {

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

        // Add the two bacnetDevices to the device list of application2.
        devices.add(bacnetDevice2001);
        devices.add(bacnetDevice2002);
    }

}
