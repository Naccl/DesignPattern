import java.util.Iterator;
import java.util.LinkedList;

public class NewBookList {

	private LinkedList bookList;
	private Iterator iterator;

	public NewBookList(Iterator iterator) {
		bookList = new LinkedList<String>();
		this.iterator = iterator;
	}

	public void setBookName() {
		while (iterator.hasNext()){
			String name = (String) iterator.next();
			bookList.add(name);
		}
	}

	public void getBookName() {
		Iterator<String> iter = bookList.iterator();
		while (iter.hasNext()){
			String name = iter.next();
			System.out.println(name);
		}
	}

}
