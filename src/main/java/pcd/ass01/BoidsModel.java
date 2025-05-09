package pcd.ass01;

import java.util.ArrayList;
import java.util.List;

public class BoidsModel {

    private static final double DELTA_POS_X = 0.1;
    private static final double DELTA_POS_Y = 0.2;
    private static final double DELTA_VEL_X = 0.3;
    private static final double DELTA_VEL_Y = 0.4;
    private final List<Boid> boids;
    private final double separationWeight;
    private final double alignmentWeight;
    private final double cohesionWeight;
    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;

    public BoidsModel(int nBoids,
                            double initialSeparationWeight,
    						double initialAlignmentWeight,
    						double initialCohesionWeight,
    						double width,
    						double height,
    						double maxSpeed,
    						double perceptionRadius,
    						double avoidRadius){
        this.separationWeight = initialSeparationWeight;
        this.alignmentWeight = initialAlignmentWeight;
        this.cohesionWeight = initialCohesionWeight;
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;
        this.boids = generateBoids(nBoids);
    }

    public List<Boid> generateBoids(int nBoids) {
        List<Boid> lst = new ArrayList<>();
        for (int i = 0; i < nBoids; i++) {
            P2d pos = new P2d(DELTA_POS_X, DELTA_POS_Y);
            V2d vel = new V2d(DELTA_VEL_X, DELTA_VEL_Y);
            lst.add(new Boid(pos, vel));
        }
        return lst;
    }

    public List<Boid> getBoids(){
    	return boids;
    }

    public double getMinX() {
    	return -width/2;
    }

    public double getMaxX() {
    	return width/2;
    }

    public double getMinY() {
    	return -height/2;
    }

    public double getMaxY() {
    	return height/2;
    }
    
    public double getWidth() {
    	return width;
    }
 
    public double getHeight() {
    	return height;
    }

    public double getSeparationWeight() {
    	return separationWeight;
    }

    public double getCohesionWeight() {
    	return cohesionWeight;
    }

    public double getAlignmentWeight() {
    	return alignmentWeight;
    }
    
    public double getMaxSpeed() {
    	return maxSpeed;
    }

    public double getAvoidRadius() {
    	return avoidRadius;
    }

    public double getPerceptionRadius() {
    	return perceptionRadius;
    }

}
