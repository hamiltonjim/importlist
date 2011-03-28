package com.moneydance.modules.features.importlist.swing;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.moneydance.modules.features.importlist.Main;

/**
 * @author Florian J. Breunig, Florian.J.Breunig@my-flow.com
 *
 */
public class ButtonImportEditor extends DefaultCellEditor {

  private static final long   serialVersionUID = 6773713824539296048L;
  private final        Main   main;
  private              String label;


  public ButtonImportEditor(final Main argMain) {
     super(new JCheckBox());
     this.main = argMain;
  }


  @Override
  public final Component getTableCellEditorComponent(
     final JTable jTable,
     final Object value,
     final boolean isSelected,
     final int row,
     final int column) {

     this.main.importFile(jTable.convertRowIndexToModel(row));

     this.label = "";
     if (value != null) {
         this.label = value.toString();
     }
     return new JButton(this.label);
  }


  @Override
  public final Object getCellEditorValue() {
    return this.label;
  }
}
