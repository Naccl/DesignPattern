public class Test {
	public static void main(String[] args) {
		Bookstore bs = new Bookstore();

		ComputerBook c1 = new ComputerBook(100);
		ComputerBook c2 = new ComputerBook(200);

		LanguageBook l1 = new LanguageBook(100);
		LanguageBook l2 = new LanguageBook(200);

		NovelBook n1 = new NovelBook(100);
		NovelBook n2 = new NovelBook(200);

		bs.addPrice(c1.getPrice());
		bs.addPrice(c2.getPrice());
		bs.addPrice(l1.getPrice());
		bs.addPrice(l2.getPrice());
		
		double priceNovelBook = n1.getPrice() + n2.getPrice();
		bs.addPrice(priceNovelBook - (int)priceNovelBook / 100 * 10);
		
		System.out.println(bs.getPrice());
	}
}
