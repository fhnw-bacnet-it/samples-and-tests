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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class DeviceCommunicationControlRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 5975917456558517142L;

    public static final byte TYPE_ID = 17;

    private final UnsignedInteger timeDuration;

    private final EnableDisable enableDisable;

    private final CharacterString password;

    public DeviceCommunicationControlRequest(final UnsignedInteger timeDuration,
            final EnableDisable enableDisable, final CharacterString password) {
        super();
        this.timeDuration = timeDuration;
        this.enableDisable = enableDisable;
        this.password = password;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public AcknowledgementService handle(LocalDevice localDevice, Address
    // from, OctetString linkService)
    // throws BACnetException {
    // throw new NotImplementedException();
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeOptional(queue, timeDuration, 0);
        write(queue, enableDisable, 1);
        writeOptional(queue, password, 2);
    }

    DeviceCommunicationControlRequest(final ByteQueue queue)
            throws BACnetException {
        timeDuration = readOptional(queue, UnsignedInteger.class, 0);
        enableDisable = read(queue, EnableDisable.class, 1);
        password = readOptional(queue, CharacterString.class, 2);
    }

    public static class EnableDisable extends Enumerated {
        private static final long serialVersionUID = -697975876817917708L;

        public static final EnableDisable enable = new EnableDisable(0);

        public static final EnableDisable disable = new EnableDisable(1);

        public static final EnableDisable disableInitiation = new EnableDisable(
                2);

        private EnableDisable(final int value) {
            super(value);
        }

        public EnableDisable(final ByteQueue queue) {
            super(queue);
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((enableDisable == null) ? 0 : enableDisable.hashCode());
        result = PRIME * result
                + ((password == null) ? 0 : password.hashCode());
        result = PRIME * result
                + ((timeDuration == null) ? 0 : timeDuration.hashCode());
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
        final DeviceCommunicationControlRequest other = (DeviceCommunicationControlRequest) obj;
        if (enableDisable == null) {
            if (other.enableDisable != null) {
                return false;
            }
        } else if (!enableDisable.equals(other.enableDisable)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (timeDuration == null) {
            if (other.timeDuration != null) {
                return false;
            }
        } else if (!timeDuration.equals(other.timeDuration)) {
            return false;
        }
        return true;
    }
}
