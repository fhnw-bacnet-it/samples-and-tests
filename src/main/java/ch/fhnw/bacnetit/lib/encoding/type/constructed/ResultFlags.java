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

public class ResultFlags extends BitString {
    private static final long serialVersionUID = 7657134249555371620L;

    public ResultFlags(final boolean firstItem, final boolean lastItem,
            final boolean moreItems) {
        super(new boolean[] { firstItem, lastItem, moreItems });
    }

    public ResultFlags(final ByteQueue queue) {
        super(queue);
    }

    public boolean isFirstItem() {
        return getValue()[0];
    }

    public boolean isLastItem() {
        return getValue()[1];
    }

    public boolean isMoreItems() {
        return getValue()[2];
    }
}
