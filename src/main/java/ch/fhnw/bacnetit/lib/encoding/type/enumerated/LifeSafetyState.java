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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class LifeSafetyState extends Enumerated {
    private static final long serialVersionUID = 2161598068039518923L;

    public static final LifeSafetyState quiet = new LifeSafetyState(0);

    public static final LifeSafetyState preAlarm = new LifeSafetyState(1);

    public static final LifeSafetyState alarm = new LifeSafetyState(2);

    public static final LifeSafetyState fault = new LifeSafetyState(3);

    public static final LifeSafetyState faultPreAlarm = new LifeSafetyState(4);

    public static final LifeSafetyState faultAlarm = new LifeSafetyState(5);

    public static final LifeSafetyState notReady = new LifeSafetyState(6);

    public static final LifeSafetyState active = new LifeSafetyState(7);

    public static final LifeSafetyState tamper = new LifeSafetyState(8);

    public static final LifeSafetyState testAlarm = new LifeSafetyState(9);

    public static final LifeSafetyState testActive = new LifeSafetyState(10);

    public static final LifeSafetyState testFault = new LifeSafetyState(11);

    public static final LifeSafetyState testFaultAlarm = new LifeSafetyState(
            12);

    public static final LifeSafetyState holdup = new LifeSafetyState(13);

    public static final LifeSafetyState duress = new LifeSafetyState(14);

    public static final LifeSafetyState tamperAlarm = new LifeSafetyState(15);

    public static final LifeSafetyState abnormal = new LifeSafetyState(16);

    public static final LifeSafetyState emergencyPower = new LifeSafetyState(
            17);

    public static final LifeSafetyState delayed = new LifeSafetyState(18);

    public static final LifeSafetyState blocked = new LifeSafetyState(19);

    public static final LifeSafetyState localAlarm = new LifeSafetyState(20);

    public static final LifeSafetyState generalAlarm = new LifeSafetyState(21);

    public static final LifeSafetyState supervisory = new LifeSafetyState(22);

    public static final LifeSafetyState testSupervisory = new LifeSafetyState(
            23);

    public static final LifeSafetyState[] ALL = { quiet, preAlarm, alarm, fault,
            faultPreAlarm, faultAlarm, notReady, active, tamper, testAlarm,
            testActive, testFault, testFaultAlarm, holdup, duress, tamperAlarm,
            abnormal, emergencyPower, delayed, blocked, localAlarm,
            generalAlarm, supervisory, testSupervisory, };

    public LifeSafetyState(final int value) {
        super(value);
    }

    public LifeSafetyState(final ByteQueue queue) {
        super(queue);
    }

    @Override
    public String toString() {
        final int type = intValue();
        if (type == quiet.intValue()) {
            return "Quiet";
        }
        if (type == preAlarm.intValue()) {
            return "Pre-alarm";
        }
        if (type == alarm.intValue()) {
            return "Alarm";
        }
        if (type == fault.intValue()) {
            return "Fault";
        }
        if (type == faultPreAlarm.intValue()) {
            return "Fault pre-alarm";
        }
        if (type == faultAlarm.intValue()) {
            return "Fault alarm";
        }
        if (type == notReady.intValue()) {
            return "Not ready";
        }
        if (type == active.intValue()) {
            return "Active";
        }
        if (type == tamper.intValue()) {
            return "Tamper";
        }
        if (type == testAlarm.intValue()) {
            return "Test alarm";
        }
        if (type == testActive.intValue()) {
            return "Test active";
        }
        if (type == testFault.intValue()) {
            return "Test fault";
        }
        if (type == testFaultAlarm.intValue()) {
            return "Test fault alarm";
        }
        if (type == holdup.intValue()) {
            return "Holdup";
        }
        if (type == duress.intValue()) {
            return "Duress";
        }
        if (type == tamperAlarm.intValue()) {
            return "Tamper alarm";
        }
        if (type == abnormal.intValue()) {
            return "Abnormal";
        }
        if (type == emergencyPower.intValue()) {
            return "Emergency power";
        }
        if (type == delayed.intValue()) {
            return "Delayed";
        }
        if (type == blocked.intValue()) {
            return "Blocked";
        }
        if (type == localAlarm.intValue()) {
            return "Local alarm";
        }
        if (type == generalAlarm.intValue()) {
            return "General alarm";
        }
        if (type == supervisory.intValue()) {
            return "Supervisory";
        }
        if (type == testSupervisory.intValue()) {
            return "Test supervisory";
        }

        return "Unknown: " + type;
    }

}
