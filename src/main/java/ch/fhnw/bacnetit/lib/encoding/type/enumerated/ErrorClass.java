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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ErrorClass extends Enumerated {
    private static final long serialVersionUID = 1756743535333051828L;

    public static final ErrorClass device = new ErrorClass(0);

    public static final ErrorClass object = new ErrorClass(1);

    public static final ErrorClass property = new ErrorClass(2);

    public static final ErrorClass resources = new ErrorClass(3);

    public static final ErrorClass security = new ErrorClass(4);

    public static final ErrorClass services = new ErrorClass(5);

    public static final ErrorClass vt = new ErrorClass(6);

    public static final ErrorClass communication = new ErrorClass(7);

    public static final ErrorClass[] ALL = { device, object, property,
            resources, security, services, vt, communication, };

    public ErrorClass(final int value) {
        super(value);
    }

    public ErrorClass(final ByteQueue queue) {
        super(queue);
    }

    @Override
    public String toString() {
        final int type = intValue();
        if (type == device.intValue()) {
            return "Device";
        }
        if (type == object.intValue()) {
            return "Object";
        }
        if (type == property.intValue()) {
            return "Property";
        }
        if (type == resources.intValue()) {
            return "Resources";
        }
        if (type == security.intValue()) {
            return "Security";
        }
        if (type == services.intValue()) {
            return "Services";
        }
        if (type == vt.intValue()) {
            return "VT";
        }
        if (type == communication.intValue()) {
            return "Communication";
        }
        return "Unknown: " + type;
    }
}
