package pcd.ass01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class BoidsSimulatorPlatform {
    public static final int N_THREADS = 2;
    private final List<Thread> workers = new ArrayList<>();
    private final BoidsModel model;

    public BoidsSimulatorPlatform(BoidsModel model) {
        this.model = model;
    }

    private void initWorkers() {
        List<List<Boid>> partitions = partitionByNumber(this.model.getBoids(), N_THREADS);
        MyBarrier velBarrier = new MyBarrier(N_THREADS);
        MyBarrier posBarrier = new MyBarrier(N_THREADS);

        for (List<Boid> partition : partitions) {
            workers.add(new Thread(() -> update(partition, velBarrier, posBarrier)));
        }
    }

    protected void update(List<Boid> boids, MyBarrier velBarrier, MyBarrier posBarrier) {
        while(true) {
            boids.forEach(boid -> boid.updateVelocity(model));
            try {
                velBarrier.await();
            } catch (InterruptedException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] interrupted while waiting for velocity barrier");
            } catch (BrokenBarrierException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] broken velocity barrier");
            }
            velBarrier.reset();
            boids.forEach(boid -> boid.updatePos(model));
            try {
                posBarrier.await();
            } catch (InterruptedException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] interrupted while waiting for position barrier");
            } catch (BrokenBarrierException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] broken position barrier");
            }
            posBarrier.reset();
        }
    }

    public void runSimulation() {
        this.initWorkers();
        this.workers.forEach(Thread::start);
        for (int i=0; i < this.workers.size(); i++) {
            try {
                this.workers.get(i).join();
            } catch (InterruptedException ignore) {
                System.out.println("Thread [" + i + "] finished");
            }
        }
        System.out.println("Run Simulation ended after waiting all threads");
    }

    public static <E> List<List<E>> partitionByNumber(List<E> elems, int numberOfPartitions) {
        List<List<E>> partitions = new ArrayList<>();
        for (int i = 0; i < numberOfPartitions; i++) {
            partitions.add(new ArrayList<E>());
        }
        for (int i = 0; i < elems.size(); i++) {
            partitions.get(i % numberOfPartitions).add(elems.get(i));
        }
        return partitions;
    }
}
