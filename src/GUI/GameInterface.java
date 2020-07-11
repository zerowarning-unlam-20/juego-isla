package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.event.*; //Para trabajar con eventos

import manager.GameManager;
import tools.Gender;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class GameInterface extends JFrame implements ActionListener{ //implements ChangeListener

	private JScrollPane scroll;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnNewButton;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GameInterface frame = new GameInterface();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GameInterface(String name, String gender, String adventure) {
		inicializarJuego(name, gender, adventure);
		setTitle("ZeroWarning - Zork");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scroll = new JScrollPane(textArea);
//		textArea.setBounds(12, 13, 408, 197);
		scroll.setBounds(12, 13, 408, 197);
		//contentPane.add(textArea);
		contentPane.add(scroll);
		
		
		textField = new JTextField();
		textField.setBounds(12, 224, 304, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);	 // Accion a tomar <==> se presiona el ENTER ==> INGRESAR TEXTO
		
		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(323, 223, 97, 25);
		btnNewButton.addActionListener(this);// Accion a tomar <==> se presiona el botòn OK ==> INGRESAR TETXO
		//btnNewButton.setEnabled(false); //Deshabilitar inicialmente este boton
		contentPane.add(btnNewButton); 
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
		 String ingresoUsuario = textField.getText();		 		// Obtener el texto desde la textField
		 if(ingresoUsuario != null && !ingresoUsuario.isEmpty()){
			textArea.append(ingresoUsuario +  "\n"); 	//Mostrar el mensaje enviado en la pantalla
			this.textField.setText("");
		} 
	  } 
	  
	  public void inicializarJuego(String name, String gender, String adventure) {
		  
			GameManager game = new GameManager(false, false); //descative consola
			//deberiamos setear el nombre de jugador
			game.loadGame(adventure,name, Gender.valueOf(gender));
			
	  }
}
