package tools;

import com.google.gson.annotations.SerializedName;

public enum NPCType {
	@SerializedName("passive") // No pega
	PASSIVE("passive"),

	@SerializedName("aggressive") // Pega
	AGGRESSIVE("aggressive"),

	@SerializedName("inanimated") // Plantas, cadaveres y esas cosas que no respiran
	INANIMATED("inanimated");

	private final String value;

	NPCType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
