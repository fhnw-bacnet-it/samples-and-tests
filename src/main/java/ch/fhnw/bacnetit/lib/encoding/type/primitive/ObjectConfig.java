package ch.fhnw.bacnetit.lib.encoding.type.primitive;

import java.io.Serializable;
import java.util.LinkedList;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.lib.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;

public class ObjectConfig implements Serializable {
    private static final long serialVersionUID = -8627210334469107678L;
    public final static int ID = 0, VALUE = 1;
    public final BACnetObjectType type;
    public final int instanceNr;
    private final LinkedList<Encodable[]> properties = new LinkedList<>();

    public ObjectConfig(final int instanceNr, final BACnetObjectType type) {
        this.instanceNr = instanceNr;
        this.type = type;
    }

    public void addProperty(final BACnetPropertyIdentifier identifier,
            final Encodable value) {
        properties.add(new Encodable[] { identifier, value });
    }

    /**
     * Returns a copied list of properties.
     *
     * @return List of properties
     */
    public LinkedList<Encodable[]> getProperties() {
        return new LinkedList<>(properties);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                "\tObject, Type: " + type + " number " + instanceNr + "\n");
        for (final Object[] property : properties) {
            builder.append(
                    "ID: " + property[0] + " value: " + property[1] + "\n");
        }
        return builder.toString();
    }

}
