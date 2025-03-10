package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Pinger extends ActiveComponent {

	public Pinger(Semaphore mutex) {
		super(mutex);
	}

	public void run() {
		while (true) {
			try {
				mutex.acquire();
				println("ping");
				println("Queue length" + mutex.getQueueLength());
			} catch (InterruptedException ex) {
				println("interrupted..");
			} finally {
				mutex.release();
			}
		}
	}
}