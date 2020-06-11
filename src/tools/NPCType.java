package tools;

public enum NPCType {
	PASSIVE("P"), AGGRESSIVE("A"), INANIMATED("I"); // Stem es liana

	private final String value;

	NPCType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}