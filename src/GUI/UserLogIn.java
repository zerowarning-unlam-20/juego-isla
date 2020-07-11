package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.event.*; //Para trabajar con eventos
import java.awt.event.*;


public class UserLogIn extends JFrame implements ActionListener{ // ChangeListener

	private static UserLogIn frame;
	private JPanel contentPane;
	private JTextField txtIngreseSuNombre;
	private JTextField username;
	private JTextField txtGenero;
	private JComboBox comboBox;
	private JButton btnNewButton;
	
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
	 */
	public UserLogIn() {
		setTitle("ZeroWarning - Zork - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
	  
	
}
