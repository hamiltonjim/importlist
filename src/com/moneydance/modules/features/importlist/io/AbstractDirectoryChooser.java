/*
 * Import List - http://my-flow.github.com/importlist/
 * Copyright (C) 2011-2012 Florian J. Breunig
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.moneydance.modules.features.importlist.io;

import java.io.File;

import com.moneydance.modules.features.importlist.util.Helper;
import com.moneydance.modules.features.importlist.util.Localizable;
import com.moneydance.modules.features.importlist.util.Preferences;

/**
 * This class provides the functionality to choose, access and reset the
 * extension's base directory. The base directory is the directory in the file
 * system to be monitored. Choosing/resetting the base directory is reflected in
 * the user's preferences (if there are any).
 *
 * @author Florian J. Breunig
 */
abstract class AbstractDirectoryChooser {

    private final Preferences prefs;
    private final Localizable localizable;

    /**
     * @param argBaseDirectory
     *            set the base directory when executed as a stand- alone
     *            application
     */
    AbstractDirectoryChooser(final String argBaseDirectory) {
        this.prefs = Helper.INSTANCE.getPreferences();
        this.localizable = Helper.INSTANCE.getLocalizable();
        if (argBaseDirectory != null) {
            this.prefs.setBaseDirectory(argBaseDirectory);
        }
    }

    abstract void chooseBaseDirectory();

    final File getBaseDirectory() {
        if (this.prefs.getBaseDirectory() == null) {
            return null;
        }

        return new File(this.prefs.getBaseDirectory());
    }

    final void reset() {
        this.prefs.setBaseDirectory(null);
    }

    protected final Preferences getPrefs() {
        return this.prefs;
    }

    protected final Localizable getLocalizable() {
        return this.localizable;
    }
}
