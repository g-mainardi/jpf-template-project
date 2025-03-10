package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ActiveComponent extends Thread {

	protected AtomicInteger p_value;
	protected Semaphore pingDone;
	protected Semaphore pongDone;

	public ActiveComponent(AtomicInteger p_value, Semaphore pingDone, Semaphore pongDone) {
		this.p_value = p_value;
		this.pingDone = pingDone;
		this.pongDone = pongDone;
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
