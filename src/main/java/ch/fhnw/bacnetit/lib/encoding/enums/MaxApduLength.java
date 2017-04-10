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

public enum MaxApduLength {
    UP_TO_50(0, 50), // MinimumMessageSize
    UP_TO_128(1, 128), //
    UP_TO_206(2, 206), // Fits in a LonTalk frame
    UP_TO_480(3, 480), // Fits in an ARCNET frame
    UP_TO_1024(4, 1024), //
    UP_TO_1476(5, 1476); // Fits in an ISO 8802-3 frame

    private byte id;

    private int maxLength;

    MaxApduLength(final int id, final int maxLength) {
        this.id = (byte) id;
        this.maxLength = maxLength;
    }

    public byte getId() {
        return id;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public static MaxApduLength valueOf(final byte id) {
        if (id == UP_TO_50.id) {
            return UP_TO_50;
        }
        if (id == UP_TO_128.id) {
            return UP_TO_128;
        }
        if (id == UP_TO_206.id) {
            return UP_TO_206;
        }
        if (id == UP_TO_480.id) {
            return UP_TO_480;
        }
        if (id == UP_TO_1024.id) {
            return UP_TO_1024;
        }
        if (id == UP_TO_1476.id) {
            return UP_TO_1476;
        }

        throw new IllegalArgumentException("Unknown id: " + id);
    }
}
