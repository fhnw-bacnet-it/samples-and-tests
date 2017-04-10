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
