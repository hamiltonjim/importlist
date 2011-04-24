package com.moneydance.modules.features.importlist.io;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang.Validate;

/**
 * This <code>FileAlterationListener</code> implementation is notified about
 * relevant modifications in the file system. It propagates these modifications
 * to the <code>FileAdministration</code>.
 *
 * @author <a href="mailto:&#102;&#108;&#111;&#114;&#105;&#97;&#110;&#46;&#106;
 *&#46;&#98;&#114;&#101;&#117;&#110;&#105;&#103;&#64;&#109;&#121;&#45;&#102;
 *&#108;&#111;&#119;&#46;&#99;&#111;&#109;">Florian J. Breunig</a>
 */
public class TransactionFileListener extends FileAlterationListenerAdaptor {

    private final FileAdministration fileAdministration;
    private boolean dirty;

    TransactionFileListener(final FileAdministration argFileAdministration) {
        Validate.notNull(argFileAdministration,
        "argFileAdministration can't be null");
        this.fileAdministration = argFileAdministration;
    }

    @Override
    public final void onStart(final FileAlterationObserver observer) {
        this.dirty = false;
    }

    @Override
    public final void onFileCreate(final File file) {
        this.dirty = true;
    }

    @Override
    public final void onFileChange(final File file) {
        this.dirty = true;
    }

    @Override
    public final void onFileDelete(final File file) {
        this.dirty = true;
    }

    @Override
    public final void onStop(final FileAlterationObserver observer) {
        if (this.dirty) {
            this.fileAdministration.setDirty(true);
        }
    }
}