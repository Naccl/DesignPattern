public class Test {
	public static void main(String[] args) {
		PetFactory petFactory = new PetFactory();
		Pet pet = petFactory.produce("quack");
		pet.makeSound();

		pet = petFactory.produce("gugu");
		pet.makeSound();
	}
}
