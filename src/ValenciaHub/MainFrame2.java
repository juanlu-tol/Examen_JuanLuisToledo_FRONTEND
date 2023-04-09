package ValenciaHub;

import javax.swing.*;
import ValenciaHubBackEnd_Hub.*;

public class MainFrame2 extends JFrame {
    private JTextField campo_ID;
    private JTextField campo_peso;
    private JTextArea campo_desc;
    private JTextField campo_remitente;
    private JTextField campo_receptora;
    private JTextField campo_pais;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JPanel mainPanel2;

    public MainFrame2(contenedor ContenedorAMostrar) {
        setContentPane(mainPanel2);
        setTitle("Gestión de contenedores - Ver detalles del contenedor");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        mainPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        campo_ID.setText(String.valueOf(ContenedorAMostrar.getId()));
        campo_peso.setText(String.valueOf(ContenedorAMostrar.getPeso()));
        campo_desc.setText(ContenedorAMostrar.getContenido());
        campo_remitente.setText(ContenedorAMostrar.getEmisor());
        campo_receptora.setText(ContenedorAMostrar.getReceptor());
        campo_pais.setText(ContenedorAMostrar.getPais());
        if(ContenedorAMostrar.isAduanas()){
            inspeccionadoEnAduanasCheckBox.setSelected(true);
        }
        if(ContenedorAMostrar.getPrioridad()==1){
            a1RadioButton.setSelected(true);
        }
        else if(ContenedorAMostrar.getPrioridad()==2){
            a2RadioButton.setSelected(true);
        }
        else a3RadioButton.setSelected(true);

    }
}

