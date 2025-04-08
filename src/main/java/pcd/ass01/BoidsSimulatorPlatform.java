package pcd.ass01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BoidsSimulatorPlatform {
    public static final int N_THREADS = 2;
    private static final int MAX_ITERATIONS_PER_THREAD = 10;
    private final List<Thread> workers = new ArrayList<>();
    private final BoidsModel model;

    public BoidsSimulatorPlatform(BoidsModel model) {
        this.model = model;
    }

    private void initWorkers(BoidsModel model) {
        var boids = model.getBoids();

        List<List<Boid>> partitions = partitionByNumber(boids, N_THREADS);
        CyclicBarrier velBarrier = new CyclicBarrier(N_THREADS);
        CyclicBarrier posBarrier = new CyclicBarrier(N_THREADS);

        for (List<Boid> partition : partitions) {
            workers.add(new Thread(() -> update(partition, velBarrier, posBarrier)));
        }
    }

    protected void update(List<Boid> boids, CyclicBarrier velBarrier, CyclicBarrier posBarrier) {
        for (int iteration = 0; iteration < MAX_ITERATIONS_PER_THREAD; iteration++) {
            boids.forEach(boid -> boid.updateVelocity(model));
            try {
                velBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] interrupted while waiting for velocity barrier");
            }
            velBarrier.reset();
            boids.forEach(boid -> boid.updatePos(model));
            try {
                posBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] interrupted while waiting for position barrier");
            }
            posBarrier.reset();
        }
    }

    public void runSimulation() {
        this.initWorkers(model);
        this.workers.forEach(Thread::start);
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
