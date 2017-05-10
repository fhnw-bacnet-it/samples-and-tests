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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.itb.primitive;

import java.io.Serializable;
import java.util.LinkedList;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.ObjectConfig;

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
