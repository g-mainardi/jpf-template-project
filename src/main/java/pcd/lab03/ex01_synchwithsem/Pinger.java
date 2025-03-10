package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static gov.nasa.jpf.util.test.TestJPF.assertEquals;
import static gov.nasa.jpf.util.test.TestJPF.assertTrue;
import static pcd.lab03.ex01_synchwithsem.P_LABEL.PING;
import static pcd.lab03.ex01_synchwithsem.P_LABEL.PONG;

public class Pinger extends ActiveComponent {

	public Pinger(AtomicInteger p_value, Semaphore pingDone, Semaphore pongDone) {
		super(p_value, pingDone, pongDone);
	}

	public void run() {
		while (true) {
			try {
				pongDone.acquire();
//				assertEquals(PONG.getCode(), p_value.get());
//				p_value.set(PING.getCode());
				assertTrue(p_value.compareAndSet(PONG.getCode(), PING.getCode()));
				println(PING.getName());
				pingDone.release();
			} catch (InterruptedException ex) {
				println("interrupted..");
			} finally {
			}
		}
	}
}