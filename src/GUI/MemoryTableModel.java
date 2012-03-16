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
    private int numOfRows;
    private int numOfCols;
    
    private final String[] colNames= {"*", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    
    private Object[][] data;
    
    public MemoryTableModel(int cols, int rows) {
        numOfRows = rows;
        numOfCols = cols;
        data = new Object[numOfRows][numOfCols];
        this.initModel();
    }
    
    private void initModel() {
        for (int index = 0x0; index < numOfRows; index++) {
            data[index][0] = Integer.toHexString(index).toUpperCase();
        }
    }
    
    @Override
    public Class getColumnClass(int column) {
        System.out.println(column);
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
    
    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }
}
