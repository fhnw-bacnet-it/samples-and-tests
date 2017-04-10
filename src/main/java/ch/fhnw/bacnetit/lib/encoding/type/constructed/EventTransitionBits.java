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
package ch.fhnw.bacnetit.lib.encoding.type.constructed;

import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class EventTransitionBits extends BitString {
    private static final long serialVersionUID = 7904969709288399573L;

    public EventTransitionBits(final boolean toOffnormal, final boolean toFault,
            final boolean toNormal) {
        super(new boolean[] { toOffnormal, toFault, toNormal });
    }

    public EventTransitionBits(final ByteQueue queue) {
        super(queue);
    }

    public boolean isToOffnormal() {
        return getValue()[0];
    }

    public boolean isToFault() {
        return getValue()[1];
    }

    public boolean isToNormal() {
        return getValue()[2];
    }

    public boolean contains(final EventState toState) {
        if (toState.equals(EventState.normal) && isToNormal()) {
            return true;
        }

        if (toState.equals(EventState.fault) && isToFault()) {
            return true;
        }

        // All other event states are considered off-normal
        return isToOffnormal();
    }
}
