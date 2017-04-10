/*******************************************************************************
x * Copyright (C) 2016 The Java BACnetITB Authors
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
package ch.fhnw.bacnetit.lib.encoding.asdu;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

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
