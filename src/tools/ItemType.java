package tools;

public enum ItemType {
	KEY("K"), RESOURCE("R"), INFORMATION("I"), EDIBLE("E"), WEAPON("W"), TOOL("T");

	private final String value;

	ItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
