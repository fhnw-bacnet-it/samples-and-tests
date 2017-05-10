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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class DoorSecuredStatus extends Enumerated {
    private static final long serialVersionUID = 7337105893343734773L;

    public static final DoorSecuredStatus secured = new DoorSecuredStatus(0);

    public static final DoorSecuredStatus unsecured = new DoorSecuredStatus(1);

    public static final DoorSecuredStatus unknown = new DoorSecuredStatus(2);

    public static final DoorSecuredStatus[] ALL = { secured, unsecured,
            unknown, };

    public DoorSecuredStatus(final int value) {
        super(value);
    }

    public DoorSecuredStatus(final ByteQueue queue) {
        super(queue);
    }
}
