public class Lion implements Beast, Action {

	public void act() {
		System.out.println("The Lion jumped up!");
	}


	/**
	 * @see Beast#update(int)
	 */
	public void update(int count) {
		System.out.println("count = " + count);
		act();
	}

}
