package ch.fhnw.bacnetit.lib.encoding.type.itb.primitive;

import java.io.Serializable;
import java.util.LinkedList;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.ObjectConfig;

public class DeviceConfig implements Serializable {
    private static final long serialVersionUID = -555457797053116570L;
    public final boolean isBDS, registerWithBDS;
    public final String eid;
    private final LinkedList<ObjectConfig> objects = new LinkedList<>();
    public int infoPanelWidth = -1, infoPanelHeight = -1;
    boolean hasInfopanel = false;

    public DeviceConfig(final String eid, final boolean isBDS,
            final boolean registerWithBDS) {
        this.eid = eid;
        this.isBDS = isBDS;
        this.registerWithBDS = registerWithBDS;
    }

    public void addObject(final ObjectConfig objectConfig) {
        this.objects.add(objectConfig);
    }

    /**
     * Returns a copied list of objects.
     *
     * @return List of objects
     */
    public LinkedList<ObjectConfig> getObjects() {
        return new LinkedList<>(objects);
    }

    public boolean hasInfoPanel() {
        return infoPanelHeight > 0 && infoPanelWidth > 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("\tDEVICE:\n");
        builder.append("EID: " + eid + "\n");
        builder.append("BDS: " + isBDS + "\n");
        builder.append("register: " + registerWithBDS + "\n");
        builder.append("info panel height: " + infoPanelHeight + " width: "
                + infoPanelWidth + "\n");
        for (final ObjectConfig objectConfig : objects) {
            builder.append(objectConfig);
        }
        return builder.toString();
    }
}
