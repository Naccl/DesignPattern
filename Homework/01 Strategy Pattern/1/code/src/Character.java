public class Character {

	public WeaponBehavior weapon;

	public String fight() {
		return "Character fight " + weapon.useWeapon();
	}

	public void setWeapon(WeaponBehavior w) {
		this.weapon = w;
	}
}
