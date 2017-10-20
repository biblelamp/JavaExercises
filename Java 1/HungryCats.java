/**
 * Java. Level 1. Lesson 7. Additional homework
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 20, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class HungryCats extends JFrame {

    final String TITLE_OF_PROGRAM = "Hungry Cats";
    final String BTN_ADD_FOOD = "Add food";
    final String BTN_FEED_CATS = "Feed cats";
    final String BTN_RESET = "Reset";
    final int WINDOW_WIDTH = 300;
    final int WINDOW_HEIGHT = 400;
    final int PLATE_VOLUME = 50;
    final int PLATE_INIT = 25;
    final int PLATE_ADD = 5;
    final int NUMBER_OF_CATS = 10;
    final int MAX_APPETITE = 20;

    Panel panel;
    PlateG plate; // plate with food
    CatG[] cats;  // array of cats

    public static void main(String args[]) {
        new HungryCats();
    }

    HungryCats() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        panel = new Panel();
        panel.setBackground(Color.white);

        Random random = new Random();
        plate = new PlateG(PLATE_VOLUME, PLATE_INIT);
        cats = new CatG[NUMBER_OF_CATS];
        for (int i = 0; i < cats.length; i++)
            cats[i] = new CatG("", random.nextInt(MAX_APPETITE) + 1);

        JButton btnAdd = new JButton(BTN_ADD_FOOD);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plate.add(PLATE_ADD);
                panel.repaint();
            }
        });
        JButton btnFeed = new JButton(BTN_FEED_CATS);
        btnFeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (CatG cat : cats)
                    cat.eat(plate);
                panel.repaint();
            }
        });
        JButton btnReset = new JButton(BTN_RESET);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (CatG cat : cats)
                    cat.setFullness(false);
                panel.repaint();
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout()); // for button panel
        btnPanel.add(btnAdd);
        btnPanel.add(btnFeed);
        btnPanel.add(btnReset);

        setLayout(new BorderLayout()); // for main window
        add(btnPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    class Panel extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Dimension size = this.getSize();
            plate.paint(g, (int)size.getWidth(), 20);
            for (int i = 0; i < cats.length; i++)
                cats[i].paint(g, (i + 1) * 30 + 10,
                    (int)(size.getWidth()) / PLATE_VOLUME, 20);
        }
    }
}

class CatG extends Cat {

    CatG(String name, int appetite) {
        super(name, appetite);
    }

    void paint(Graphics g, int y, int dx, int dy) {
        if (fullness) {
            g.setColor(Color.green);
            g.fill3DRect(20, y, appetite * dx + 1, dy + 1, true);
        }
        g.setColor(Color.black);
        g.drawRect(20, y, appetite * dx, dy);
    }
}

class PlateG extends Plate {

    PlateG(int volume, int food) {
        super(volume, food);
    }

    void paint(Graphics g, int windowWidth, int dy) {
        g.setColor(Color.red);
        g.fill3DRect(10, 10, food * (windowWidth - 20) / volume + 1, dy + 1, false);
        g.setColor(Color.black);
        g.drawRect(10, 10, windowWidth - 20, dy);
    }
}