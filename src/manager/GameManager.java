package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import commands.ActionCommand;
import commands.DrinkCommand;
import commands.GoCommand;
import commands.GrabCommand;
import commands.HitCommand;
import commands.LookCommand;
import commands.OpenCommand;
import commands.UnlockCommand;
import entities.Entity;
import tools.MessageType;
import tools.WordBuilder;
import tools.WorldLoader;

public class GameManager {
	private Game game;
	private WordBuilder wordBuilder;
	private HashMap<String, ActionCommand> actionCommands;
	private boolean testMode;
	private static List<String> messageHistory = new ArrayList<String>();

	public GameManager(boolean testMode) {
		try {
			wordBuilder = new WordBuilder("words.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageHistory = new ArrayList<String>();
		this.testMode = testMode;
	}

	public GameManager() {
		try {
			wordBuilder = new WordBuilder("words.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageHistory = new ArrayList<String>();
	}

	public void setInternalGame(Game game) {
		reset();
		this.game = game;
	}

	public void consoleRun() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String comando = "";

		while (comando != "-") {
			try {
				comando = reader.readLine().toLowerCase();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			Scanner cmdScanner = new Scanner(comando);
			processCommand(cmdScanner);
		}
	}

	public void reset() {
		this.game = null;
	}

	private void loadCommands() {
		actionCommands = new HashMap<>();
		actionCommands.put("drink", new DrinkCommand(game.getCharacter()));
		actionCommands.put("go", new GoCommand(game.getCharacter()));
		actionCommands.put("grab", new GrabCommand(game.getCharacter()));
		actionCommands.put("look", new LookCommand(game.getCharacter()));
		actionCommands.put("open", new OpenCommand(game.getCharacter()));
		actionCommands.put("unlock", new UnlockCommand(game.getCharacter()));
		actionCommands.put("hit", new HitCommand(game.getCharacter()));
	}

	private void processCommand(Scanner strCommand) {
		if (strCommand.hasNext()) {
			String cmd = strCommand.next();
			ActionCommand action = actionCommands.get(wordBuilder.getWord(cmd));
			if (action != null) {
				action.perform(strCommand);
			}
		}
	}

	public void sendMessage(MessageType type, Entity sender, String message) {
		getMessageHistory().add(message);
		if (testMode == false) {
			switch (type.getValue()) {
			case ("E"):
				System.out.println("//" + message + "//");
				break;
			case ("C"):
				System.out.println(sender.getName() + ": " + message);
				break;
			case ("S"):
				System.out.println(sender.getName() + message);
				break;
			}
		}
	}

	public static List<String> getMessageHistory() {
		return messageHistory;
	}

	public boolean isTestMode() {
		return testMode;
	}

	public void loadGame(String folder) {
		try {
			reset();
			game = new Game(this, WorldLoader.loadCharacter(folder), WorldLoader.loadLocations(folder),
					WorldLoader.loadEntities(folder));
			loadCommands();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Game getGame() {
		return game;
	}

}
