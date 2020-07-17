package island;

import tools.Gender;
import tools.Namber;

public abstract class GameObject {
	protected Gender gender;
	protected Namber number;
	protected String name;
	protected String description;

	public GameObject(Gender gender, Namber number, String name, String description) {
		this.gender = gender;
		this.number = number;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getNormalName() {
		return gender.getNormal(number) + " " + name;
	}

	public String getOnlyName() {
		return gender.getOnly(number) + " " + name;
	}

	public String getTermination() {
		return gender.getTermination(number);
	}

	public String getLocationPrefix() {
		return gender.getLocation();
	}

	public String getDescription() {
		return description;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameObject other = (GameObject) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
