package tools;

import com.google.gson.annotations.SerializedName;

public enum Gender {
	@SerializedName("M")
	M("M"), @SerializedName("F")
	F("F");

	private final String value;
	private final String singular;
	private final String plural;
	private final String onlySingular;
	private final String onlyPlural;
	private final String terminationSingular;
	private final String terminationPlural;

	private final String genderSingular;
	private final String genderPlural;

	private final String location;

	Gender(String value) {
		this.value = value;
		singular = (value == "M") ? "el" : "la";
		plural = (value == "M") ? "los" : "las";

		onlySingular = (value == "M") ? "un" : "una";
		onlyPlural = (value == "M") ? "unos" : "unas";

		terminationSingular = (value == "M") ? "o" : "a";
		terminationPlural = (value == "M") ? "os" : "as";

		genderSingular = (value == "M") ? "el" : "ella";
		genderPlural = (value == "M") ? "ellos" : "ellas";

		location = (value == "M") ? "l" : " la";
	}

	public String getNormal(Namber number) {
		switch (number) {
		case S:
			return singular;
		case P:
			return plural;
		}
		return null;
	}

	public String getOnly(Namber number) {
		switch (number) {
		case S:
			return onlySingular;
		case P:
			return onlyPlural;
		}
		return null;
	}

	public String getGender(Namber number) {
		switch (number) {
		case S:
			return genderSingular;
		case P:
			return genderPlural;
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public String getTermination(Namber number) {
		switch (number) {
		case S:
			return terminationSingular;
		case P:
			return terminationPlural;
		}
		return null;
	}

	public String getLocation() {
		return location;
	}
}