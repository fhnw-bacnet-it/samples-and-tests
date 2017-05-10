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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.ObjectCovSubscription;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetRuntimeException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetServiceException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.Address;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.BaseType;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PriorityArray;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PriorityValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.PropertyValue;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.BinaryPV;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Date;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.OctetString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Time;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;

/**
 * BACnetObject Representation
 *
 * @author IMVS, FHNW
 *
 */
public class BACnetObject implements Serializable {
    private static final long serialVersionUID = 569892306207282576L;
    private final BACnetObjectIdentifier id;
    private final Map<BACnetPropertyIdentifier, Encodable> properties = new HashMap<BACnetPropertyIdentifier, Encodable>();
    private final List<ObjectCovSubscription> covSubscriptions = new ArrayList<ObjectCovSubscription>();

    public BACnetObject(final BACnetObjectIdentifier id) {

        if (id == null) {
            throw new IllegalArgumentException("object id cannot be null");
        }
        this.id = id;

        try {
            // setProperty(BACnetPropertyIdentifier.objectName, new
            // CharacterString(id.toString()));

            // Set any default values.
            final List<BACnetProperty> defs = BACnetObjectFactory
                    .getPropertyTypeDefinitions(id.getObjectType());
            for (final BACnetProperty def : defs) {
                if (def.getDefaultValue() != null) {
                    setProperty(def.getPropertyIdentifier(),
                            def.getDefaultValue());
                }
            }
        } catch (final BACnetServiceException e) {
            // Should never happen, but wrap in an unchecked just in case.
            throw new BACnetRuntimeException(e);
        }
    }

    public BACnetObjectIdentifier getId() {
        return id;
    }

    public int getInstanceId() {
        return id.getInstanceNumber();
    }

    public String getObjectName() {
        final CharacterString name = getRawObjectName();
        if (name == null) {
            return null;
        }
        return name.getValue();
    }

    public CharacterString getRawObjectName() {
        return (CharacterString) properties
                .get(BACnetPropertyIdentifier.objectName);
    }

    public String getDescription() {
        final CharacterString name = (CharacterString) properties
                .get(BACnetPropertyIdentifier.description);
        if (name == null) {
            return null;
        }
        return name.getValue();
    }

    //
    //
    // Get property
    //
    public Encodable getProperty(final BACnetPropertyIdentifier pid)
            throws BACnetServiceException {
        if (pid.intValue() == BACnetPropertyIdentifier.objectIdentifier
                .intValue()) {
            return id;
        }
        if (pid.intValue() == BACnetPropertyIdentifier.objectType.intValue()) {
            return id.getObjectType();
        }

        // Check that the requested property is valid for the object. This will
        // throw an exception if the
        // property doesn't belong.
        BACnetObjectFactory
                .getPropertyTypeDefinitionRequired(id.getObjectType(), pid);

        // Do some property-specific checking here.
        if (pid.intValue() == BACnetPropertyIdentifier.localTime.intValue()) {
            return new Time();
        }
        if (pid.intValue() == BACnetPropertyIdentifier.localDate.intValue()) {
            return new Date();
        }

        return properties.get(pid);
    }

    public Encodable getPropertyRequired(final BACnetPropertyIdentifier pid)
            throws BACnetServiceException {
        final Encodable p = getProperty(pid);
        if (p == null) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.unknownProperty);
        }
        return p;
    }

    public Encodable getProperty(final BACnetPropertyIdentifier pid,
            final UnsignedInteger propertyArrayIndex)
            throws BACnetServiceException {
        final Encodable result = getProperty(pid);
        if (propertyArrayIndex == null) {
            return result;
        }

        if (!(result instanceof SequenceOf<?>)) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.propertyIsNotAnArray);
        }

        final SequenceOf<?> array = (SequenceOf<?>) result;
        final int index = propertyArrayIndex.intValue();
        if (index == 0) {
            return new UnsignedInteger(array.getCount());
        }

        if (index > array.getCount()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.invalidArrayIndex);
        }

        return array.get(index);
    }

    public Encodable getPropertyRequired(final BACnetPropertyIdentifier pid,
            final UnsignedInteger propertyArrayIndex)
            throws BACnetServiceException {
        final Encodable p = getProperty(pid, propertyArrayIndex);
        if (p == null) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.unknownProperty);
        }
        return p;
    }

    //
    //
    // Set property
    //
    public void setProperty(final BACnetPropertyIdentifier pid,
            final Encodable value) throws BACnetServiceException {
        BACnetObjectFactory.validateValue(id.getObjectType(), pid, value);
        setPropertyImpl(pid, value);

        // If the relinquish default was set, make sure the present value gets
        // updated as necessary.
        if (pid.equals(BACnetPropertyIdentifier.relinquishDefault)) {
            setCommandableImpl((PriorityArray) getProperty(
                    BACnetPropertyIdentifier.priorityArray));
        }
    }

    @SuppressWarnings("unchecked")
    public void setProperty(final BACnetPropertyIdentifier propId,
            final int index, final Encodable value)
            throws BACnetServiceException {
        BACnetObjectFactory.validateSequenceValue(id.getObjectType(), propId,
                value);
        SequenceOf<Encodable> list = (SequenceOf<Encodable>) properties
                .get(propId);
        if (list == null) {
            list = new SequenceOf<Encodable>();
            setPropertyImpl(propId, list);
        }
        list.set(index, value);
    }

    public void setProperty(final PropertyValue value)
            throws BACnetServiceException {
        final BACnetPropertyIdentifier pid = value.getPropertyIdentifier();

        if (pid.intValue() == BACnetPropertyIdentifier.objectIdentifier
                .intValue()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.writeAccessDenied);
        }
        if (pid.intValue() == BACnetPropertyIdentifier.objectType.intValue()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.writeAccessDenied);
        }
        if (pid.intValue() == BACnetPropertyIdentifier.priorityArray
                .intValue()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.writeAccessDenied);
            // if (pid.intValue() ==
            // PropertyIdentifier.relinquishDefault.intValue())
            // throw new BACnetServiceException(ErrorClass.property,
            // ErrorCode.writeAccessDenied);
        }

        if (BACnetObjectFactory.isCommandable((BACnetObjectType) getProperty(
                BACnetPropertyIdentifier.objectType), pid)) {
            setCommandable(value.getValue(), value.getPriority());
        } else if (value.getValue() == null) {
            if (value.getPropertyArrayIndex() == null) {
                removeProperty(value.getPropertyIdentifier());
            } else {
                removeProperty(value.getPropertyIdentifier(),
                        value.getPropertyArrayIndex());
            }
        } else {
            if (value.getPropertyArrayIndex() != null) {
                setProperty(pid, value.getPropertyArrayIndex().intValue(),
                        value.getValue());
            } else {
                setProperty(pid, value.getValue());
            }
        }
    }

    public void setCommandable(final Encodable value,
            final UnsignedInteger priority) throws BACnetServiceException {
        int pri = 16;
        if (priority != null) {
            pri = priority.intValue();
        }

        final PriorityArray priorityArray = (PriorityArray) getProperty(
                BACnetPropertyIdentifier.priorityArray);
        priorityArray.set(pri, createCommandValue(value));
        setCommandableImpl(priorityArray);
    }

    private void setCommandableImpl(final PriorityArray priorityArray)
            throws BACnetServiceException {
        PriorityValue priorityValue = null;
        for (final PriorityValue priv : priorityArray) {
            if (!priv.isNull()) {
                priorityValue = priv;
                break;
            }
        }

        Encodable newValue = getProperty(
                BACnetPropertyIdentifier.relinquishDefault);
        if (priorityValue != null) {
            newValue = priorityValue.getValue();
        }

        setPropertyImpl(BACnetPropertyIdentifier.presentValue, newValue);
    }

    private void setPropertyImpl(final BACnetPropertyIdentifier pid,
            final Encodable value) {
        final Encodable oldValue = properties.get(pid);
        properties.put(pid, value);

        if (!Objects.equals(value, oldValue)) {
            // Check for subscriptions.
            if (ObjectCovSubscription.sendCovNotification(id.getObjectType(),
                    pid, this.getCovIncrement())) {
                synchronized (covSubscriptions) {
                    final long now = System.currentTimeMillis();
                    ObjectCovSubscription sub;
                    for (int i = covSubscriptions.size() - 1; i >= 0; i--) {
                        sub = covSubscriptions.get(i);
                        if (sub.hasExpired(now)) {
                            covSubscriptions.remove(i);
                        } else if (sub.isNotificationRequired(pid, value)) {
                            sendCovNotification(sub, now);
                        }
                    }
                }
            }
        }
    }

    private PriorityValue createCommandValue(final Encodable value)
            throws BACnetServiceException {
        if (value instanceof Null) {
            return new PriorityValue((Null) value);
        }

        final BACnetObjectType type = (BACnetObjectType) getProperty(
                BACnetPropertyIdentifier.objectType);
        if (type.equals(BACnetObjectType.accessDoor)) {
            return new PriorityValue((BaseType) value);
        }
        if (type.equals(BACnetObjectType.analogOutput)
                || type.equals(BACnetObjectType.analogValue)) {
            return new PriorityValue((Real) value);
        }
        if (type.equals(BACnetObjectType.binaryOutput)
                || type.equals(BACnetObjectType.binaryValue)) {
            return new PriorityValue((BinaryPV) value);
        }
        return new PriorityValue((UnsignedInteger) value);
    }

    /**
     * return all implemented properties
     *
     * @return
     */
    public List<BACnetPropertyIdentifier> getProperties() {
        final ArrayList<BACnetPropertyIdentifier> list = new ArrayList<BACnetPropertyIdentifier>();
        for (final BACnetPropertyIdentifier pid : properties.keySet()) {
            list.add(pid);
        }
        return list;
    }

    //
    //
    // COV subscriptions
    //
    public void addCovSubscription(final Address from,
            final OctetString linkService,
            final UnsignedInteger subscriberProcessIdentifier,
            final Boolean issueConfirmedNotifications,
            final UnsignedInteger lifetime) throws BACnetServiceException {
        synchronized (covSubscriptions) {
            ObjectCovSubscription sub = findCovSubscription(from,
                    subscriberProcessIdentifier);
            final boolean confirmed = issueConfirmedNotifications
                    .booleanValue();

            if (sub == null) {
                // Ensure that this object is valid for COV notifications.
                if (!ObjectCovSubscription.sendCovNotification(
                        id.getObjectType(), null, this.getCovIncrement())) {
                    throw new BACnetServiceException(ErrorClass.services,
                            ErrorCode.covSubscriptionFailed,
                            "Object is invalid for COV notifications");
                }

                if (confirmed) {
                    // If the peer wants confirmed notifications, it must be in
                    // the remote device list.
                    // RemoteDevice d = localDevice.getRemoteDevice(from);
                    // if (d == null)
                    // throw new BACnetServiceException(ErrorClass.services,
                    // ErrorCode.covSubscriptionFailed,
                    // "From address not found in remote device list. Cannot
                    // send confirmed notifications");
                }

                sub = new ObjectCovSubscription(from, linkService,
                        subscriberProcessIdentifier, this.getCovIncrement());
                covSubscriptions.add(sub);
            }

            sub.setIssueConfirmedNotifications(
                    issueConfirmedNotifications.booleanValue());
            sub.setExpiryTime(lifetime.intValue());
        }
    }

    public Real getCovIncrement() {
        return (Real) properties.get(BACnetPropertyIdentifier.covIncrement);
    }

    public void removeCovSubscription(final Address from,
            final UnsignedInteger subscriberProcessIdentifier) {
        synchronized (covSubscriptions) {
            final ObjectCovSubscription sub = findCovSubscription(from,
                    subscriberProcessIdentifier);
            if (sub != null) {
                covSubscriptions.remove(sub);
            }
        }
    }

    private ObjectCovSubscription findCovSubscription(final Address from,
            final UnsignedInteger subscriberProcessIdentifier) {
        for (final ObjectCovSubscription sub : covSubscriptions) {
            if (sub.getAddress().equals(from)
                    && sub.getSubscriberProcessIdentifier()
                            .equals(subscriberProcessIdentifier)) {
                return sub;
            }
        }
        return null;
    }

    private void sendCovNotification(final ObjectCovSubscription sub,
            final long now) {
        // try {
        // UnsignedInteger timeLeft = new
        // UnsignedInteger(sub.getTimeRemaining(now));
        // SequenceOf<PropertyValue> values = new
        // SequenceOf<PropertyValue>(ObjectCovSubscription.getValues(this));
        //
        // if (sub.isIssueConfirmedNotifications()) {
        // // Confirmed
        // ConfirmedCovNotificationRequest req = new
        // ConfirmedCovNotificationRequest(
        // sub.getSubscriberProcessIdentifier(),
        // localDevice.getConfiguration().getId(), id, timeLeft,
        // values);
        // RemoteDevice d = localDevice.getRemoteDevice(sub.getAddress());
        // localDevice.send(d, req);
        // }
        // else {
        // // Unconfirmed
        // UnconfirmedCovNotificationRequest req = new
        // UnconfirmedCovNotificationRequest(
        // sub.getSubscriberProcessIdentifier(),
        // localDevice.getConfiguration().getId(), id, timeLeft,
        // values);
        // localDevice.sendUnconfirmed(sub.getAddress(), sub.getLinkService(),
        // req);
        // }
        // }
        // catch (BACnetException e) {
        // ExceptionDispatch.fireReceivedException(e);
        // }
    }

    public void validate() throws BACnetServiceException {
        // Ensure that all required properties have values.
        final List<BACnetProperty> defs = BACnetObjectFactory
                .getRequiredPropertyTypeDefinitions(id.getObjectType());
        for (final BACnetProperty def : defs) {
            if (getProperty(def.getPropertyIdentifier()) == null) {
                throw new BACnetServiceException(ErrorClass.property,
                        ErrorCode.other, "Required property not set: "
                                + def.getPropertyIdentifier());
            }
        }
    }

    public void removeProperty(final BACnetPropertyIdentifier pid)
            throws BACnetServiceException {
        final BACnetProperty def = BACnetObjectFactory
                .getPropertyTypeDefinitionRequired(id.getObjectType(), pid);
        if (def.isRequired()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.writeAccessDenied);
        }
        properties.remove(pid);
    }

    public void removeProperty(final BACnetPropertyIdentifier pid,
            final UnsignedInteger propertyArrayIndex)
            throws BACnetServiceException {
        final BACnetProperty def = BACnetObjectFactory
                .getPropertyTypeDefinitionRequired(id.getObjectType(), pid);
        if (!def.isSequence()) {
            throw new BACnetServiceException(ErrorClass.property,
                    ErrorCode.invalidArrayIndex);
        }
        final SequenceOf<?> sequence = (SequenceOf<?>) properties.get(pid);
        if (sequence != null) {
            sequence.remove(propertyArrayIndex.intValue());
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BACnetObject other = (BACnetObject) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
