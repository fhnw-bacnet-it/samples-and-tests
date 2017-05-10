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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.enums;

/**
 * Per ASHRAE Standard 135-2012 p.713
 *
 */
public enum Month {
    JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(
            7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(
                    12), ODD_MONTHS(13), EVEN_MONTHS(14), UNSPECIFIED(255);// X'FF'
                                                                           // =
                                                                           // any
                                                                           // month

    private byte id;

    Month(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return id;
    }

    public static Month valueOf(final byte id) {
        if (id == JANUARY.id) {
            return JANUARY;
        }
        if (id == FEBRUARY.id) {
            return FEBRUARY;
        }
        if (id == MARCH.id) {
            return MARCH;
        }
        if (id == APRIL.id) {
            return APRIL;
        }
        if (id == MAY.id) {
            return MAY;
        }
        if (id == JUNE.id) {
            return JUNE;
        }
        if (id == JULY.id) {
            return JULY;
        }
        if (id == AUGUST.id) {
            return AUGUST;
        }
        if (id == SEPTEMBER.id) {
            return SEPTEMBER;
        }
        if (id == OCTOBER.id) {
            return OCTOBER;
        }
        if (id == NOVEMBER.id) {
            return NOVEMBER;
        }
        if (id == DECEMBER.id) {
            return DECEMBER;
        }
        if (id == ODD_MONTHS.id) {
            return ODD_MONTHS;
        }
        if (id == EVEN_MONTHS.id) {
            return EVEN_MONTHS;
        }
        return UNSPECIFIED;
    }
}
