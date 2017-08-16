import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class FrameWithMenuShortcut extends JFrame {
    private JMenuBar menuBar = new JMenuBar(); // Window menu bar
    private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem;
    private JRadioButtonMenuItem lineItem, rectangleItem, circleItem;
    private JCheckBoxMenuItem redItem, yellowItem;
    
    public FrameWithMenuShortcut(String title) {
        JMenu fileMenu = new JMenu("File"); // Create File menu
        JMenu elementMenu = new JMenu("Elements"); // Create Elements menu
        fileMenu.setMnemonic('F'); // Create shortcut
        elementMenu.setMnemonic('E'); // Create shortcut
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        newItem = fileMenu.add("New");
        openItem = fileMenu.add("Open");
        closeItem = fileMenu.add("Close");
        fileMenu.addSeparator();
        saveItem = fileMenu.add("Save");
        saveAsItem = fileMenu.add("Save As...");
        fileMenu.addSeparator();
        printItem = fileMenu.add("Print");
        elementMenu.add(lineItem = new JRadioButtonMenuItem("Line", true));
        elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle", false));
        elementMenu.add(circleItem = new JRadioButtonMenuItem("Circle", false));
        ButtonGroup types = new ButtonGroup();
        types.add(lineItem);
        types.add(rectangleItem);
        types.add(circleItem);
        elementMenu.addSeparator();
        elementMenu.add(redItem = new JCheckBoxMenuItem("Red", false));
        elementMenu.add(yellowItem = new JCheckBoxMenuItem("Yellow", false));
        menuBar.add(fileMenu);
        menuBar.add(elementMenu);
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        printItem.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
        lineItem.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
        rectangleItem.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
        circleItem.setAccelerator(KeyStroke.getKeyStroke('I', CTRL_DOWN_MASK));
        elementMenu.addSeparator();
        redItem.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
        yellowItem.setAccelerator(KeyStroke.getKeyStroke('Y', CTRL_DOWN_MASK));
        menuBar.add(fileMenu);
        menuBar.add(elementMenu);
    }

    public static void main(String[] a) {
        FrameWithMenuShortcut window = new FrameWithMenuShortcut("");
        window.setBounds(30, 30, 300, 300);
        window.setVisible(true);
    }
}