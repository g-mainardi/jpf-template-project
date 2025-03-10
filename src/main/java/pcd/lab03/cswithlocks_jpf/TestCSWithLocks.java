package pcd.lab03.cswithlocks_jpf;

import java.util.concurrent.locks.*;

public class TestCSWithLocks {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();				
		new MyWorkerB(lock).start();
		new MyWorkerA(lock).start();		
	}
}
