/*******************************************************************************
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2017 University of Applied Sciences and Arts,
 * Northwestern Switzerland FHNW,
 * Institute of Mobile and Distributed Systems.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.orglicenses.
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
