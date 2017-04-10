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

import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
