public abstract class SoyaMilkWithHook {

	final void prepareRecipe() {
		selectMaterial();
		if (hook()) {
			addCondiments();
		}
		soak();
		beat();
	}

	void selectMaterial() {
		System.out.println("选择好了新鲜黄豆");
	}

	abstract void addCondiments();

	void soak() {
		System.out.println("材料开始浸泡");
	}

	void beat() {
		System.out.println("材料放到豆浆机打碎");
	}

	boolean hook() {
		return true;
	}

}
