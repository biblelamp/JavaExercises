package homework4.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FeedingCats extends JFrame {

    final String TITLE_OF_PROGRAM = "Hungry Cats";
    final String BTN_ADD_FOOD = "Add food";
    final String BTN_FEED_CATS = "Feed cats";
    final String BTN_RESET = "Reset cats";
    final int WINDOW_WIDTH = 300;
    final int WINDOW_HEIGHT = 400;
    final int PLATE_VOLUME = 50;
    final int PLATE_INIT = 25;
    final int PLATE_ADD = 5;
    final int NUMBER_OF_CATS = 10;
    final int MAX_APPETITE = 20;

    Panel panel;
    SwingPlate plate; // plate with food
    SwingCat[] cats;  // array of cats

    public static void main(String args[]) {
        new FeedingCats();
    }

    public FeedingCats() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        panel = new Panel();
        panel.setBackground(Color.white);

        Random random = new Random();

        plate = new SwingPlate(PLATE_VOLUME, PLATE_INIT);
        cats = new SwingCat[NUMBER_OF_CATS];
        for (int i = 0; i < cats.length; i++)
            cats[i] = new SwingCat("", random.nextInt(MAX_APPETITE) + 1);

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
                for (SwingCat cat : cats) {
                    cat.eat(plate);
                }
                panel.repaint();
            }
        });
        JButton btnReset = new JButton(BTN_RESET);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (SwingCat cat : cats) {
                    cat.setFullness(false);
                }
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
            for (int i = 0; i < cats.length; i++) {
                cats[i].paint(g, (i + 1) * 30 + 10,
                        (int)(size.getWidth()) / PLATE_VOLUME, 20);
            }
        }
    }
}