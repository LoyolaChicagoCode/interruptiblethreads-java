package threads;

class PropagatingTask implements Task {
	public void run() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("PropagatingTask completed");
	}
}