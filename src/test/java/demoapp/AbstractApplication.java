package demoapp;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.bacnetit.ase.application.service.api.ApplicationService;
// Import packages from the BACnet/IT opensource projects
// By convention just classes within an api package should be used
import ch.fhnw.bacnetit.ase.application.transaction.api.ChannelListener;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.encoding.api.TPDU;
import ch.fhnw.bacnetit.ase.encoding.api.T_UnitDataRequest;
// BACnet4J components
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.ASDU;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu.IncomingRequestParser;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;
import ch.fhnw.bacnetit.samplesandtests.api.service.confirmed.ReadPropertyRequest;

/**
 * AbstractApplication provides base functionality
 *
 * @author IMVS, FHNW
 *
 */
public abstract class AbstractApplication {

    // applicationService is the application's view of the stack.
    // applicationService is used to send BACnet messages to other devices.
    // The configurator class will pass the applicationService, as shown later
    final private ApplicationService applicationService;

    // devices is a list of simulated BACnet devices.
    final public List<ChannelListener> devices = new LinkedList<ChannelListener>();

    public AbstractApplication(final ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * sendReadPropertyRequestUsingBACnet4j() sends a ReadRequestProperty to a
     * given destination. To represent such a ReadPropertyRequest BACnet4J is
     * used. Note that the TPDU constructor demands a byte array that represents
     * the BACnet service. Feel free to provide the byte array without the usage
     * of BACnet4J by using the sendBACnetMessage(byte[]) method.
     *
     * @throws URISyntaxException
     */
    public void sendReadPropertyRequestUsingBACnet4j(final URI destination,
            final BACnetEID from, final BACnetEID to)
            throws URISyntaxException {
        final ReadPropertyRequest readRequest = new ReadPropertyRequest(
                new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                BACnetPropertyIdentifier.presentValue);
        final ByteQueue byteQueue = new ByteQueue();
        readRequest.write(byteQueue);
        final TPDU tpdu = new TPDU(from, to, byteQueue.popAll());

        final T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(
                destination, tpdu, 1, null);

        applicationService.doRequest(unitDataRequest);

    }

    /**
     * sendBACnetMessage() sends a BACnet message represented as byte array to
     * the given destination. Ensure the byte array represents a valid BACnet
     * message.
     *
     * @param bacnetMessage
     * @throws URISyntaxException
     */
    public void sendBACnetMessage(final URI destination, final BACnetEID from,
            final BACnetEID to, final byte[] confirmedBacnetMessage)
            throws URISyntaxException {

        final TPDU tpdu = new TPDU(from, to, confirmedBacnetMessage);

        final T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(
                destination, tpdu, 1, null);

        applicationService.doRequest(unitDataRequest);
    }

    /**
     * getServiceFromBody() is a helper method to interpret received BACnet
     * messages.
     *
     * @param body
     * @return
     */
    protected ASDU getServiceFromBody(final byte[] body) {
        final ByteQueue queue = new ByteQueue(body);
        final ServicesSupported servicesSupported = new ServicesSupported();
        servicesSupported.setAll(true);
        final IncomingRequestParser parser = new IncomingRequestParser(
                servicesSupported, queue);
        ASDU request = null;

        try {
            request = parser.parse();
        } catch (final Exception e) {
            System.out.println(e);
        }
        return request;
    }

}
