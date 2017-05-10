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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

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
