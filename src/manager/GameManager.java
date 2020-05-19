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
import entities.Access;
import entities.UserCharacter;
import entities.Container;
import entities.IdManager;
import entities.Item;
import entities.Liquid;
import entities.Location;
import entities.Tool;

public class GameManager {
	private UserCharacter character;
	private List<Location> map;
	private List<ActionCommand> actionCommands;

	public GameManager() {
		map = new ArrayList<>();
		loadGame();
	}

	public GameManager(UserCharacter character, List<Location> locations) {
		loadGame(character, locations);
	}

	private void loadGame() {
		try {
			loadExample();
			character = new UserCharacter(map.get(0));
			ActionCommand[] commands = { (ActionCommand) new DrinkCommand(character),
					(ActionCommand) new GoCommand(character), (ActionCommand) new GrabCommand(character),
					(ActionCommand) new LookCommand(character), (ActionCommand) new OpenCommand(character),
					new UnlockCommand(character) };
			actionCommands = Arrays.asList(commands);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadGame(UserCharacter character, List<Location> locations) {
		this.character = character;
		map = locations;
		for (Location location : locations) {
			for (Location otherLocation : locations) {
				if (location != otherLocation) {
					location.addLink(otherLocation);
				}
			}
		}
	}

	public void recieveCommand(String command) {
		if (command != "-") {
			Scanner cmdScanner = new Scanner(command);
			System.out.println(processCommand(cmdScanner));
		}
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
			System.out.println(processCommand(cmdScanner));
		}
	}

	public void reset() {

	}

	private String processCommand(Scanner strCommand) {
		String stringToReturn = null;
		String cmd = strCommand.next();
		
		for (ActionCommand command : actionCommands) {
			
			if (command.getClass().getAnnotation(Command.class).value().equals(cmd)) {
				stringToReturn = command.perform(strCommand);
				break;
			}
			
		}
		
		return (stringToReturn != null)? stringToReturn : "Que es eso";
	}

	private void loadExample() {
		IdManager idManager = new IdManager();
		Item llave = new Tool(idManager.getNext(), "llave", "Llave de bronce", true);
		ArrayList<Item> itemsHabitacion = new ArrayList<>();
		itemsHabitacion.add(llave);

		Container botella = new Container(idManager.getNext(), "botella", "Botella de vidrio", true);
		Liquid cerveza = new Liquid(idManager.getNext(), "cerveza", "No es light", true);
		botella.setContent(cerveza);
		ArrayList<Item> itemsSalida = new ArrayList<>();
		itemsSalida.add(botella);

		Location habitacion = new Location(idManager.getNext(), "habitacion",
				"Habitacion chica, con una llave en el suelo", true, itemsHabitacion);
		Location salida = new Location(idManager.getNext(), "salida", "Una birra nomas", true, itemsSalida);
		Access a1p2 = new Access(idManager.getNext(), "puerta", "puerta de madera", true, true, false, salida.getId(),
				llave.getId());
		ArrayList<Access> accesosHabitacion = new ArrayList<>();
		accesosHabitacion.add(a1p2);
		
		Access a2p1 = new Access(idManager.getNext(), "puerta", "puerta de madera", true, true, false, habitacion.getId(),
				llave.getId());
		ArrayList<Access> accesosSalida = new ArrayList<>();
		accesosSalida.add(a2p1);

		habitacion.setAccesses(accesosHabitacion);
		salida.setAccesses(accesosSalida);

		habitacion.addLink(salida);

		map.add(habitacion);
		map.add(salida);
	}

	public UserCharacter getCharacter() {
		return character;
	}
}
