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

public class FileAccessMethod extends Enumerated {
    private static final long serialVersionUID = -2447330212564862936L;

    public static final FileAccessMethod recordAccess = new FileAccessMethod(0);

    public static final FileAccessMethod streamAccess = new FileAccessMethod(1);

    public static final FileAccessMethod[] ALL = { recordAccess,
            streamAccess, };

    public FileAccessMethod(final int value) {
        super(value);
    }

    public FileAccessMethod(final ByteQueue queue) {
        super(queue);
    }
}
