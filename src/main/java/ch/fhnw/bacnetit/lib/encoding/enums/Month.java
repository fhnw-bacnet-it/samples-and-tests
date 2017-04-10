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
package ch.fhnw.bacnetit.lib.encoding.enums;

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
