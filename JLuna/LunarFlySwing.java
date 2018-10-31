import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Lunar lander simulator
 *
 * @author Sergey Iryupin
 * @version dated Oct 29, 2018
 */

public class LunarFlySwing extends JFrame {

    // flight constants
    float accelOfGravity = 1.62f;   // m/s^2, at Moon surface
    int dryWeight = 2000 + 150;     // kg, lunarfly and pilot
    int exhaustSpeed = 3660;        // m/s, from the engine
    float accelLimit = 3 * 9.81f;   // 3G, G is earth acceleration of gravity
    float speedLimit = 5;           // m/s, landing speed limit

    // init variables
    float startSpeed = 0;
    float startHeight = 0;
    int startFuelWeight = 400;
    float startFlightTime = 0;

    // flight variables
    float fuel;                     // kg, fuel consumption
    float duration;                 // s, maneuver time
    float height;                   // m, current height
    float speed;                    // m/s^2, current speed
    float acceleration;
    float fuelWeight;
    float flightTime;
    boolean isLanding;

    // left panel (variables)
    JTextField textHeight;
    JTextField textSpeed;
    JTextField textAccel;
    JTextField textFuelWeight;
    JTextField textFlightTime;
    
    // left panel (constants)
    JTextField textGravity;
    JTextField textWeight;
    JTextField textExhaustSpeed;
    JTextField textAccelLimit;
    JTextField textSpeedLimit;

    // left panel (control)
    JTextField textFuel;
    JTextField textTime;
    
    // table
    DefaultTableModel model;
    JTable table;
    Object columnNames[] = {"Event", "Fuel", "Time", "Speed", "Altitude",  "Accel", "Total fuel", "Total time"};
    Object rowData[][] = new Object[1][8];

    public LunarFlySwing() {
        setTitle("Lunar Fly");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(680, 500);
        setLocationRelativeTo(null);

        JPanel varPanel = new JPanel();
        varPanel.setBorder(BorderFactory.createTitledBorder("Var"));
        varPanel.setLayout(new BoxLayout(varPanel, BoxLayout.PAGE_AXIS));

        varPanel.add(new JLabel("Altitude, m"));
        textHeight = new JTextField();
        textHeight.setMaximumSize(new Dimension(Integer.MAX_VALUE, textHeight.getMinimumSize().height));
        textHeight.setEditable(false);
        varPanel.add(textHeight);

        varPanel.add(new JLabel("Speed, m/s"));
        textSpeed = new JTextField();
        textSpeed.setMaximumSize(new Dimension(Integer.MAX_VALUE, textSpeed.getMinimumSize().height));
        textSpeed.setEditable(false);
        varPanel.add(textSpeed);

        varPanel.add(new JLabel("A, m/s^2"));
        textAccel = new JTextField();
        textAccel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAccel.getMinimumSize().height));
        textAccel.setEditable(false);
        varPanel.add(textAccel);

        varPanel.add(new JLabel("Fuel, kg"));
        textFuelWeight = new JTextField();
        textFuelWeight.setMaximumSize(new Dimension(Integer.MAX_VALUE, textFuelWeight.getMinimumSize().height));
        textFuelWeight.setEditable(false);
        varPanel.add(textFuelWeight);

        varPanel.add(new JLabel("Time, s"));
        textFlightTime = new JTextField();
        textFlightTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, textFlightTime.getMinimumSize().height));
        textFlightTime.setEditable(false);
        varPanel.add(textFlightTime);

        JPanel constPanel = new JPanel();
        constPanel.setBorder(BorderFactory.createTitledBorder("Const"));
        constPanel.setLayout(new BoxLayout(constPanel, BoxLayout.PAGE_AXIS));

        constPanel.add(new JLabel("Gravity, m/s^2"));
        textGravity = new JTextField();
        textGravity.setMaximumSize(new Dimension(Integer.MAX_VALUE, textGravity.getMinimumSize().height));
        textGravity.setEditable(false);
        constPanel.add(textGravity);

        constPanel.add(new JLabel("Weight, kg"));
        textWeight = new JTextField();
        textWeight.setMaximumSize(new Dimension(Integer.MAX_VALUE, textWeight.getMinimumSize().height));
        textWeight.setEditable(false);
        constPanel.add(textWeight);

        constPanel.add(new JLabel("Exhaust, m/c"));
        textExhaustSpeed = new JTextField();
        textExhaustSpeed.setMaximumSize(new Dimension(Integer.MAX_VALUE, textExhaustSpeed.getMinimumSize().height));
        textExhaustSpeed.setEditable(false);
        constPanel.add(textExhaustSpeed);

        constPanel.add(new JLabel("A limit, m/c^2"));
        textAccelLimit = new JTextField();
        textAccelLimit.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAccelLimit.getMinimumSize().height));
        textAccelLimit.setEditable(false);
        constPanel.add(textAccelLimit);

        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setBorder(BorderFactory.createTitledBorder("Ctrl"));
        ctrlPanel.setLayout(new BoxLayout(ctrlPanel, BoxLayout.PAGE_AXIS));

        ctrlPanel.add(new JLabel("Fuel, kg"));
        textFuel = new JTextField();
        textFuel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textFuel.getMinimumSize().height));
        ctrlPanel.add(textFuel);

        ctrlPanel.add(new JLabel("Time, s"));
        textTime = new JTextField();
        textTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, textTime.getMinimumSize().height));
        ctrlPanel.add(textTime);
        
        JButton btnStart = new JButton("Start");
        btnStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnStart.getMinimumSize().height));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6", "Column 7", "Column 8"});
            }
        });
        ctrlPanel.add(btnStart);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(varPanel);
        leftPanel.add(constPanel);
        leftPanel.add(ctrlPanel);

        model = new DefaultTableModel();
        for (Object item : columnNames)
            model.addColumn(item);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
    
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LunarFlySwing();
    }
}
