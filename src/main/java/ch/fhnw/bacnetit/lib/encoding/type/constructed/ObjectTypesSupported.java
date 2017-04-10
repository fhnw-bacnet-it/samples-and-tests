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

public class ObjectTypesSupported extends BitString {
    private static final long serialVersionUID = 4916909530588071979L;

    public ObjectTypesSupported() {
        super(new boolean[31]);
    }

    public ObjectTypesSupported(final ByteQueue queue) {
        super(queue);
    }

    public boolean isAnalogInput() {
        return getValue()[0];
    }

    public void setAnalogInput(final boolean analogInput) {
        getValue()[0] = analogInput;
    }

    public boolean isAnalogOutput() {
        return getValue()[1];
    }

    public void setAnalogOutput(final boolean analogOutput) {
        getValue()[1] = analogOutput;
    }

    public boolean isAnalogValue() {
        return getValue()[2];
    }

    public void setAnalogValue(final boolean analogValue) {
        getValue()[2] = analogValue;
    }

    public boolean isBinaryInput() {
        return getValue()[3];
    }

    public void setBinaryInput(final boolean binaryInput) {
        getValue()[3] = binaryInput;
    }

    public boolean isBinaryOutput() {
        return getValue()[4];
    }

    public void setBinaryOutput(final boolean binaryOutput) {
        getValue()[4] = binaryOutput;
    }

    public boolean isBinaryValue() {
        return getValue()[5];
    }

    public void setBinaryValue(final boolean binaryValue) {
        getValue()[5] = binaryValue;
    }

    public boolean isCalendar() {
        return getValue()[6];
    }

    public void setCalendar(final boolean calendar) {
        getValue()[6] = calendar;
    }

    public boolean isCommand() {
        return getValue()[7];
    }

    public void setCommand(final boolean command) {
        getValue()[7] = command;
    }

    public boolean isDevice() {
        return getValue()[8];
    }

    public void setDevice(final boolean device) {
        getValue()[8] = device;
    }

    public boolean isEventEnrollment() {
        return getValue()[9];
    }

    public void setEventEnrollment(final boolean eventEnrollment) {
        getValue()[9] = eventEnrollment;
    }

    public boolean isFile() {
        return getValue()[10];
    }

    public void setFile(final boolean file) {
        getValue()[10] = file;
    }

    public boolean isGroup() {
        return getValue()[11];
    }

    public void setGroup(final boolean group) {
        getValue()[11] = group;
    }

    public boolean isLoop() {
        return getValue()[12];
    }

    public void setLoop(final boolean loop) {
        getValue()[12] = loop;
    }

    public boolean isMultiStateInput() {
        return getValue()[13];
    }

    public void setMultiStateInput(final boolean multiStateInput) {
        getValue()[13] = multiStateInput;
    }

    public boolean isMultiStateOutput() {
        return getValue()[14];
    }

    public void setMultiStateOutput(final boolean multiStateOutput) {
        getValue()[14] = multiStateOutput;
    }

    public boolean isNotificationClass() {
        return getValue()[15];
    }

    public void setNotificationClass(final boolean notificationClass) {
        getValue()[15] = notificationClass;
    }

    public boolean isProgram() {
        return getValue()[16];
    }

    public void setProgram(final boolean program) {
        getValue()[16] = program;
    }

    public boolean isSchedule() {
        return getValue()[17];
    }

    public void setSchedule(final boolean schedule) {
        getValue()[17] = schedule;
    }

    public boolean isAveraging() {
        return getValue()[18];
    }

    public void setAveraging(final boolean averaging) {
        getValue()[18] = averaging;
    }

    public boolean isMultiStateValue() {
        return getValue()[19];
    }

    public void setMultiStateValue(final boolean multiStateValue) {
        getValue()[19] = multiStateValue;
    }

    public boolean isTrendLog() {
        return getValue()[20];
    }

    public void setTrendLog(final boolean trendLog) {
        getValue()[20] = trendLog;
    }

    public boolean isLifeSafetyPoint() {
        return getValue()[21];
    }

    public void setLifeSafetyPoint(final boolean lifeSafetyPoint) {
        getValue()[21] = lifeSafetyPoint;
    }

    public boolean isLifeSafetyZone() {
        return getValue()[22];
    }

    public void setLifeSafetyZone(final boolean lifeSafetyZone) {
        getValue()[22] = lifeSafetyZone;
    }

    public boolean isAccumulator() {
        return getValue()[23];
    }

    public void setAccumulator(final boolean accumulator) {
        getValue()[23] = accumulator;
    }

    public boolean isPulseConverter() {
        return getValue()[24];
    }

    public void setPulseConverter(final boolean pulseConverter) {
        getValue()[24] = pulseConverter;
    }

    public boolean isEventLog() {
        return getValue()[25];
    }

    public void setEventLog(final boolean eventLog) {
        getValue()[25] = eventLog;
    }

    public boolean isTrendLogMultiple() {
        return getValue()[27];
    }

    public void setTrendLogMultiple(final boolean trendLogMultiple) {
        getValue()[27] = trendLogMultiple;
    }

    public boolean isLoadControl() {
        return getValue()[28];
    }

    public void setLoadControl(final boolean loadControl) {
        getValue()[28] = loadControl;
    }

    public boolean isStructuredView() {
        return getValue()[29];
    }

    public void setStructuredView(final boolean structuredView) {
        getValue()[29] = structuredView;
    }

    public boolean isAccessDoor() {
        return getValue()[30];
    }

    public void setAccessDoor(final boolean accessDoor) {
        getValue()[30] = accessDoor;
    }
}
