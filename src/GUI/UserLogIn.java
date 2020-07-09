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

public class UserLogIn extends JFrame {

	private static UserLogIn frame;
	private JPanel contentPane;
	private JTextField txtIngreseSuNombre;
	private JTextField username;
	private JTextField txtGenero;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UserLogIn();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtIngreseSuNombre = new JTextField();
		txtIngreseSuNombre.setEditable(false);
		txtIngreseSuNombre.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtIngreseSuNombre.setText("INGRESE SU NOMBRE");
		txtIngreseSuNombre.setBounds(64, 41, 247, 58);
		contentPane.add(txtIngreseSuNombre);
		txtIngreseSuNombre.setColumns(10);

		username = new JTextField();
		username.setBounds(64, 112, 201, 30);
		contentPane.add(username);
		username.setColumns(10);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = username.getText();
				if (user.equals("")) user = "Player";
				frame.setVisible(false);
				AdventureSelection.runFrame(user, (String) comboBox.getSelectedItem());
			}
		});
		btnNewButton.setBounds(64, 210, 116, 30);
		contentPane.add(btnNewButton);

		txtGenero = new JTextField();
		txtGenero.setEditable(false);
		txtGenero.setText("Genero");
		txtGenero.setBounds(0, 165, 56, 22);
		contentPane.add(txtGenero);
		txtGenero.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		comboBox.setToolTipText("M\r\nF");
		comboBox.setBounds(64, 165, 43, 22);
		contentPane.add(comboBox);
	}
}
