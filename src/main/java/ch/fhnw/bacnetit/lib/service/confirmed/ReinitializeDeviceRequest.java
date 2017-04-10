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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.CharacterString;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ReinitializeDeviceRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = -1121790418202250804L;

    public static final byte TYPE_ID = 20;

    private final ReinitializedStateOfDevice reinitializedStateOfDevice;

    private final CharacterString password;

    public ReinitializeDeviceRequest(
            final ReinitializedStateOfDevice reinitializedStateOfDevice,
            final CharacterString password) {
        this.reinitializedStateOfDevice = reinitializedStateOfDevice;
        this.password = password;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, reinitializedStateOfDevice, 0);
        writeOptional(queue, password, 1);
    }

    ReinitializeDeviceRequest(final ByteQueue queue) throws BACnetException {
        reinitializedStateOfDevice = read(queue,
                ReinitializedStateOfDevice.class, 0);
        password = readOptional(queue, CharacterString.class, 1);
    }

    public static class ReinitializedStateOfDevice extends Enumerated {
        private static final long serialVersionUID = -819543984468869678L;

        public static final ReinitializedStateOfDevice coldstart = new ReinitializedStateOfDevice(
                0);

        public static final ReinitializedStateOfDevice warmstart = new ReinitializedStateOfDevice(
                1);

        public static final ReinitializedStateOfDevice startbackup = new ReinitializedStateOfDevice(
                2);

        public static final ReinitializedStateOfDevice endbackup = new ReinitializedStateOfDevice(
                3);

        public static final ReinitializedStateOfDevice startrestore = new ReinitializedStateOfDevice(
                4);

        public static final ReinitializedStateOfDevice endrestore = new ReinitializedStateOfDevice(
                5);

        public static final ReinitializedStateOfDevice abortrestore = new ReinitializedStateOfDevice(
                6);

        public ReinitializedStateOfDevice(final int value) {
            super(value);
        }

        public ReinitializedStateOfDevice(final ByteQueue queue) {
            super(queue);
        }
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // String password = null;
    // if (this.password != null)
    // password = this.password.getValue();
    // if (password == null)
    // password = "";
    //
    // // Check the password
    // if (StringUtils.equals(localDevice.getPassword(), password)) {
    // localDevice.getEventHandler().reinitializeDevice(reinitializedStateOfDevice);
    // return null;
    // }
    //
    // throw new BACnetErrorException(getChoiceId(), ErrorClass.security,
    // ErrorCode.passwordFailure);
    // }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((password == null) ? 0 : password.hashCode());
        result = PRIME * result + ((reinitializedStateOfDevice == null) ? 0
                : reinitializedStateOfDevice.hashCode());
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
        final ReinitializeDeviceRequest other = (ReinitializeDeviceRequest) obj;
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (reinitializedStateOfDevice == null) {
            if (other.reinitializedStateOfDevice != null) {
                return false;
            }
        } else if (!reinitializedStateOfDevice
                .equals(other.reinitializedStateOfDevice)) {
            return false;
        }
        return true;
    }
}
