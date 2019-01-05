import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
  
public class Arrows
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ArrowPanel());
        f.setSize(500,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
  
class ArrowPanel extends JPanel
{
    int barb;
    double phi;
  
    public ArrowPanel()
    {
        barb = 20;                   // barb length
        phi = Math.PI/6;             // 30 degrees barb angle
        setBackground(Color.white);
    }
  
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        double theta, x, y;
  
        g2.setPaint(Color.blue);
        double x1 = w*3/24, y1 = h*3/32, x2 = w*11/24, y2 = y1;
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        // draw this arrow head at point x2, y2 and measure
        // angle theta relative to same point, ie, y2 - and x2 -
        theta = Math.atan2(y2 - y1, x2 - x1);
        drawArrow(g2, theta, x2, y2);
  
        x1 = w*3/8; y1 = h*13/15; x2 = w*2/3; y2 = y1;
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        theta = Math.atan2(y1 - y2, x1 - x2);
        drawArrow(g2, theta, x1, y1);
  
        g2.setPaint(Color.red);
        x1 = w*3/24; y1 = h*4/32; x2 = x1; y2 = h*18/32;
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        theta = Math.atan2(y2 - y1, x2 - x1);
        drawArrow(g2, theta, x2, y2);
  
        g2.setPaint(Color.orange);
        x1 = w*5/32; y1 = h*27/32; x2 = w*27/32; y2 = h*5/32;
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        theta = Math.atan2(y2 - y1, x2 - x1);
        drawArrow(g2, theta, x2, y2);
  
        g2.setPaint(Color.green.darker());
        x1 = w/2; y1 = h/2; x2 = w*27/32; y2 = h*27/32;
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        theta = Math.atan2(y2 - y1, x2 - x1);
        drawArrow(g2, theta, x2, y2);
    }
  
    private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
    {
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
    }
}