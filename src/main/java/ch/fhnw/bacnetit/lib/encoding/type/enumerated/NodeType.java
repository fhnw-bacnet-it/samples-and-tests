/*******************************************************************************
 * Copyright (C) 2016 The Java BACnetITB Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
