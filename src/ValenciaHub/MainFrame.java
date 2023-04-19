package ValenciaHub;
import ValenciaHubBackEnd_Hub.*;
/*
TRABAJO - FUNDAMENTOS DE PROGRAMACIÓN II
INTERFAZ GRÁFICA DE PROGRAMA DE GESTIÓN DE CONTENEDORES "VALENCIA HUB MANAGEMENT SOFTWARE"
- ALEJANDRO ROMERO LORENZO
- ALEJANDRO GONZÁLEZ CORTIJO
- RICARDO VILLAMAR MONROY
- CARLOS MARTÍNEZ PÉREZ

ESCUELA SUPERIOR DE INGENIERÍA INFORMÁTICA
UNIVERSIDAD DE CASTILLA-LA MANCHA

 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.nio.file.Files;

public class MainFrame extends JFrame {
    private JTextField campo_ID;
    private JTextField campo_Peso;
    private JTextField campo_Desc;
    private JTextField campo_Remitente;
    private JTextField campo_Receptora;
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
    private JComboBox cuantosComboBox;
    private JComboBox paisComboBox;
    private JPanel mainPanel;
    private JPanel operaciones;
    private JRadioButton hub1RadioButton;
    private JRadioButton hub2RadioButton;
    private JRadioButton hub3RadioButton;
    private JButton hub1Button;


    puerto p1;
    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Gestión de contenedores");
        setSize(800,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        hub1RadioButton.setSelected(true);
        paisComboBox.addItem((String)"España");
        paisComboBox.addItem((String)"Alemania");
        paisComboBox.addItem((String)"Francia");
        paisComboBox.addItem((String)"Irlanda");
        paisComboBox.addItem((String)"Arbacete");
        paisComboBox.addItem((String)"Murcia");

        cuantosComboBox.addItem((String)"España");
        cuantosComboBox.addItem((String)"Alemania");
        cuantosComboBox.addItem((String)"Francia");
        cuantosComboBox.addItem((String)"Irlanda");
        cuantosComboBox.addItem((String)"Arbacete");
        cuantosComboBox.addItem((String)"Murcia");
        JMenuBar menuBar = new JMenuBar();
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem acercaDe = new JMenuItem("Acerca de");
        JMenuItem salir = new JMenuItem("Salir");
        JMenuItem borrar = new JMenuItem("Borrar archivo de guardado");
        menuBar.add(archivoMenu);
        archivoMenu.add(acercaDe);
        archivoMenu.add(borrar);
        archivoMenu.add(salir);
        this.setJMenuBar(menuBar);
        try {
            FileInputStream fis = new FileInputStream("puerto.dat");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            p1 = (puerto) entrada.readObject(); //es necesario el casting
            fis.close(); // no es obligatorio pero es recomendable
            entrada.close(); // no es obligatorio pero es recomendable
        } catch (Exception e) {
            //Si el fichero no existe y aparece un error se crea el Puerto con el constructor por defecto
            p1 = new puerto();

        }
        mostrarPlanoDelHubTextArea.setText(p1.toString(0));
        //AL PRESIONAR ACERCA DE EN EL MENÚ
        acercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Creado por: \nAlejandro Romero Lorenzo\nAlejandro González Cortijo\nRicardo David Villamar Monroy\nCarlos Martínez Pérez\nJuan Luis Toledo Gómez\n\nFundamentos de Programación II\nEscuela Superior de Ingeniería Informática\nUniversidad de Castilla-La Mancha");
            }
        });
        //AL PRESIONAR SALIR EN EL MENÚ
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
        });
        //AL PRESIONAR BORRAR EN EL MENÚ
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File aBorrar = new File("puerto.dat");
                aBorrar.delete();
                JOptionPane.showMessageDialog(null, "Se reiniciará el programa para aplicar los cambios");
                dispose();
                MainFrame.main(null);
            }
        });

        //PRESIONAR BOTON APILAR
        apilarButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                //comprobación y set prioridad
                int prioridad;
                if(a1RadioButton.isSelected()){
                    prioridad=1;
                }
                else if(a2RadioButton.isSelected()){
                    prioridad=2;
                }
                else prioridad=3;

                int id = Integer.parseInt(campo_ID.getText());
                int peso = Integer.parseInt(campo_Peso.getText());
                String s = (String)paisComboBox.getSelectedItem();
                contenedor aux = new contenedor(id, peso, prioridad, s, campo_Desc.getText(), campo_Remitente.getText(), campo_Receptora.getText(), inspeccionadoEnAduanasCheckBox.isSelected());

                if(p1.apilarContenedor(aux, hubSelected())){
                    JOptionPane.showMessageDialog(null, "Se ha apilado el contenedor correctamente");
                }
                else{
                    JOptionPane.showMessageDialog(null, "El hub está lleno");
                }
                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                    fos.close();
                    salida.close();
                } catch (Exception ex) {
                    // si aparece un error se muestra en pantalla el tipo de error
                    ex.printStackTrace();
                }

                mostrarPlanoDelHubTextArea.setText(p1.toString(hubSelected()));
                //contenedor aux=new contenedor();
            }
        });
        //PRESIONAR BOTON DESAPILAR
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.desapilar(hubSelected(), Integer.parseInt(númeroDeColumnaTextField.getText()));
                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                    fos.close();
                    salida.close();
                } catch (Exception ex) {
                    // si aparece un error se muestra en pantalla el tipo de error
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Contenedor desapilado correctamente");
                mostrarPlanoDelHubTextArea.setText(p1.toString(hubSelected()));
            }

        });
        //PRESIONAR RADIOBUTTON 1

        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a1RadioButton.isSelected()==false){
                    a1RadioButton.setSelected(true);
                }
                else {
                    a2RadioButton.setSelected(false);
                    a3RadioButton.setSelected(false);
                    //JOptionPane.showMessageDialog(null, "Has presionado Prioridad 1");
                }
            }
        });

        //PRESIONAR RADIOBUTTON 2

        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a2RadioButton.isSelected()==false){
                    a2RadioButton.setSelected(true);
                }
                else {
                    a1RadioButton.setSelected(false);
                    a3RadioButton.setSelected(false);
                    //JOptionPane.showMessageDialog(null, "Has presionado Prioridad 2");
                }
            }
        });

        //PRESIONAR RADIOBUTTON 3

        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a3RadioButton.isSelected()==false){
                    a3RadioButton.setSelected(true);
                }
                else {
                    a1RadioButton.setSelected(false);
                    a2RadioButton.setSelected(false);
                    //JOptionPane.showMessageDialog(null, "Has presionado Prioridad 3");
                }
            }
        });
        //PRESIONAR INSPECCIONADO EN ADUANAS
        inspeccionadoEnAduanasCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if(inspeccionadoEnAduanasCheckBox.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has marcado que el contenedor ha sido inspeccionado en aduanas");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Has marcado que el contenedor NO ha sido inspeccionado en aduanas");
                }*/
            }
        });
        //PRESIONAR BOTON CUANTOS

        cuántosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String)paisComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null,"Hay "+p1.contadorPaises(hubSelected(), s)+" contenedores que provienen de "+s);
            }
        });

        //PRESIONAR MOSTRAR DATOS CONTENEDOR

        mostrarDatosContenedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int aux=Integer.parseInt(IDContenedorTextField.getText());


                MainFrame2 test2 = new MainFrame2(p1.datosAPartirDeId(hubSelected(), aux));
            }
        });

        //PRESIONAR HUB1
        hub1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hub1RadioButton.isSelected()==false){
                    hub1RadioButton.setSelected(true);
                }
                else {
                    hub2RadioButton.setSelected(false);
                    hub3RadioButton.setSelected(false);
                }
                mostrarPlanoDelHubTextArea.setText(p1.toString(0));
            }

        });

        //PRESIONAR HUB2
        hub2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hub2RadioButton.isSelected()==false){
                    hub2RadioButton.setSelected(true);
                }
                else {
                    hub1RadioButton.setSelected(false);
                    hub3RadioButton.setSelected(false);
                }
                mostrarPlanoDelHubTextArea.setText(p1.toString(1));
            }
        });

        //PRESIONAR HUB3
        hub3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hub3RadioButton.isSelected()==false){
                    hub3RadioButton.setSelected(true);
                }
                else {
                    hub1RadioButton.setSelected(false);
                    hub2RadioButton.setSelected(false);
                }
                mostrarPlanoDelHubTextArea.setText(p1.toString(2));
            }
        });

        //QUITAR TEXTO Número de columna
        númeroDeColumnaTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(númeroDeColumnaTextField.getText().equals("Número de columna")){
                    númeroDeColumnaTextField.setText("");
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(númeroDeColumnaTextField.getText().equals("")){
                    númeroDeColumnaTextField.setText("Número de columna");
                }
            }
        });

        //QUITAR TEXTO ID Contenedor
        IDContenedorTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(IDContenedorTextField.getText().equals("ID Contenedor")){
                    IDContenedorTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(IDContenedorTextField.getText().equals("")){
                    IDContenedorTextField.setText("ID Contenedor");
                }
            }
        });


    }
    private int hubSelected(){
        int hubNumber;
        if(hub1RadioButton.isSelected()){
            hubNumber=0;
        }
        else if(hub2RadioButton.isSelected()){
            hubNumber=1;
        }
        else hubNumber=2;

        return hubNumber;
    }
    public static void main(String[] args) {
        MainFrame test = new MainFrame();
        
    }
}
