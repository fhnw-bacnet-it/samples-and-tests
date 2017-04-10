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
package ch.fhnw.bacnetit.lib.deviceobjects;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BACnetObjectType extends Enumerated {
    private static final long serialVersionUID = 5428575132396799009L;

    public static final BACnetObjectType analogInput = new BACnetObjectType(0);

    public static final BACnetObjectType analogOutput = new BACnetObjectType(1);

    public static final BACnetObjectType analogValue = new BACnetObjectType(2);

    public static final BACnetObjectType binaryInput = new BACnetObjectType(3);

    public static final BACnetObjectType binaryOutput = new BACnetObjectType(4);

    public static final BACnetObjectType binaryValue = new BACnetObjectType(5);

    public static final BACnetObjectType calendar = new BACnetObjectType(6);

    public static final BACnetObjectType command = new BACnetObjectType(7);

    public static final BACnetObjectType device = new BACnetObjectType(8);

    public static final BACnetObjectType eventEnrollment = new BACnetObjectType(
            9);

    public static final BACnetObjectType file = new BACnetObjectType(10);

    public static final BACnetObjectType group = new BACnetObjectType(11);

    public static final BACnetObjectType loop = new BACnetObjectType(12);

    public static final BACnetObjectType multiStateInput = new BACnetObjectType(
            13);

    public static final BACnetObjectType multiStateOutput = new BACnetObjectType(
            14);

    public static final BACnetObjectType notificationClass = new BACnetObjectType(
            15);

    public static final BACnetObjectType program = new BACnetObjectType(16);

    public static final BACnetObjectType schedule = new BACnetObjectType(17);

    public static final BACnetObjectType averaging = new BACnetObjectType(18);

    public static final BACnetObjectType multiStateValue = new BACnetObjectType(
            19);

    public static final BACnetObjectType trendLog = new BACnetObjectType(20);

    public static final BACnetObjectType lifeSafetyPoint = new BACnetObjectType(
            21);

    public static final BACnetObjectType lifeSafetyZone = new BACnetObjectType(
            22);

    public static final BACnetObjectType accumulator = new BACnetObjectType(23);

    public static final BACnetObjectType pulseConverter = new BACnetObjectType(
            24);

    public static final BACnetObjectType eventLog = new BACnetObjectType(25);

    public static final BACnetObjectType trendLogMultiple = new BACnetObjectType(
            27);

    public static final BACnetObjectType loadControl = new BACnetObjectType(28);

    public static final BACnetObjectType structuredView = new BACnetObjectType(
            29);

    public static final BACnetObjectType accessDoor = new BACnetObjectType(30);

    public static final BACnetObjectType siemensWallLight = new BACnetObjectType(
            260);

    public static final BACnetObjectType[] ALL = { analogInput, analogOutput,
            analogValue, binaryInput, binaryOutput, binaryValue, calendar,
            command, device, eventEnrollment, file, group, loop,
            multiStateInput, multiStateOutput, notificationClass, program,
            schedule, averaging, multiStateValue, trendLog, lifeSafetyPoint,
            lifeSafetyZone, accumulator, pulseConverter, eventLog,
            trendLogMultiple, loadControl, structuredView, accessDoor,
            siemensWallLight };

    public BACnetObjectType(final int value) {
        super(value);
    }

    public BACnetObjectType(final ByteQueue queue) {
        super(queue);
    }

    @Override
    public String toString() {
        final int type = intValue();
        if (type == analogInput.intValue()) {
            return "Analog Input";
        }
        if (type == analogOutput.intValue()) {
            return "Analog Output";
        }
        if (type == analogValue.intValue()) {
            return "Analog Value";
        }
        if (type == binaryInput.intValue()) {
            return "Binary Input";
        }
        if (type == binaryOutput.intValue()) {
            return "Binary Output";
        }
        if (type == binaryValue.intValue()) {
            return "Binary Value";
        }
        if (type == calendar.intValue()) {
            return "Calendar";
        }
        if (type == command.intValue()) {
            return "Command";
        }
        if (type == device.intValue()) {
            return "Device";
        }
        if (type == eventEnrollment.intValue()) {
            return "Event Enrollment";
        }
        if (type == file.intValue()) {
            return "File";
        }
        if (type == group.intValue()) {
            return "Group";
        }
        if (type == loop.intValue()) {
            return "Loop";
        }
        if (type == multiStateInput.intValue()) {
            return "Multi-state Input";
        }
        if (type == multiStateOutput.intValue()) {
            return "Multi-state Output";
        }
        if (type == notificationClass.intValue()) {
            return "Notification Class";
        }
        if (type == program.intValue()) {
            return "Program";
        }
        if (type == schedule.intValue()) {
            return "Schedule";
        }
        if (type == averaging.intValue()) {
            return "Averaging";
        }
        if (type == multiStateValue.intValue()) {
            return "Multi-state Value";
        }
        if (type == trendLog.intValue()) {
            return "Trend Log";
        }
        if (type == lifeSafetyPoint.intValue()) {
            return "Life Safety Point";
        }
        if (type == lifeSafetyZone.intValue()) {
            return "Life Safety Zone";
        }
        if (type == accumulator.intValue()) {
            return "Accumulator";
        }
        if (type == pulseConverter.intValue()) {
            return "Pulse Converter";
        }
        if (type == eventLog.intValue()) {
            return "Event Log";
        }
        if (type == trendLogMultiple.intValue()) {
            return "Trend Log Multiple";
        }
        if (type == loadControl.intValue()) {
            return "Load Control";
        }
        if (type == structuredView.intValue()) {
            return "Structured View";
        }
        if (type == accessDoor.intValue()) {
            return "Access Door";
        }
        return "Vendor Specific (" + type + ")";
    }
}
