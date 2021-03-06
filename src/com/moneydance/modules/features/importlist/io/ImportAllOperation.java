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
import java.io.FileFilter;
import java.util.Collections;
import java.util.List;

import com.moneydance.apps.md.controller.FeatureModuleContext;

/**
 * @author Florian J. Breunig
 */
final class ImportAllOperation implements FileOperation {

    private final FeatureModuleContext context;
    private final FileFilter           transactionFileFilter;
    private final FileFilter           textFileFilter;

    ImportAllOperation(
            final FeatureModuleContext argContext,
            final FileFilter argTransactionFileFilter,
            final FileFilter argTextFileFilter) {
        this.context                = argContext;
        this.transactionFileFilter  = argTransactionFileFilter;
        this.textFileFilter         = argTextFileFilter;
    }

    @Override
    public void showWarningAndPerform(final List<File> files) {
        this.perform(files);
    }

    @Override
    public void perform(final List<File> files) {
        FileOperation importOneOperation = new ImportOneOperation(
                this.context,
                this.transactionFileFilter,
                this.textFileFilter);
        for (final File file : files) {
            importOneOperation.perform(Collections.singletonList(file));
        }
    }
}
