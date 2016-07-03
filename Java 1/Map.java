import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    int linesCount;
    final int PANEL_SIZE = 500;
    int cellSize;
    int x = -1;
    int y = -1;

    public Map(int linesCount){
        this.linesCount = linesCount;
        cellSize = PANEL_SIZE/linesCount;
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                x = e.getX()/cellSize;
                y = e.getY()/cellSize;
                System.out.println(e.getX()+" "+e.getY());
                System.out.println(x+" "+y);
                repaint();
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
        if(x != -1 && y != -1){
            g.drawLine(x*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
            g.drawLine((x+1)*cellSize, y*cellSize, (x)*cellSize, (y+1)*cellSize);
        }
    }
}