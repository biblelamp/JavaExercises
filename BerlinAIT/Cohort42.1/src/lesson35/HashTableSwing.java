package lesson35;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class HashTableSwing extends JFrame {
    private final int WIDTH_SIZE = 1024;
    private final int HEIGHT_SIZE = 600;
    private HashTable<String, String> hashTable = new HashTable<>(4);

    public static void main(String[] args) {
        new HashTableSwing();
    }

    public HashTableSwing() {
        setTitle("HashTable");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initHashTable();

        CanvasPanel canvasPanel = new CanvasPanel();
        canvasPanel.setPreferredSize(new Dimension(WIDTH_SIZE, HEIGHT_SIZE));
        canvasPanel.setBackground(Color.lightGray);

        JTextField textField = new JTextField();
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            String result = "";
            String[] tokens = textField.getText().split(" ");
            if (tokens[0].equals("put")) {
                hashTable.put(tokens[1], tokens[2]);
            }
            if (tokens[0].equals("remove")) {
                result = hashTable.remove(tokens[1]);
            }
            if (tokens[0].equals("get")) {
                result = hashTable.get(tokens[1]);
            }
            textField.setText(result);
            canvasPanel.repaint();
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(textField);
        bottomPanel.add(enterButton);

        add(canvasPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initHashTable() {
//        hashTable.put("Kyiv", "Ukraine");
//        hashTable.put("Paris", "France");
//        hashTable.put("Moscow", "Russia");
//        hashTable.put("Berlin", "Germany");
//        hashTable.put("Prague", "Czech Republic");
//        hashTable.put("Warsaw", "Poland");
//        hashTable.put("Madrid", "Spain");
    }

    private class CanvasPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            HashTable<String, String>.Entry<String, String>[] table = hashTable.getTable();
            for (int i = 0; i < table.length; i++) {
                int link = 0;
                int x = 10 + i * 70;
                int y = 20;
                int dx = 65;
                int dy = 65;
                if (table[i] != null) {
                    g.setColor(Color.cyan);
                    g.fillRect(x, y, dx, dy);
                    g.setColor(Color.black);
                    g.drawString(String.format("%d %s", i, table[i].key), x + 5, y + 25);
                    g.drawString(table[i].value, x + 5, y + 40);
                    String next = (table[i].next == null) ? "n-> null" : "n-> " + table[i].next.key;
                    g.drawString(next, x + 5, y + 55);
                    HashTable<String, String>.Entry<String, String> pointer = table[i];
                    while (pointer.next != null) {
                        y = 20 + 65 + dy * link + 10 * (link + 1);
                        link++;
                        g.setColor(Color.cyan);
                        g.fillRect(x, y, dx, dy);
                        g.setColor(Color.black);
                        g.drawString(String.format("%d %s", i, pointer.next.key), x + 5, y + 25);
                        g.drawString((String) pointer.next.value, x + 5, y + 40);
                        String next2 = (pointer.next.next == null) ? "n-> null" : "n-> " + pointer.next.next.key;
                        g.drawString(next2, x + 5, y + 55);
                        pointer = pointer.next;
                    }
                } else {
                    g.setColor(Color.gray);
                    g.drawRect(x, y, dx, dy);
                    g.setColor(Color.black);
                    g.drawString(String.valueOf(i), x + 5, y + 25);
                }
            }
        }
    }
}
