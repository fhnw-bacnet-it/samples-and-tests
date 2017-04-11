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

public class VtClass extends Enumerated {
    private static final long serialVersionUID = 8557805107090951917L;

    public static final VtClass defaultTerminal = new VtClass(0);

    public static final VtClass ansi_x3_64 = new VtClass(1);

    public static final VtClass dec_vt52 = new VtClass(2);

    public static final VtClass dec_vt100 = new VtClass(3);

    public static final VtClass dec_vt220 = new VtClass(4);

    public static final VtClass hp_700_94 = new VtClass(5);

    public static final VtClass ibm_3130 = new VtClass(6);

    public static final VtClass[] ALL = { defaultTerminal, ansi_x3_64, dec_vt52,
            dec_vt100, dec_vt220, hp_700_94, ibm_3130, };

    public VtClass(final int value) {
        super(value);
    }

    public VtClass(final ByteQueue queue) {
        super(queue);
    }
}
