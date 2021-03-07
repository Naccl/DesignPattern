public class Scene {
	public static void main(String[] args) {
		Person tom = new Person("Tom");

		Gold gold = new Gold(500);
		Diamond diamond = new Diamond(100);

		Tiger[] tigers = new Tiger[5];
		for (int i = 0; i < 5; i++) {
			tigers[i] = new Tiger();
		}

		Lion[] lions = new Lion[7];
		for (int i = 0; i < 7; i++) {
			lions[i] = new Lion();
		}
		gold.registerGuard(tigers[0]);
		gold.registerGuard(tigers[1]);
		gold.registerGuard(tigers[2]);
		gold.registerGuard(lions[0]);
		gold.registerGuard(lions[1]);
		gold.registerGuard(lions[2]);

		diamond.registerGuard(tigers[3]);
		diamond.registerGuard(tigers[4]);
		diamond.registerGuard(lions[3]);
		diamond.registerGuard(lions[4]);
		diamond.registerGuard(lions[5]);
		diamond.registerGuard(lions[6]);

		tom.take(gold, 1);
		tom.take(gold, 0.5);
		tom.take(diamond, 1);
	}
}
