package pcd.lab03.cswithlocks_jpf;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import static gov.nasa.jpf.util.test.TestJPF.assertTrue;

public class MyWorkerB extends Worker {
	
	private Lock lock;
	
	public MyWorkerB(Lock lock, AtomicInteger counter){
		super(counter);
		this.lock = lock;
	}

	public void run(){
		while (true){
		  try {
			  lock.lockInterruptibly();
			  assertTrue(counter.incrementAndGet() == 1);
			  b1();
			  b2();
			  assertTrue(counter.decrementAndGet() == 0);
		  } catch (InterruptedException ex) {
		  } finally {
			  lock.unlock();
		  }
		  b3();
		}
	}
	
	protected void b1(){
		// println("b1");
		// wasteRandomTime(0,1000);	
	}
	
	protected void b2(){
		// println("b2");
		// wasteRandomTime(100,200);	
	}
	protected void b3(){
		// println("b3");
		// wasteRandomTime(1000,2000);	
	}
}
