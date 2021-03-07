import java.util.LinkedList;
import java.util.Queue;

public class PrintPool {

	private static PrintPool printPool = null;
	private Queue<Task> task;

	private PrintPool() {
		this.task = new LinkedList<>();
	}

	public static PrintPool getPrintPool() {
		if (printPool == null) {
			System.out.println("创建PrintPool...");
			printPool = new PrintPool();
		} else {
			System.out.println("已经存在PrintPool...");
		}
		return printPool;
	}

	public Queue<Task> getTask() {
		return task;
	}

	public void create(String taskName) {
		this.task.offer(new Task(taskName));
		System.out.println("create task...");
	}

	public void delete() {
		System.out.println("delete...");
	}

	public void pause() {
		System.out.println("pause...");
	}

	public void update() {
		System.out.println("update...");
	}

}
