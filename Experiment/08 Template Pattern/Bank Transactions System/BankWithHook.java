public abstract class BankWithHook {

	void start() {
		getNumber();
		doBusiness();
		if (hook()){
			judgeOrder();
		}
	}

	void getNumber() {
		System.out.println("取号排队");
	}

	abstract void doBusiness();

	void judgeOrder() {
		System.out.println("评分");
	}

	boolean hook() {
		return true;
	}

}
