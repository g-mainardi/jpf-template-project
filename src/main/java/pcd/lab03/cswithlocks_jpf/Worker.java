package pcd.lab03.cswithlocks_jpf;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Worker extends Thread {

	protected AtomicInteger counter;
		
	public Worker(final AtomicInteger counter){
		this.counter = counter;
	}

	protected void print(String msg){
		synchronized (System.out){
			System.out.print(msg);
		}
	}

	protected void println(String msg){
		synchronized (System.out){
			System.out.println(msg);
		}
	}

}
