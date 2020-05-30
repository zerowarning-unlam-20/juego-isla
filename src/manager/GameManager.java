package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import commands.ActionCommand;
import commands.Command;
import commands.DrinkCommand;
import commands.GoCommand;
import commands.GrabCommand;
import commands.LookCommand;
import commands.OpenCommand;
import commands.UnlockCommand;
import entities.UserCharacter;
import island.GameObject;
import items.Access;
import items.Item;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import tools.Gender;
import tools.IdManager;
import tools.ItemType;

public class GameManager {
	private Game game;
	private List<ActionCommand> actionCommands;
	private static boolean testMode;
	private static List<String> messageHistory = new ArrayList<String>();

	public GameManager() {
		messageHistory = new ArrayList<String>();
	}

	public void setGame(Game game) {
		reset();
		this.game = game;
	}

	public void run() {
		loadExample();
		loadCommands();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String comando = "";

		while (comando != "-") {
			try {
				comando = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
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
		try {
			ActionCommand[] commands = { (ActionCommand) new DrinkCommand(game.getCharacter()),
					(ActionCommand) new GoCommand(game.getCharacter()),
					(ActionCommand) new GrabCommand(game.getCharacter()),
					(ActionCommand) new LookCommand(game.getCharacter()),
					(ActionCommand) new OpenCommand(game.getCharacter()), new UnlockCommand(game.getCharacter()) };
			actionCommands = Arrays.asList(commands);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processCommand(Scanner strCommand) {
		String cmd = strCommand.next();
		for (ActionCommand command : actionCommands)
			if (command.getClass().getAnnotation(Command.class).value().equals(cmd)) {
				command.perform(strCommand);
				break;
			}
	}

	public void loadExample() {
		Item llave = new Item(IdManager.getNext(), Gender.F, "llave", "Llave de bronce", ItemType.UNBREAKABLE);
		ArrayList<Item> itemsHabitacion = new ArrayList<>();
		itemsHabitacion.add(llave);

		SingleContainer botella = new SingleContainer(IdManager.getNext(), Gender.F, "botella", "Botella de vidrio",
				ItemType.UNBREAKABLE);
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

		game = new Game(new UserCharacter(habitacion), locations);
	}

	public static void sendMessage(GameObject sender, String message) {
		getMessageHistory().add(message);
		if (testMode == false) {
			System.out.println(sender.getName() + ": " + message);
		}
	}

	public static List<String> getMessageHistory() {
		return messageHistory;
	}

	public static void setTestMode(boolean testMode) {
		GameManager.testMode = testMode;
	}

}
