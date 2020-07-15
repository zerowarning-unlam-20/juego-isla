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
	
   //
	
	private JButton btnNewButton;
	private GameManager game;
	private JLabel imagen;
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
//		textArea.setBounds(12, 13, 408, 197);
		scroll.setBounds(12, 13, 780, 197);
		//contentPane.add(textArea);
		contentPane.add(scroll);
		
		
		textField = new JTextField();
		textField.setBounds(12, 224, 304, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);	 // Accion a tomar <==> se presiona el ENTER ==> INGRESAR TEXTO
		
		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(323, 223, 110, 40);
		btnNewButton.addActionListener(this);// Accion a tomar <==> se presiona el bot�n OK ==> INGRESAR TETXO
		
		//dise�o boton
		ImageIcon enviar = new ImageIcon(".\\imagenes\\boton.png");
		Icon iconEnviar = new ImageIcon(enviar.getImage().getScaledInstance(btnNewButton.getWidth()+12,btnNewButton.getHeight(), Image.SCALE_FAST));
		btnNewButton.setIcon(iconEnviar);
		//btnNewButton.setEnabled(false); //Deshabilitar inicialmente este boton
		contentPane.add(btnNewButton);
		
		
		/////////////agregados
		JLabel gif = new JLabel(); //gif
		gif.setBounds(0, 300, 790, 450);
		ImageIcon islaGif = new ImageIcon(".\\imagenes\\fondo.gif");
		Icon iconoIsla = new ImageIcon(islaGif.getImage().getScaledInstance(gif.getWidth()+10, gif.getHeight(), Image.SCALE_DEFAULT));
		gif.setIcon(iconoIsla);
		
		contentPane.add(gif);
		
		//colores
		contentPane.setBackground(new Color(80,30,0));
		textArea.setBackground(new Color(102,51,0));
		textArea.setForeground(Color.white);
		
		//<foco en textfield
		addWindowListener( new WindowAdapter() {
		       public void windowOpened( WindowEvent e ){
		           textField.requestFocus();
		       }
		}); 
		/////////////////
		
		inicializarJuego(name, gender, adventure);
	}
	
//	  @Override
//	  public void stateChanged(ChangeEvent e){
//		  
//		  if(this.textField.getText().equals(""))
//			  this.btnNewButton.setEnabled(false);
//		  else
//			  this.btnNewButton.setEnabled(true);
//
//	  }

	  @Override
	  public void actionPerformed(ActionEvent e){
	    //Si clic en boton  => hacer...
		 String ingresoUsuario = textField.getText();		 		// Obtener el texto desde la textFiel
		 
		 if(ingresoUsuario != null && !ingresoUsuario.isEmpty()){
 				 
			textArea.append(ingresoUsuario +  "\n"); 	//Mostrar el mensaje enviado en la pantalla
			game.interfaceRun(ingresoUsuario);  //le envio el comando a game manager
			textArea.append(game.getMsgInterface()+ "\n\n");
			this.textField.setText("");
		} 
	  }


	  
	  public void inicializarJuego(String name, String gender, String adventure) {
			game = new GameManager(false,false);
			game.loadGameInterface(adventure,name, Gender.valueOf(gender));	
			
			textArea.append(game.getMsgInterface()); //Mensaje de intro
			game.getGame().getCharacter().lookAround();
			textArea.append(game.getMsgInterface()+ "\n");

	  }


}
