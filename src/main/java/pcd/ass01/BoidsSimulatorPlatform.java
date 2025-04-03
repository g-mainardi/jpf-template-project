package pcd.ass01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static pcd.ass01.ListUtils.partitionByNumber;

public class BoidsSimulatorPlatform {
    private final List<Thread> workers = new ArrayList<>();
    private final BoidsModel model;

    public BoidsSimulatorPlatform(BoidsModel model) {
        this.model = model;
    }

    private void initWorkers(BoidsModel model) {
        var boids = model.getBoids();
        int nThreads = Runtime.getRuntime().availableProcessors();

        List<List<Boid>> partitions = partitionByNumber(boids, nThreads);
        CyclicBarrier velBarrier = new CyclicBarrier(nThreads);
        CyclicBarrier posBarrier = new CyclicBarrier(nThreads);

        for (List<Boid> partition : partitions) {
            workers.add(new Thread(() -> update(partition, velBarrier, posBarrier)));
        }
    }

    protected void update(List<Boid> boids, CyclicBarrier velBarrier, CyclicBarrier posBarrier) {
        while (true) {
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

}
