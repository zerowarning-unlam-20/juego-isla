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

public class UserLogIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngreseSuNombre;
	private JTextField username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogIn frame = new UserLogIn();
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
		txtIngreseSuNombre.setBounds(64, 70, 247, 58);
		contentPane.add(txtIngreseSuNombre);
		txtIngreseSuNombre.setColumns(10);
		
		username = new JTextField();
		username.setBounds(64, 159, 201, 30);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = username.getSelectedText();
				if(user != "") {
					
				}
			}
		});
		btnNewButton.setBounds(277, 159, 116, 30);
		contentPane.add(btnNewButton);
	}
}
