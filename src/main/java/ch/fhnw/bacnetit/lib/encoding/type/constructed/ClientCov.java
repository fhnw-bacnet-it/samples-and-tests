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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Real;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class ClientCov extends BaseType {
    private static final long serialVersionUID = 654453440700433527L;

    private Real realIncrement;

    private Null defaultIncrement;

    public ClientCov(final Real realIncrement) {
        this.realIncrement = realIncrement;
    }

    public ClientCov(final Null defaultIncrement) {
        this.defaultIncrement = defaultIncrement;
    }

    @Override
    public void write(final ByteQueue queue) {
        if (realIncrement != null) {
            write(queue, realIncrement);
        } else {
            write(queue, defaultIncrement);
        }
    }

    public ClientCov(final ByteQueue queue) throws BACnetException {
        final int tag = (queue.peek(0) & 0xff) >> 4;
        if (tag == Null.TYPE_ID) {
            defaultIncrement = new Null(queue);
        } else if (tag == Real.TYPE_ID) {
            realIncrement = new Real(queue);
        } else {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.invalidParameterDataType);
        }
    }

    public Real getRealIncrement() {
        return realIncrement;
    }

    public Null getDefaultIncrement() {
        return defaultIncrement;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((defaultIncrement == null) ? 0
                : defaultIncrement.hashCode());
        result = PRIME * result
                + ((realIncrement == null) ? 0 : realIncrement.hashCode());
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
        final ClientCov other = (ClientCov) obj;
        if (defaultIncrement == null) {
            if (other.defaultIncrement != null) {
                return false;
            }
        } else if (!defaultIncrement.equals(other.defaultIncrement)) {
            return false;
        }
        if (realIncrement == null) {
            if (other.realIncrement != null) {
                return false;
            }
        } else if (!realIncrement.equals(other.realIncrement)) {
            return false;
        }
        return true;
    }
}
