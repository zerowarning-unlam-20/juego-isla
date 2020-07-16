package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.WorldLoader;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.event.*; //Para trabajar con eventos
import java.awt.event.*;

public class AdventureSelection extends JFrame implements ActionListener{

	private String nombre;
	private AdventureSelection aventuraFrame;
	private JPanel contentPane;
	private JTextField aventurero;
	private JTextField txtElijaSuAventura_1;
	private JComboBox comboBox;
	private JButton btnNewButton;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdventureSelection AventuraFrame = new AdventureSelection();
//					AventuraFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdventureSelection(String nombre, String gender) {
		setTitle("ZeroWarning - Zork - Adventure Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setBounds(100, 100, 419, 277);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aventurero = new JTextField();
		aventurero.setFont(new Font("Consolas", Font.PLAIN, 27));
		aventurero.setEditable(false);
		aventurero.setBounds(32, 42, 304, 42);
		contentPane.add(aventurero);
		aventurero.setColumns(10);
		aventurero.setText("Hola " + nombre + ",");
		
		comboBox = new JComboBox();
		comboBox.setBounds(32, 184, 304, 33);
		contentPane.add(comboBox);
		
		ArrayList<String> games = (ArrayList<String>) WorldLoader.listFolders(new File("games"));
		
		for(String game : games) {
			comboBox.addItem(game);
		}
		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(347, 184, 62, 33);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		txtElijaSuAventura_1 = new JTextField();
		txtElijaSuAventura_1.setText("elija su aventura:");
		txtElijaSuAventura_1.setFont(new Font("Consolas", Font.PLAIN, 27));
		txtElijaSuAventura_1.setEditable(false);
		txtElijaSuAventura_1.setColumns(10);
		txtElijaSuAventura_1.setBounds(105, 106, 304, 42);
		contentPane.add(txtElijaSuAventura_1);
	}
	
	public static void runFrame(String nombre, String gender) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdventureSelection AventuraFrame = new AdventureSelection(nombre, gender);
					AventuraFrame.setLocationRelativeTo(null);
					AventuraFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	  @Override
	  public void actionPerformed(ActionEvent e){
		  
	    //Si clic en boton  => abrir la ventana principal del juego
	    if(e.getSource() == btnNewButton){
	    	
	        GameInterface ventanaPrincipal = new GameInterface();
	        //ventanaPrincipal.setResizable(false);
	        ventanaPrincipal.setLocationRelativeTo(null);
	        ventanaPrincipal.setVisible(true);
	        this.setVisible(false);
	        
	    } 
	    
	  } 
	
	
}