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

public class BACnetPropertyIdentifier extends Enumerated {
    private static final long serialVersionUID = 7289026444729646422L;

    public static final BACnetPropertyIdentifier ackedTransitions = new BACnetPropertyIdentifier(
            0);

    public static final BACnetPropertyIdentifier ackRequired = new BACnetPropertyIdentifier(
            1);

    public static final BACnetPropertyIdentifier action = new BACnetPropertyIdentifier(
            2);

    public static final BACnetPropertyIdentifier actionText = new BACnetPropertyIdentifier(
            3);

    public static final BACnetPropertyIdentifier activeText = new BACnetPropertyIdentifier(
            4);

    public static final BACnetPropertyIdentifier activeVtSessions = new BACnetPropertyIdentifier(
            5);

    public static final BACnetPropertyIdentifier alarmValue = new BACnetPropertyIdentifier(
            6);

    public static final BACnetPropertyIdentifier alarmValues = new BACnetPropertyIdentifier(
            7);

    public static final BACnetPropertyIdentifier all = new BACnetPropertyIdentifier(
            8);

    public static final BACnetPropertyIdentifier allWritesSuccessful = new BACnetPropertyIdentifier(
            9);

    public static final BACnetPropertyIdentifier apduSegmentTimeout = new BACnetPropertyIdentifier(
            10);

    public static final BACnetPropertyIdentifier apduTimeout = new BACnetPropertyIdentifier(
            11);

    public static final BACnetPropertyIdentifier applicationSoftwareVersion = new BACnetPropertyIdentifier(
            12);

    public static final BACnetPropertyIdentifier archive = new BACnetPropertyIdentifier(
            13);

    public static final BACnetPropertyIdentifier bias = new BACnetPropertyIdentifier(
            14);

    public static final BACnetPropertyIdentifier changeOfStateCount = new BACnetPropertyIdentifier(
            15);

    public static final BACnetPropertyIdentifier changeOfStateTime = new BACnetPropertyIdentifier(
            16);

    public static final BACnetPropertyIdentifier notificationClass = new BACnetPropertyIdentifier(
            17);

    public static final BACnetPropertyIdentifier controlledVariableReference = new BACnetPropertyIdentifier(
            19);

    public static final BACnetPropertyIdentifier controlledVariableUnits = new BACnetPropertyIdentifier(
            20);

    public static final BACnetPropertyIdentifier controlledVariableValue = new BACnetPropertyIdentifier(
            21);

    public static final BACnetPropertyIdentifier covIncrement = new BACnetPropertyIdentifier(
            22);

    public static final BACnetPropertyIdentifier dateList = new BACnetPropertyIdentifier(
            23);

    public static final BACnetPropertyIdentifier daylightSavingsStatus = new BACnetPropertyIdentifier(
            24);

    public static final BACnetPropertyIdentifier deadband = new BACnetPropertyIdentifier(
            25);

    public static final BACnetPropertyIdentifier derivativeConstant = new BACnetPropertyIdentifier(
            26);

    public static final BACnetPropertyIdentifier derivativeConstantUnits = new BACnetPropertyIdentifier(
            27);

    public static final BACnetPropertyIdentifier description = new BACnetPropertyIdentifier(
            28);

    public static final BACnetPropertyIdentifier descriptionOfHalt = new BACnetPropertyIdentifier(
            29);

    public static final BACnetPropertyIdentifier deviceAddressBinding = new BACnetPropertyIdentifier(
            30);

    public static final BACnetPropertyIdentifier deviceType = new BACnetPropertyIdentifier(
            31);

    public static final BACnetPropertyIdentifier effectivePeriod = new BACnetPropertyIdentifier(
            32);

    public static final BACnetPropertyIdentifier elapsedActiveTime = new BACnetPropertyIdentifier(
            33);

    public static final BACnetPropertyIdentifier errorLimit = new BACnetPropertyIdentifier(
            34);

    public static final BACnetPropertyIdentifier eventEnable = new BACnetPropertyIdentifier(
            35);

    public static final BACnetPropertyIdentifier eventState = new BACnetPropertyIdentifier(
            36);

    public static final BACnetPropertyIdentifier eventType = new BACnetPropertyIdentifier(
            37);

    public static final BACnetPropertyIdentifier exceptionSchedule = new BACnetPropertyIdentifier(
            38);

    public static final BACnetPropertyIdentifier faultValues = new BACnetPropertyIdentifier(
            39);

    public static final BACnetPropertyIdentifier feedbackValue = new BACnetPropertyIdentifier(
            40);

    public static final BACnetPropertyIdentifier fileAccessMethod = new BACnetPropertyIdentifier(
            41);

    public static final BACnetPropertyIdentifier fileSize = new BACnetPropertyIdentifier(
            42);

    public static final BACnetPropertyIdentifier fileType = new BACnetPropertyIdentifier(
            43);

    public static final BACnetPropertyIdentifier firmwareRevision = new BACnetPropertyIdentifier(
            44);

    public static final BACnetPropertyIdentifier highLimit = new BACnetPropertyIdentifier(
            45);

    public static final BACnetPropertyIdentifier inactiveText = new BACnetPropertyIdentifier(
            46);

    public static final BACnetPropertyIdentifier inProcess = new BACnetPropertyIdentifier(
            47);

    public static final BACnetPropertyIdentifier instanceOf = new BACnetPropertyIdentifier(
            48);

    public static final BACnetPropertyIdentifier integralConstant = new BACnetPropertyIdentifier(
            49);

    public static final BACnetPropertyIdentifier integralConstantUnits = new BACnetPropertyIdentifier(
            50);

    public static final BACnetPropertyIdentifier limitEnable = new BACnetPropertyIdentifier(
            52);

    public static final BACnetPropertyIdentifier listOfGroupMembers = new BACnetPropertyIdentifier(
            53);

    public static final BACnetPropertyIdentifier listOfObjectPropertyReferences = new BACnetPropertyIdentifier(
            54);

    public static final BACnetPropertyIdentifier listOfSessionKeys = new BACnetPropertyIdentifier(
            55);

    public static final BACnetPropertyIdentifier localDate = new BACnetPropertyIdentifier(
            56);

    public static final BACnetPropertyIdentifier localTime = new BACnetPropertyIdentifier(
            57);

    public static final BACnetPropertyIdentifier location = new BACnetPropertyIdentifier(
            58);

    public static final BACnetPropertyIdentifier lowLimit = new BACnetPropertyIdentifier(
            59);

    public static final BACnetPropertyIdentifier manipulatedVariableReference = new BACnetPropertyIdentifier(
            60);

    public static final BACnetPropertyIdentifier maximumOutput = new BACnetPropertyIdentifier(
            61);

    public static final BACnetPropertyIdentifier maxApduLengthAccepted = new BACnetPropertyIdentifier(
            62);

    public static final BACnetPropertyIdentifier maxInfoFrames = new BACnetPropertyIdentifier(
            63);

    public static final BACnetPropertyIdentifier maxMaster = new BACnetPropertyIdentifier(
            64);

    public static final BACnetPropertyIdentifier maxPresValue = new BACnetPropertyIdentifier(
            65);

    public static final BACnetPropertyIdentifier minimumOffTime = new BACnetPropertyIdentifier(
            66);

    public static final BACnetPropertyIdentifier minimumOnTime = new BACnetPropertyIdentifier(
            67);

    public static final BACnetPropertyIdentifier minimumOutput = new BACnetPropertyIdentifier(
            68);

    public static final BACnetPropertyIdentifier minPresValue = new BACnetPropertyIdentifier(
            69);

    public static final BACnetPropertyIdentifier modelName = new BACnetPropertyIdentifier(
            70);

    public static final BACnetPropertyIdentifier modificationDate = new BACnetPropertyIdentifier(
            71);

    public static final BACnetPropertyIdentifier notifyType = new BACnetPropertyIdentifier(
            72);

    public static final BACnetPropertyIdentifier numberOfApduRetries = new BACnetPropertyIdentifier(
            73);

    public static final BACnetPropertyIdentifier numberOfStates = new BACnetPropertyIdentifier(
            74);

    public static final BACnetPropertyIdentifier objectIdentifier = new BACnetPropertyIdentifier(
            75);

    public static final BACnetPropertyIdentifier objectList = new BACnetPropertyIdentifier(
            76);

    public static final BACnetPropertyIdentifier objectName = new BACnetPropertyIdentifier(
            77);

    public static final BACnetPropertyIdentifier objectPropertyReference = new BACnetPropertyIdentifier(
            78);

    public static final BACnetPropertyIdentifier objectType = new BACnetPropertyIdentifier(
            79);

    public static final BACnetPropertyIdentifier optional = new BACnetPropertyIdentifier(
            80);

    public static final BACnetPropertyIdentifier outOfService = new BACnetPropertyIdentifier(
            81);

    public static final BACnetPropertyIdentifier outputUnits = new BACnetPropertyIdentifier(
            82);

    public static final BACnetPropertyIdentifier eventParameters = new BACnetPropertyIdentifier(
            83);

    public static final BACnetPropertyIdentifier polarity = new BACnetPropertyIdentifier(
            84);

    public static final BACnetPropertyIdentifier presentValue = new BACnetPropertyIdentifier(
            85);

    public static final BACnetPropertyIdentifier priority = new BACnetPropertyIdentifier(
            86);

    public static final BACnetPropertyIdentifier priorityArray = new BACnetPropertyIdentifier(
            87);

    public static final BACnetPropertyIdentifier priorityForWriting = new BACnetPropertyIdentifier(
            88);

    public static final BACnetPropertyIdentifier processIdentifier = new BACnetPropertyIdentifier(
            89);

    public static final BACnetPropertyIdentifier programChange = new BACnetPropertyIdentifier(
            90);

    public static final BACnetPropertyIdentifier programLocation = new BACnetPropertyIdentifier(
            91);

    public static final BACnetPropertyIdentifier programState = new BACnetPropertyIdentifier(
            92);

    public static final BACnetPropertyIdentifier proportionalConstant = new BACnetPropertyIdentifier(
            93);

    public static final BACnetPropertyIdentifier proportionalConstantUnits = new BACnetPropertyIdentifier(
            94);

    public static final BACnetPropertyIdentifier protocolObjectTypesSupported = new BACnetPropertyIdentifier(
            96);

    public static final BACnetPropertyIdentifier protocolServicesSupported = new BACnetPropertyIdentifier(
            97);

    public static final BACnetPropertyIdentifier protocolVersion = new BACnetPropertyIdentifier(
            98);

    public static final BACnetPropertyIdentifier readOnly = new BACnetPropertyIdentifier(
            99);

    public static final BACnetPropertyIdentifier reasonForHalt = new BACnetPropertyIdentifier(
            100);

    public static final BACnetPropertyIdentifier recipientList = new BACnetPropertyIdentifier(
            102);

    public static final BACnetPropertyIdentifier reliability = new BACnetPropertyIdentifier(
            103);

    public static final BACnetPropertyIdentifier relinquishDefault = new BACnetPropertyIdentifier(
            104);

    public static final BACnetPropertyIdentifier required = new BACnetPropertyIdentifier(
            105);

    public static final BACnetPropertyIdentifier resolution = new BACnetPropertyIdentifier(
            106);

    public static final BACnetPropertyIdentifier segmentationSupported = new BACnetPropertyIdentifier(
            107);

    public static final BACnetPropertyIdentifier setpoint = new BACnetPropertyIdentifier(
            108);

    public static final BACnetPropertyIdentifier setpointReference = new BACnetPropertyIdentifier(
            109);

    public static final BACnetPropertyIdentifier stateText = new BACnetPropertyIdentifier(
            110);

    public static final BACnetPropertyIdentifier statusFlags = new BACnetPropertyIdentifier(
            111);

    public static final BACnetPropertyIdentifier systemStatus = new BACnetPropertyIdentifier(
            112);

    public static final BACnetPropertyIdentifier timeDelay = new BACnetPropertyIdentifier(
            113);

    public static final BACnetPropertyIdentifier timeOfActiveTimeReset = new BACnetPropertyIdentifier(
            114);

    public static final BACnetPropertyIdentifier timeOfStateCountReset = new BACnetPropertyIdentifier(
            115);

    public static final BACnetPropertyIdentifier timeSynchronizationRecipients = new BACnetPropertyIdentifier(
            116);

    public static final BACnetPropertyIdentifier units = new BACnetPropertyIdentifier(
            117);

    public static final BACnetPropertyIdentifier updateInterval = new BACnetPropertyIdentifier(
            118);

    public static final BACnetPropertyIdentifier utcOffset = new BACnetPropertyIdentifier(
            119);

    public static final BACnetPropertyIdentifier vendorIdentifier = new BACnetPropertyIdentifier(
            120);

    public static final BACnetPropertyIdentifier vendorName = new BACnetPropertyIdentifier(
            121);

    public static final BACnetPropertyIdentifier vtClassesSupported = new BACnetPropertyIdentifier(
            122);

    public static final BACnetPropertyIdentifier weeklySchedule = new BACnetPropertyIdentifier(
            123);

    public static final BACnetPropertyIdentifier attemptedSamples = new BACnetPropertyIdentifier(
            124);

    public static final BACnetPropertyIdentifier averageValue = new BACnetPropertyIdentifier(
            125);

    public static final BACnetPropertyIdentifier bufferSize = new BACnetPropertyIdentifier(
            126);

    public static final BACnetPropertyIdentifier clientCovIncrement = new BACnetPropertyIdentifier(
            127);

    public static final BACnetPropertyIdentifier covResubscriptionInterval = new BACnetPropertyIdentifier(
            128);

    public static final BACnetPropertyIdentifier eventTimeStamps = new BACnetPropertyIdentifier(
            130);

    public static final BACnetPropertyIdentifier logBuffer = new BACnetPropertyIdentifier(
            131);

    public static final BACnetPropertyIdentifier logDeviceObjectProperty = new BACnetPropertyIdentifier(
            132);

    public static final BACnetPropertyIdentifier enable = new BACnetPropertyIdentifier(
            133);

    public static final BACnetPropertyIdentifier logInterval = new BACnetPropertyIdentifier(
            134);

    public static final BACnetPropertyIdentifier maximumValue = new BACnetPropertyIdentifier(
            135);

    public static final BACnetPropertyIdentifier minimumValue = new BACnetPropertyIdentifier(
            136);

    public static final BACnetPropertyIdentifier notificationThreshold = new BACnetPropertyIdentifier(
            137);

    public static final BACnetPropertyIdentifier protocolRevision = new BACnetPropertyIdentifier(
            139);

    public static final BACnetPropertyIdentifier recordsSinceNotification = new BACnetPropertyIdentifier(
            140);

    public static final BACnetPropertyIdentifier recordCount = new BACnetPropertyIdentifier(
            141);

    public static final BACnetPropertyIdentifier startTime = new BACnetPropertyIdentifier(
            142);

    public static final BACnetPropertyIdentifier stopTime = new BACnetPropertyIdentifier(
            143);

    public static final BACnetPropertyIdentifier stopWhenFull = new BACnetPropertyIdentifier(
            144);

    public static final BACnetPropertyIdentifier totalRecordCount = new BACnetPropertyIdentifier(
            145);

    public static final BACnetPropertyIdentifier validSamples = new BACnetPropertyIdentifier(
            146);

    public static final BACnetPropertyIdentifier windowInterval = new BACnetPropertyIdentifier(
            147);

    public static final BACnetPropertyIdentifier windowSamples = new BACnetPropertyIdentifier(
            148);

    public static final BACnetPropertyIdentifier maximumValueTimestamp = new BACnetPropertyIdentifier(
            149);

    public static final BACnetPropertyIdentifier minimumValueTimestamp = new BACnetPropertyIdentifier(
            150);

    public static final BACnetPropertyIdentifier varianceValue = new BACnetPropertyIdentifier(
            151);

    public static final BACnetPropertyIdentifier activeCovSubscriptions = new BACnetPropertyIdentifier(
            152);

    public static final BACnetPropertyIdentifier backupFailureTimeout = new BACnetPropertyIdentifier(
            153);

    public static final BACnetPropertyIdentifier configurationFiles = new BACnetPropertyIdentifier(
            154);

    public static final BACnetPropertyIdentifier databaseRevision = new BACnetPropertyIdentifier(
            155);

    public static final BACnetPropertyIdentifier directReading = new BACnetPropertyIdentifier(
            156);

    public static final BACnetPropertyIdentifier lastRestoreTime = new BACnetPropertyIdentifier(
            157);

    public static final BACnetPropertyIdentifier maintenanceRequired = new BACnetPropertyIdentifier(
            158);

    public static final BACnetPropertyIdentifier memberOf = new BACnetPropertyIdentifier(
            159);

    public static final BACnetPropertyIdentifier mode = new BACnetPropertyIdentifier(
            160);

    public static final BACnetPropertyIdentifier operationExpected = new BACnetPropertyIdentifier(
            161);

    public static final BACnetPropertyIdentifier setting = new BACnetPropertyIdentifier(
            162);

    public static final BACnetPropertyIdentifier silenced = new BACnetPropertyIdentifier(
            163);

    public static final BACnetPropertyIdentifier trackingValue = new BACnetPropertyIdentifier(
            164);

    public static final BACnetPropertyIdentifier zoneMembers = new BACnetPropertyIdentifier(
            165);

    public static final BACnetPropertyIdentifier lifeSafetyAlarmValues = new BACnetPropertyIdentifier(
            166);

    public static final BACnetPropertyIdentifier maxSegmentsAccepted = new BACnetPropertyIdentifier(
            167);

    public static final BACnetPropertyIdentifier profileName = new BACnetPropertyIdentifier(
            168);

    public static final BACnetPropertyIdentifier autoSlaveDiscovery = new BACnetPropertyIdentifier(
            169);

    public static final BACnetPropertyIdentifier manualSlaveAddressBinding = new BACnetPropertyIdentifier(
            170);

    public static final BACnetPropertyIdentifier slaveAddressBinding = new BACnetPropertyIdentifier(
            171);

    public static final BACnetPropertyIdentifier slaveProxyEnable = new BACnetPropertyIdentifier(
            172);

    public static final BACnetPropertyIdentifier lastNotifyRecord = new BACnetPropertyIdentifier(
            173);

    public static final BACnetPropertyIdentifier scheduleDefault = new BACnetPropertyIdentifier(
            174);

    public static final BACnetPropertyIdentifier acceptedModes = new BACnetPropertyIdentifier(
            175);

    public static final BACnetPropertyIdentifier adjustValue = new BACnetPropertyIdentifier(
            176);

    public static final BACnetPropertyIdentifier count = new BACnetPropertyIdentifier(
            177);

    public static final BACnetPropertyIdentifier countBeforeChange = new BACnetPropertyIdentifier(
            178);

    public static final BACnetPropertyIdentifier countChangeTime = new BACnetPropertyIdentifier(
            179);

    public static final BACnetPropertyIdentifier covPeriod = new BACnetPropertyIdentifier(
            180);

    public static final BACnetPropertyIdentifier inputReference = new BACnetPropertyIdentifier(
            181);

    public static final BACnetPropertyIdentifier limitMonitoringInterval = new BACnetPropertyIdentifier(
            182);

    public static final BACnetPropertyIdentifier loggingObject = new BACnetPropertyIdentifier(
            183);

    public static final BACnetPropertyIdentifier loggingRecord = new BACnetPropertyIdentifier(
            184);

    public static final BACnetPropertyIdentifier prescale = new BACnetPropertyIdentifier(
            185);

    public static final BACnetPropertyIdentifier pulseRate = new BACnetPropertyIdentifier(
            186);

    public static final BACnetPropertyIdentifier scale = new BACnetPropertyIdentifier(
            187);

    public static final BACnetPropertyIdentifier scaleFactor = new BACnetPropertyIdentifier(
            188);

    public static final BACnetPropertyIdentifier updateTime = new BACnetPropertyIdentifier(
            189);

    public static final BACnetPropertyIdentifier valueBeforeChange = new BACnetPropertyIdentifier(
            190);

    public static final BACnetPropertyIdentifier valueSet = new BACnetPropertyIdentifier(
            191);

    public static final BACnetPropertyIdentifier valueChangeTime = new BACnetPropertyIdentifier(
            192);

    public static final BACnetPropertyIdentifier alignIntervals = new BACnetPropertyIdentifier(
            193);

    public static final BACnetPropertyIdentifier intervalOffset = new BACnetPropertyIdentifier(
            195);

    public static final BACnetPropertyIdentifier lastRestartReason = new BACnetPropertyIdentifier(
            196);

    public static final BACnetPropertyIdentifier loggingType = new BACnetPropertyIdentifier(
            197);

    public static final BACnetPropertyIdentifier restartNotificationRecipients = new BACnetPropertyIdentifier(
            202);

    public static final BACnetPropertyIdentifier timeOfDeviceRestart = new BACnetPropertyIdentifier(
            203);

    public static final BACnetPropertyIdentifier timeSynchronizationInterval = new BACnetPropertyIdentifier(
            204);

    public static final BACnetPropertyIdentifier trigger = new BACnetPropertyIdentifier(
            205);

    public static final BACnetPropertyIdentifier utcTimeSynchronizationRecipients = new BACnetPropertyIdentifier(
            206);

    public static final BACnetPropertyIdentifier nodeSubtype = new BACnetPropertyIdentifier(
            207);

    public static final BACnetPropertyIdentifier nodeType = new BACnetPropertyIdentifier(
            208);

    public static final BACnetPropertyIdentifier structuredObjectList = new BACnetPropertyIdentifier(
            209);

    public static final BACnetPropertyIdentifier subordinateAnnotations = new BACnetPropertyIdentifier(
            210);

    public static final BACnetPropertyIdentifier subordinateList = new BACnetPropertyIdentifier(
            211);

    public static final BACnetPropertyIdentifier actualShedLevel = new BACnetPropertyIdentifier(
            212);

    public static final BACnetPropertyIdentifier dutyWindow = new BACnetPropertyIdentifier(
            213);

    public static final BACnetPropertyIdentifier expectedShedLevel = new BACnetPropertyIdentifier(
            214);

    public static final BACnetPropertyIdentifier fullDutyBaseline = new BACnetPropertyIdentifier(
            215);

    public static final BACnetPropertyIdentifier requestedShedLevel = new BACnetPropertyIdentifier(
            218);

    public static final BACnetPropertyIdentifier shedDuration = new BACnetPropertyIdentifier(
            219);

    public static final BACnetPropertyIdentifier shedLevelDescriptions = new BACnetPropertyIdentifier(
            220);

    public static final BACnetPropertyIdentifier shedLevels = new BACnetPropertyIdentifier(
            221);

    public static final BACnetPropertyIdentifier stateDescription = new BACnetPropertyIdentifier(
            222);

    public static final BACnetPropertyIdentifier doorAlarmState = new BACnetPropertyIdentifier(
            226);

    public static final BACnetPropertyIdentifier doorExtendedPulseTime = new BACnetPropertyIdentifier(
            227);

    public static final BACnetPropertyIdentifier doorMembers = new BACnetPropertyIdentifier(
            228);

    public static final BACnetPropertyIdentifier doorOpenTooLongTime = new BACnetPropertyIdentifier(
            229);

    public static final BACnetPropertyIdentifier doorPulseTime = new BACnetPropertyIdentifier(
            230);

    public static final BACnetPropertyIdentifier doorStatus = new BACnetPropertyIdentifier(
            231);

    public static final BACnetPropertyIdentifier doorUnlockDelayTime = new BACnetPropertyIdentifier(
            232);

    public static final BACnetPropertyIdentifier lockStatus = new BACnetPropertyIdentifier(
            233);

    public static final BACnetPropertyIdentifier maskedAlarmValues = new BACnetPropertyIdentifier(
            234);

    public static final BACnetPropertyIdentifier securedStatus = new BACnetPropertyIdentifier(
            235);

    public static final BACnetPropertyIdentifier backupAndRestoreState = new BACnetPropertyIdentifier(
            338);

    public static final BACnetPropertyIdentifier backupPreparationTime = new BACnetPropertyIdentifier(
            339);

    public static final BACnetPropertyIdentifier restoreCompletionTime = new BACnetPropertyIdentifier(
            340);

    public static final BACnetPropertyIdentifier restorePreparationTime = new BACnetPropertyIdentifier(
            341);

    public static final BACnetPropertyIdentifier[] ALL = { ackedTransitions,
            ackRequired, action, actionText, activeText, activeVtSessions,
            alarmValue, alarmValues, all, allWritesSuccessful,
            apduSegmentTimeout, apduTimeout, applicationSoftwareVersion,
            archive, bias, changeOfStateCount, changeOfStateTime,
            notificationClass, controlledVariableReference,
            controlledVariableUnits, controlledVariableValue, covIncrement,
            dateList, daylightSavingsStatus, deadband, derivativeConstant,
            derivativeConstantUnits, description, descriptionOfHalt,
            deviceAddressBinding, deviceType, effectivePeriod,
            elapsedActiveTime, errorLimit, eventEnable, eventState, eventType,
            exceptionSchedule, faultValues, feedbackValue, fileAccessMethod,
            fileSize, fileType, firmwareRevision, highLimit, inactiveText,
            inProcess, instanceOf, integralConstant, integralConstantUnits,
            limitEnable, listOfGroupMembers, listOfObjectPropertyReferences,
            listOfSessionKeys, localDate, localTime, location, lowLimit,
            manipulatedVariableReference, maximumOutput, maxApduLengthAccepted,
            maxInfoFrames, maxMaster, maxPresValue, minimumOffTime,
            minimumOnTime, minimumOutput, minPresValue, modelName,
            modificationDate, notifyType, numberOfApduRetries, numberOfStates,
            objectIdentifier, objectList, objectName, objectPropertyReference,
            objectType, optional, outOfService, outputUnits, eventParameters,
            polarity, presentValue, priority, priorityArray, priorityForWriting,
            processIdentifier, programChange, programLocation, programState,
            proportionalConstant, proportionalConstantUnits,
            protocolObjectTypesSupported, protocolServicesSupported,
            protocolVersion, readOnly, reasonForHalt, recipientList,
            reliability, relinquishDefault, required, resolution,
            segmentationSupported, setpoint, setpointReference, stateText,
            statusFlags, systemStatus, timeDelay, timeOfActiveTimeReset,
            timeOfStateCountReset, timeSynchronizationRecipients, units,
            updateInterval, utcOffset, vendorIdentifier, vendorName,
            vtClassesSupported, weeklySchedule, attemptedSamples, averageValue,
            bufferSize, clientCovIncrement, covResubscriptionInterval,
            eventTimeStamps, logBuffer, logDeviceObjectProperty, enable,
            logInterval, maximumValue, minimumValue, notificationThreshold,
            protocolRevision, recordsSinceNotification, recordCount, startTime,
            stopTime, stopWhenFull, totalRecordCount, validSamples,
            windowInterval, windowSamples, maximumValueTimestamp,
            minimumValueTimestamp, varianceValue, activeCovSubscriptions,
            backupFailureTimeout, configurationFiles, databaseRevision,
            directReading, lastRestoreTime, maintenanceRequired, memberOf, mode,
            operationExpected, setting, silenced, trackingValue, zoneMembers,
            lifeSafetyAlarmValues, maxSegmentsAccepted, profileName,
            autoSlaveDiscovery, manualSlaveAddressBinding, slaveAddressBinding,
            slaveProxyEnable, lastNotifyRecord, scheduleDefault, acceptedModes,
            adjustValue, count, countBeforeChange, countChangeTime, covPeriod,
            inputReference, limitMonitoringInterval, loggingObject,
            loggingRecord, prescale, pulseRate, scale, scaleFactor, updateTime,
            valueBeforeChange, valueSet, valueChangeTime, alignIntervals,
            intervalOffset, lastRestartReason, loggingType,
            restartNotificationRecipients, timeOfDeviceRestart,
            timeSynchronizationInterval, trigger,
            utcTimeSynchronizationRecipients, nodeSubtype, nodeType,
            structuredObjectList, subordinateAnnotations, subordinateList,
            actualShedLevel, dutyWindow, expectedShedLevel, fullDutyBaseline,
            requestedShedLevel, shedDuration, shedLevelDescriptions, shedLevels,
            stateDescription, doorAlarmState, doorExtendedPulseTime,
            doorMembers, doorOpenTooLongTime, doorPulseTime, doorStatus,
            doorUnlockDelayTime, lockStatus, maskedAlarmValues, securedStatus,
            backupAndRestoreState, backupPreparationTime, restoreCompletionTime,
            restorePreparationTime, };

    public BACnetPropertyIdentifier(final int value) {
        super(value);
    }

    public BACnetPropertyIdentifier(final ByteQueue queue) {
        super(queue);
    }

    @Override
    public String toString() {
        final int type = intValue();
        if (type == ackedTransitions.intValue()) {
            return "Acked transitions";
        }
        if (type == ackRequired.intValue()) {
            return "Ack required";
        }
        if (type == action.intValue()) {
            return "Action";
        }
        if (type == actionText.intValue()) {
            return "Action text";
        }
        if (type == activeText.intValue()) {
            return "Active text";
        }
        if (type == activeVtSessions.intValue()) {
            return "Active VT sessions";
        }
        if (type == alarmValue.intValue()) {
            return "Alarm value";
        }
        if (type == alarmValues.intValue()) {
            return "Alarm values";
        }
        if (type == all.intValue()) {
            return "All";
        }
        if (type == allWritesSuccessful.intValue()) {
            return "All writes successful";
        }
        if (type == apduSegmentTimeout.intValue()) {
            return "APDU segment timeout";
        }
        if (type == apduTimeout.intValue()) {
            return "APDU timeout";
        }
        if (type == applicationSoftwareVersion.intValue()) {
            return "Application software version";
        }
        if (type == archive.intValue()) {
            return "Archive";
        }
        if (type == bias.intValue()) {
            return "Bias";
        }
        if (type == changeOfStateCount.intValue()) {
            return "Change of state count";
        }
        if (type == changeOfStateTime.intValue()) {
            return "Change of state time";
        }
        if (type == notificationClass.intValue()) {
            return "Notification class";
        }
        if (type == controlledVariableReference.intValue()) {
            return "Controlled variable reference";
        }
        if (type == controlledVariableUnits.intValue()) {
            return "Controlled variable units";
        }
        if (type == controlledVariableValue.intValue()) {
            return "Controlled variable value";
        }
        if (type == covIncrement.intValue()) {
            return "COV increment";
        }
        if (type == dateList.intValue()) {
            return "Date list";
        }
        if (type == daylightSavingsStatus.intValue()) {
            return "Daylight savings status";
        }
        if (type == deadband.intValue()) {
            return "Deadband";
        }
        if (type == derivativeConstant.intValue()) {
            return "Derivative constant";
        }
        if (type == derivativeConstantUnits.intValue()) {
            return "Derivative constant units";
        }
        if (type == description.intValue()) {
            return "Description";
        }
        if (type == descriptionOfHalt.intValue()) {
            return "Description of halt";
        }
        if (type == deviceAddressBinding.intValue()) {
            return "Device address binding";
        }
        if (type == deviceType.intValue()) {
            return "Device type";
        }
        if (type == effectivePeriod.intValue()) {
            return "Effective period";
        }
        if (type == elapsedActiveTime.intValue()) {
            return "Elapsed active time";
        }
        if (type == errorLimit.intValue()) {
            return "Error limit";
        }
        if (type == eventEnable.intValue()) {
            return "Event enable";
        }
        if (type == eventState.intValue()) {
            return "Event state";
        }
        if (type == eventType.intValue()) {
            return "Event type";
        }
        if (type == exceptionSchedule.intValue()) {
            return "Exception schedule";
        }
        if (type == faultValues.intValue()) {
            return "Fault values";
        }
        if (type == feedbackValue.intValue()) {
            return "Feedback value";
        }
        if (type == fileAccessMethod.intValue()) {
            return "File access method";
        }
        if (type == fileSize.intValue()) {
            return "File size";
        }
        if (type == fileType.intValue()) {
            return "File type";
        }
        if (type == firmwareRevision.intValue()) {
            return "Firmware revision";
        }
        if (type == highLimit.intValue()) {
            return "High limit";
        }
        if (type == inactiveText.intValue()) {
            return "Inactive text";
        }
        if (type == inProcess.intValue()) {
            return "In process";
        }
        if (type == instanceOf.intValue()) {
            return "Instance of";
        }
        if (type == integralConstant.intValue()) {
            return "Integral constant";
        }
        if (type == integralConstantUnits.intValue()) {
            return "Integral constant units";
        }
        if (type == limitEnable.intValue()) {
            return "Limit enable";
        }
        if (type == listOfGroupMembers.intValue()) {
            return "List of group members";
        }
        if (type == listOfObjectPropertyReferences.intValue()) {
            return "List of object property references";
        }
        if (type == listOfSessionKeys.intValue()) {
            return "List of session keys";
        }
        if (type == localDate.intValue()) {
            return "Local date";
        }
        if (type == localTime.intValue()) {
            return "Local time";
        }
        if (type == location.intValue()) {
            return "Location";
        }
        if (type == lowLimit.intValue()) {
            return "Low limit";
        }
        if (type == manipulatedVariableReference.intValue()) {
            return "Manipulated variable reference";
        }
        if (type == maximumOutput.intValue()) {
            return "Maximum output";
        }
        if (type == maxApduLengthAccepted.intValue()) {
            return "Max APDU length accepted";
        }
        if (type == maxInfoFrames.intValue()) {
            return "Max info frames";
        }
        if (type == maxMaster.intValue()) {
            return "Max master";
        }
        if (type == maxPresValue.intValue()) {
            return "Max pres value";
        }
        if (type == minimumOffTime.intValue()) {
            return "Minimum off time";
        }
        if (type == minimumOnTime.intValue()) {
            return "Minimum on time";
        }
        if (type == minimumOutput.intValue()) {
            return "Minimum output";
        }
        if (type == minPresValue.intValue()) {
            return "Min pres value";
        }
        if (type == modelName.intValue()) {
            return "Model name";
        }
        if (type == modificationDate.intValue()) {
            return "Modification date";
        }
        if (type == notifyType.intValue()) {
            return "Notify type";
        }
        if (type == numberOfApduRetries.intValue()) {
            return "Number of APDU retries";
        }
        if (type == numberOfStates.intValue()) {
            return "Number of states";
        }
        if (type == objectIdentifier.intValue()) {
            return "Object identifier";
        }
        if (type == objectList.intValue()) {
            return "Object list";
        }
        if (type == objectName.intValue()) {
            return "Object name";
        }
        if (type == objectPropertyReference.intValue()) {
            return "Object property reference";
        }
        if (type == objectType.intValue()) {
            return "Object type";
        }
        if (type == optional.intValue()) {
            return "Optional";
        }
        if (type == outOfService.intValue()) {
            return "Out of service";
        }
        if (type == outputUnits.intValue()) {
            return "Output units";
        }
        if (type == eventParameters.intValue()) {
            return "Event parameters";
        }
        if (type == polarity.intValue()) {
            return "Polarity";
        }
        if (type == presentValue.intValue()) {
            return "Present value";
        }
        if (type == priority.intValue()) {
            return "Priority";
        }
        if (type == priorityArray.intValue()) {
            return "Priority array";
        }
        if (type == priorityForWriting.intValue()) {
            return "Priority for writing";
        }
        if (type == processIdentifier.intValue()) {
            return "Process identifier";
        }
        if (type == programChange.intValue()) {
            return "Program change";
        }
        if (type == programLocation.intValue()) {
            return "Program location";
        }
        if (type == programState.intValue()) {
            return "Program state";
        }
        if (type == proportionalConstant.intValue()) {
            return "Proportional constant";
        }
        if (type == proportionalConstantUnits.intValue()) {
            return "Proportional constant units";
        }
        if (type == protocolObjectTypesSupported.intValue()) {
            return "Protocol object types supported";
        }
        if (type == protocolServicesSupported.intValue()) {
            return "Protocol services supported";
        }
        if (type == protocolVersion.intValue()) {
            return "Protocol version";
        }
        if (type == readOnly.intValue()) {
            return "Read only";
        }
        if (type == reasonForHalt.intValue()) {
            return "Reason for halt";
        }
        if (type == recipientList.intValue()) {
            return "Recipient list";
        }
        if (type == reliability.intValue()) {
            return "Reliability";
        }
        if (type == relinquishDefault.intValue()) {
            return "Relinquish default";
        }
        if (type == required.intValue()) {
            return "Required";
        }
        if (type == resolution.intValue()) {
            return "Resolution";
        }
        if (type == segmentationSupported.intValue()) {
            return "Segmentation supported";
        }
        if (type == setpoint.intValue()) {
            return "Setpoint";
        }
        if (type == setpointReference.intValue()) {
            return "Setpoint reference";
        }
        if (type == stateText.intValue()) {
            return "State text";
        }
        if (type == statusFlags.intValue()) {
            return "Status flags";
        }
        if (type == systemStatus.intValue()) {
            return "System status";
        }
        if (type == timeDelay.intValue()) {
            return "Time delay";
        }
        if (type == timeOfActiveTimeReset.intValue()) {
            return "Time of active time reset";
        }
        if (type == timeOfStateCountReset.intValue()) {
            return "Time of state count reset";
        }
        if (type == timeSynchronizationRecipients.intValue()) {
            return "Time synchronization recipients";
        }
        if (type == units.intValue()) {
            return "Units";
        }
        if (type == updateInterval.intValue()) {
            return "Update interval";
        }
        if (type == utcOffset.intValue()) {
            return "UTC offset";
        }
        if (type == vendorIdentifier.intValue()) {
            return "Vendor identifier";
        }
        if (type == vendorName.intValue()) {
            return "Vendor name";
        }
        if (type == vtClassesSupported.intValue()) {
            return "VT classes supported";
        }
        if (type == weeklySchedule.intValue()) {
            return "Weekly schedule";
        }
        if (type == attemptedSamples.intValue()) {
            return "Attempted samples";
        }
        if (type == averageValue.intValue()) {
            return "Average value";
        }
        if (type == bufferSize.intValue()) {
            return "Buffer size";
        }
        if (type == clientCovIncrement.intValue()) {
            return "Client COV increment";
        }
        if (type == covResubscriptionInterval.intValue()) {
            return "COV resubscription interval";
        }
        if (type == eventTimeStamps.intValue()) {
            return "Event time stamps";
        }
        if (type == logBuffer.intValue()) {
            return "Log buffer";
        }
        if (type == logDeviceObjectProperty.intValue()) {
            return "Log device object property";
        }
        if (type == enable.intValue()) {
            return "enable";
        }
        if (type == logInterval.intValue()) {
            return "Log interval";
        }
        if (type == maximumValue.intValue()) {
            return "Maximum value";
        }
        if (type == minimumValue.intValue()) {
            return "Minimum value";
        }
        if (type == notificationThreshold.intValue()) {
            return "Notification threshold";
        }
        if (type == protocolRevision.intValue()) {
            return "Protocol revision";
        }
        if (type == recordsSinceNotification.intValue()) {
            return "Records since notification";
        }
        if (type == recordCount.intValue()) {
            return "Record count";
        }
        if (type == startTime.intValue()) {
            return "Start time";
        }
        if (type == stopTime.intValue()) {
            return "Stop time";
        }
        if (type == stopWhenFull.intValue()) {
            return "Stop when full";
        }
        if (type == totalRecordCount.intValue()) {
            return "Total record count";
        }
        if (type == validSamples.intValue()) {
            return "Valid samples";
        }
        if (type == windowInterval.intValue()) {
            return "Window interval";
        }
        if (type == windowSamples.intValue()) {
            return "Window samples";
        }
        if (type == maximumValueTimestamp.intValue()) {
            return "Maximum value timestamp";
        }
        if (type == minimumValueTimestamp.intValue()) {
            return "Minimum value timestamp";
        }
        if (type == varianceValue.intValue()) {
            return "Variance value";
        }
        if (type == activeCovSubscriptions.intValue()) {
            return "Active COV subscriptions";
        }
        if (type == backupFailureTimeout.intValue()) {
            return "Backup failure timeout";
        }
        if (type == configurationFiles.intValue()) {
            return "Configuration files";
        }
        if (type == databaseRevision.intValue()) {
            return "Database revision";
        }
        if (type == directReading.intValue()) {
            return "Direct reading";
        }
        if (type == lastRestoreTime.intValue()) {
            return "Last restore time";
        }
        if (type == maintenanceRequired.intValue()) {
            return "Maintenance required";
        }
        if (type == memberOf.intValue()) {
            return "Member of";
        }
        if (type == mode.intValue()) {
            return "Mode";
        }
        if (type == operationExpected.intValue()) {
            return "Operation expected";
        }
        if (type == setting.intValue()) {
            return "Setting";
        }
        if (type == silenced.intValue()) {
            return "Silenced";
        }
        if (type == trackingValue.intValue()) {
            return "Tracking value";
        }
        if (type == zoneMembers.intValue()) {
            return "Zone members";
        }
        if (type == lifeSafetyAlarmValues.intValue()) {
            return "Life safety alarm values";
        }
        if (type == maxSegmentsAccepted.intValue()) {
            return "Max segments accepted";
        }
        if (type == profileName.intValue()) {
            return "Profile name";
        }
        if (type == autoSlaveDiscovery.intValue()) {
            return "Auto slave discovery";
        }
        if (type == manualSlaveAddressBinding.intValue()) {
            return "Manual slave address binding";
        }
        if (type == slaveAddressBinding.intValue()) {
            return "Slave address binding";
        }
        if (type == slaveProxyEnable.intValue()) {
            return "Slave proxy enable";
        }
        if (type == lastNotifyRecord.intValue()) {
            return "Last notify record";
        }
        if (type == scheduleDefault.intValue()) {
            return "Schedule default";
        }
        if (type == acceptedModes.intValue()) {
            return "Accepted modes";
        }
        if (type == adjustValue.intValue()) {
            return "Adjust value";
        }
        if (type == count.intValue()) {
            return "Count";
        }
        if (type == countBeforeChange.intValue()) {
            return "Count before change";
        }
        if (type == countChangeTime.intValue()) {
            return "Count change time";
        }
        if (type == covPeriod.intValue()) {
            return "COV period";
        }
        if (type == inputReference.intValue()) {
            return "Input reference";
        }
        if (type == limitMonitoringInterval.intValue()) {
            return "Limit monitoring interval";
        }
        if (type == loggingObject.intValue()) {
            return "Logging object";
        }
        if (type == loggingRecord.intValue()) {
            return "Logging record";
        }
        if (type == prescale.intValue()) {
            return "Prescale";
        }
        if (type == pulseRate.intValue()) {
            return "Pulse rate";
        }
        if (type == scale.intValue()) {
            return "Scale";
        }
        if (type == scaleFactor.intValue()) {
            return "Scale factor";
        }
        if (type == updateTime.intValue()) {
            return "Update time";
        }
        if (type == valueBeforeChange.intValue()) {
            return "Value before change";
        }
        if (type == valueSet.intValue()) {
            return "Value set";
        }
        if (type == valueChangeTime.intValue()) {
            return "Value change time";
        }
        if (type == alignIntervals.intValue()) {
            return "Align Intervals";
        }
        if (type == intervalOffset.intValue()) {
            return "Interval Offset";
        }
        if (type == lastRestartReason.intValue()) {
            return "Last Restart Reason";
        }
        if (type == loggingType.intValue()) {
            return "Logging Type";
        }
        if (type == restartNotificationRecipients.intValue()) {
            return "Restart Notification Recipients";
        }
        if (type == timeOfDeviceRestart.intValue()) {
            return "Time Of Device Restart";
        }
        if (type == timeSynchronizationInterval.intValue()) {
            return "Time Synchronization Interval";
        }
        if (type == trigger.intValue()) {
            return "Trigger";
        }
        if (type == utcTimeSynchronizationRecipients.intValue()) {
            return "UTC Time Synchronization Recipients";
        }
        if (type == nodeSubtype.intValue()) {
            return "Node Subtype";
        }
        if (type == nodeType.intValue()) {
            return "Node Type";
        }
        if (type == structuredObjectList.intValue()) {
            return "Structured Object List";
        }
        if (type == subordinateAnnotations.intValue()) {
            return "Subordinate Annotations";
        }
        if (type == subordinateList.intValue()) {
            return "Subordinate List";
        }
        if (type == actualShedLevel.intValue()) {
            return "Actual Shed Level";
        }
        if (type == dutyWindow.intValue()) {
            return "Duty Window";
        }
        if (type == expectedShedLevel.intValue()) {
            return "Expected Shed Level";
        }
        if (type == fullDutyBaseline.intValue()) {
            return "Full Duty Baseline";
        }
        if (type == requestedShedLevel.intValue()) {
            return "Requested Shed Level";
        }
        if (type == shedDuration.intValue()) {
            return "Shed Duration";
        }
        if (type == shedLevelDescriptions.intValue()) {
            return "Shed Level Descriptions";
        }
        if (type == shedLevels.intValue()) {
            return "Shed Levels";
        }
        if (type == stateDescription.intValue()) {
            return "State Description";
        }
        if (type == doorAlarmState.intValue()) {
            return "Door Alarm State";
        }
        if (type == doorExtendedPulseTime.intValue()) {
            return "Door Extended Pulse Time";
        }
        if (type == doorMembers.intValue()) {
            return "Door Members";
        }
        if (type == doorOpenTooLongTime.intValue()) {
            return "Door Open Too Long Time";
        }
        if (type == doorPulseTime.intValue()) {
            return "Door Pulse Time";
        }
        if (type == doorStatus.intValue()) {
            return "Door Status";
        }
        if (type == doorUnlockDelayTime.intValue()) {
            return "Door Unlock Delay Time";
        }
        if (type == lockStatus.intValue()) {
            return "Lock Status";
        }
        if (type == maskedAlarmValues.intValue()) {
            return "Masked Alarm Values";
        }
        if (type == securedStatus.intValue()) {
            return "Secured Status";
        }
        if (type == backupAndRestoreState.intValue()) {
            return "Backup And Restore State";
        }
        if (type == backupPreparationTime.intValue()) {
            return "Backup Preparation Time";
        }
        if (type == restoreCompletionTime.intValue()) {
            return "Restore Completion Time";
        }
        if (type == restorePreparationTime.intValue()) {
            return "Restore Preparation Time";
        }
        return "Unknown: " + type;
    }
}
