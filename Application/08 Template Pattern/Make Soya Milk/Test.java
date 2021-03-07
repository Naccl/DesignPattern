/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-26
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("\n-----制作纯豆浆步骤-----");
		SoyaMilkWithHook pureSoyaMilk = new PureSoyaMilk();
		pureSoyaMilk.prepareRecipe();

		System.out.println("\n-----制作红枣豆浆步骤-----");
		SoyaMilkWithHook reddatesSoyaMilk = new ReddatesSoyaMilk();
		reddatesSoyaMilk.prepareRecipe();

		System.out.println("\n-----制作核桃豆浆步骤-----");
		SoyaMilkWithHook nutSoyaMilk = new NutSoyaMilk();
		nutSoyaMilk.prepareRecipe();
	}
}
