package items;

import tools.DamageType;
import tools.Gender;

public class Weapon extends Item {
	private Double damage;
	private DamageType damageType;

	public Weapon(int id, Gender gender, String name, String description, DamageType damageType, Double damage) {
		super(id, gender, name, description, null);
		this.damageType = damageType;
		this.damage = damage;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public Double getDamage() {
		return damage;
	}

}
