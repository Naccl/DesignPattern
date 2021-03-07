public class WithdrawMoney extends BankWithHook {
	void doBusiness() {
		System.out.println("取款");
	}

	void judgeOrder() {
		System.out.println("评分");
	}
}
