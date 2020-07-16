package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.*; //Para trabajar con eventos


import java.awt.event.*;


public class UserLogIn extends JFrame implements ActionListener{ // ChangeListener

	private static UserLogIn frame;
	private JPanel contentPane;
	private JPanel panelImagen;
	private JTextField txtIngreseSuNombre;
	private JTextField username;
	private JTextField txtGenero;
	private JComboBox comboBox;
	private JButton btnNewButton;


	private ImageIcon fondo;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UserLogIn();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public UserLogIn() throws IOException {
		setTitle("ZeroWarning - Zork - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
				

		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
				
		fondo = new ImageIcon("fondo.gif");
		JLabel picLabel = new JLabel();
//		ImageIcon imageIcon = new ImageIcon("fondo.gif");
		picLabel.setIcon(fondo);
		picLabel.setBounds(0, 0, 600, 600);
		//picLabel.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH)));
		fondo.setImageObserver(picLabel);
		//contentPane.add(picLabel);
		
		
		
		
		
		txtIngreseSuNombre = new JTextField();
		txtIngreseSuNombre.setEditable(false);
		txtIngreseSuNombre.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtIngreseSuNombre.setText("Ingrese su Nombre:");
		txtIngreseSuNombre.setBounds(64, 41, 247, 31);
		contentPane.add(txtIngreseSuNombre);
		txtIngreseSuNombre.setColumns(10);

		username = new JTextField();
		username.setFont(new Font("Consolas", Font.PLAIN, 14));
		username.setBounds(86, 82, 201, 30);
		
		contentPane.add(username);
		username.setColumns(10);
		
		//<foco en textfield
		addWindowListener( new WindowAdapter() {
		       public void windowOpened( WindowEvent e ){
		           username.requestFocus();
		       }
		}); 
		

		
		username.addActionListener(this);	 // Accion a tomar <==> se presiona el ENTER ==> INGRESAR TEXTO

		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(292, 209, 116, 30);
		
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String user = username.getText();
//				if (user.equals("")) user = "Player";
//				frame.setVisible(false);
//				AdventureSelection.runFrame(user, (String) comboBox.getSelectedItem());
//			}
//		});
		btnNewButton.addActionListener(this);
		//btnNewButton.setEnabled(false); //Deshabilitar inicialmente este boton
		contentPane.add(btnNewButton);

		txtGenero = new JTextField();
		txtGenero.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtGenero.setEditable(false);
		txtGenero.setText("Seleccione un Genero:");
		txtGenero.setBounds(64, 123, 247, 31);
		contentPane.add(txtGenero);
		txtGenero.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Consolas", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		comboBox.setToolTipText("M\r\nF");
		comboBox.setBounds(86, 165, 36, 30);
		contentPane.add(comboBox);
	}
	
//	  @Override
//	  public void stateChanged(ChangeEvent e){
//		  
//		  if(username.getText() != null && this.username.getText().equals(" ")) //Deshabilitar inicialmente este boton
//			  this.btnNewButton.setEnabled(false);
//		  else
//			  this.btnNewButton.setEnabled(true);
//
//	  }

	  @Override
	  public void actionPerformed(ActionEvent e){
	    //Si clic en boton  => hacer...
	    if(username.getText() != null && !this.username.getText().equals("")){ //e.getSource() == btnNewButton
			String user = username.getText();
			if (user.equals("")) user = "Player";
			frame.setVisible(false);
			AdventureSelection.runFrame(user, (String) comboBox.getSelectedItem());
	    } 
	  } 
	  
	  //////////////////////////////////////////////////////////////////////////////////////////

	
}
