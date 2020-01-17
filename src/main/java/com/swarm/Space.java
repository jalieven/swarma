package com.swarm;

import com.swarm.dashboard.Dashboard;
import com.swarm.dashboard.DashboardVariables;
import com.swarm.domain.Boid;
import com.swarm.domain.Predator;
import com.swarm.domain.Swarm;
import com.swarm.domain.SwarmObject;
// import net.sf.pii.Pii;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Insert JavaDoc!
 */
public class Space extends PApplet {

    private Swarm swarm;

    // private Pii pii;

    private Dashboard dashboard;

//    private PVector windCalibrationVector;

    private float spaceRotationX = PI;

    private float spaceRotationY = PI;

    public Space() {
        this.swarm = new Swarm();
        if (DashboardVariables.wiiRemote) {
//            this.pii = new Pii(null);
//            pii.setLedStatus(new boolean[]{true, true, true, true});
//            int[] wiiStartCalibration = this.pii.getAccelerationData();
//            this.windCalibrationVector = new PVector(wiiStartCalibration[0], wiiStartCalibration[1], wiiStartCalibration[2]);
        }
        this.dashboard = new Dashboard(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        frameRate(DashboardVariables.frameRate);
        size(DashboardVariables.screenWidth, DashboardVariables.screenHeight, P3D);
        // add the predators
        addPredators();
        // add the boids
        addBoids();

    }

    private void addBoids() {
        List<SwarmObject> boids = new ArrayList<SwarmObject>();
        for (int b = 0; b < DashboardVariables.boidCount; b++) {
            PVector position = new PVector(0, 0, 0);
            PVector velocity = new PVector(0, 0, 0);
            switch (DashboardVariables.boidStartPositionType) {
                case FLOOR:
                    position = SwarmUtil.createRandomPositionFloorPVector();
                    velocity = SwarmUtil.createRandomVelocityPVector();
                    break;
                case MIDDLE:
                    position = SwarmUtil.getBoidStartPositionPVector();
                    velocity = SwarmUtil.createRandomVelocityPVector();
                    break;
                case RANDOM:
                    position = SwarmUtil.createRandomPositionPVector();
                    velocity = SwarmUtil.createRandomVelocityPVector();
                    break;
            }
            Boid boid = new Boid((long) b, position, velocity, new PVector(0, 0, 0), DashboardVariables.boidMaxForce, DashboardVariables.boidMaxSpeed);
            boids.add(boid);
        }
        swarm.addAllSwarmObject(boids);
    }

    private void addPredators() {
        List<SwarmObject> predators = new ArrayList<SwarmObject>();
        for (int p = 0; p < DashboardVariables.predatorCount; p++) {
            PVector position = new PVector(0, 0, 0);
            switch (DashboardVariables.predatorStartPositionType) {
                case FLOOR:
                    position = SwarmUtil.createRandomPositionFloorPVector();
                    break;
                case MIDDLE:
                    position = SwarmUtil.getBoidStartPositionPVector();
                    break;
                case RANDOM:
                    position = SwarmUtil.createRandomPositionPVector();
                    break;
            }
            PVector velocity = SwarmUtil.createRandomVelocityPVector();
            Predator predator = new Predator((long) p, position, velocity, new PVector(0, 0, 0), DashboardVariables.predatorMaxForce, DashboardVariables.predatorMaxSpeed);
            predators.add(predator);
        }
        swarm.addAllSwarmObject(predators);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        noFill();
        background(0);
        translate(DashboardVariables.screenWidth * .5F, DashboardVariables.screenHeight * .5F, -DashboardVariables.screenDepth);
        rotateX(spaceRotationX);
        rotateY(spaceRotationY);
        int[] wind = new int[]{0, 0, 0};
        if (DashboardVariables.wiiRemote) {
            // wind = this.pii.getAccelerationData();
            dashboard.control();
        }
        swarm.swirl(this, wind);
        // draw a box for 3D reference frame
        if (DashboardVariables.boxVisible) {
            strokeWeight(DashboardVariables.boxStrokeWeight);
            stroke(DashboardVariables.boxStroke);
            box(DashboardVariables.screenWidth * DashboardVariables.boxInflation,
                    DashboardVariables.screenHeight * DashboardVariables.boxInflation,
                    DashboardVariables.screenDepth * DashboardVariables.boxInflation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (key == CODED) {
            if (keyCode == SHIFT) {
                PVector position = SwarmUtil.createRandomPositionPVector();
                PVector velocity = SwarmUtil.createRandomVelocityPVector();
                PVector acceleration = new PVector(0, 0, 0);
                this.swarm.addSwarmObject(new Predator((long) 0, position, velocity, acceleration, DashboardVariables.predatorMaxForce, DashboardVariables.predatorMaxSpeed));
            } else if (keyCode == CONTROL) {
                this.swarm.removeAllPredators();
            } else if (keyCode == RIGHT) {
                spaceRotationY += .02;
            } else if (keyCode == LEFT) {
                spaceRotationY -= .02;
            } else if (keyCode == UP) {
                spaceRotationX += .02;
            } else if (keyCode == DOWN) {
                spaceRotationX -= .02;
            }
        } 
    }

//    public void setWindCalibrationVector(PVector windCalibrationVector) {
//        this.windCalibrationVector = windCalibrationVector;
//    }

    public PVector getWindCalibrationVector() {
        return null;
    }

    public Swarm getSwarm() {
        return swarm;
    }

//    public Pii getPii() {
//        return pii;
//    }
}
