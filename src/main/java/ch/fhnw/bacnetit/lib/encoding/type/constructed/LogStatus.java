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

import ch.fhnw.bacnetit.lib.encoding.type.primitive.BitString;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class LogStatus extends BitString {
    private static final long serialVersionUID = 2623926749486653669L;

    public LogStatus(final boolean logDisabled, final boolean bufferPurged) {
        super(new boolean[] { logDisabled, bufferPurged });
    }

    public LogStatus(final ByteQueue queue) {
        super(queue);
    }

    public boolean isLogDisabled() {
        return getValue()[0];
    }

    public boolean isBufferPurged() {
        return getValue()[1];
    }
}
