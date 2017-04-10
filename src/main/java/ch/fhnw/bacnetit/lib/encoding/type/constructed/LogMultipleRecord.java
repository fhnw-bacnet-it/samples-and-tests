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

import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class LogMultipleRecord extends BaseType {
    private static final long serialVersionUID = 3817374635968734673L;

    private final DateTime timestamp;

    private final LogData logData;

    public LogMultipleRecord(final DateTime timestamp, final LogData logData) {
        this.timestamp = timestamp;
        this.logData = logData;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, timestamp, 0);
        write(queue, logData, 1);
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public LogData getLogData() {
        return logData;
    }

    public LogMultipleRecord(final ByteQueue queue) throws BACnetException {
        timestamp = read(queue, DateTime.class, 0);
        logData = read(queue, LogData.class, 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logData == null) ? 0 : logData.hashCode());
        result = prime * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        final LogMultipleRecord other = (LogMultipleRecord) obj;
        if (logData == null) {
            if (other.logData != null) {
                return false;
            }
        } else if (!logData.equals(other.logData)) {
            return false;
        }
        if (timestamp == null) {
            if (other.timestamp != null) {
                return false;
            }
        } else if (!timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }
}
