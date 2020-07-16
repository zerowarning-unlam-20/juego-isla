package states;

import entities.Attack;

public interface State {
	public boolean open(String objectName);

	public boolean unlock(String toUnlockName, String keyName);

	public boolean look(String objectName);

	public boolean goTo(String locationName);

	public boolean grab(String itemName);

	public boolean grab(String sourceName, String itemName);

	public boolean grab(String itemName, String sourceName, String containerName);

	public boolean lookAround();

	public boolean lookInventory();

	public boolean attack(String weaponName, String targetName);

	public State receiveAttack(Attack attack);

	public boolean talk(String otherName, String messageName);

	public boolean listen(String otherName, String messageName);

	public boolean use(String itemName);

	public boolean read(String itemName);

	public boolean inspect(String itemName);

	public void lookState();

	public State drink(String name, String dispenserName);

	public boolean drop(String item);

	public State eat(String name);
}
