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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.BackupState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.BinaryPV;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.DeviceStatus;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.DoorAlarmState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EngineeringUnits;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.EventType;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyMode;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.LifeSafetyState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.Polarity;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ProgramError;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ProgramRequest;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ProgramState;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.Reliability;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.RestartReason;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class PropertyStates extends BaseType {
    private static final long serialVersionUID = 1112998027203005048L;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Boolean.class); // 0
        classes.add(BinaryPV.class); // 1
        classes.add(EventType.class); // 2
        classes.add(Polarity.class); // 3
        classes.add(ProgramRequest.class); // 4;
        classes.add(ProgramState.class); // 5
        classes.add(ProgramError.class); // 6
        classes.add(Reliability.class); // 7
        classes.add(EventState.class); // 8
        classes.add(DeviceStatus.class); // 9
        classes.add(EngineeringUnits.class); // 10
        classes.add(UnsignedInteger.class); // 11
        classes.add(LifeSafetyMode.class); // 12
        classes.add(LifeSafetyState.class); // 13
        classes.add(RestartReason.class); // 14
        classes.add(DoorAlarmState.class); // 15
        classes.add(Encodable.class); // 16
        classes.add(Encodable.class); // 17
        classes.add(Encodable.class); // 18
        classes.add(Encodable.class); // 19
        classes.add(Encodable.class); // 20
        classes.add(Encodable.class); // 21
        classes.add(Encodable.class); // 22
        classes.add(Encodable.class); // 23
        classes.add(Encodable.class); // 24
        classes.add(Encodable.class); // 25
        classes.add(Encodable.class); // 26
        classes.add(Encodable.class); // 27
        classes.add(Encodable.class); // 28
        classes.add(Encodable.class); // 29
        classes.add(Encodable.class); // 30
        classes.add(Encodable.class); // 31
        classes.add(Encodable.class); // 32
        classes.add(Encodable.class); // 33
        classes.add(Encodable.class); // 34
        classes.add(Encodable.class); // 35
        classes.add(BackupState.class); // 36
    }

    public interface Types {
        int BOOLEAN = 0;

        int BINARY_PV = 1;

        int EVENT_TYPE = 2;

        int POLARITY = 3;

        int PROGRAM_REQUEST = 4;

        int PROGRAM_STATE = 5;

        int PROGRAM_ERROR = 6;

        int RELIABILITY = 7;

        int EVENT_STATE = 8;

        int DEVICE_STATUS = 9;

        int ENGINEERING_UNITS = 10;

        int UNSIGNED = 11;

        int LIFE_SAFETY_MODE = 12;

        int LIFE_SAFETY_STATE = 13;

        int RESTART_REASON = 14;

        int DOOR_ALARM_STATE = 15;

        int BACKUP_STATE = 36;
    }

    private final Choice state;

    public PropertyStates(final int type, final BaseType state) {
        this.state = new Choice(type, state);
    }

    public int getType() {
        return state.getContextId();
    }

    public BaseType getState() {
        return (BaseType) state.getDatum();
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, state);
    }

    public PropertyStates(final ByteQueue queue) throws BACnetException {
        state = new Choice(queue, classes);
    }
}
