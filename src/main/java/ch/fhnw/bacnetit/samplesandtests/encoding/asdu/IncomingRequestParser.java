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
package ch.fhnw.bacnetit.samplesandtests.encoding.asdu;

import ch.fhnw.bacnetit.samplesandtests.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.samplesandtests.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.samplesandtests.encoding.util.ByteQueue;

public class IncomingRequestParser {
    protected final ByteQueue originalQueue;

    protected final ByteQueue queue;

    private final ServicesSupported servicesSupported;

    public IncomingRequestParser(final ServicesSupported servicesSupported,
            final ByteQueue queue) {
        this.servicesSupported = servicesSupported;
        this.queue = queue;
        this.originalQueue = (ByteQueue) queue.clone();
    }

    public ASDU parse() throws BACnetException {

        final ASDU asdu = ASDU.createASDU(servicesSupported, queue);

        return asdu;
    }
}
