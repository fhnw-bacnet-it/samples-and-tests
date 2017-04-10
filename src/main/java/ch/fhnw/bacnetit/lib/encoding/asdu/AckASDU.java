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
package ch.fhnw.bacnetit.lib.encoding.asdu;

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
