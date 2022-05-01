import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

public class ConverterGUI extends JFrame{
    Converter converter = new Converter();

    TreeMap<String, String> labelToCurrency = new TreeMap<>() {{
        put("Белорусский рубль(BYN)", "BYN");
        put("Биткоин Голд(BTG)", "BTG");
        put("Биткойн(BTG)", "BTC");
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

    JComboBox<String> comboBoxFrom;
    JComboBox<String> comboBoxTo;
    JTextField textFieldFrom;
    JTextField textFieldTo;
    JButton convert;

    public ConverterGUI() {
        Box panel = new Box(BoxLayout.X_AXIS);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(60,10,60,10));
        textFieldFrom = new JTextField(20);
        panel.add(textFieldFrom);

        comboBoxFrom = new JComboBox(labelToCurrency.keySet().toArray());
        comboBoxFrom.setSelectedIndex(-1);
        panel.add(comboBoxFrom);
        panel.add(Box.createHorizontalStrut(10));

        convert = new JButton("Конвертировать");
        panel.add(convert);
        panel.add(Box.createHorizontalStrut(10));
        convert.addActionListener(new MyConvertListener());

        textFieldTo = new JTextField("Результат", 20);
        textFieldTo.setEnabled(false);
        textFieldTo.setDisabledTextColor(Color.BLACK);
        panel.add(textFieldTo);

        comboBoxTo = new JComboBox(labelToCurrency.keySet().toArray());
        comboBoxTo.setSelectedIndex(-1);
        panel.add(comboBoxTo);

        setIconImage (Toolkit.getDefaultToolkit().getImage("C:\\Users\\kabachok\\IdeaProjects\\converter\\src\\main\\resources\\pig.PNG"));
        setTitle("Конвертер валют");
        setVisible(true);
        setSize(900,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.PINK);
    }

    public class MyConvertListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            String currencyFrom = labelToCurrency.get((String) comboBoxFrom.getSelectedItem()); // Down cast
            String currencyTo = labelToCurrency.get((String) comboBoxTo.getSelectedItem());
            //вытаскиваем из комбобоксов то что пользователь выбрал
            if (currencyFrom == null || currencyTo == null) {
                return;
            }

            if (currencyFrom.equals(currencyTo)) {
                textFieldTo.setText(textFieldFrom.getText());
                return;
            }

            double valueFrom = Double.parseDouble(textFieldFrom.getText());

            try {
                var valueTo = converter.convert(currencyFrom, currencyTo, valueFrom);
                textFieldTo.setText(Double.toString(valueTo));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ConverterGUI();
    }
}
