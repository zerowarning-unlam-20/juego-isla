package tools;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * Esto es para blueprint, como no iba a servir con nombres únicos esto era mas
 * copado, imaginate que agarras una soga de casualidad y despues una liana, vas
 * a usar una o las dos?
 *
 */

public enum ResourceType {

	@SerializedName("wood")
	WOOD("madera"), @SerializedName("rope")
	ROPE("cuerda"), @SerializedName("stone")
	STONE("piedra"), @SerializedName("food")
	FOOD("comida");

	private final String value;

	ResourceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}