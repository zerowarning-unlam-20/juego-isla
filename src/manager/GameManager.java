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
import entities.NPC;
import entities.UserCharacter;
import island.Location;
import items.Access;
import items.Item;
import items.Liquid;
import items.SingleContainer;
import items.Weapon;
import tools.DamageType;
import tools.Gender;
import tools.IdManager;
import tools.MessageType;
import tools.NPCType;
import tools.WordBuilder;

public class GameManager {
	private Game game;
	private WordBuilder wordBuilder;
	private HashMap<String, ActionCommand> actionCommands;
	private boolean testMode;
	private static List<String> messageHistory = new ArrayList<String>();

	public GameManager(boolean testMode) {
		wordBuilder = new WordBuilder("words.wds");
		messageHistory = new ArrayList<String>();
		this.testMode = testMode;
	}

	public GameManager() {
		wordBuilder = new WordBuilder("words.wds");
		messageHistory = new ArrayList<String>();
	}

	public void setGame(Game game) {
		reset();
		this.game = game;
	}

	public void consoleRun() {
		loadExample();
		loadCommands();

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
		this.actionCommands = null;
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
		String cmd = strCommand.next();
		ActionCommand action = actionCommands.get(wordBuilder.getWord(cmd));
		if (action != null) {
			action.perform(strCommand);
		}
	}

	public void loadExample() {
		Item llave = new Item(IdManager.getNext(), Gender.F, "llave", "Llave de bronce", null);
		ArrayList<Item> itemsHabitacion = new ArrayList<>();
		itemsHabitacion.add(llave);

		SingleContainer botella = new SingleContainer(IdManager.getNext(), Gender.F, "botella", "Botella de vidrio");
		Liquid cerveza = new Liquid(IdManager.getNext(), Gender.F, "cerveza", "No es light", true);
		botella.setContent(cerveza);
		ArrayList<Item> itemsSalida = new ArrayList<>();
		itemsSalida.add(botella);

		Location habitacion = new Location(IdManager.getNext(), Gender.F, "habitacion",
				"Habitacion chica, con una llave en el suelo", true, itemsHabitacion);
		Location salida = new Location(IdManager.getNext(), Gender.F, "salida", "Una birra nomas", true, itemsSalida);
		Access a1p2 = new Access(IdManager.getNext(), Gender.F, "puerta", "puerta de madera", true, false,
				salida.getId(), llave.getId());
		ArrayList<Access> accesosHabitacion = new ArrayList<>();
		accesosHabitacion.add(a1p2);

		Access a2p1 = new Access(IdManager.getNext(), Gender.F, "puerta", "puerta de madera", true, false,
				habitacion.getId(), llave.getId());
		ArrayList<Access> accesosSalida = new ArrayList<>();
		accesosSalida.add(a2p1);

		for (Access access : accesosHabitacion) {
			habitacion.addAccess(access);
		}

		for (Access access : accesosSalida) {
			salida.addAccess(access);
		}

		habitacion.addLink(salida);

		ArrayList<Location> locations = new ArrayList<>();
		locations.add(habitacion);
		locations.add(salida);

		Weapon crossbow = new Weapon(99, Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);

		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(crossbow);
		UserCharacter player = new UserCharacter(this, 0, Gender.M, "Test", "Test_desc", habitacion, inventory); // id
																													// 0

		NPC tree = new NPC(this, 50, Gender.F, "Palmera", "Palmera_test", habitacion, NPCType.PASSIVE, 0);
		habitacion.addNpc(tree);
		game = new Game(player, locations);
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

}
