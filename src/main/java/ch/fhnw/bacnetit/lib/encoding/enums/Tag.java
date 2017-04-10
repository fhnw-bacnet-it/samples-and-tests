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

public enum Tag {
    OPENING_TAG((byte) 0xe), CLOSING_TAG((byte) 0xf), UNSPECIFIED((byte) 0x0);

    private byte id;

    Tag(final byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public static Tag valueOf(final byte id) {
        if (id == OPENING_TAG.id) {
            return OPENING_TAG;
        }
        if (id == CLOSING_TAG.id) {
            return CLOSING_TAG;
        }

        return UNSPECIFIED;
    }

    public boolean isContextTag() {
        return false;
    }
}
