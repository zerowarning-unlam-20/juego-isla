package entities;

import tools.DamageType;

public class Attack {
	private Double damage;
	private Entity attacker;
	private DamageType damageType;

	public Attack(Double damage, Entity attacker, DamageType damageType) {
		this.damage = damage;
		this.attacker = attacker;
		this.damageType = damageType;
	}

	public Double getDamage() {
		return damage;
	}

	public Entity getAttacker() {
		return attacker;
	}

	public DamageType getDamageType() {
		return damageType;
	}
}
