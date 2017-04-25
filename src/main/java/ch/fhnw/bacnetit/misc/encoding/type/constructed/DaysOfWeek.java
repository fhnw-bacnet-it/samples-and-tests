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
package ch.fhnw.bacnetit.misc.encoding.type.constructed;

import ch.fhnw.bacnetit.misc.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

public class DaysOfWeek extends BitString {
    private static final long serialVersionUID = -7381461753680751900L;

    public DaysOfWeek() {
        super(new boolean[7]);
    }

    public DaysOfWeek(final boolean defaultValue) {
        super(7, defaultValue);
    }

    public DaysOfWeek(final ByteQueue queue) {
        super(queue);
    }

    public boolean contains(final int day) {
        return getValue()[day];
    }

    public boolean isMonday() {
        return getValue()[0];
    }

    public void setMonday(final boolean monday) {
        getValue()[0] = monday;
    }

    public boolean isTuesday() {
        return getValue()[1];
    }

    public void setTuesday(final boolean tuesday) {
        getValue()[1] = tuesday;
    }

    public boolean isWednesday() {
        return getValue()[2];
    }

    public void setWednesday(final boolean wednesday) {
        getValue()[2] = wednesday;
    }

    public boolean isThursday() {
        return getValue()[3];
    }

    public void setThursday(final boolean thursday) {
        getValue()[3] = thursday;
    }

    public boolean isFriday() {
        return getValue()[4];
    }

    public void setFriday(final boolean friday) {
        getValue()[4] = friday;
    }

    public boolean isSaturday() {
        return getValue()[5];
    }

    public void setSaturday(final boolean saturday) {
        getValue()[5] = saturday;
    }

    public boolean isSunday() {
        return getValue()[6];
    }

    public void setSunday(final boolean sunday) {
        getValue()[6] = sunday;
    }
}
