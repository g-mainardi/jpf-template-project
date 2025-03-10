package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		AtomicInteger p_value = new AtomicInteger(P_LABEL.PONG.getCode());

		Semaphore pingDone = new Semaphore(0,true);
		Semaphore pongDone = new Semaphore(1,true);

		new Pinger(p_value, pingDone, pongDone).start();
		new Ponger(p_value, pingDone, pongDone).start();

		pingDone.release();
	}

}

