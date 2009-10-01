package threads;

class IgnoringTask implements Task {
	public void run() {
		long now = System.currentTimeMillis();
		long end = now + 1000;
		while (now < end) {
			try {
				Thread.sleep(end - now);
			} catch (InterruptedException ie) {
				System.out.println("IgnoringTask ignoring interruption");
			}
			now = System.currentTimeMillis();
		}
		System.out.println("IgnoringTask completed");
	}
}