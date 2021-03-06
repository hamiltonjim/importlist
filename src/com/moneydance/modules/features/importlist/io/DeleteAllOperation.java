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

import java.awt.Image;
import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moneydance.modules.features.importlist.util.Helper;
import com.moneydance.modules.features.importlist.util.Localizable;

/**
 * @author Florian J. Breunig
 */
final class DeleteAllOperation implements FileOperation {

    /**
     * Static initialization of class-dependent logger.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(DeleteAllOperation.class);

    private final Localizable   localizable;

    DeleteAllOperation() {
        this.localizable = Helper.INSTANCE.getLocalizable();
    }

    @Override
    public void showWarningAndPerform(final List<File> files) {
        final String message =
                this.localizable.getConfirmationMessageDeleteAllFiles(
                        files.size());
        final Object confirmationLabel = new JLabel(message);
        final Image image = Helper.INSTANCE.getIconImage();
        Icon  icon  = null;
        if (image != null) {
            icon = new ImageIcon(image);
        }
        final Object[] options = {
                this.localizable.getOptionDeleteFile(),
                this.localizable.getOptionCancel()
        };

        final int choice = JOptionPane.showOptionDialog(
                null, // no parent component
                confirmationLabel,
                null, // no title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                options,
                options[1]);

        if (choice == 0) {
            this.perform(files);
        } else {
            LOG.info("Canceled deleting all files");
        }
    }

    @Override
    public void perform(final List<File> files) {
        FileOperation deleteOneOperation = new DeleteOneOperation();
        for (final File file : files) {
            deleteOneOperation.perform(Collections.singletonList(file));
        }
    }
}
