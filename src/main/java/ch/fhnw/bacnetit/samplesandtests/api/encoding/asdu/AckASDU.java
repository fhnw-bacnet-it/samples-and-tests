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
package ch.fhnw.bacnetit.samplesandtests.api.encoding.asdu;

abstract public class AckASDU extends ASDU {
    private static final long serialVersionUID = -1568364467441619342L;

    /**
     * This parameter shall be the 'invokeID' contained in the confirmed service
     * request being acknowledged. The same 'originalinvokeID' shall be used for
     * all segments of a segmented acknowledgment.
     */
    protected byte originalInvokeId;

    public byte getOriginalInvokeId() {
        return originalInvokeId;
    }

    public boolean isServer() {
        return true;
    }
}
