import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionGame extends JFrame {

    JSlider slider;
    JLabel sliderLabel;

    public OptionGame(final MyWindow owner) {
        setTitle("Options");
        setSize(200, 200);
        setLocation(900, 400);
        setResizable(false);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JLabel jb1 = new JLabel("Players");
        jb1.setFont(new Font("Arial",Font.BOLD, 14));
        jb1.setBounds(0,0,200,30);
        final JRadioButton jRadioBut1 = new JRadioButton("Human vs Computer");
        jRadioBut1.setSelected(true);
        JRadioButton jRadioBut2 = new JRadioButton("Human vs Human");
        jRadioBut2.setSelected(false);
        ButtonGroup playerButGroup = new ButtonGroup();
        playerButGroup.add(jRadioBut1);
        playerButGroup.add(jRadioBut2);

        add(jb1);
        add(jRadioBut1);
        add(jRadioBut2);

        sliderLabel = new JLabel("Field size: 3");
        sliderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(sliderLabel);
        slider = new JSlider();
        slider.setMinimum(3);
        slider.setValue(3);
        slider.setMaximum(10);
        add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderLabel.setText("Field size: "+slider.getValue());
            }
        });

        JButton jbOk = new JButton("Accept");
        jbOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setEnabled(true);
                owner.createMap(slider.getValue(), jRadioBut1.isSelected());
                setVisible(false);
            }
        });
        add(jbOk);
    }
}