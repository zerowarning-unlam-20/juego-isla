package tests;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Area;
import island.Location;
import items.Access;
import items.Item;
import items.types.Weapon;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;
import tools.NPCType;

class ListenerTest {
	private GameManager gameManager;
	private Player player;
	private NPC npc;

	@BeforeEach
	void setUp() throws Exception {
		gameManager = new GameManager();
		// Load locations
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<String, Area>(),
				new HashMap<String, Access>());
		s1.addAccess(new Access(Gender.F, "Puerta", "Puerta azul", false, true, null, "s2", null, DamageType.HACK));
		Location s2 = new Location(Gender.M, "s2", "Segundo lugar", true, new HashMap<String, Area>(),
				new HashMap<String, Access>());
		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta azul", false, true, null, "s1", null, DamageType.HACK));

		HashMap<String, Location> locations = new HashMap<String, Location>();
		locations.put(s1.getName().toLowerCase(), s1);
		locations.put(s2.getName().toLowerCase(), s2);

		// NPC empty list (constructor purposes)
		HashMap<String, NPC> npcs = new HashMap<>();

		// Inventory
		HashMap<String, Item> inventory = new HashMap<>();

		player = new Player(gameManager, Gender.M, "Test", "Test_desc", inventory, "s1");
		HashMap<String, String> chat = new HashMap<>();
		chat.put("デフォールト", "Enemigo a la vista");

		npc = new NPC(gameManager, Gender.F, "Enemigo", "test", s2, new HashMap<>(), "s2", NPCType.AGGRESSIVE, null,
				"hacha");
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		npc.addItem(axe);
		s2.addEntity(npc);

		Game game = new Game(gameManager, player, locations, npcs, null);
		gameManager.setInternalGame(game);
		gameManager.loadCommands();
		gameManager.sendCommand("ir s2");
		gameManager.sendMessage(MessageType.EVENT, null, String.valueOf(gameManager.getTurn()));
		gameManager.sendCommand("ver alrededor");
		gameManager.sendMessage(MessageType.EVENT, null, String.valueOf(gameManager.getTurn()));
	}

	@Test
	void test() {

	}

}
