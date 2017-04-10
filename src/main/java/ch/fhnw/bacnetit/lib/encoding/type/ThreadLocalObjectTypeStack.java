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
package ch.fhnw.bacnetit.lib.encoding.type;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectType;

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
