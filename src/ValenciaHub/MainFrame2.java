package ValenciaHub;

import javax.swing.*;

public class MainFrame2 extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JPanel mainPanel2;

    public MainFrame2() {
        setContentPane(mainPanel2);
        setTitle("Gestión de contenedores - Ver detalles del contenedor");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        mainPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
}

