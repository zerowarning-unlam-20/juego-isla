package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
import items.Access;
import items.Item;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import items.Tool;
import tools.Gender;
import tools.IdManager;
import tools.ItemType;

public class GameManager {
	private static GameManager instance;
	private Game game;
	private List<ActionCommand> actionCommands;

	private GameManager() {
		loadExample();
		loadCommands();
	}

	static {
		try {
			instance = new GameManager();
		} catch (Exception e) {
			throw new RuntimeException("Exception creating singleton");
		}
	}

	public static GameManager getInstance() {
		return instance;
	}

	public void setGame(Game game) {
		reset();
		this.game = game;
	}

	public void run() {

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

	public void recieveMessage(String name, String message) {
		System.out.println(name + ": " + message);
	}

	private void loadExample() {
		IdManager idManager = new IdManager();
		Item llave = new Item(idManager.getNext(), Gender.F, "llave", "Llave de bronce", ItemType.UNBREAKABLE);
		ArrayList<Item> itemsHabitacion = new ArrayList<>();
		itemsHabitacion.add(llave);

		SingleContainer botella = new SingleContainer(idManager.getNext(), Gender.F, "botella", "Botella de vidrio",
				ItemType.UNBREAKABLE);
		Liquid cerveza = new Liquid(idManager.getNext(), Gender.F, "cerveza", "No es light", true);
		botella.setContent(cerveza);
		ArrayList<Item> itemsSalida = new ArrayList<>();
		itemsSalida.add(botella);

		Location habitacion = new Location(idManager.getNext(), Gender.F, "habitacion",
				"Habitacion chica, con una llave en el suelo", true, itemsHabitacion);
		Location salida = new Location(idManager.getNext(), Gender.F, "salida", "Una birra nomas", true, itemsSalida);
		Access a1p2 = new Access(idManager.getNext(), Gender.F, "puerta", "puerta de madera", true, false,
				salida.getId(), llave.getId());
		ArrayList<Access> accesosHabitacion = new ArrayList<>();
		accesosHabitacion.add(a1p2);

		Access a2p1 = new Access(idManager.getNext(), Gender.F, "puerta", "puerta de madera", true, false,
				habitacion.getId(), llave.getId());
		ArrayList<Access> accesosSalida = new ArrayList<>();
		accesosSalida.add(a2p1);

		habitacion.setAccesses(accesosHabitacion);
		salida.setAccesses(accesosSalida);

		habitacion.addLink(salida);

		ArrayList<Location> locations = new ArrayList<>();
		locations.add(habitacion);
		locations.add(salida);

		game = new Game(new UserCharacter(habitacion), locations);
	}
}
