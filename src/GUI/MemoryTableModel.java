/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.AbstractTableModel;
import rvm.*;

/**
 *
 * @author ernestas
 */
public class MemoryTableModel extends AbstractTableModel {
    private int numOfRows = Constants.MEMORY_SIZE;
    private int numOfCols = 0x4;
    
    private final String[] colNames= {"LA", "PHA", "VALUE", "INSTR"};
    
    private Object[][] data = new Object[numOfRows][numOfCols];
    
    public MemoryTableModel() {
        this.initModel();
    }
    
    private void initModel() {
        for (int index = 0x0; index < numOfRows; index++) {
            data[index][0] = Integer.toHexString(index);
        }
    }
    
    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
    
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    @Override
    public int getRowCount()  {
        return data.length;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        data[row][col] = ((String) aValue).toUpperCase();
    }
}
