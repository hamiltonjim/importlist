package com.moneydance.modules.features.importlist.view;

import java.util.Hashtable;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import org.apache.log4j.Logger;

import com.moneydance.modules.features.importlist.util.Preferences;

/**
 * This <code>EventListener</code> is notified upon rearrangement, resorting,
 * and resizing of the table. It saves the modifications in the
 * <code>Preferences</code>.
 *
 * @author <a href="mailto:&#102;&#108;&#111;&#114;&#105;&#97;&#110;&#46;&#106;
 *&#46;&#98;&#114;&#101;&#117;&#110;&#105;&#103;&#64;&#109;&#121;&#45;&#102;
 *&#108;&#111;&#119;&#46;&#99;&#111;&#109;">Florian J. Breunig</a>
 */
class TableListener implements TableColumnModelListener, RowSorterListener {

    /**
     * Static initialization of class-dependent logger.
     */
    private static Logger log = Logger.getLogger(TableListener.class);

    private final Preferences prefs;
    private final JTable table;
    private int lastFrom;
    private int lastTo;

    public TableListener(final JTable argTable) {
        this.prefs = Preferences.getInstance();
        this.table = argTable;
    }

    @Override
    public final void columnMarginChanged(final ChangeEvent e) {
        log.info("Margin of a column changed.");
        this.saveColumnWidths();
    }

    @Override
    public final void columnMoved(final TableColumnModelEvent e) {
        if (e.getFromIndex() == this.lastFrom
                && e.getToIndex() == this.lastTo) {
            return;
        }
        log.info("Order of columns changed.");
        this.lastFrom = e.getFromIndex();
        this.lastTo   = e.getToIndex();

        this.saveColumnOrder();
        this.saveSortKey();
        this.saveColumnWidths();
    }

    @Override
    public final void sorterChanged(final RowSorterEvent e) {
        log.info("Sort order of a column changed.");
        this.saveSortKey();
    }

    private void saveColumnOrder() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int column = 0; column < this.table.getColumnCount(); column++) {
            String columnName = this.table.getColumnName(column);
            hashtable.put(column + "", columnName);
        }
        this.prefs.setColumnNames(hashtable);
    }

    private void saveColumnWidths() {
        for (int column = 0; column < this.table.getColumnCount(); column++) {
            String columnName = this.table.getColumnName(column);
            int columnWidth   = this.table.getColumn(columnName).getWidth();
            this.prefs.setColumnWidths(column, columnWidth);
        }
    }

    private void saveSortKey() {
        if (this.table.getRowSorter().getSortKeys().isEmpty()) {
            return;
        }
        RowSorter.SortKey originalSortKey =
            this.table.getRowSorter().getSortKeys().get(0);
        int adjustedColumn = this.table.convertColumnIndexToView(
                originalSortKey.getColumn());
        RowSorter.SortKey adjustedSortKey =
            new RowSorter.SortKey(
                    adjustedColumn,
                    originalSortKey.getSortOrder());
        this.prefs.setSortKey(adjustedSortKey);
    }

    @Override
    public final void columnAdded(final TableColumnModelEvent e) {
    }

    @Override
    public final void columnRemoved(final TableColumnModelEvent e) {
    }

    @Override
    public final void columnSelectionChanged(final ListSelectionEvent e) {
    }
}
