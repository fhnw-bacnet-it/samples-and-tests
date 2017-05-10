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

public class ProgramError extends Enumerated {
    private static final long serialVersionUID = 4478176770591341682L;

    public static final ProgramError normal = new ProgramError(0);

    public static final ProgramError loadFailed = new ProgramError(1);

    public static final ProgramError internal = new ProgramError(2);

    public static final ProgramError program = new ProgramError(3);

    public static final ProgramError other = new ProgramError(4);

    public static final ProgramError[] ALL = { normal, loadFailed, internal,
            program, other, };

    public ProgramError(final int value) {
        super(value);
    }

    public ProgramError(final ByteQueue queue) {
        super(queue);
    }
}
