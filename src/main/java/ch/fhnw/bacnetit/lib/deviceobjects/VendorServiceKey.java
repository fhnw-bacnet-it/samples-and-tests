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
package ch.fhnw.bacnetit.lib.deviceobjects;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;

public class VendorServiceKey {
    private final UnsignedInteger vendorId;

    private final UnsignedInteger serviceNumber;

    public VendorServiceKey(final int vendorId, final int serviceNumber) {
        this(new UnsignedInteger(vendorId), new UnsignedInteger(serviceNumber));
    }

    public VendorServiceKey(final UnsignedInteger vendorId,
            final UnsignedInteger serviceNumber) {
        this.vendorId = vendorId;
        this.serviceNumber = serviceNumber;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((serviceNumber == null) ? 0 : serviceNumber.hashCode());
        result = PRIME * result
                + ((vendorId == null) ? 0 : vendorId.hashCode());
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
        final VendorServiceKey other = (VendorServiceKey) obj;
        if (serviceNumber == null) {
            if (other.serviceNumber != null) {
                return false;
            }
        } else if (!serviceNumber.equals(other.serviceNumber)) {
            return false;
        }
        if (vendorId == null) {
            if (other.vendorId != null) {
                return false;
            }
        } else if (!vendorId.equals(other.vendorId)) {
            return false;
        }
        return true;
    }
}
