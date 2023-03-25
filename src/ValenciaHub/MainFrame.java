package ValenciaHub;
/*
TRABAJO - FUNDAMENTOS DE PROGRAMACIÓN II
INTERFAZ GRÁFICA DE PROGRAMA DE GESTIÓN DE CONTENEDORES "VALENCIA HUB MANAGEMENT SOFTWARE"
- ALEJANDRO ROMERO LORENZO
- ALEJANDRO GONZÁLEZ CORTIJO
- RICARDO VILLAMAR MONROY
- CARLOS MARTÍNEZ PÉREZ

ESCUELA SUPERIOR DE INGENIERÍA INFORMÁTICA
UNIVERSIDAD DE CASTILLA-LA MANCHA
Esta I parte del trabajo sólo incluye la interfaz gráfica y su clase correspondiente.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JTextArea mostrarPlanoDelHubTextArea;
    private JButton apilarButton;
    private JButton desapilarButton;
    private JTextField númeroDeColumnaTextField;
    private JButton mostrarDatosContenedorButton;
    private JTextField IDContenedorTextField;
    private JButton cuántosButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel mainPanel;
    private JPanel operaciones;

    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Gestión de contenedores");
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        //PRESIONAR BOTON APILAR
        apilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Has presionado apilar");
            }
        });
        //PRESIONAR BOTON DESAPILAR
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Has presionado desapilar");
            }
        });
        //PRESIONAR RADIOBUTTON 1

        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            a2RadioButton.setSelected(false);
            a3RadioButton.setSelected(false);
            JOptionPane.showMessageDialog(null,"Has presionado Prioridad 1");
            }
        });

        //PRESIONAR RADIOBUTTON 2

        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a1RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
                JOptionPane.showMessageDialog(null,"Has presionado Prioridad 2");
            }
        });

        //PRESIONAR RADIOBUTTON 3

        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a1RadioButton.setSelected(false);
                a2RadioButton.setSelected(false);
                JOptionPane.showMessageDialog(null,"Has presionado Prioridad 3");
            }
        });
        //PRESIONAR INSPECCIONADO EN ADUANAS
        inspeccionadoEnAduanasCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inspeccionadoEnAduanasCheckBox.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has marcado que el contenedor ha sido inspeccionado en aduanas");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Has marcado que el contenedor NO ha sido inspeccionado en aduanas");
                }
            }
        });
        //PRESIONAR BOTON CUANTOS

        cuántosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Has presionado Cuantos");
            }
        });

        //PRESIONAR MOSTRAR DATOS CONTENEDOR

        mostrarDatosContenedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame2 test2 = new MainFrame2();
            }
        });
    }

    public static void main(String[] args) {
        MainFrame test = new MainFrame();
    }
}
