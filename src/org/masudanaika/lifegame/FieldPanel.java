package org.masudanaika.lifegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author masuda, Masuda Naika
 */
public class FieldPanel extends JPanel {
    
    private final int rows;
    private final int columns;
    
    private static final int CELL_WIDTH = 10;
    private static final int CELL_HEIGHT = 10;
    
    private final CellData cellData;
    
    public CellData getCellData() {
        return cellData;
    }
    
    public FieldPanel(int rows, int columns) {
        setBorder(null);
        this.rows = rows;
        this.columns = columns;
        cellData = new CellData(rows, columns);
        Dimension d = new Dimension(CELL_WIDTH * columns, CELL_HEIGHT * rows);
        setPreferredSize(d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        g.setColor(Color.WHITE);
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < columns; ++col) {
                if (cellData.getCellData(row, col)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * CELL_WIDTH + 1, row * CELL_HEIGHT + 1, CELL_WIDTH - 2, CELL_HEIGHT - 2);
                }
            }
        }
    }

}
