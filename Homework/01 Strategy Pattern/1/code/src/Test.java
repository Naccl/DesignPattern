public class Test {
	public static void main(String[] args) {
		King k = new King();

		k.setWeapon(new AxeBehavior());
		System.out.println(k.fight());

		k.setWeapon(new BowAndArrowBehavior());
		System.out.println(k.fight());

		k.setWeapon(new KnifeBehavior());
		System.out.println(k.fight());

		k.setWeapon(new SwordBehavior());
		System.out.println(k.fight());


		Queen q = new Queen();

		q.setWeapon(new AxeBehavior());
		System.out.println(q.fight());

		q.setWeapon(new BowAndArrowBehavior());
		System.out.println(q.fight());

		q.setWeapon(new KnifeBehavior());
		System.out.println(q.fight());

		q.setWeapon(new SwordBehavior());
		System.out.println(q.fight());
	}
}
