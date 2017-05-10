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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.type.constructed;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.samplesandtests.api.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.Encodable;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Boolean;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.SignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.samplesandtests.api.encoding.util.ByteQueue;

public class LogData extends BaseType {
    private static final long serialVersionUID = -1976023645603339559L;

    public static Choice booleanElement(final Boolean datum) {
        return new Choice(0, datum);
    }

    public static Choice realElement(final Real datum) {
        return new Choice(1, datum);
    }

    public static Choice enumElement(final Enumerated datum) {
        return new Choice(2, datum);
    }

    public static Choice unsignedElement(final UnsignedInteger datum) {
        return new Choice(3, datum);
    }

    public static Choice signedElement(final SignedInteger datum) {
        return new Choice(4, datum);
    }

    public static Choice bitstringElement(final BitString datum) {
        return new Choice(5, datum);
    }

    public static Choice nullElement(final Null datum) {
        return new Choice(6, datum);
    }

    public static Choice failureElement(final BACnetError datum) {
        return new Choice(7, datum);
    }

    public static Choice anyElement(final BaseType datum) {
        return new Choice(8, datum);
    }

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Boolean.class);
        classes.add(Real.class);
        classes.add(Enumerated.class);
        classes.add(UnsignedInteger.class);
        classes.add(SignedInteger.class);
        classes.add(BitString.class);
        classes.add(Null.class);
        classes.add(BACnetError.class);
        classes.add(Encodable.class);
    }

    private final LogStatus logStatus;

    private final SequenceOf<Choice> logData;

    private final Real timeChange;

    public LogData(final LogStatus logStatus, final SequenceOf<Choice> logData,
            final Real timeChange) {
        this.logStatus = logStatus;
        this.logData = logData;
        this.timeChange = timeChange;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, logStatus, 0);
        write(queue, logData, 1);
        write(queue, timeChange, 2);
    }

    public LogStatus getLogStatus() {
        return logStatus;
    }

    public SequenceOf<Choice> getLogData() {
        return logData;
    }

    public Real getTimeChange() {
        return timeChange;
    }

    public int getChoiceType(final int indexBase1) {
        return logData.get(indexBase1).getContextId();
    }

    public Boolean getBoolean(final int indexBase1) {
        return (Boolean) logData.get(indexBase1).getDatum();
    }

    public Real getReal(final int indexBase1) {
        return (Real) logData.get(indexBase1).getDatum();
    }

    public Enumerated getEnumerated(final int indexBase1) {
        return (Enumerated) logData.get(indexBase1).getDatum();
    }

    public UnsignedInteger getUnsignedInteger(final int indexBase1) {
        return (UnsignedInteger) logData.get(indexBase1).getDatum();
    }

    public SignedInteger getSignedInteger(final int indexBase1) {
        return (SignedInteger) logData.get(indexBase1).getDatum();
    }

    public BitString getBitString(final int indexBase1) {
        return (BitString) logData.get(indexBase1).getDatum();
    }

    public Null getNull(final int indexBase1) {
        return (Null) logData.get(indexBase1).getDatum();
    }

    public BACnetError getBACnetError(final int indexBase1) {
        return (BACnetError) logData.get(indexBase1).getDatum();
    }

    public BaseType getAny(final int indexBase1) {
        return (BaseType) logData.get(indexBase1).getDatum();
    }

    public LogData(final ByteQueue queue) throws BACnetException {
        logStatus = read(queue, LogStatus.class, 0);
        logData = readSequenceOfChoice(queue, classes, 1);
        timeChange = read(queue, Real.class, 2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logData == null) ? 0 : logData.hashCode());
        result = prime * result
                + ((logStatus == null) ? 0 : logStatus.hashCode());
        result = prime * result
                + ((timeChange == null) ? 0 : timeChange.hashCode());
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
        final LogData other = (LogData) obj;
        if (logData == null) {
            if (other.logData != null) {
                return false;
            }
        } else if (!logData.equals(other.logData)) {
            return false;
        }
        if (logStatus == null) {
            if (other.logStatus != null) {
                return false;
            }
        } else if (!logStatus.equals(other.logStatus)) {
            return false;
        }
        if (timeChange == null) {
            if (other.timeChange != null) {
                return false;
            }
        } else if (!timeChange.equals(other.timeChange)) {
            return false;
        }
        return true;
    }
}
