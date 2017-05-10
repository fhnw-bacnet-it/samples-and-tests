/*******************************************************************************
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2017 University of Applied Sciences and Arts,
 * Northwestern Switzerland FHNW,
 * Institute of Mobile and Distributed Systems.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.orglicenses.
 *******************************************************************************/
package ch.fhnw.bacnetit.samplesandtests.api.deviceobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.AmbiguousValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.AccumulatorRecord;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ActionList;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.AddressBinding;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.CalendarEntry;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ClientCov;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.CovSubscription;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DailySchedule;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DateRange;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DateTime;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Destination;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DeviceObjectPropertyReference;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.DeviceObjectReference;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.EventLogRecord;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.EventTransitionBits;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.LimitEnable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.LogMultipleRecord;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.LogRecord;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ObjectPropertyReference;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ObjectTypesSupported;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Prescale;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PriorityArray;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ReadAccessResult;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ReadAccessSpecification;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Recipient;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Scale;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SessionKey;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SetpointReference;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.ShedLevel;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SpecialEvent;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.StatusFlags;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.TimeStamp;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.VtSession;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Action;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.BackupState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.BinaryPV;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.DeviceStatus;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.DoorAlarmState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.DoorSecuredStatus;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.DoorStatus;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.DoorValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EngineeringUnits;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.EventType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.FileAccessMethod;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LifeSafetyMode;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LifeSafetyOperation;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LifeSafetyState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LockStatus;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.LoggingType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Maintenance;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.NodeType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.NotifyType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Polarity;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ProgramError;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ProgramRequest;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ProgramState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Reliability;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.RestartReason;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.Segmentation;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ShedState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.SilencedState;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.VtClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.eventParameter.EventParameter;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Date;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Time;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Unsigned16;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;

public class BACnetObjectFactory {
    private static final Map<BACnetObjectType, List<BACnetProperty>> propertyTypes = new HashMap<BACnetObjectType, List<BACnetProperty>>();

    public static BACnetProperty getPropertyTypeDefinition(
            final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier) {
        final List<BACnetProperty> list = propertyTypes.get(objectType);
        if (list == null) {
            return null;
        }
        for (final BACnetProperty def : list) {
            if (def.getPropertyIdentifier().equals(propertyIdentifier)) {
                return def;
            }
        }
        return null;
    }

    public static BACnetProperty getPropertyTypeDefinitionRequired(
            final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier)
            throws BACnetServiceException {
        final BACnetProperty def = getPropertyTypeDefinition(objectType,
                propertyIdentifier);
        if (def == null) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.unknownProperty,
                    objectType + "/" + propertyIdentifier);
        }
        return def;
    }

    @SuppressWarnings("unchecked")
    public static void validateValue(final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final Encodable value) throws BACnetServiceException {
        final BACnetProperty def = getPropertyTypeDefinitionRequired(objectType,
                propertyIdentifier);
        if (def.isSequence()) {
            final SequenceOf<Encodable> seq = (SequenceOf<Encodable>) value;
            for (final Encodable e : seq) {
                if (!def.getClazz().isAssignableFrom(e.getClass())) {
                    throw new BACnetServiceException(ErrorClass.property,
                            ErrorCode.invalidDataType,
                            "expected " + def.getClazz() + ", received="
                                    + value.getClass());
                }
            }
        } else if (!def.getClazz().isAssignableFrom(value.getClass())) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.invalidDataType, "expected " + def.getClazz()
                            + ", received=" + value.getClass());
        }
    }

    public static void validateSequenceValue(final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final Encodable value) throws BACnetServiceException {
        final BACnetProperty def = getPropertyTypeDefinitionRequired(objectType,
                propertyIdentifier);
        if (!def.isSequence()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.propertyIsNotAnArray);
        }
        if (!def.getClazz().isAssignableFrom(value.getClass())) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.invalidDataType);
        }
    }

    public static List<BACnetProperty> getPropertyTypeDefinitions(
            final BACnetObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 0);
    }

    public static List<BACnetProperty> getRequiredPropertyTypeDefinitions(
            final BACnetObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 1);
    }

    public static List<BACnetProperty> getOptionalPropertyTypeDefinitions(
            final BACnetObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 2);
    }

    public static boolean isCommandable(final BACnetObjectType type,
            final BACnetPropertyIdentifier pid) {
        if (!pid.equals(BACnetPropertyIdentifier.presentValue)) {
            return false;
        }
        return type.equals(BACnetObjectType.analogOutput)
                || type.equals(BACnetObjectType.analogValue)
                || type.equals(BACnetObjectType.binaryOutput)
                || type.equals(BACnetObjectType.binaryValue)
                || type.equals(BACnetObjectType.multiStateOutput)
                || type.equals(BACnetObjectType.multiStateValue)
                || type.equals(BACnetObjectType.accessDoor);
    }

    /**
     * @param objectType
     * @param include
     *            0 = all, 1 = required, 2 = optional
     * @return
     */
    private static List<BACnetProperty> getPropertyTypeDefinitions(
            final BACnetObjectType objectType, final int include) {
        final List<BACnetProperty> result = new ArrayList<BACnetProperty>();
        final List<BACnetProperty> list = propertyTypes.get(objectType);
        if (list != null) {
            for (final BACnetProperty def : list) {
                if (include == 0 || (include == 1 && def.isRequired())
                        || (include == 2 && def.isOptional())) {
                    result.add(def);
                }
            }
        }
        return result;
    }

    private static void add(final BACnetObjectType type,
            final BACnetPropertyIdentifier pid,
            final Class<? extends Encodable> clazz, final boolean sequence,
            final boolean required, final Encodable defaultValue) {
        List<BACnetProperty> list = propertyTypes.get(type);
        if (list == null) {
            list = new ArrayList<BACnetProperty>();
            propertyTypes.put(type, list);
        }

        // Check for existing entries.
        for (final BACnetProperty def : list) {
            if (def.getPropertyIdentifier().equals(pid)) {
                list.remove(def);
                break;
            }
        }

        list.add(new BACnetProperty(type, pid, clazz, sequence, required,
                defaultValue));
    }

    public static void addPropertyTypeDefinition(final BACnetObjectType type,
            final BACnetPropertyIdentifier pid,
            final Class<? extends Encodable> clazz, final boolean sequence,
            final boolean required, final Encodable defaultValue) {
        final List<BACnetProperty> list = propertyTypes.get(type);
        if (list == null) {
            throw new RuntimeException("ObjectType not found: " + type);
        }

        // Check for existing entries.
        for (final BACnetProperty def : list) {
            if (def.getPropertyIdentifier().equals(pid)) {
                throw new RuntimeException(
                        "ObjectType already contains the given PropertyIdentifier");
            }
        }

        list.add(new BACnetProperty(type, pid, clazz, sequence, required,
                defaultValue));
    }

    static {
        // Access door
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.accessDoor,
                        0x3fffff));
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.accessDoor);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.presentValue,
                DoorValue.class, false, true, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, true, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.priorityArray,
                PriorityArray.class, false, true, new PriorityArray());
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.relinquishDefault, DoorValue.class,
                false, true, DoorValue.lock);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.doorStatus,
                DoorStatus.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.lockStatus,
                LockStatus.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.securedStatus,
                DoorSecuredStatus.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.doorMembers,
                DeviceObjectReference.class, true, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.doorPulseTime,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.doorExtendedPulseTime,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.doorUnlockDelayTime,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.doorOpenTooLongTime,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.doorAlarmState, DoorAlarmState.class,
                false, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.maskedAlarmValues,
                DoorAlarmState.class, true, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.maintenanceRequired, Maintenance.class,
                false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.alarmValues,
                DoorAlarmState.class, true, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.faultValues,
                DoorAlarmState.class, true, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.accessDoor,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.accessDoor, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Accumulator
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.accumulator,
                        0x3fffff));
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.accumulator);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.presentValue,
                UnsignedInteger.class, false, true, new UnsignedInteger(0));
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.deviceType,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.scale,
                Scale.class, false, true, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.prescale,
                Prescale.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.maxPresValue,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.valueChangeTime, DateTime.class, false,
                false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.valueBeforeChange,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.valueSet,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.loggingRecord, AccumulatorRecord.class,
                false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.loggingObject,
                BACnetObjectIdentifier.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.pulseRate,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.highLimit,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.lowLimit,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.limitMonitoringInterval,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.limitEnable,
                LimitEnable.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.accumulator,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.accumulator, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Analog input
        add(BACnetObjectType.analogInput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.analogInput,
                        0x3fffff));
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.analogInput);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.presentValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.deviceType,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.analogInput,
                BACnetPropertyIdentifier.updateInterval, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.minPresValue,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.maxPresValue,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.resolution,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.covIncrement,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogInput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.highLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.lowLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.deadband,
                Real.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.limitEnable,
                LimitEnable.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogInput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.analogInput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.analogInput, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Analog output
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.analogOutput,
                        0x3fffff));
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.analogOutput);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.presentValue, Real.class, false, true,
                new Real(0));
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.deviceType,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.minPresValue, Real.class, false, false,
                null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.maxPresValue, Real.class, false, false,
                null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.resolution,
                Real.class, false, false, null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, true, new PriorityArray());
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.relinquishDefault, Real.class, false,
                true, new Real(0));
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.covIncrement, Real.class, false, false,
                null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.highLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.lowLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.deadband,
                Real.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.limitEnable,
                LimitEnable.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.analogOutput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.analogOutput, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Analog value
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.analogValue,
                        0x3fffff));
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.analogValue);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.presentValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, false, new PriorityArray());
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.relinquishDefault, Real.class, false,
                false, new Real(0));
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.covIncrement,
                Real.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.highLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.lowLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.deadband,
                Real.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.limitEnable,
                LimitEnable.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.analogValue,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.analogValue, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Averaging
        add(BACnetObjectType.averaging,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.averaging,
                        0x3fffff));
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.averaging);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.minimumValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.averaging,
                BACnetPropertyIdentifier.minimumValueTimestamp, DateTime.class,
                false, false, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.averageValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.varianceValue,
                Real.class, false, false, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.maximumValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.averaging,
                BACnetPropertyIdentifier.maximumValueTimestamp, DateTime.class,
                false, false, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.averaging,
                BACnetPropertyIdentifier.attemptedSamples,
                UnsignedInteger.class, false, true, new UnsignedInteger(0));
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.validSamples,
                UnsignedInteger.class, false, true, new UnsignedInteger(0));
        add(BACnetObjectType.averaging,
                BACnetPropertyIdentifier.objectPropertyReference,
                DeviceObjectPropertyReference.class, false, true, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.windowInterval,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.windowSamples,
                UnsignedInteger.class, false, true, new UnsignedInteger(0));
        add(BACnetObjectType.averaging, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Binary input
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.binaryInput,
                        0x3fffff));
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.binaryInput);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.presentValue,
                BinaryPV.class, false, true, BinaryPV.inactive);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.deviceType,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.polarity,
                Polarity.class, false, true, Polarity.normal);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.inactiveText,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.activeText,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.changeOfStateTime, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.changeOfStateCount,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.timeOfStateCountReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.elapsedActiveTime,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.timeOfActiveTimeReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.alarmValue,
                BinaryPV.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.binaryInput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.binaryInput, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Binary output
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.binaryOutput,
                        0x3fffff));
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.binaryOutput);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.presentValue, BinaryPV.class, false,
                true, BinaryPV.inactive);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.deviceType,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.polarity,
                Polarity.class, false, true, Polarity.normal);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.inactiveText, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.activeText,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.changeOfStateTime, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.changeOfStateCount,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.timeOfStateCountReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.elapsedActiveTime,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.timeOfActiveTimeReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.minimumOffTime, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.minimumOnTime, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, true, new PriorityArray());
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.relinquishDefault, BinaryPV.class,
                false, true, BinaryPV.inactive);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.feedbackValue, BinaryPV.class, false,
                false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.binaryOutput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.binaryOutput, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Binary value
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.binaryValue,
                        0x3fffff));
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.binaryValue);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.presentValue,
                BinaryPV.class, false, true, BinaryPV.inactive);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.inactiveText,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.activeText,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.changeOfStateTime, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.changeOfStateCount,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.timeOfStateCountReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.elapsedActiveTime,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.timeOfActiveTimeReset, DateTime.class,
                false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.minimumOffTime, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.minimumOnTime, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, false, new PriorityArray());
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.relinquishDefault, BinaryPV.class,
                false, false, BinaryPV.inactive);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.alarmValue,
                BinaryPV.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.binaryValue,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.binaryValue, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Calendar
        add(BACnetObjectType.calendar,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.calendar,
                        0x3fffff));
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.calendar);
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.presentValue,
                Boolean.class, false, true, null);
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.dateList,
                CalendarEntry.class, true, true, null);
        add(BACnetObjectType.calendar, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Command
        add(BACnetObjectType.command, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.command, 0x3fffff));
        add(BACnetObjectType.command, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.command);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.presentValue,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.inProcess,
                Boolean.class, false, true, null);
        add(BACnetObjectType.command,
                BACnetPropertyIdentifier.allWritesSuccessful, Boolean.class,
                false, true, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.action,
                ActionList.class, true, true, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.actionText,
                CharacterString.class, true, false, null);
        add(BACnetObjectType.command, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Device
        add(BACnetObjectType.device, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.device, 0x3fffff));
        add(BACnetObjectType.device, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.device);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.systemStatus,
                DeviceStatus.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.vendorName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.vendorIdentifier,
                Unsigned16.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.modelName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.firmwareRevision,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.applicationSoftwareVersion,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.location,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.protocolVersion,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.protocolRevision,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.protocolServicesSupported,
                ServicesSupported.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.protocolObjectTypesSupported,
                ObjectTypesSupported.class, false, true, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.objectList,
                BACnetObjectIdentifier.class, true, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.structuredObjectList,
                BACnetObjectIdentifier.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.maxApduLengthAccepted,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.segmentationSupported,
                Segmentation.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.vtClassesSupported, VtClass.class,
                true, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.activeVtSessions,
                VtSession.class, true, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.localTime,
                Time.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.localDate,
                Date.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.utcOffset,
                SignedInteger.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.daylightSavingsStatus, Boolean.class,
                false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.apduSegmentTimeout,
                UnsignedInteger.class, false, true, new UnsignedInteger(1000));
        add(BACnetObjectType.device, BACnetPropertyIdentifier.apduTimeout,
                UnsignedInteger.class, false, true, new UnsignedInteger(5000));
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.numberOfApduRetries,
                UnsignedInteger.class, false, true, new UnsignedInteger(2));
        add(BACnetObjectType.device, BACnetPropertyIdentifier.listOfSessionKeys,
                SessionKey.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.timeSynchronizationRecipients,
                Recipient.class, true, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.maxMaster,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.maxInfoFrames,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.deviceAddressBinding,
                AddressBinding.class, true, true,
                new SequenceOf<AddressBinding>());
        add(BACnetObjectType.device, BACnetPropertyIdentifier.databaseRevision,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.configurationFiles,
                BACnetObjectIdentifier.class, true, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.lastRestoreTime,
                TimeStamp.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.backupFailureTimeout, Unsigned16.class,
                false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.backupPreparationTime,
                Unsigned16.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.restorePreparationTime,
                Unsigned16.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.restoreCompletionTime,
                Unsigned16.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.backupAndRestoreState,
                BackupState.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.activeCovSubscriptions,
                CovSubscription.class, true, true,
                new SequenceOf<CovSubscription>());
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.maxSegmentsAccepted,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.utcTimeSynchronizationRecipients,
                Recipient.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.timeSynchronizationInterval,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.alignIntervals,
                Boolean.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.intervalOffset,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.slaveProxyEnable,
                Boolean.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.autoSlaveDiscovery, Boolean.class,
                true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.slaveAddressBinding,
                AddressBinding.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.manualSlaveAddressBinding,
                AddressBinding.class, true, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.lastRestartReason,
                RestartReason.class, false, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.restartNotificationRecipients,
                Recipient.class, true, false, null);
        add(BACnetObjectType.device,
                BACnetPropertyIdentifier.timeOfDeviceRestart, TimeStamp.class,
                false, false, null);
        add(BACnetObjectType.device, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Event enrollment
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.eventEnrollment,
                        0x3fffff));
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.eventEnrollment);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.eventType, EventType.class, false,
                true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.eventParameters, EventParameter.class,
                false, true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.objectPropertyReference,
                DeviceObjectPropertyReference.class, false, true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                true, null);
        add(BACnetObjectType.eventEnrollment,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Event log
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.eventLog,
                        0x3fffff));
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.eventLog);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.enable,
                Boolean.class, false, true, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.startTime,
                DateTime.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.stopTime,
                DateTime.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.stopWhenFull,
                Boolean.class, false, true, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.bufferSize,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.logBuffer,
                EventLogRecord.class, true, true,
                new SequenceOf<EventLogRecord>());
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.recordCount,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.totalRecordCount,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.notificationThreshold,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.recordsSinceNotification,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.lastNotifyRecord,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.eventLog,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.eventTimeStamps,
                TimeStamp.class, true, false, null);
        add(BACnetObjectType.eventLog, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // File
        add(BACnetObjectType.file, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.file, 0x3fffff));
        add(BACnetObjectType.file, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.file);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.fileType,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.fileSize,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.modificationDate,
                DateTime.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.archive,
                Boolean.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.readOnly,
                Boolean.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.fileAccessMethod,
                FileAccessMethod.class, false, true, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.recordCount,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.file, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Group
        add(BACnetObjectType.group, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.group, 0x3fffff));
        add(BACnetObjectType.group, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.group, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.group);
        add(BACnetObjectType.group, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.group, BACnetPropertyIdentifier.listOfGroupMembers,
                ReadAccessSpecification.class, true, true, null);
        add(BACnetObjectType.group, BACnetPropertyIdentifier.presentValue,
                ReadAccessResult.class, true, true, null);
        add(BACnetObjectType.group, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Life safety point
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.lifeSafetyPoint,
                        0x3fffff));
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.lifeSafetyPoint);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.presentValue, LifeSafetyState.class,
                false, true, LifeSafetyState.quiet);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.trackingValue, LifeSafetyState.class,
                false, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.deviceType, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.lifeSafetyPoint, BACnetPropertyIdentifier.mode,
                LifeSafetyMode.class, false, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.acceptedModes, LifeSafetyMode.class,
                true, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.timeDelay, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.lifeSafetyAlarmValues,
                LifeSafetyState.class, true, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.alarmValues, LifeSafetyState.class,
                true, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.faultValues, LifeSafetyState.class,
                true, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.lifeSafetyPoint, BACnetPropertyIdentifier.silenced,
                SilencedState.class, false, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.operationExpected,
                LifeSafetyOperation.class, false, true, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.maintenanceRequired, Maintenance.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyPoint, BACnetPropertyIdentifier.setting,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.directReading, Real.class, false,
                false, null);
        add(BACnetObjectType.lifeSafetyPoint, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.lifeSafetyPoint, BACnetPropertyIdentifier.memberOf,
                DeviceObjectReference.class, true, false, null);
        add(BACnetObjectType.lifeSafetyPoint,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Life safety zone
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.lifeSafetyZone,
                        0x3fffff));
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.lifeSafetyZone);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.presentValue, LifeSafetyState.class,
                false, true, LifeSafetyState.quiet);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.trackingValue, LifeSafetyState.class,
                false, true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.deviceType, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.lifeSafetyZone, BACnetPropertyIdentifier.mode,
                LifeSafetyMode.class, false, true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.acceptedModes, LifeSafetyMode.class,
                true, true, null);
        add(BACnetObjectType.lifeSafetyZone, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.lifeSafetyAlarmValues,
                LifeSafetyState.class, true, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.alarmValues, LifeSafetyState.class,
                true, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.faultValues, LifeSafetyState.class,
                true, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.lifeSafetyZone, BACnetPropertyIdentifier.silenced,
                SilencedState.class, false, true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.operationExpected,
                LifeSafetyOperation.class, false, true, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.maintenanceRequired, Maintenance.class,
                false, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.zoneMembers,
                DeviceObjectReference.class, true, true, null);
        add(BACnetObjectType.lifeSafetyZone, BACnetPropertyIdentifier.memberOf,
                DeviceObjectReference.class, true, false, null);
        add(BACnetObjectType.lifeSafetyZone,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Load control
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.loadControl,
                        0x3fffff));
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true,
                BACnetObjectType.loadControl);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.presentValue,
                ShedState.class, false, true, ShedState.shedInactive);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.stateDescription,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, true, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.requestedShedLevel, ShedLevel.class,
                false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.startTime,
                DateTime.class, false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.shedDuration,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.dutyWindow,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.enable,
                Boolean.class, false, true, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.fullDutyBaseline, Real.class, false,
                false, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.expectedShedLevel, ShedLevel.class,
                false, true, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.actualShedLevel, ShedLevel.class,
                false, true, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.shedLevels,
                UnsignedInteger.class, true, true, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.shedLevelDescriptions,
                CharacterString.class, true, true, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.loadControl,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.loadControl, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Loop
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.loop, 0x3fffff));
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.loop);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.presentValue,
                Real.class, false, true, new Real(0));
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.updateInterval,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.outputUnits,
                EngineeringUnits.class, false, true, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.manipulatedVariableReference,
                ObjectPropertyReference.class, false, true, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.controlledVariableReference,
                ObjectPropertyReference.class, false, true, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.controlledVariableValue, Real.class,
                false, true, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.controlledVariableUnits,
                EngineeringUnits.class, false, true, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.setpointReference,
                SetpointReference.class, false, true, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.setpoint,
                Real.class, false, true, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.action,
                Action.class, false, true, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.proportionalConstant, Real.class,
                false, false, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.proportionalConstantUnits,
                EngineeringUnits.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.integralConstant,
                Real.class, false, false, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.integralConstantUnits,
                EngineeringUnits.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.derivativeConstant,
                Real.class, false, false, null);
        add(BACnetObjectType.loop,
                BACnetPropertyIdentifier.derivativeConstantUnits,
                EngineeringUnits.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.bias, Real.class,
                false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.maximumOutput,
                Real.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.minimumOutput,
                Real.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.priorityForWriting,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.covIncrement,
                Real.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.errorLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.deadband,
                Real.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.eventTimeStamps,
                TimeStamp.class, true, false, null);
        add(BACnetObjectType.loop, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Multi state input
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.multiStateInput,
                        0x3fffff));
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.multiStateInput);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.presentValue, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.deviceType, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.numberOfStates, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.stateText, CharacterString.class, true,
                false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.timeDelay, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.alarmValues, UnsignedInteger.class,
                true, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.faultValues, UnsignedInteger.class,
                true, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.multiStateInput,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Multi state output
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.multiStateOutput,
                        0x3fffff));
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.multiStateOutput);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.presentValue, UnsignedInteger.class,
                false, true, new UnsignedInteger(0));
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.deviceType, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.numberOfStates, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.stateText, CharacterString.class, true,
                false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, true, new PriorityArray());
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.relinquishDefault,
                UnsignedInteger.class, false, true, new UnsignedInteger(0));
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.timeDelay, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.feedbackValue, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.multiStateOutput,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Multi state value
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.multiStateValue,
                        0x3fffff));
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.multiStateValue);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.presentValue, UnsignedInteger.class,
                false, true, new UnsignedInteger(0));
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.numberOfStates, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.stateText, CharacterString.class, true,
                false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.priorityArray, PriorityArray.class,
                false, false, new PriorityArray());
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.relinquishDefault,
                UnsignedInteger.class, false, false, new UnsignedInteger(0));
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.timeDelay, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.alarmValues, UnsignedInteger.class,
                true, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.faultValues, UnsignedInteger.class,
                true, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.multiStateValue,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Notification class
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.notificationClass,
                        0x3fffff));
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.notificationClass);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.priority, UnsignedInteger.class, true,
                true, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.ackRequired, EventTransitionBits.class,
                false, true, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.recipientList, Destination.class, true,
                true, null);
        add(BACnetObjectType.notificationClass,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Program
        add(BACnetObjectType.program, BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.program, 0x3fffff));
        add(BACnetObjectType.program, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.program);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.programState,
                ProgramState.class, false, true, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.programChange,
                ProgramRequest.class, false, true, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.reasonForHalt,
                ProgramError.class, false, false, null);
        add(BACnetObjectType.program,
                BACnetPropertyIdentifier.descriptionOfHalt,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.programLocation,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.instanceOf,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.program, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);
        add(BACnetObjectType.program, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.program, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);

        // Pulse converter
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.pulseConverter,
                        0x3fffff));
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.pulseConverter);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.presentValue, Real.class, false, true,
                null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.inputReference,
                ObjectPropertyReference.class, false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.outOfService, Boolean.class, false,
                true, new Boolean(true));
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.units,
                EngineeringUnits.class, false, true, EngineeringUnits.noUnits);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.scaleFactor, Real.class, false, true,
                null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.adjustValue, Real.class, false, true,
                null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.count,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.updateTime, DateTime.class, false,
                true, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.countChangeTime, DateTime.class, false,
                true, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.countBeforeChange,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.covIncrement, Real.class, false, false,
                null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.covPeriod,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.timeDelay,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.highLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.lowLimit,
                Real.class, false, false, null);
        add(BACnetObjectType.pulseConverter, BACnetPropertyIdentifier.deadband,
                Real.class, false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.limitEnable, LimitEnable.class, false,
                false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.pulseConverter,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Schedule
        add(BACnetObjectType.schedule,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.schedule,
                        0x3fffff));
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.schedule);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.presentValue,
                AmbiguousValue.class, false, true, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.effectivePeriod,
                DateRange.class, false, true, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.weeklySchedule,
                DailySchedule.class, true, false, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.scheduleDefault,
                AmbiguousValue.class, false, true, null);
        add(BACnetObjectType.schedule,
                BACnetPropertyIdentifier.exceptionSchedule, SpecialEvent.class,
                true, false, null);
        add(BACnetObjectType.schedule,
                BACnetPropertyIdentifier.listOfObjectPropertyReferences,
                DeviceObjectPropertyReference.class, true, true, null);
        add(BACnetObjectType.schedule,
                BACnetPropertyIdentifier.priorityForWriting,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, true, null);
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.outOfService,
                Boolean.class, false, true, new Boolean(true));
        add(BACnetObjectType.schedule, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, true, null);

        // Structured View
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.structuredView,
                        0x3fffff));
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.structuredView);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.structuredView, BACnetPropertyIdentifier.nodeType,
                NodeType.class, false, false, null);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.nodeSubtype, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.subordinateList,
                DeviceObjectReference.class, true, true, null);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.subordinateAnnotations,
                CharacterString.class, true, false, null);
        add(BACnetObjectType.structuredView,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);

        // Trend log
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.trendLog,
                        0x3fffff));
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.objectName,
                CharacterString.class, false, true, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.objectType,
                BACnetObjectType.class, false, true, BACnetObjectType.trendLog);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.description,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.enable,
                Boolean.class, false, true, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.startTime,
                DateTime.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.stopTime,
                DateTime.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.logDeviceObjectProperty,
                DeviceObjectPropertyReference.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.logInterval,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.covResubscriptionInterval,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.clientCovIncrement, ClientCov.class,
                false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.stopWhenFull,
                Boolean.class, false, true, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.bufferSize,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.logBuffer,
                LogRecord.class, true, true, new SequenceOf<LogRecord>());
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.recordCount,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.totalRecordCount,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.notificationThreshold,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.recordsSinceNotification,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.lastNotifyRecord,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.eventState,
                EventState.class, false, true, EventState.normal);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.eventEnable,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.trendLog,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.notifyType,
                NotifyType.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.eventTimeStamps,
                TimeStamp.class, true, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.profileName,
                CharacterString.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.loggingType,
                LoggingType.class, false, true, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.alignIntervals,
                Boolean.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.intervalOffset,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.trigger,
                Boolean.class, false, false, null);
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.statusFlags,
                StatusFlags.class, false, true,
                new StatusFlags(false, false, false, true));
        add(BACnetObjectType.trendLog, BACnetPropertyIdentifier.reliability,
                Reliability.class, false, false, null);

        // Trend log multiple
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.objectIdentifier,
                BACnetObjectIdentifier.class, false, true,
                new BACnetObjectIdentifier(BACnetObjectType.trendLogMultiple,
                        0x3fffff));
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.objectName, CharacterString.class,
                false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.objectType, BACnetObjectType.class,
                false, true, BACnetObjectType.trendLogMultiple);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.description, CharacterString.class,
                false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.statusFlags, StatusFlags.class, false,
                true, new StatusFlags(false, false, false, true));
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.eventState, EventState.class, false,
                true, EventState.normal);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.reliability, Reliability.class, false,
                false, null);
        add(BACnetObjectType.trendLogMultiple, BACnetPropertyIdentifier.enable,
                Boolean.class, false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.startTime, DateTime.class, false,
                false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.stopTime, DateTime.class, false, false,
                null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.logDeviceObjectProperty,
                DeviceObjectPropertyReference.class, true, true,
                new SequenceOf<DeviceObjectPropertyReference>());
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.loggingType, LoggingType.class, false,
                true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.logInterval, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.alignIntervals, Boolean.class, false,
                false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.intervalOffset, UnsignedInteger.class,
                false, false, null);
        add(BACnetObjectType.trendLogMultiple, BACnetPropertyIdentifier.trigger,
                Boolean.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.stopWhenFull, Boolean.class, false,
                true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.bufferSize, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.logBuffer, LogMultipleRecord.class,
                true, true, new SequenceOf<LogMultipleRecord>());
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.recordCount, UnsignedInteger.class,
                false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.totalRecordCount,
                UnsignedInteger.class, false, true, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.notificationThreshold,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.recordsSinceNotification,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.lastNotifyRecord,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.notificationClass,
                UnsignedInteger.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.eventEnable, EventTransitionBits.class,
                false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.ackedTransitions,
                EventTransitionBits.class, false, false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.notifyType, NotifyType.class, false,
                false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.eventTimeStamps, TimeStamp.class, true,
                false, null);
        add(BACnetObjectType.trendLogMultiple,
                BACnetPropertyIdentifier.profileName, CharacterString.class,
                false, false, null);
    }
}
