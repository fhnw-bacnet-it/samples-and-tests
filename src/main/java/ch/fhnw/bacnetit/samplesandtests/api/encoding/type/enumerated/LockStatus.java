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
public class LockStatus extends Enumerated {
    private static final long serialVersionUID = -1433958074950622510L;

    public static final LockStatus locked = new LockStatus(0);

    public static final LockStatus unlocked = new LockStatus(1);

    public static final LockStatus fault = new LockStatus(2);

    public static final LockStatus unknown = new LockStatus(3);

    public static final LockStatus[] ALL = { locked, unlocked, fault,
            unknown, };

    public LockStatus(final int value) {
        super(value);
    }

    public LockStatus(final ByteQueue queue) {
        super(queue);
    }
}
