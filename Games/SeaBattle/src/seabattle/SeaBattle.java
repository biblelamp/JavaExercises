package seabattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeaBattle extends JFrame {

    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGTH = 800;

    private BattleField battleField;

    public static void main(String[] args) {
        new SeaBattle();
    }

    public SeaBattle() {
        setTitle("Sea Battle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Image ship = new ImageIcon(this.getClass().getResource("img/ship.jpg")).getImage();

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGTH));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                int ax = e.getX();
                int ay = e.getY();
                int x = ax / (WINDOW_WIDTH / 10);
                int y = ay / (WINDOW_HEIGTH / 10);
                battleField.mouseClick(x, y);
            }
        });

        battleField = new BattleField();
        battleField.setCanvas(canvas);
        battleField.setShip(ship);
        battleField.init();

        JButton btnRestart = new JButton("Restart");
        JButton btnExit = new JButton("Exit");

        btnRestart.addActionListener(e -> {
            battleField.init();
            canvas.repaint();
        });
        btnExit.addActionListener(e -> System.exit(0));

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnRestart);
        btnPanel.add(btnExit);

        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, btnPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Dimension size = getSize();

            // расчертим поле
            g.setColor(Color.lightGray);
            for (int i = 0; i < 9; i++) {
                int y = size.height / 10 * (i + 1);
                int x = size.width / 10 * (i + 1);
                g.drawLine(0, y, size.width, y);
                g.drawLine(x, 0, x, size.height);
            }

            // переход в улучшенную графику
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(3));

            g2d.setColor(Color.black);
            g2d.drawLine(size.width / 2, 0, size.width / 2, size.height);

            // нарисовать таблицу
            battleField.paint(g2d, size);
        }
    }
}
