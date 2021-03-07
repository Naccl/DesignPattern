/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-26
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("\n-----取款步骤-----");
		BankWithHook withdrawMoney = new WithdrawMoney();
		withdrawMoney.start();

		System.out.println("\n-----存款步骤-----");
		BankWithHook deposit = new Deposit();
		deposit.start();

		System.out.println("\n-----转账步骤-----");
		BankWithHook transfer = new Transfer();
		transfer.start();
	}
}
