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

public enum MaxSegments {
    UNSPECIFIED(0, Integer.MAX_VALUE), //
    UP_TO_2(1, 2), //
    UP_TO_4(2, 4), //
    UP_TO_8(3, 8), //
    UP_TO_16(4, 16), //
    UP_TO_32(5, 32), //
    UP_TO_64(6, 64), //
    MORE_THAN_64(7, Integer.MAX_VALUE), //
    ;

    private byte id;

    private int maxSegments;

    MaxSegments(final int id, final int maxSegments) {
        this.id = (byte) id;
        this.maxSegments = maxSegments;
    }

    public byte getId() {
        return id;
    }

    public int getMaxSegments() {
        return maxSegments;
    }

    public static MaxSegments valueOf(final byte id) {
        if (id == UNSPECIFIED.id) {
            return UNSPECIFIED;
        }
        if (id == UP_TO_2.id) {
            return UP_TO_2;
        }
        if (id == UP_TO_4.id) {
            return UP_TO_4;
        }
        if (id == UP_TO_8.id) {
            return UP_TO_8;
        }
        if (id == UP_TO_16.id) {
            return UP_TO_16;
        }
        if (id == UP_TO_32.id) {
            return UP_TO_32;
        }
        if (id == UP_TO_64.id) {
            return UP_TO_64;
        }
        if (id == MORE_THAN_64.id) {
            return MORE_THAN_64;
        }

        throw new IllegalArgumentException("Unknown id: " + id);
    }
}
