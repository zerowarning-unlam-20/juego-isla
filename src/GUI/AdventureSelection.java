package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;

public class AdventureSelection extends JFrame {

	private JPanel contentPane;
	private JTextField txtElijaSuAventura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdventureSelection frame = new AdventureSelection();
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
	public AdventureSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtElijaSuAventura = new JTextField();
		txtElijaSuAventura.setFont(new Font("Consolas", Font.PLAIN, 27));
		txtElijaSuAventura.setEditable(false);
		txtElijaSuAventura.setText("Elija su aventura:");
		txtElijaSuAventura.setBounds(12, 67, 304, 42);
		contentPane.add(txtElijaSuAventura);
		txtElijaSuAventura.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(12, 154, 304, 33);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(327, 154, 62, 33);
		contentPane.add(btnNewButton);
	}
}
