public class Transfer extends BankWithHook {
	void doBusiness() {
		System.out.println("转账");
	}

	void judgeOrder() {
		System.out.println("评分");
	}

	boolean hook() {
		return false;
	}
}
