package pcd.lab03.cswithlocks_jpf;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import static gov.nasa.jpf.util.test.TestJPF.assertTrue;

public class MyWorkerA extends Worker {

	private Lock lock;

	public MyWorkerA(Lock lock, AtomicInteger counter){
		super(counter);
		this.lock = lock;
	}

	public void run(){
		while (true){
			a1();
			try {
				lock.lockInterruptibly();
				assertTrue(counter.incrementAndGet() == 1);
				a2();
				a3();
				assertTrue(counter.decrementAndGet() == 0);
			} catch (InterruptedException ex) {
			} finally {
				lock.unlock();
			}
		}
	}

	protected void a1(){
		// println("a1");
		// wasteRandomTime(100,500);	
	}

	protected void a2(){
		// println("a2");
		// wasteRandomTime(300,700);	
	}
	protected void a3(){
		// println("a3");
		// wasteRandomTime(300,700);	
	}
}

