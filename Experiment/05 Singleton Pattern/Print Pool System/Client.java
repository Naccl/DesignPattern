public class Client {
	public static void main(String[] args) {
		PrintPool printPool1, printPool2;
		printPool1 = PrintPool.getPrintPool();
		printPool2 = PrintPool.getPrintPool();

		System.out.println("printPool是否为同一个:" + (printPool1 == printPool2));

		printPool1.create("task1");
		System.out.println("task队列任务是否相同:" + (printPool1.getTask().peek() == printPool2.getTask().peek()));
		printPool1.pause();
		printPool1.update();
		printPool1.delete();
	}
}
