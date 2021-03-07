public class EnumTolterAdapter implements Iterator {

	private Enumeration bookenum;

	public EnumTolterAdapter(Enumeration bookenum) {
		this.bookenum = bookenum;
	}

	public boolean hasNext() {
		return bookenum.hasMoreElements();
	}

	public Object next() {
		return bookenum.nextElement();
	}

	public void remove() {
		System.out.println("枚举器没有删除集合元素的方法");
	}

}
