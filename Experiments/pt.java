import javax.swing.*; 
import javax.swing.border.Border;
import java.awt.*;  
import java.awt.event.*; 
import java.awt.color.*; 
import java.util.*; 
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;
 
class pt extends JFrame { // класс pt называют прямым наследником класса JFrame

    private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

    private JMenuBar menu = null;
    private final String fileItems[] = new String []{"New", "Statistic", "Exit"};
    private static Random generator = new Random(); // генератор случайных чисел
    private int[][] numbers = new int[4][4];
    public TimerLabel tl = new TimerLabel();
    public TimerLabel2 tl2 = new TimerLabel2();
 
    pt() {
        setTitle("Пятнашки"); //Заголовок окна
        setSize (300, 300); // Задаем размеры окна приложения
        setLocationRelativeTo(null); // Окно приложения центрируется относительно экрана
        setResizable(true); // задаем возможность растягивать окно
        createMenu(); //инициализируем меню
        setJMenuBar(menu); // добавляем панель меню к окну
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрываем программу при закрытии окна
        Container container = getContentPane(); 
        init();
        panel.setDoubleBuffered(true);
        panel.setBackground(Color.white); // устанавливаем цвет фона
        container.add(panel); // добавление компонентов в контейнер
        repaintField();
        menu.add(tl);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // создаем границу черного цвета
        Font font = new Font("Verdana", Font.PLAIN, 12); // задаем тип шрифта, и его размер
        tl.setBorder(solidBorder); // устанавливаем границу
        tl.setFont(font); // устанавливаем тип текста
        tl.setForeground(Color.RED); // Устанавливаем цвет текста
    }

    void init() { // описание метода init
        int[] invariants = new int[16]; // инициализируем массив с именем invariants из 16 елементов - лт 0 до 15
        
        for (int i = 0; i < 4; i++) { // перебираем елементы i от 0 до 3
            for (int j = 0; j < 4; j++) { // перебираем елементы j от 0 до 3
                numbers[i][j] = 0; // указываем что перебор в цыкле начинаеться с нулевого елемента
                invariants[i*4 + j] = 0; // определяем какой из 16 елементов будет = 0
            }
        }
 
        for (int i = 1; i < 16; i++) { // перебираем елементы i от 1 до 15
            int k; //обьявляем переменную k типа int
            int l; //обьявляем переменную l типа int
            do { // цыкл с послеусловием
                k = generator.nextInt(100) % 4; // переменной k присваиваем произвольное число от 0 до 100 деленное по модулю на 4
                l = generator.nextInt(100) % 4; // переменной l присваиваем произвольное число от 0 до 100 деленное по модулю на 4
            }
            while (numbers[k][l] != 0); // до тех пор пока двумерный массив numbers не равен 0
            numbers[k][l] = i; // присваиваем двумерному массиву numbers значение i в цикле от 1 до 15
            invariants[k*4+l] = i; // определяем позиции всех елементов кроме 0 на сетке
        }

        boolean change = true; // в булевую переменную change заносим значение true
        int counter = 1; // инициализируем переменную counter типа int и присваеваем ей 1
        while (change) {
            change = false;
            for (int i = 0; i < 16; i++) {
                if (invariants[i] != i) {
                    for (int j = 0; j < 16; j++) {
                        if (invariants[j] == i) {
                            int temp = invariants[i];
                            invariants[i] = invariants[j];
                            invariants[j] = temp;
                            change = true;
                            counter++;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if (counter % 2 != 0) {
            int temp = numbers[0][0];
            numbers[0][0] = numbers[3][3];
            numbers[3][3] = temp;
        }
    }
    
    // класс для создания таймера и вывода его в JLabel
    class TimerLabel extends JLabel {
        public Timer timer = new Timer();

        public TimerLabel() {
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            //public void scheduleAtFixedRate(TimerTask task, long delay, long period)
            //указываем задержку и период
        }

        private TimerTask timerTask = new TimerTask() {

            private volatile int time = -1;
            /*определение переменной с ключевым словом volatile(«изменчивый») означает, 
            что значение переменной будет изменяться разными потоками.*/

            private Runnable refresher = new Runnable() {
                public void run(){
                    int t = time;
                    DecimalFormat decimalFormat = new DecimalFormat("00"); // задаем формат вывода секундомера
                    TimerLabel.this.setText(decimalFormat.format(t / 60) + ":" + decimalFormat.format(t % 60));
                }
            };

            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };

        public void StopTimer() {
            timer.cancel();
        }
    }

    public class TimerLabel2 extends JLabel {

        public Timer timer2 = new Timer();

        public TimerLabel2() {
            timer2.scheduleAtFixedRate(timerTask2, 0, 1000);
            //public void scheduleAtFixedRate(TimerTask task, long delay, long period)
            //указываем задержку и период
        }

        private TimerTask timerTask2 = new TimerTask() {

            private volatile int time2 = -1;
            /*определение переменной с ключевым словом volatile(«изменчивый») означает, 
            что значение переменной будет изменяться разными потоками.*/

            private Runnable refresher = new Runnable() {
                public void run(){
                    int t = time2;
                    DecimalFormat decimalFormat = new DecimalFormat("00"); // задаем формат вывода секундомера
                    TimerLabel2.this.setText(decimalFormat.format(t / 60) + ":" + decimalFormat.format(t % 60));
                }
            };

            public void run() {
                time2++;
                SwingUtilities.invokeLater(refresher);
            }
        };

        public void StopTimer() {
            timer2.cancel();
        }
    }

    public void NewTimer(){
        menu.add(tl2);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // создаем границу черного цвета
        Font font = new Font("Verdana", Font.PLAIN, 12); // задаем тип шрифта, и его размер
        tl2.setBorder(solidBorder); // устанавливаем границу
        tl2.setFont(font); // устанавливаем тип текста
        tl2.setForeground(Color.RED); // Устанавливаем цвет текста
    }

    public void repaintField() {  //метод расстановки кнопок со значениями на сетке
        panel.removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton(Integer.toString(numbers[i][j]));
                button.setFocusable(false);
                panel.add(button);
                button.setBackground(Color.getHSBColor(0.1059322f, 0.5221239f, 0.8862745f)); // устанавливаем цвет кнопок
                if (numbers[i][j] == 0) {  
                    button.setVisible(false); // сокрытие нулевого елемента массива
                } else
                    button.addActionListener(new ClickListener());
            }
        }
        panel.validate();
     }
 
    public boolean checkWin() { //метод проверки выигрыша
        boolean status = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j > 2) //проверка на то что последняя ячейка в сетке пустая
                    break;
                if (numbers[i][j] != i * 4 + j + 1) { //проверка на соотвествие элементам массива координатам в сетке
                    status = false;
                }
            }
        }
        return status;
    }
 
    private void createMenu() {
        menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        for (int i = 0; i < fileItems.length; i++) {
            JMenuItem item = new JMenuItem(fileItems[i]);
            JSeparator separator = new JSeparator();
            item.setActionCommand(fileItems[i].toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
            fileMenu.add(separator);
        }
        //fileMenu.insertSeparator(1);
        menu.add(fileMenu);
    }

    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) {
                System.exit(0);
            }
            if ("statistic".equals(command)){
                JOptionPane.showMessageDialog(null, "ВЫ ВЫИГРАЛИ!", "Поздравляем", 1);
            }
            if ("new".equals(command)) {
                init();
                repaintField();
                tl.setVisible(false);
                tl.disable();
                NewTimer();
            }
        }
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            change(Integer.parseInt(name));
        }
    }
 
    public void change(int num) { // передаем в качестве входящих параметров метода change переменную num типа int
        int i = 0, j = 0; // присваиваем перменным i и j типа int значение равное 0
        for (int k = 0; k < 4; k++) { // перебираем елементы k от 0 до 3
            for (int l = 0; l < 4; l++) { // перебираем елементы l от 0 до 3
                if (numbers[k][l] == num) { // если массив numbers[k][l] равный переменной num то,
                    i = k; // переменную i приравниваем переменной k
                    j = l; // переменную j приравниваем переменной l
                }
            }
        }
        /*реализация логики сдвигов кнопок на сетке 4 Х 4*/
        //сдвиг вверх по строкам
        if (i > 0) { // условие отвечающее за то можно ли сдвинуть кнопку по строке
            if (numbers[i - 1][j] == 0) { //сравниваем значение координат элемента массива с кнопкой которая в текущем массиве равна нулю
                numbers[i - 1][j] = num; //присваиваем переменной num значение координат элемента массива 
                numbers[i][j] = 0; //присваеваем нулевой элемент массива в ячейку которая перед этим смещалась в ноль
            }
        }
        //сдвиг вниз по строкам
        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        //сдвиг влево по столбцам
        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }
        //сдвиг вправо по столбцам
        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }
        repaintField();
        if (checkWin()) {
            tl.StopTimer();
            tl2.StopTimer();
            JOptionPane.showMessageDialog(null, "ВЫ ВЫИГРАЛИ!", "Поздравляем", 1);
            init();
            repaintField();
            setVisible(false);
            setVisible(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame app = new pt();
        app.setVisible(true);
    }
}