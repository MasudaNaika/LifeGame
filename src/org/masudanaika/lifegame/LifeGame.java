package org.masudanaika.lifegame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author masuda, Masuda Naika
 */
public class LifeGame {

    private static final int NUM_ROWS = 48;
    private static final int NUM_COLUMNS = 60;

    private FieldPanel panel;
    private final CellData tempData = new CellData(NUM_ROWS, NUM_COLUMNS);
    private int generation = 0;

    private static final String[] GLIDER_GUN = {
        //01234567890123456789012345678901234567
        "......................................",
        ".........................o............",
        ".......................o.o............",
        ".............oo......oo............oo.",
        "............o...o....oo............oo.",
        ".oo........o.....o...oo...............",
        ".oo........o...o.oo....o.o............",
        "...........o.....o.......o............",
        "............o...o.....................",
        ".............oo.......................",
        "......................................"};

    public static void main(String[] args) {
        new LifeGame().start();
    }

    private void start() {

        panel = new FieldPanel(NUM_ROWS, NUM_COLUMNS);
        JFrame frame = new JFrame();
        frame.setTitle("Life Game");
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(panel, BorderLayout.CENTER);
        JLabel lbl = new JLabel();
        p.add(lbl, BorderLayout.NORTH);
        frame.setContentPane(p);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        ActionListener al = (ActionEvent e) -> {
            nextGeneration();
            lbl.setText(String.valueOf(++generation));
            panel.repaint();

        };
        Timer timer = new Timer(50, al);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
                frame.dispose();
                System.exit(0);
            }
        });
        
        loadGliderGun();
        
        frame.setVisible(true);
        timer.start();
    }

    private void nextGeneration() {

        CellData cellData = panel.getCellData();
        cellData.copyTo(tempData);

        for (int row = 0; row < NUM_ROWS; ++row) {
            for (int col = 0; col < NUM_COLUMNS; ++col) {
                int cnt = countSurroundingLiveCells(tempData, row, col);
                // 生存：生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
                // 過密：生きているセルに隣接する生きたセルが4つ以上ならば、過密により死滅する。
                // 過疎：生きているセルに隣接する生きたセルが1つ以下ならば、過疎により死滅する。
                if (tempData.getCellData(row, col)) {
                    if (cnt >= 4 || cnt <= 1) {
                        cellData.setCellData(row, col, false);
                    }
                    // 誕生：死んでいるセルに隣接する生きたセルがちょうど3つあれば、次の世代が誕生する。    
                } else {
                    if (cnt == 3) {
                        cellData.setCellData(row, col, true);
                    }
                }
            }
        }

    }

    private int countSurroundingLiveCells(CellData cellData, int row, int col) {
        int cnt = 0;
        for (int dy = -1; dy <= 1; ++dy) {
            for (int dx = -1; dx <= 1; ++dx) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int testRow = row + dy;
                int testCol = col + dx;
                if (testRow >= 0 && testRow < NUM_ROWS
                        && testCol >= 0 && testCol < NUM_COLUMNS
                        && cellData.getCellData(testRow, testCol)) {
                    ++cnt;
                }
            }
        }

        return cnt;
    }

    private void loadGliderGun() {
        CellData cellData = panel.getCellData();
        for (int row = 0; row < GLIDER_GUN.length; ++row) {
            String data = GLIDER_GUN[row];
            for (int col = 0; col < data.length(); ++col) {
                cellData.setCellData(row, col, data.charAt(col) == 'o');
            }
        }
    }

}
