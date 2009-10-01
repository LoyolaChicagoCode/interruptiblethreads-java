package threads;

public class TaskClient implements Runnable {
	public static final int taskCount = 1000;

	Task[] tasks;

	public void doMain(Task[] tasks) {
		this.tasks = tasks;
		Thread worker = new Thread(this);
		worker.start();
		try {
			System.in.read();
		} catch (java.io.IOException ioe) {
			System.out.println("I/O exception on input");
		}
		System.out.println("=======Shutting down");
		worker.interrupt();
		try {
			worker.join();
		} catch (InterruptedException ie) {
			System.out.println("Unexpected interruption on main thread");
		}
	}

	public void run() {
		try {
			for (int n = 0; n < taskCount; n++) {
				tasks[n].run();
				if (Thread.interrupted()) {
					System.out.println("Interrupted state detected by client");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException ie) {
			System.out.println("Interruption caught by client");
		}
	}

	public static void main(String[] args) {
		try {
			Class cls = Class.forName(args[0]);
			Task[] tasks = new Task[taskCount];
			for (int n = 0; n < taskCount; n++) {
				tasks[n] = (Task) cls.newInstance();
			}
			new TaskClient().doMain(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}