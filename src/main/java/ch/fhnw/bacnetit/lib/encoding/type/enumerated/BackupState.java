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

import ch.fhnw.bacnetit.lib.encoding.type.primitive.Enumerated;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class BackupState extends Enumerated {
    private static final long serialVersionUID = -4811672152182130623L;

    public static final BackupState idle = new BackupState(0);

    public static final BackupState preparingForBackup = new BackupState(1);

    public static final BackupState preparingForRestore = new BackupState(2);

    public static final BackupState performingABackup = new BackupState(3);

    public static final BackupState performingARestore = new BackupState(4);

    public static final BackupState backupFailure = new BackupState(5);

    public static final BackupState restoreFailure = new BackupState(6);

    public static final BackupState[] ALL = { idle, preparingForBackup,
            preparingForRestore, performingABackup, performingARestore,
            backupFailure, restoreFailure, };

    public BackupState(final int value) {
        super(value);
    }

    public BackupState(final ByteQueue queue) {
        super(queue);
    }
}
