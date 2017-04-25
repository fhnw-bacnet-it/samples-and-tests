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
package ch.fhnw.bacnetit.misc.encoding.enums;

/**
 * Per ASHRAE Standard 135-2012 p.713
 *
 *
 */
public enum DayOfWeek {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(
            6), SUNDAY(7), UNSPECIFIED(255);// i.e.
                                            // X'FF'
                                            // =
                                            // any
                                            // day
                                            // of
                                            // week

    private byte id;

    DayOfWeek(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return id;
    }

    public static DayOfWeek valueOf(final byte id) {
        if (id == MONDAY.id) {
            return MONDAY;
        }
        if (id == TUESDAY.id) {
            return TUESDAY;
        }
        if (id == WEDNESDAY.id) {
            return WEDNESDAY;
        }
        if (id == THURSDAY.id) {
            return THURSDAY;
        }
        if (id == FRIDAY.id) {
            return FRIDAY;
        }
        if (id == SATURDAY.id) {
            return SATURDAY;
        }
        if (id == SUNDAY.id) {
            return SUNDAY;
        }
        return UNSPECIFIED;
    }
}
