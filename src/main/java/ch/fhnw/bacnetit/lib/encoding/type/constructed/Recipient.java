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

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.Encodable;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class Recipient extends BaseType {
    private static final long serialVersionUID = -2993858722446507060L;

    private final Choice choice;

    private static List<Class<? extends Encodable>> classes;

    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(BACnetObjectIdentifier.class);
        classes.add(Address.class);
    }

    public Recipient(final BACnetObjectIdentifier device) {
        choice = new Choice(0, device);
    }

    public Recipient(final Address address) {
        choice = new Choice(1, address);
    }

    public boolean isObjectIdentifier() {
        return choice.getContextId() == 0;
    }

    public BACnetObjectIdentifier getObjectIdentifier() {
        return (BACnetObjectIdentifier) choice.getDatum();
    }

    public boolean isAddress() {
        return choice.getContextId() == 1;
    }

    public Address getAddress() {
        return (Address) choice.getDatum();
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, choice);
    }

    public Recipient(final ByteQueue queue) throws BACnetException {
        choice = new Choice(queue, classes);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((choice == null) ? 0 : choice.hashCode());
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
        final Recipient other = (Recipient) obj;
        if (choice == null) {
            if (other.choice != null) {
                return false;
            }
        } else if (!choice.equals(other.choice)) {
            return false;
        }
        return true;
    }
}
