package org.masudanaika.lifegame;

/**
 *
 * @author masuda, Masuda Naika
 */
public class CellData {
    
    private final boolean[] cellData;
    private final int rows;
    private final int columns;
    
    
    public CellData(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cellData = new boolean[rows * columns];
    }

    public boolean[] getCellData() {
        return cellData;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    
    public boolean getCellData(int row, int col) {
        return cellData[row * columns + col];
    }
    
    public void setCellData(int row, int col, boolean b) {
        cellData[row * columns + col] = b;
    }
    
    public void copyTo(CellData dest) {
        System.arraycopy(cellData, 0, dest.getCellData(), 0, cellData.length);
    }
}
