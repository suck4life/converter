import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

public class ConverterGUI extends JFrame implements ActionListener {
    Converter converter = new Converter();

    TreeMap<String, String> labelToCurrency = new TreeMap<>() {{
        put("Белорусский рубль(BYN)", "BYN");
        put("Биткоин Голд(BTG)", "BTG");
        put("Биткойн(BTC)", "BTC");
        put("Биткойн Кэш(BCH)", "BCH");
        put("Грузинский лари(GEL)", "GEL");
        put("Доллар США(USD)", "USD");
        put("Евро(EUR)", "EUR");
        put("Зкеш(ZEC)", "ZEC");
        put("Индонезийская рупия(IDR)", "IDR");
        put("Канадский доллар(CAD)", "CAD");
        put("Китайский юань(CNY)", "CNY");
        put("Молдавский лей(MDL)", "MDL");
        put("Мьянманский кьят(MMK)", "MMK");
        put("Риппл(XRP)", "XRP");
        put("Рубль(RUB)", "RUB");
        put("Сербский динар(RSD)", "RSD");
        put("Тайский бат(THB)", "THB");
        put("Фунт стерлингов(GBP)", "GBP");
        put("Швейцарский франк(CHF)", "CHF");
        put("Шри-ланкийская рупия(LKR)", "LKR");
        put("Эфириум(ETH)", "ETH");
        put("Японская иена(JPY)", "JPY");
    }};
    // созданием TreeMap чтобы названия валют имели легко читаемый вид

    JComboBox<String> comboBoxFrom;
    JComboBox<String> comboBoxTo;
    JTextField textFieldFrom;
    JTextField textFieldTo;
    JButton convert;

    public ConverterGUI() {
        Box panel = new Box(BoxLayout.X_AXIS);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(60, 10, 60, 10));
        //создаем панель, устанавливаем границы невидимые для нее

        textFieldFrom = new JTextField(20);
        panel.add(textFieldFrom);
        //добавляем на панель поле для ввода числа

        comboBoxFrom = new JComboBox(labelToCurrency.keySet().toArray());
        comboBoxFrom.setSelectedIndex(-1);
        panel.add(comboBoxFrom);
        panel.add(Box.createHorizontalStrut(10));
        //добавляем на панель первый комбоБокс, устанавливаем индекс -1 чтобы не было выбранного значения

        convert = new JButton("Конвертировать");
        panel.add(convert);
        panel.add(Box.createHorizontalStrut(10));
        convert.addActionListener(this);
        //добавляем на панель кнопку, привязываем к ней ActionListener

        textFieldTo = new JTextField("Результат", 20);
        textFieldTo.setEnabled(false);
        textFieldTo.setDisabledTextColor(Color.BLACK);
        panel.add(textFieldTo);
        //добавляем на панель поле для вывода результата, ввод false

        comboBoxTo = new JComboBox(labelToCurrency.keySet().toArray());
        comboBoxTo.setSelectedIndex(-1);
        panel.add(comboBoxTo);
        //добавляем на панель второй комбоБокс, устанавливаем индекс -1 чтобы не было выбранного значения

        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kabachok\\IdeaProjects\\" +
                "converter\\src\\main\\resources\\pig.PNG"));
        //добавляем иконку приложения в левый верхний угол
        setTitle("Конвертер валют");
        setVisible(true);
        setSize(900, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.PINK);
        //устанавливаем размеры окна приложения, делаем его неизменяемым, устанавливаем цвет, видимость
    }

    public void actionPerformed(ActionEvent a) {
        //вытаскиваем из комбобоксов то, что пользователь выбрал
        String currencyFromLabel = (String) comboBoxFrom.getSelectedItem();
        String currencyToLabel = (String) comboBoxTo.getSelectedItem();

        if (currencyFromLabel == null || currencyToLabel == null) {
            return;
        }

        String currencyFromValue = labelToCurrency.get(currencyFromLabel); // Down cast
        String currencyToValue = labelToCurrency.get(currencyToLabel);
        String stringValueFrom = textFieldFrom.getText();


        //если значения в комбобоксах равны, то ввод копируется в вывод
        if (currencyFromValue.equals(currencyToValue)) {
            textFieldTo.setText(stringValueFrom);
            return;
        }


        try {
            //создаем переменную и вкладываем в нее введенное пользователем значение приведенное к Double
            double valueFrom = Double.parseDouble(stringValueFrom);
            //проверяем, что введенное значение не меньше 0
            if (valueFrom < 0) {
                JOptionPane.showMessageDialog(this, "Неправильный ввод, значение должно быть не меньше нуля");
                return;
            }
            //применяем метод convert с введенными параметрами и выводим результат.
            var valueTo = converter.convert(currencyFromValue, currencyToValue, valueFrom);
            textFieldTo.setText(Double.toString(valueTo));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Неправильный ввод, введите число");
        }

    }

    public static void main(String[] args) {
        new ConverterGUI();
    }
}
