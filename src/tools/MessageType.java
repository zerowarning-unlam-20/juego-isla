package tools;

public enum MessageType {
	EVENT("E"), CHARACTER("C"), STORY("S");

	private final String value;

	MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
