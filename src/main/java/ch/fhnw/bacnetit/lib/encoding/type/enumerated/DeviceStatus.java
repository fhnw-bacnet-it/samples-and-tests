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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class DeviceStatus extends Enumerated {
    private static final long serialVersionUID = -111489048861220863L;

    public static final DeviceStatus operational = new DeviceStatus(0);

    public static final DeviceStatus operationalReadOnly = new DeviceStatus(1);

    public static final DeviceStatus downloadRequired = new DeviceStatus(2);

    public static final DeviceStatus downloadInProgress = new DeviceStatus(3);

    public static final DeviceStatus nonOperational = new DeviceStatus(4);

    public static final DeviceStatus backupInProgress = new DeviceStatus(5);

    public static final DeviceStatus[] ALL = { operational, operationalReadOnly,
            downloadRequired, downloadInProgress, nonOperational,
            backupInProgress, };

    public DeviceStatus(final int value) {
        super(value);
    }

    public DeviceStatus(final ByteQueue queue) {
        super(queue);
    }
}
