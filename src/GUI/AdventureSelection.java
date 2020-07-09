package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.WorldLoader;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.List;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

public class AdventureSelection extends JFrame {

	private String nombre;
	private AdventureSelection aventuraFrame;
	private JPanel contentPane;
	private JTextField aventurero;
	private JTextField txtElijaSuAventura_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aventurero = new JTextField();
		aventurero.setFont(new Font("Consolas", Font.PLAIN, 27));
		aventurero.setEditable(false);
		aventurero.setBounds(12, 23, 304, 42);
		contentPane.add(aventurero);
		aventurero.setColumns(10);
		aventurero.setText("Hola " + nombre + ",");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 154, 304, 33);
		contentPane.add(comboBox);
		
		ArrayList<String> games = (ArrayList<String>) WorldLoader.listFolders(new File("games"));
		
		for(String game : games) {
			comboBox.addItem(game);
		}
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(327, 154, 62, 33);
		contentPane.add(btnNewButton);
		
		txtElijaSuAventura_1 = new JTextField();
		txtElijaSuAventura_1.setText("elija su aventura:");
		txtElijaSuAventura_1.setFont(new Font("Consolas", Font.PLAIN, 27));
		txtElijaSuAventura_1.setEditable(false);
		txtElijaSuAventura_1.setColumns(10);
		txtElijaSuAventura_1.setBounds(12, 78, 304, 42);
		contentPane.add(txtElijaSuAventura_1);
	}
	
	public static void runFrame(String nombre, String gender) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdventureSelection AventuraFrame = new AdventureSelection(nombre, gender);
					AventuraFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}