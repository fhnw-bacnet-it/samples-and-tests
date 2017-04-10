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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class CovSubscription extends BaseType {
    private static final long serialVersionUID = -455474598550254295L;

    private final RecipientProcess recipient;

    private final ObjectPropertyReference monitoredPropertyReference;

    private final Boolean issueConfirmedNotifications;

    private final UnsignedInteger timeRemaining;

    private final Real covIncrement;

    public CovSubscription(final RecipientProcess recipient,
            final ObjectPropertyReference monitoredPropertyReference,
            final Boolean issueConfirmedNotifications,
            final UnsignedInteger timeRemaining, final Real covIncrement) {
        this.recipient = recipient;
        this.monitoredPropertyReference = monitoredPropertyReference;
        this.issueConfirmedNotifications = issueConfirmedNotifications;
        this.timeRemaining = timeRemaining;
        this.covIncrement = covIncrement;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, recipient, 0);
        write(queue, monitoredPropertyReference, 1);
        write(queue, issueConfirmedNotifications, 2);
        write(queue, timeRemaining, 3);
        writeOptional(queue, covIncrement, 4);
    }

    public CovSubscription(final ByteQueue queue) throws BACnetException {
        recipient = read(queue, RecipientProcess.class, 0);
        monitoredPropertyReference = read(queue, ObjectPropertyReference.class,
                1);
        issueConfirmedNotifications = read(queue, Boolean.class, 2);
        timeRemaining = read(queue, UnsignedInteger.class, 3);
        covIncrement = readOptional(queue, Real.class, 4);
    }

    public RecipientProcess getRecipient() {
        return recipient;
    }

    public ObjectPropertyReference getMonitoredPropertyReference() {
        return monitoredPropertyReference;
    }

    public Boolean getIssueConfirmedNotifications() {
        return issueConfirmedNotifications;
    }

    public UnsignedInteger getTimeRemaining() {
        return timeRemaining;
    }

    public Real getCovIncrement() {
        return covIncrement;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((covIncrement == null) ? 0 : covIncrement.hashCode());
        result = prime * result + ((issueConfirmedNotifications == null) ? 0
                : issueConfirmedNotifications.hashCode());
        result = prime * result + ((monitoredPropertyReference == null) ? 0
                : monitoredPropertyReference.hashCode());
        result = prime * result
                + ((recipient == null) ? 0 : recipient.hashCode());
        result = prime * result
                + ((timeRemaining == null) ? 0 : timeRemaining.hashCode());
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
        final CovSubscription other = (CovSubscription) obj;
        if (covIncrement == null) {
            if (other.covIncrement != null) {
                return false;
            }
        } else if (!covIncrement.equals(other.covIncrement)) {
            return false;
        }
        if (issueConfirmedNotifications == null) {
            if (other.issueConfirmedNotifications != null) {
                return false;
            }
        } else if (!issueConfirmedNotifications
                .equals(other.issueConfirmedNotifications)) {
            return false;
        }
        if (monitoredPropertyReference == null) {
            if (other.monitoredPropertyReference != null) {
                return false;
            }
        } else if (!monitoredPropertyReference
                .equals(other.monitoredPropertyReference)) {
            return false;
        }
        if (recipient == null) {
            if (other.recipient != null) {
                return false;
            }
        } else if (!recipient.equals(other.recipient)) {
            return false;
        }
        if (timeRemaining == null) {
            if (other.timeRemaining != null) {
                return false;
            }
        } else if (!timeRemaining.equals(other.timeRemaining)) {
            return false;
        }
        return true;
    }
}
