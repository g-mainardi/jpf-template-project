package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Ponger extends ActiveComponent {

	public Ponger(Semaphore mutex) {
		super(mutex);
	}

	public void run() {
		while (true) {
			try {
				mutex.acquire();
				println("pong");
			} catch (InterruptedException ex) {
				println("interrupted..");
			} finally {
				mutex.release();
			}
		}
	}
}