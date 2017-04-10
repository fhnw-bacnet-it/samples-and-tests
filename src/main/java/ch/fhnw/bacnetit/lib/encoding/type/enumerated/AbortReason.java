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
package ch.fhnw.bacnetit.lib.encoding.type.enumerated;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class AbortReason extends Enumerated {
    private static final long serialVersionUID = -5845112557505021907L;

    public static final AbortReason other = new AbortReason(0);

    public static final AbortReason bufferOverflow = new AbortReason(1);

    public static final AbortReason invalidApduInThisState = new AbortReason(2);

    public static final AbortReason preemptedByHigherPriorityTask = new AbortReason(
            3);

    public static final AbortReason segmentationNotSupported = new AbortReason(
            4);

    private final BNAbortReason bnAbortReason;

    public enum BNAbortReason {
        OTHER((byte) 0, "Other"), BUFFER_OVERFLOW((byte) 1,
                "Buffer overflow"), INVALID_APDU_IN_THIS_STATE((byte) 2,
                        "Invalid APDU in this state"), PREEMPTED_BY_HIGHER_PRIORITY_TASK(
                                (byte) 3,
                                "Preempted by higher priority task"), SEGMENTATION_NOT_SUPPORTED(
                                        (byte) 4, "Segmentation not supported");

        private BNAbortReason(final byte bCode, final String desc) {
            this.value = bCode;
            this.desc = desc;
        }

        private final String desc;

        private final byte value;

        public byte byteCode() {
            return value;
        }

        public String description() {
            return desc;
        }

        private static final Map<Byte, BNAbortReason> reasonByBCode = new HashMap<Byte, BNAbortReason>(
                BNAbortReason.values().length);

        static {
            for (final BNAbortReason r : BNAbortReason.values()) {
                reasonByBCode.put(r.value, r);
            }
        }

        public static BNAbortReason reasonByBCode(final byte bCode) {
            return reasonByBCode.get(bCode);
        }
    }

    public static final AbortReason[] ALL = { other, bufferOverflow,
            invalidApduInThisState, preemptedByHigherPriorityTask,
            segmentationNotSupported };

    public AbortReason(final int value) {
        super(value);
        this.bnAbortReason = BNAbortReason.reasonByBCode((byte) value);
    }

    public AbortReason(final ByteQueue queue) {
        super(queue);
        this.bnAbortReason = BNAbortReason.reasonByBCode(byteValue());
    }

    public BNAbortReason getReason() {
        return bnAbortReason;
    }

    @Override
    public String toString() {
        return bnAbortReason.description();
    }
}
