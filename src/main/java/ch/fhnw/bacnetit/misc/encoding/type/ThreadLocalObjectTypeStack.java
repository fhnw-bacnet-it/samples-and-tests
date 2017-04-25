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
package ch.fhnw.bacnetit.misc.encoding.type;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.misc.deviceobjects.BACnetObjectType;

public class ThreadLocalObjectTypeStack {
    private static ThreadLocal<List<BACnetObjectType>> objType = new ThreadLocal<List<BACnetObjectType>>();

    public static void set(final BACnetObjectType objectType) {
        List<BACnetObjectType> stack = objType.get();

        if (stack == null) {
            stack = new ArrayList<BACnetObjectType>();
            objType.set(stack);
        }

        stack.add(objectType);
    }

    public static BACnetObjectType get() {
        final List<BACnetObjectType> stack = objType.get();
        if (stack == null) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public static void remove() {
        final List<BACnetObjectType> stack = objType.get();
        if (stack == null) {
            return;
        }

        if (stack.size() <= 1) {
            objType.remove();
        } else {
            stack.remove(stack.size() - 1);
        }
    }
}
