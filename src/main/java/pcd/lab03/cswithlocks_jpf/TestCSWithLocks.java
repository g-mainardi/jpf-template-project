package pcd.lab03.cswithlocks_jpf;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class TestCSWithLocks {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		AtomicInteger counter = new AtomicInteger();
		new MyWorkerB(lock, counter).start();
		new MyWorkerA(lock, counter).start();
	}
}
