public class IdentityCardNo {

	private String no;

	private static IdentityCardNo instance = null;

	private IdentityCardNo() {

	}

	public static IdentityCardNo getInstance() {
		if (instance == null) {
			System.out.println("第一次办理身份证，分配新号码...");
			instance = new IdentityCardNo();
			instance.setIndentityCardNo("NO400011112222");
		} else {
			System.out.println("重复办理身份证，获取旧号码...");
		}
		return instance;
	}

	private void setIndentityCardNo(String no) {
		this.no = no;
	}

	public String getIdentityCardNo() {
		return no;
	}

}
