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
import ch.fhnw.bacnetit.lib.encoding.type.primitive.Date;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

/**
 * ASHRAE Standard 135-2012 Clause 21 p. 667<br>
 * BACnetDateRange ::= SEQUENCE { -- see
 * {@link ch.fhnw.siemens.bacnet.asn1.type.primitive.Date} Clause 20.2.12 p.631
 * for restrictions <br>
 * startDate Date,<br>
 * endDate Date }<br>
 *
 */
public class DateRange extends BaseType {
    private static final long serialVersionUID = 7219491325251523667L;

    private final Date startDate;

    private final Date endDate;

    public DateRange(final Date startDate, final Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, startDate);
        write(queue, endDate);
    }

    public DateRange(final ByteQueue queue) throws BACnetException {
        startDate = read(queue, Date.class);
        endDate = read(queue, Date.class);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = PRIME * result
                + ((startDate == null) ? 0 : startDate.hashCode());
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
        final DateRange other = (DateRange) obj;
        if (endDate == null) {
            if (other.endDate != null) {
                return false;
            }
        } else if (!endDate.equals(other.endDate)) {
            return false;
        }
        if (startDate == null) {
            if (other.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(other.startDate)) {
            return false;
        }
        return true;
    }
}
