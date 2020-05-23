package tools;

public enum ItemType {
	UNBREAKABLE("U"), WOOD("F"), STEM("S");

	private final String value;

	ItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
