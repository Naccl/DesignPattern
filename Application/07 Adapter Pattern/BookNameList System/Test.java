/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class Test {
	public static void main(String[] args) {
		BookNameList oldBookList = new BookNameList();
		oldBookList.setBookName();
		Enumeration bookenum = oldBookList.getEnumeration();
		EnumTolterAdapter adapter = new EnumTolterAdapter(bookenum);

		NewBookList newBookList = new NewBookList((java.util.Iterator) adapter);
		newBookList.setBookName();
		System.out.println("导入到新系统中的图书列表");
		newBookList.getBookName();
	}
}
