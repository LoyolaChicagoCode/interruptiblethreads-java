package threads;

class DeferringTask implements Task {
	public void run() {
		long now = System.currentTimeMillis();
		long end = now + 1000;
		boolean wasInterrupted = false;
		while (now < end) {
			try {
				Thread.sleep(end - now);
			} catch (InterruptedException ie) {
				System.out.println("DeferringTask deferring interruption");
				wasInterrupted = true;
			}
			now = System.currentTimeMillis();
		}
		System.out.println("DeferringTask completed");
		if (wasInterrupted) {
			Thread.currentThread().interrupt();
		}
	}
}