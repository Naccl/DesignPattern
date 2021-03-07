public class Tiger implements Beast, Action {

	public void act() {
		System.out.println("The Tiger jumped up!");
	}


	/**
	 * @see Beast#update(int)
	 */
	public void update(int count) {
		System.out.println("count = " + count);
		act();
	}

}
