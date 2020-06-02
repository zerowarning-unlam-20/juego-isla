package tools;

public enum DamageType {
	HACK("H"), SLASH("S"), BLUNT("B"), SHOT("S");

	private final String value;

	DamageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
