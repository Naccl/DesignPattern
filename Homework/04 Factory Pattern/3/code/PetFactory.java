public class PetFactory {

	public Pet produce(String voice) {
		if ("quack".equals(voice)) {
			System.out.println("produce Duck...");
			return new Duck();
		} else if ("gugu".equals(voice)) {
			System.out.println("produce Pigeon...");
			return new Pigeon();
		}
		return null;
	}

}
