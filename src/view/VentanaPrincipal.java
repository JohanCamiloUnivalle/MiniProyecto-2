package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import controller.Controlador;
import model.Music;

public class VentanaPrincipal extends JFrame {

    private String colorBola;
    private JLabel L_laberinto;
    private JLabel L_creators;
    private JLabel L_color;
    private JButton btnSalir;
    private JTextField TFnombre;
    private Fondo fondo;
    private static VentanaNivel1 nivel1;
    public JButton btnIniciar;
    public JComboBox<String> listaColores;
    public static String jugadorLab;

    public VentanaPrincipal() {
        super("Menú Principal");
        configVentana();
        iniciarLabels();
        iniciarBotones();
        iniciarTextFields();
        iniciarVentana2();
        iniciarColor();
        cerrarJuego();
    }

    // Configuracion de la ventana
    public void configVentana() {
        fondo = new Fondo();
        this.setContentPane(fondo);
        this.setSize(350, 380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Crear etiquetas
    public void iniciarLabels() {
        L_laberinto = new JLabel("Laberinto");
        L_laberinto.setOpaque(true);
        L_laberinto.setBounds(80, 30, 200, 40);
        L_laberinto.setFont(new Font("Arial Black", 1, 30));
        L_laberinto.setHorizontalAlignment(SwingConstants.CENTER);
        L_laberinto.setBackground(new Color(255, 255, 255, 0));
        this.add(L_laberinto);

        L_creators = new JLabel("By: Los tocayos & Dylan");
        L_creators.setOpaque(true);
        L_creators.setBounds(182, 325, 200, 30);
        L_creators.setFont(new Font("Arial", 0, 11));
        L_creators.setHorizontalAlignment(SwingConstants.CENTER);
        L_creators.setBackground(new Color(255, 255, 255, 0));
        this.add(L_creators);

        L_color = new JLabel("Color :");
        L_color.setOpaque(true);
        L_color.setBounds(93, 162, 60, 25);
        L_color.setFont(new Font("Arial Black", 0, 15));
        L_color.setHorizontalAlignment(SwingConstants.CENTER);
        L_color.setBackground(new Color(255, 255, 255, 0));
        this.add(L_color);
    }

    // Crear Botones
    public void iniciarBotones() {
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setOpaque(true);
        btnIniciar.setBounds(125, 220, 110, 35);
        btnIniciar.setFont(new Font("Arial Black", 0, 20));
        btnIniciar.setHorizontalAlignment(SwingConstants.CENTER);
        btnIniciar.setBackground(new Color(240, 240, 240));
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(btnIniciar);

        btnSalir = new JButton("Salir");
        btnSalir.setOpaque(true);
        btnSalir.setBounds(125, 280, 110, 35);
        btnSalir.setFont(new Font("Arial Black", 0, 20));
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setBackground(new Color(240, 240, 240));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(btnSalir);
    }

    // Crear caja de texto
    public void iniciarTextFields() {
        TFnombre = new JTextField(10);
        TFnombre.setBounds(105, 105, 150, 35);
        TextPrompt placeholder = new TextPrompt("Ingrese su sombre", TFnombre);
        placeholder.setFont(new Font("Arial", 2, 13));
        placeholder.setForeground(Color.lightGray);
        this.add(TFnombre);
    }

    // Seleccion de color
    public void iniciarColor() {
        String[] colores = { "Rojo", "Verde", "Azul", "Amarillo", "Magenta" };
        listaColores = new JComboBox<>(colores);
        listaColores.setBounds(155, 162, 120, 30);
        listaColores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listaColores.setBackground(new Color(255, 255, 255));
        listaColores.setFont(new Font("Arial", 0, 13));
        this.add(listaColores);
    }

    // iniciar la segunda ventana
    public void iniciarVentana2() {
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                colorBola = (String) listaColores.getSelectedItem();
                jugadorLab = TFnombre.getText();
                if (jugadorLab.length() == 0) {
                    jugadorLab = "User";
                }

                nivel1 = new VentanaNivel1(jugadorLab);

                // Controlador
                Controlador controlador = new Controlador(nivel1, colorBola, jugadorLab);
                nivel1.getLienzo().addKeyListener(controlador);

                controlador.conectar();
                while(Tablero.getcantJugadores()<1) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "ESPERANDO JUGADORES...");
                } // error
                Music musica = new Music();
                musica.AudioIniciar();
                nivel1.setVisible(true); 
            }
        });
    }

    // Cerrar el juego
    public void cerrarJuego() {
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "¡Gracias por jugar! :)");
                System.exit(0);
            }
        });
    }

    public String getColorBola() {
        return colorBola;
    }

    public static void finJuego() {
        nivel1.setVisible(false);
    }
}