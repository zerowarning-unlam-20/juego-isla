package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.event.*; //Para trabajar con eventos

import manager.GameManager;
import tools.Gender;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class GameInterface extends JFrame implements ActionListener{ //implements ChangeListener
	
	private JScrollPane scroll;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnEnviar;
	
   //
	private JButton btnAtras;
	private JButton btnSalir;
	private GameManager game;
	private JLabel gif;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameInterface frame = new GameInterface("Matias","M","Blue Hawaii");
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameInterface(String name, String gender, String adventure) {
		
		
		setTitle("ZeroWarning - Zork");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 800, 800);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(100, 100, 600, 500);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		
		scroll = new JScrollPane(textArea);
		scroll.setBounds(12, 13, 780, 197);
		//contentPane.add(textArea);
		contentPane.add(scroll);
		
		
		textField = new JTextField();
		textField.setBounds(12, 224, 304, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);	 // Accion a tomar <==> se presiona el ENTER ==> INGRESAR TEXTO
		//foco en textfield
		addWindowListener( new WindowAdapter() {
		       public void windowOpened( WindowEvent e ){
		           textField.requestFocus();
		       }
		}); 
		
		btnEnviar = new JButton();
		btnEnviar.setBounds(323, 223, 110, 40);
		btnEnviar.addActionListener(this);// Accion a tomar <==> se presiona el botòn OK ==> INGRESAR TETXO
		
		//diseño boton enviar
		ImageIcon enviar = new ImageIcon(".\\imagenes\\boton.png");
		Icon iconEnviar = new ImageIcon(enviar.getImage().getScaledInstance(btnEnviar.getWidth()+12,btnEnviar.getHeight(), Image.SCALE_FAST));
		btnEnviar.setIcon(iconEnviar);
		contentPane.add(btnEnviar);
		


		
		
		/////////////agregados
		gif = new JLabel(); //gif
		gif.setBounds(0, 280, 790, 430);
		ImageIcon islaGif = new ImageIcon(".\\imagenes\\fondo.gif");
		Icon iconoIsla = new ImageIcon(islaGif.getImage().getScaledInstance(gif.getWidth()+10, gif.getHeight(), Image.SCALE_DEFAULT));
		gif.setIcon(iconoIsla);
		contentPane.add(gif);
		
		//colores
		contentPane.setBackground(new Color(80,30,0));
		textArea.setBackground(new Color(102,51,0));
		textArea.setForeground(Color.white);
		
		//boton atras
		btnAtras = new JButton();
		btnAtras.setBounds(20, 715, 110, 50);
			
		btnAtras.addActionListener(e -> {
			   this.setVisible(false);
		});
		
		ImageIcon atras = new ImageIcon(".\\imagenes\\atras.png");
		Icon iconAtras = new ImageIcon(atras.getImage().getScaledInstance(btnAtras.getWidth()+12,btnAtras.getHeight(), Image.SCALE_FAST));
		btnAtras.setIcon(iconAtras);
		contentPane.add(btnAtras);
		
		//boton salir del juego
		btnSalir = new JButton();
		btnSalir.setBounds(140, 715, 110, 50);
		
		btnSalir.addActionListener(e -> {
			   this.setVisible(false);
		});
		
		ImageIcon salir = new ImageIcon(".\\imagenes\\salir.png");
		Icon iconSalir = new ImageIcon(salir.getImage().getScaledInstance(btnSalir.getWidth()+12,btnSalir.getHeight(), Image.SCALE_FAST));
		btnSalir.setIcon(iconSalir);
		contentPane.add(btnSalir);
		/////////////////
		
		inicializarJuego(name, gender, adventure);
	}
	
//	  @Override
//	  public void stateChanged(ChangeEvent e){
//		  
//		  if(this.textField.getText().equals(""))
//			  this.btnEnviar.setEnabled(false);
//		  else
//			  this.btnEnviar.setEnabled(true);
//
//	  }

	  @Override
	  public void actionPerformed(ActionEvent e){
	    //Si clic en boton  => hacer...
		 String ingresoUsuario = textField.getText();		 		// Obtener el texto desde la textFiel
		 
		 if(ingresoUsuario != null && !ingresoUsuario.isEmpty()){
 				 
			textArea.append("\n" + ingresoUsuario +  "\n"); 	//Mostrar el mensaje enviado en la pantalla
			game.interfaceRun(ingresoUsuario);  //le envio el comando a game manager
			textArea.append(game.getMsgInterface()+ "\n\n");
			this.textField.setText("");
		} 
	  }


	  
	  public void inicializarJuego(String name, String gender, String adventure) {
			game = new GameManager(false,false);
			game.loadGame(adventure,name, Gender.valueOf(gender));
			textArea.append(game.getMsgInterface()); //Mensaje de intro
			//game.loadGameInterface(adventure,name, Gender.valueOf(gender));	
			//game.getGame().getCharacter().lookAround();
			//textArea.append(game.getMsgInterface()+ "\n");

	  }


}
