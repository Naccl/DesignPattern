public class Deposit extends BankWithHook {
	void doBusiness() {
		System.out.println("存款");
	}

	void judgeOrder() {
		System.out.println("评分");
	}
}
