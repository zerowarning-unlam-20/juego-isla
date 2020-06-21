package tools;

import com.google.gson.annotations.SerializedName;

public enum MessageType {
	@SerializedName("e")
	EVENT("E"),
	@SerializedName("c")
	CHARACTER("C"),
	@SerializedName("s")
	STORY("S");

	private final String value;

	MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
