package items.types;

import items.Item;
import items.properties.Holdable;
import tools.DamageType;
import tools.Gender;

public class Weapon extends Item implements Holdable {
	private Double damage;
	private DamageType damageType;

	public Weapon(Gender gender, String name, String description, int price, DamageType damageType, Double damage) {
		super(gender, name, description, price);
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
