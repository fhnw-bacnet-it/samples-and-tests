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
package ch.fhnw.bacnetit.samplesandtests.encoding.type.enumerated;

import ch.fhnw.bacnetit.samplesandtests.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class NodeType extends Enumerated {
    private static final long serialVersionUID = -1462203629019212150L;

    public static final NodeType unknown = new NodeType(0);

    public static final NodeType system = new NodeType(1);

    public static final NodeType network = new NodeType(2);

    public static final NodeType device = new NodeType(3);

    public static final NodeType organizational = new NodeType(4);

    public static final NodeType area = new NodeType(5);

    public static final NodeType equipment = new NodeType(6);

    public static final NodeType point = new NodeType(7);

    public static final NodeType collection = new NodeType(8);

    public static final NodeType property = new NodeType(9);

    public static final NodeType functional = new NodeType(10);

    public static final NodeType other = new NodeType(11);

    public static final NodeType[] ALL = { unknown, system, network, device,
            organizational, area, equipment, point, collection, property,
            functional, other, };

    public NodeType(final int value) {
        super(value);
    }

    public NodeType(final ByteQueue queue) {
        super(queue);
    }
}
