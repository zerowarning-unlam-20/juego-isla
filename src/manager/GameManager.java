package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;

import commands.ActionCommand;
import commands.AttackCommand;
import commands.DrinkCommand;
import commands.DropCommand;
import commands.EatCommand;
import commands.GoCommand;
import commands.GrabCommand;
import commands.InspectCommand;
import commands.LookCommand;
import commands.OpenCommand;
import commands.ReadCommand;
import commands.TalkCommand;
import commands.UnlockCommand;
import commands.UseCommand;
import entities.Entity;
import entities.NPC;
import tools.Gender;
import tools.MessageType;
import tools.WordBuilder;
import tools.WorldLoader;
import tools.ui.Sound;

public class GameManager {
	private Game game;
	private WordBuilder wordBuilder;
	private String helpCommands;
	private HashMap<String, ActionCommand> actionCommands;
	private boolean testMode;
	private boolean consoleMode;
	private int turn;
	private static List<Message> messageHistory = new ArrayList<>();
	private String lastCommand;
	private String currentCommand;
	private boolean gameOver;
	private Sound soundManager;

	private DefaultListModel<String> mensajes = new DefaultListModel<String>();
	private String msgInterface = "";

	public GameManager(boolean consoleMode, boolean testMode) {
		try {
			lastCommand = "";
			helpCommands = WorldLoader.getHelpCommands();
			wordBuilder = new WordBuilder("words.json");
		} catch (IOException e) {
			System.out.println("Error archivos iniciales: " + e.getMessage());
			System.exit(-1);
		}
		messageHistory = new ArrayList<Message>();
		this.testMode = testMode;
		this.consoleMode = consoleMode;
	}

	public GameManager(boolean consoleMode) {
		try {
			lastCommand = "";
			helpCommands = WorldLoader.getHelpCommands();
			wordBuilder = new WordBuilder("words.json");
		} catch (IOException e) {
			System.out.println("Error archivos iniciales: " + e.getMessage());
			System.exit(-1);
		}
		messageHistory = new ArrayList<Message>();
	}

	public void setInternalGame(Game game) {
		reset();
		this.game = game;
	}

	public void consoleRun() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		currentCommand = "";
		consoleMode = true;
		while (currentCommand != "salir del juego") {
			try {
				currentCommand = reader.readLine().toLowerCase();
			} catch (IOException e) {
				System.out.println("Error en entrada: " + e.getMessage());
				System.exit(-1);
			}
			sendCommand(currentCommand);
		}
	}

	public void interfaceRun(String msg) {
		msgInterface = "";
		sendCommand(msg.toLowerCase());
	}

	public void reset() {
		turn = 1;
		game = null;
	}

	public void loadCommands() {
		actionCommands = new HashMap<>();
		actionCommands.put("drink", new DrinkCommand(game.getCharacter()));
		actionCommands.put("go", new GoCommand(game.getCharacter()));
		actionCommands.put("grab", new GrabCommand(game.getCharacter()));
		actionCommands.put("look", new LookCommand(game.getCharacter()));
		actionCommands.put("open", new OpenCommand(game.getCharacter()));
		actionCommands.put("unlock", new UnlockCommand(game.getCharacter()));
		actionCommands.put("attack", new AttackCommand(game.getCharacter()));
		actionCommands.put("use", new UseCommand(game.getCharacter()));
		actionCommands.put("inspect", new InspectCommand(game.getCharacter()));
		actionCommands.put("read", new ReadCommand(game.getCharacter()));
		actionCommands.put("talk", new TalkCommand(game.getCharacter()));
		actionCommands.put("drop", new DropCommand(game.getCharacter()));
		actionCommands.put("eat", new EatCommand(game.getCharacter()));
	}

	private void processCommand(Scanner strCommand) {
		if (strCommand.hasNext()) {
			String cmd = strCommand.next();
			ActionCommand action = actionCommands.get(wordBuilder.getWord(cmd));
			if (action != null) {
				action.perform(strCommand);
				turn++;
			} else
				sendMessage(MessageType.EVENT, null, "Que?");
		}
	}

	public void sendMessage(MessageType type, String otherName, String mes) {
		String content = mes.replaceAll("a el", "al");
		Message message = null;
		if (testMode == false) {
			switch (type.getValue()) {
			case ("E"):
				message = new Message(content, type);
				if (consoleMode)
					System.out.println("//" + content + "//");
				else {
					setMsgInterface("//" + content + "//");
				}
				break;
			case ("C"):
				message = new Message(otherName + ": " + content, type);
				if (consoleMode)
					System.out.println(otherName + ": " + content);
				else {
					setMsgInterface(otherName + ": " + content);
				}
				break;
			case ("S"):
				message = new Message(content, type);
				if (consoleMode)
					System.out.println(content);
				else {
					setMsgInterface(content);
				}
				break;
			}
		}
		messageHistory.add(message);
//		System.out.println(mensajes);
//		System.out.println((messageHistory.get(messageHistory.size()-1).getContent()));
//		System.out.println("ASMDÑLASD");

//		if(messageHistory.get(messageHistory.size()-1).getMessageType().getValue().equals("E"))
//			setMsgInterface("//" + messageHistory.get(messageHistory.size()-1).getContent() + "//");//
//		else if(messageHistory.get(messageHistory.size()-1).getMessageType().getValue().equals("C"))
//			setMsgInterface(otherName + ": " + messageHistory.get(messageHistory.size()-1).getContent());//	
//		else if(messageHistory.get(messageHistory.size()-1).getMessageType().getValue().equals("S"))
//			setMsgInterface(messageHistory.get(messageHistory.size()-1).getContent());//				
//		else
//			setMsgInterface(otherName + ": " + messageHistory.get(messageHistory.size()-1).getContent());//	

	}

	public List<Message> getMessageHistory() {
		return messageHistory;
	}

	public boolean isTestMode() {
		return testMode;
	}

	/*
	 * public void loadGame(String folder) { WorldLoader worldLoader = new
	 * WorldLoader(folder); try { reset(); game = new Game(this,
	 * worldLoader.loadCharacter(), worldLoader.loadLocations(),
	 * worldLoader.loadEntities(), worldLoader.loadEvents()); loadCommands();
	 * sendMessage(MessageType.STORY, "game", worldLoader.loadInitialMessage());
	 * game.getCharacter().lookAround(); } catch (IOException e) {
	 * System.out.println("Error al cargar el juego" + e.getMessage());
	 * System.exit(-1); } }
	 */
	public void loadGame(String folder, String name, Gender gender) {
		WorldLoader worldLoader = new WorldLoader(folder);
		try {
			reset();

			game = new Game(this, name, gender, worldLoader.loadCharacter(), worldLoader.loadLocations(),
					worldLoader.loadEntities(), worldLoader.loadEvents());
			loadCommands();
			sendMessage(MessageType.STORY, "game", worldLoader.loadInitialMessage());
			mensajes.addElement(" ");
			game.getCharacter().lookAround();

			try {
				Sound.filePath = "sounds/" + folder + ".wav";
				soundManager = new Sound();
				soundManager.play();
			} catch (Exception ex) {
				System.out.println("Error sonido: " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Error al cargar el juego" + e.getMessage());
			System.exit(-1);
		}
	}

	//
	/*
	 * public void loadGameInterface(String folder, String name, Gender gender) {
	 * WorldLoader worldLoader = new WorldLoader(folder); try { reset();
	 * 
	 * /*game = new Game(this, worldLoader.loadCharacter(),
	 * worldLoader.loadLocations(), worldLoader.loadEntities(),
	 * worldLoader.loadEvents());
	 * 
	 * game = new Game(this, name, gender, worldLoader.loadCharacter(),
	 * worldLoader.loadLocations(), worldLoader.loadEntities(),
	 * worldLoader.loadEvents());
	 * 
	 * loadCommands(); sendMessage(MessageType.STORY, null,
	 * worldLoader.loadInitialMessage()); //game.getCharacter().lookAround(); }
	 * catch (IOException e) { System.out.println("Error al cargar el juego" +
	 * e.getMessage()); System.exit(-1); } }
	 */

	public Game getGame() {
		return game;
	}

	public void setTestMode(boolean value) {
		testMode = value;
	}

	public int getTurn() {
		return turn;
	}

	public void sendCommand(String value) {
		String normalize = Normalizer.normalize(value, Normalizer.Form.NFD);
		value = normalize.replaceAll("[^\\p{ASCII}]", "");
		if (value.trim().equalsIgnoreCase("rc")) {
			Scanner scanner = new Scanner(lastCommand);
			processCommand(scanner);
			scanner.close();
			return;
		} else {
			lastCommand = value;
		}
		if (value.trim().equalsIgnoreCase("pausar musica")) {
			soundManager.pause();
			sendMessage(MessageType.STORY, null, "Musica pausada");
		} else if (value.trim().equalsIgnoreCase("ayuda") || value.trim().equalsIgnoreCase("help")) {
			sendMessage(MessageType.STORY, null, helpCommands);
		} else
			processCommand(new Scanner(value));
	}
	//
	//

	public void endGame() {
		for (Entity entity : game.getEntities().values()) {
			if (entity instanceof NPC && ((NPC) entity).getEntityListener() != null) {
				((NPC) entity).getEntityListener().onEntityDisappeared(game.getCharacter());
			}
		}
		reset();
		gameOver = true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public Sound getSoundManager() {
		return soundManager;
	}

	////////////////////////////////////////////////////////////////////

	public String getMsgInterface() {
		return msgInterface;
	}

	public void setMsgInterface(String msgInterface) {

		if (msgInterface == "")
			this.msgInterface = msgInterface;
		else
			this.msgInterface += msgInterface + "\n";
	}

	public DefaultListModel<String> getMensajes() {
		return mensajes;
	}

}
