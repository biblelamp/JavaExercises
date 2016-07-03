import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    private int linesCount;
    private final int PANEL_SIZE = 500;
    private int cellSize;
    private char[][] field = new char[5][5];
    private final char dotHuman = 'x';
    private final char dotAI = 'o';
    private final char dotEmpty = 0xB7;

    public Map(int linesCount) {
        this.linesCount = linesCount;
        initField(this.linesCount);
        cellSize = PANEL_SIZE/linesCount;
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/cellSize;
                int y = e.getY()/cellSize;
                if (isCellBusy(x, y)) {
                    System.out.println("Cell " + x + ":" + y + " is busy.");
                } else {
                    field[x][y] = dotHuman;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <linesCount; i++) {
            g.drawLine(0, i*cellSize, PANEL_SIZE, i*cellSize);
            g.drawLine(i*cellSize, 0, i*cellSize, PANEL_SIZE);
        }
        for (int x = 0; x < linesCount; x++) {
            for (int y = 0; y < linesCount; y++) {
                if (field[x][y] == dotHuman) {
                    g.drawLine(x*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                    g.drawLine((x+1)*cellSize, y*cellSize, (x)*cellSize, (y+1)*cellSize);
                }
                if (field[x][y] == dotAI) {
                    g.drawOval(x*cellSize, y*cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void initField(int field_size) {
        for (int y = 0; y < field_size; y++) {
            for (int x = 0; x < field_size; x++) {
                field[x][y] = dotEmpty;
            }
        }
    }

    private boolean isCellBusy(int x, int y) {
        return (field[x][y] != dotEmpty);
    }
}