package com.moneydance.modules.features.importlist.swing;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.moneydance.modules.features.importlist.Main;

/**
 * @author Florian J. Breunig, Florian.J.Breunig@my-flow.com
 */
public class ButtonDeleteEditor extends DefaultCellEditor {

  private static final long   serialVersionUID = 6773713824539296048L;
  private final        Main   main;
  private              String label;


  public ButtonDeleteEditor(final Main argMain) {
     super(new JCheckBox());
     this.main = argMain;
  }


  @Override
  public final Component getTableCellEditorComponent(
     final JTable table,
     final Object value,
     final boolean isSelected,
     final int row,
     final int column) {

     if (value != null) {
         this.label = value.toString();
     }

     JButton button = new JButton(this.label);
     ActionListener actionListener = this.main.getDeleteActionListener(
           table.convertRowIndexToModel(row));
     button.addActionListener(actionListener);

     return button;
  }


  @Override
  public final Object getCellEditorValue() {
    return this.label;
  }
}