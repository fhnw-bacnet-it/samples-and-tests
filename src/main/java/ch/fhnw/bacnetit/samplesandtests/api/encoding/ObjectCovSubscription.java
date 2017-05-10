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
package ch.fhnw.bacnetit.samplesandtests.api.encoding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObject;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectFactory;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.samplesandtests.api.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Address;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;

public class ObjectCovSubscription implements Serializable {
    private static final long serialVersionUID = 3546250271813406695L;

    private static Set<BACnetObjectType> supportedObjectTypes = new HashSet<BACnetObjectType>();

    private static Set<BACnetPropertyIdentifier> supportedPropertyIdentifiers = new HashSet<BACnetPropertyIdentifier>();

    /**
     * These types require a COV threshold, before any subscriptions are allowed
     */
    private static Set<BACnetObjectType> covThresholdRequired = new HashSet<BACnetObjectType>();

    static {
        supportedObjectTypes.add(BACnetObjectType.accessDoor);
        supportedObjectTypes.add(BACnetObjectType.accumulator);
        supportedObjectTypes.add(BACnetObjectType.analogInput);
        supportedObjectTypes.add(BACnetObjectType.analogOutput);
        supportedObjectTypes.add(BACnetObjectType.analogValue);
        supportedObjectTypes.add(BACnetObjectType.binaryInput);
        supportedObjectTypes.add(BACnetObjectType.binaryOutput);
        supportedObjectTypes.add(BACnetObjectType.binaryValue);
        supportedObjectTypes.add(BACnetObjectType.lifeSafetyPoint);
        supportedObjectTypes.add(BACnetObjectType.loop);
        supportedObjectTypes.add(BACnetObjectType.multiStateInput);
        supportedObjectTypes.add(BACnetObjectType.multiStateOutput);
        supportedObjectTypes.add(BACnetObjectType.multiStateValue);
        supportedObjectTypes.add(BACnetObjectType.pulseConverter);

        supportedPropertyIdentifiers.add(BACnetPropertyIdentifier.presentValue);
        supportedPropertyIdentifiers.add(BACnetPropertyIdentifier.statusFlags);
        supportedPropertyIdentifiers
                .add(BACnetPropertyIdentifier.doorAlarmState);

        covThresholdRequired.add(BACnetObjectType.analogInput);
        covThresholdRequired.add(BACnetObjectType.analogOutput);
        covThresholdRequired.add(BACnetObjectType.analogValue);
        covThresholdRequired.add(BACnetObjectType.loop);
        covThresholdRequired.add(BACnetObjectType.pulseConverter);
    }

    public static void addSupportedObjectType(
            final BACnetObjectType objectType) {
        supportedObjectTypes.add(objectType);
    }

    public static void addSupportedPropertyIdentifier(
            final BACnetPropertyIdentifier propertyIdentifier) {
        supportedPropertyIdentifiers.add(propertyIdentifier);
    }

    public static boolean supportedObjectType(
            final BACnetObjectType objectType) {
        return supportedObjectTypes.contains(objectType);
    }

    public static boolean sendCovNotification(final BACnetObjectType objectType,
            final BACnetPropertyIdentifier pid, final Real covThresholdValue) {
        if (!supportedObjectType(objectType)) {
            return false;
        }

        if (pid != null && !supportedPropertyIdentifiers.contains(pid)) {
            return false;
        }

        // Don't allow COV notifications when there is no threshold for Objects
        // that require thresholds.
        if (covThresholdRequired.contains(objectType)
                && covThresholdValue == null) {
            return false;
        }

        return true;
    }

    public static List<PropertyValue> getValues(final BACnetObject obj) {
        final List<PropertyValue> values = new ArrayList<PropertyValue>();
        for (final BACnetPropertyIdentifier pid : supportedPropertyIdentifiers) {
            addValue(obj, values, pid);
        }
        return values;
    }

    private static void addValue(final BACnetObject obj,
            final List<PropertyValue> values,
            final BACnetPropertyIdentifier pid) {
        try {
            // Ensure that the obj has the given property. The addition of
            // doorAlarmState requires this.
            if (BACnetObjectFactory.getPropertyTypeDefinition(
                    obj.getId().getObjectType(), pid) != null) {
                final Encodable value = obj.getProperty(pid);
                if (value != null) {
                    values.add(new PropertyValue(pid, value));
                }
            }
        } catch (final BACnetServiceException e) {
            // Should never happen, so wrap in a RuntimeException
            throw new RuntimeException(e);
        }
    }

    private final Address address;

    private final OctetString linkService;

    private final UnsignedInteger subscriberProcessIdentifier;

    private boolean issueConfirmedNotifications;

    private long expiryTime;

    /**
     * The increment/threshold at which COV notifications should be sent out.
     * Only applies to property identifiers that are {@link Real}'s and
     * {@link BACnetObjectType}'s mentioned in
     * {@link ObjectCovSubscription#covThresholdRequired}.
     */
    private final Real covIncrement;

    /**
     * Contains the last sent values per property identifier. It is used to
     * determine if a COV notification should be sent.
     */
    private final Map<BACnetPropertyIdentifier, Encodable> lastSentValues = new HashMap<BACnetPropertyIdentifier, Encodable>();

    public ObjectCovSubscription(final Address address,
            final OctetString linkService,
            final UnsignedInteger subscriberProcessIdentifier,
            final Real covIncrement) {
        this.address = address;
        this.linkService = linkService;
        this.subscriberProcessIdentifier = subscriberProcessIdentifier;
        this.covIncrement = covIncrement;
    }

    public Address getAddress() {
        return address;
    }

    public OctetString getLinkService() {
        return linkService;
    }

    public boolean isIssueConfirmedNotifications() {
        return issueConfirmedNotifications;
    }

    public UnsignedInteger getSubscriberProcessIdentifier() {
        return subscriberProcessIdentifier;
    }

    public void setIssueConfirmedNotifications(
            final boolean issueConfirmedNotifications) {
        this.issueConfirmedNotifications = issueConfirmedNotifications;
    }

    public void setExpiryTime(final int seconds) {
        if (seconds == 0) {
            expiryTime = -1;
        } else {
            expiryTime = System.currentTimeMillis() + seconds * 1000;
        }
    }

    public boolean hasExpired(final long now) {
        if (expiryTime == -1) {
            return false;
        }
        return expiryTime < now;
    }

    public int getTimeRemaining(final long now) {
        if (expiryTime == -1) {
            return 0;
        }
        final int left = (int) ((expiryTime - now) / 1000);
        if (left < 1) {
            return 1;
        }
        return left;
    }

    /**
     * Determine if a notification needs to be sent out based on the Threshold
     * if relevant.
     *
     * @param pid
     *            The {@link BACnetPropertyIdentifier} being updated
     * @param value
     *            The new value
     * @return true if a COV notification should be sent out, false otherwise.
     */
    public boolean isNotificationRequired(final BACnetPropertyIdentifier pid,
            final Encodable value) {
        final Encodable lastSentValue = this.lastSentValues.get(pid);

        final boolean notificationRequired = ThresholdCalculator
                .isValueOutsideOfThreshold(this.covIncrement, lastSentValue,
                        value);

        if (notificationRequired) {
            this.lastSentValues.put(pid, value);
        }

        return notificationRequired;
    }

    /**
     * Utility Class to determine whether COV thresholds/increments have been
     * surpassed.
     *
     * @author japearson
     *
     */
    public static class ThresholdCalculator {
        /**
         * Convert the given encodable value to a {@link Float} if possible.
         *
         * @param value
         *            The value to attempt to convert to a {@link Float}.
         * @return A {@link Float} value if the {@link Encodable} can be
         *         converted, otherwise null.
         */
        private static Float convertEncodableToFloat(final Encodable value) {
            Float floatValue = null;

            if (value instanceof Real) {
                floatValue = ((Real) value).floatValue();
            }

            return floatValue;
        }

        /**
         * Determine if the newValue has surpassed the threshold value compared
         * with the original value.
         * <p>
         * When the originalValue is null, it is automatically assumed to be
         * outside the threshold, because it means the property hasn't been seen
         * before.
         * <p>
         * If any of the parameters cannot be converted to a {@link Float}, then
         * this method returns true when the original and new value are not
         * equal and false otherwise.
         *
         * @param threshold
         *            The threshold value
         * @param originalValue
         *            The original or last sent value
         * @param newValue
         *            The new value to check
         * @return true if the new value is outside the threshold or false
         *         otherwise.
         */
        public static boolean isValueOutsideOfThreshold(final Real threshold,
                final Encodable originalValue, final Encodable newValue) {
            final Float floatThreshold = convertEncodableToFloat(threshold);
            final Float floatOriginal = convertEncodableToFloat(originalValue);
            final Float floatNewValue = convertEncodableToFloat(newValue);

            // This property hasn't been seen before, so a notification is
            // required
            if (originalValue == null) {
                return true;
            }
            // Handle types that can't do threshold comparisons
            else if (floatThreshold == null || floatOriginal == null
                    || floatNewValue == null) {
                return !originalValue.equals(newValue);
            } else {
                // Due to floating point maths, it's possible that where the
                // difference should be equal to the threshold
                // and not be outside the threshold actually evaluates to true
                // due to precision errors. However since
                // this threshold is calculated only for use in deciding whether
                // to trigger a COV notification, small
                // margins of error on boundary cases are acceptable.
                return Math.abs(floatNewValue - floatOriginal) > floatThreshold;
            }
        }
    }
}
