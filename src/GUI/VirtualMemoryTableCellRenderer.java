/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
/**
 *
 * @author ernestas
 */
public class VirtualMemoryTableCellRenderer extends DefaultTableCellRenderer {
    private int nextInstructionAddress = -1;
    
    @Override
    public Component getTableCellRendererComponent
            (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if ((value instanceof String) && (col != 0)) {
            int address = Integer.valueOf((String) table.getModel().getValueAt(row, 0));
            address += Integer.valueOf((String) table.getModel().getColumnName(col), 16);
            if (isNextInstruction(address)) {
                cell.setBackground(Color.lightGray);
            } else {
                cell.setBackground(Color.WHITE);
            }
        }
        return cell;
    }
    
    private boolean isNextInstruction(int address) {
        if (address == this.nextInstructionAddress) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setNextInstructionAddress(int nextInstruction) {
        this.nextInstructionAddress = nextInstruction;
    }
}

























