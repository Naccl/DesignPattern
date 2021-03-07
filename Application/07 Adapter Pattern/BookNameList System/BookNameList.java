import java.util.Vector;

public class BookNameList {

	private Vector<String> vector;
	private Enumeration bookenum;

	public BookNameList() {
		vector = new Vector<>();
	}

	public void setBookName() {
		vector.add("Java程序设计");
		vector.add("J2ME程序设计");
		vector.add("XML程序设计");
		vector.add("JSP程序设计");
	}

	public Enumeration getEnumeration() {
		return (Enumeration) vector.elements();
	}

}
