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
package ch.fhnw.bacnetit.misc.encoding.type.enumerated;

import ch.fhnw.bacnetit.misc.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class ProgramRequest extends Enumerated {
    private static final long serialVersionUID = 8388693192499087156L;

    public static final ProgramRequest ready = new ProgramRequest(0);

    public static final ProgramRequest load = new ProgramRequest(1);

    public static final ProgramRequest run = new ProgramRequest(2);

    public static final ProgramRequest halt = new ProgramRequest(3);

    public static final ProgramRequest restart = new ProgramRequest(4);

    public static final ProgramRequest unload = new ProgramRequest(5);

    public static final ProgramRequest[] ALL = { ready, load, run, halt,
            restart, unload, };

    public ProgramRequest(final int value) {
        super(value);
    }

    public ProgramRequest(final ByteQueue queue) {
        super(queue);
    }
}
