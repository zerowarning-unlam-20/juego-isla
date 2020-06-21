package tools;

import com.google.gson.annotations.SerializedName;

public enum DamageType {
	@SerializedName("H")  // Axe
	HACK("H"),
	@SerializedName("SL") // Dagger
	SLASH("SL"),
	@SerializedName("B") // Club, hammer
	BLUNT("B"),
	@SerializedName("SH") // Pistol
	SHOT("SH");

	private final String value;

	DamageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
