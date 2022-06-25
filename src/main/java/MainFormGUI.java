import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.*;

public class MainFormGUI extends JFrame{
    private JButton button = new JButton("Вычислить");
    private JButton button_1 = new JButton("Открыть файл");

    private JTextField textField_1 = new JTextField("", 20);
    private JLabel label_1 = new JLabel("Дебет/Счет");

    private JTextField textField_2 = new JTextField("", 20);
    private JLabel label_2 = new JLabel("Кредит/Счет");

    private JTextField textField_3 = new JTextField("", 20);
    private JLabel label_3 = new JLabel("Название компании");

    private JLabel label_4 = new JLabel("Контрольная сумма");

    String path;

    Box mainBox, box_1, box_2, box_3, box_4;
    public MainFormGUI () {
        super();
        Font font = new Font("Verdana", Font.BOLD, 16);
        this.setIconImage(new ImageIcon("D:\\parser\\icon.png").getImage());
        setLocation(900, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        label_4.setFont(font);
        label_4.setAlignmentX(CENTER_ALIGNMENT);
        label_3.setAlignmentX(CENTER_ALIGNMENT);
        label_2.setAlignmentX(CENTER_ALIGNMENT);
        label_1.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button_1.setAlignmentX(CENTER_ALIGNMENT);
        mainBox = Box.createVerticalBox();
        box_1 = Box.createVerticalBox();
        box_2 = Box.createVerticalBox();
        box_3 = Box.createVerticalBox();
        box_4 = Box.createVerticalBox();

        box_1.add(label_4);
        box_1.add(Box.createVerticalStrut(10));
        box_2.add(label_1);
        box_2.add(textField_1);
        box_3.add(label_2);
        box_3.add(textField_2);
        box_3.add(label_3);
        box_3.add(textField_3);

        mainBox.add(box_1);
        mainBox.add(box_2);
        mainBox.add(box_3);
        mainBox.add(box_4);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(button_1);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                if(!textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("") && path != null)
                {
                    if(textField_1.getText().contains("."))
                    {
                        if(!textField_2.getText().contains(".") && !textField_2.getText().contains(","))
                        {
                            SumResearch sumResearch = new SumResearch();
                            try {
                                ResFormGUI resFormGUI = new ResFormGUI(sumResearch.findRes(textField_1.getText(), textField_2.getText(), textField_3.getText(), path));
                            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                                ex.printStackTrace();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,
                                    "Кредит/счет должен быть целым числом...",
                                    "Сообщение об ошибке",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,
                                "Дебет/счет должен быть вещественным числом, либо вы указали запятую вместо точки...",
                                "Сообщение об ошибке",JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Присутствуют незаполненные поля! Возможно вы не указали путь к файлу или какую либо информацию по поиску данных...",
                            "Сообщение об ошибке",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                try {
                    OpenDocument openDocument = new OpenDocument();
                    path = openDocument.path;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setContentPane(mainBox);
        setVisible(true);
        pack();
    }

    public class ResFormGUI extends JFrame{

        private JTextArea textField = new JTextArea();

        private JLabel label = new JLabel("Результат");

        private JScrollPane scroll;

        Box mainBox;
        public ResFormGUI (String text) {
            super();
            Font font = new Font("Verdana", Font.BOLD, 16);
            label.setFont(font);
            label.setAlignmentX(CENTER_ALIGNMENT);
            this.setIconImage(new ImageIcon("D:\\parser\\icon1.png").getImage());
            setSize(500, 250);
            setLocation(900, 400);
            textField.setText(text);
            scroll = new JScrollPane(textField);
            mainBox = Box.createVerticalBox();
            mainBox.add(label);
            mainBox.add(Box.createVerticalStrut(10));
            mainBox.add(scroll);
            setContentPane(mainBox);
            setVisible(true);
        }
    }
}
