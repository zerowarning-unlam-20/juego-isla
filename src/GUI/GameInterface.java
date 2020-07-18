package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import manager.GameManager;
import tools.Gender;
import tools.WorldLoader;

public class GameInterface extends JFrame implements ActionListener { // implements ChangeListener

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
	private JList<String> mensajes;
	private String name;
	private String gender;
	private String currentAdventure;
	private HashMap<String, ArrayList<String>> adventureImagen = new HashMap<String, ArrayList<String>>();
	ArrayList<String> adventures = (ArrayList<String>) WorldLoader.listFolders(new File("games"));
	ArrayList<String> imagenes = (ArrayList<String>) WorldLoader.listFiles(new File("imagenes"));
	private ImageIcon previousImg;

	/**
	 ** Launch the application.
	 **/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameInterface frame = new GameInterface("Matias", "M", "Blue Hawaii");
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

		this.name = name;
		this.gender = gender;
		this.currentAdventure = adventure;
		cargarImg();
		setTitle("ZeroWarning - Zork");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 900, 800);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textAreaMsg();
		// jListMsg();
		// scroll = new JScrollPane(mensajes);
		textField();
		printGif(adventure);
		buttons();

		contentPane.setBackground(new Color(80, 30, 0));
		scroll = new JScrollPane(textArea);
		scroll.setBounds(12, 13, 880, 197);
		contentPane.add(textField);
		contentPane.add(scroll);

		inicializarJuego(name, gender, adventure);
		// mensajes.setModel(game.getMensajes());

	}

	private void cargarImg() {
		for (String s : adventures) {
			adventureImagen.put(s, new ArrayList<String>());
		}

		for (String img : imagenes) {
			for (String adv : adventures) {
				if ((img.substring(0, adv.length()).equals(adv))) {
					adventureImagen.get(adv).add(img);
				}
			}
		}

	}

	private void mostrarImg(String mensaje, String aventura) {
		ArrayList<String> imagenes = adventureImagen.get(aventura);
		ImageIcon image = null;
		String m;
		String s2;

		for (String s : imagenes) {
			m = mensaje.toLowerCase();
			s2 = s.toLowerCase();
			if (m.contains(s2.substring(aventura.length(), s2.length() - 4))) {
				image = new ImageIcon(".\\\\imagenes\\\\" + s2);
				previousImg = image;
			}

		}
		if (image == null)
			image = previousImg;
		Icon iconoIsla = new ImageIcon(
				image.getImage().getScaledInstance(gif.getWidth() + 10, gif.getHeight(), Image.SCALE_DEFAULT));

		gif.setIcon(iconoIsla);
		contentPane.add(gif);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Si clic en boton => hacer...
		String ingresoUsuario = textField.getText();
		String mensaje;// Obtener el texto desde la textFiel

		if (ingresoUsuario != null && !ingresoUsuario.isEmpty()) {

			textArea.append("\n" + "> " + ingresoUsuario + "\n"); // Mostrar el mensaje enviado en la pantalla
			game.interfaceRun(ingresoUsuario); // le envio el comando a game manager
			mensaje = game.getMsgInterface();
			textArea.append(mensaje + "\n");
			mostrarImg(mensaje, this.currentAdventure);

			// if(ingresoUsuario.split(" ")[0].equals("golpear"))
			// textArea.append(game.getMsgInterface()+ "\n");
			this.textField.setText("");
			this.textField.requestFocus();

//			game.getMensajes().addElement(" ");
//			game.getMensajes().addElement("-" + ingresoUsuario);
//			game.getMensajes().addElement(" ");

//			if(ingresoUsuario.split(" ")[0].equals("golpear"))
//				textArea.append(game.getMsgInterface()+ "\n");
//			textArea.append(game.getMessageHistory().get(game.getMessageHistory().size()-1).getContent() + "\n\n");
			// messageHistory.get(messageHistory.size()-1).getContent()
		}
	}

	public void inicializarJuego(String name, String gender, String adventure) {
		game = new GameManager(false, false);
		game.loadGame(adventure, name, Gender.valueOf(gender));
		textArea.append(game.getMsgInterface()); // Mensaje de intro
		// textArea.append(game.getMessageHistory().get(0).getContent() + "\n\n");
		// game.loadGameInterface(adventure,name, Gender.valueOf(gender));
		// game.getGame().getCharacter().lookAround();
		// textArea.append(game.getMsgInterface()+ "\n");
	}

	private void printGif(String adventure) {
		gif = new JLabel(); // gif
		gif.setBounds(0, 280, 900, 430);

		ImageIcon islaGif;

		if (adventure.equals("Blue Hawaii"))
			islaGif = new ImageIcon(".\\imagenes\\Blue HawaiiInicial.gif");
		else if (adventure.equals("Piratas"))
			islaGif = new ImageIcon(".\\imagenes\\PiratasInicial.gif");
		else
			islaGif = new ImageIcon(".\\imagenes\\storeInicial.gif");
		previousImg = islaGif;
		Icon iconoIsla = new ImageIcon(
				islaGif.getImage().getScaledInstance(gif.getWidth() + 10, gif.getHeight(), Image.SCALE_DEFAULT));

		gif.setIcon(iconoIsla);
		contentPane.add(gif);
	}

	private void buttons() {
		// enviar
		btnEnviar = new JButton();
		btnEnviar.setBounds(323, 223, 110, 40);
		btnEnviar.addActionListener(this);// Accion a tomar <==> se presiona el botòn OK ==> INGRESAR TETXO

		ImageIcon enviar = new ImageIcon(".\\imagenes\\BlueHawaiiboton.png");
		Icon iconEnviar = new ImageIcon(enviar.getImage().getScaledInstance(btnEnviar.getWidth() + 12,
				btnEnviar.getHeight(), Image.SCALE_FAST));
		btnEnviar.setIcon(iconEnviar);
		contentPane.add(btnEnviar);

		// salir
		btnSalir = new JButton();
		btnSalir.setBounds(140, 715, 110, 50);

		btnSalir.addActionListener(e -> {
			this.setVisible(false);
			this.dispose();
			System.exit(0);
		});

		ImageIcon salir = new ImageIcon(".\\imagenes\\BlueHawaiisalir.png");
		Icon iconSalir = new ImageIcon(
				salir.getImage().getScaledInstance(btnSalir.getWidth() + 12, btnSalir.getHeight(), Image.SCALE_FAST));
		btnSalir.setIcon(iconSalir);
		contentPane.add(btnSalir);

		// atras
		btnAtras = new JButton();
		btnAtras.setBounds(20, 715, 110, 50);

		btnAtras.addActionListener(e -> {
			this.setVisible(false);
			AdventureSelection adventure;
			try {
				adventure = new AdventureSelection(this.name, this.gender);
				adventure.setLocationRelativeTo(null);
				adventure.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.game.interfaceRun("pausar musica");
			this.dispose();
		});

		ImageIcon atras = new ImageIcon(".\\imagenes\\BlueHawaiiatras.png");
		Icon iconAtras = new ImageIcon(
				atras.getImage().getScaledInstance(btnAtras.getWidth() + 12, btnAtras.getHeight(), Image.SCALE_FAST));
		btnAtras.setIcon(iconAtras);
		contentPane.add(btnAtras);
	}

	private void textAreaMsg() {
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(100, 100, 600, 500);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(102, 51, 0));
		textArea.setForeground(Color.white);
	}

	private void jListMsg() {
		mensajes = new JList<String>();
		mensajes.setVisible(true);
		mensajes.setBackground(new Color(102, 51, 0));
		mensajes.setForeground(Color.white);
	}

	private void textField() {
		textField = new JTextField();
		textField.setBounds(12, 224, 304, 40);
		textField.setColumns(10);
		textField.addActionListener(this); // Accion a tomar <==> se presiona el ENTER ==> INGRESAR TEXTO
		// foco en textfield
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textField.requestFocus();
			}
		});
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
}
