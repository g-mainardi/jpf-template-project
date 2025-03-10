package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static gov.nasa.jpf.util.test.TestJPF.assertEquals;
import static gov.nasa.jpf.util.test.TestJPF.assertTrue;
import static pcd.lab03.ex01_synchwithsem.P_LABEL.PING;
import static pcd.lab03.ex01_synchwithsem.P_LABEL.PONG;

public class Ponger extends ActiveComponent {

	public Ponger(AtomicInteger p_value, Semaphore pingDone, Semaphore pongDone) {
		super(p_value, pingDone, pongDone);
	}

	public void run() {
		while (true) {
			try {
				pingDone.acquire();
//				assertEquals(PING.getCode(), p_value.get());
//				p_value.set(PONG.getCode());
				assertTrue(p_value.compareAndSet(P_LABEL.PING.getCode(), P_LABEL.PONG.getCode()));
				println(PONG.getName());
				pongDone.release();
			} catch (InterruptedException ex) {
				println("interrupted..");
			} finally {
			}
		}
	}
}