package com.swarm.domain;

import com.swarm.dashboard.DashboardVariables;
import com.swarm.DrawType;
import com.swarm.Space;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Interface for all flying objects swirling in the swarm.
 */
public abstract class SwarmObject {

    private Long id;

    private PVector position;
    private PVector velocity;
    private PVector acceleration;

    private Float maxForce;
    private Float maxSpeed;

    protected SwarmObject(Long id, PVector position, PVector velocity, PVector acceleration, Float maxForce, Float maxSpeed) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Method that makes a {@link SwarmObject} fly relative to the other {@link SwarmObject}
     * swirling around it.
     *
     * @param otherSwarmObjects
     * @param space
     * @param wind
     */
    public abstract void fly(ConcurrentLinkedQueue<SwarmObject> otherSwarmObjects, Space space, int[] wind);

    /**
     * Method to update the position of the {@link SwarmObject} according to its current acceleration
     * and current speed.
     */
    public void updatePosition() {
        // update velocity
        velocity.add(acceleration);
        // limit speed to the max
        velocity.limit(maxSpeed);
        position.add(velocity);
        // reset accelertion to 0 each cycle
        acceleration.mult(0);
    }

    /**
     *
     * @param space
     * @param drawType
     * @param drawSize
     */
    public void sketch(PApplet space, DrawType drawType, float drawSize){
        switch (drawType) {
            case POINT:
                space.point(this.getPosition().x, this.getPosition().y, this.getPosition().z);
                break;
            case LINE:
                space.line(this.getPosition().x - (this.getVelocity().x * drawSize),
                        this.getPosition().y - (this.getVelocity().y * DashboardVariables.boidDrawSize),
                        this.getPosition().z - (this.getVelocity().z * DashboardVariables.boidDrawSize),
                        this.getPosition().x + (this.getVelocity().x * DashboardVariables.boidDrawSize),
                        this.getPosition().y + (this.getVelocity().y * DashboardVariables.boidDrawSize),
                        this.getPosition().z + (this.getVelocity().z * DashboardVariables.boidDrawSize));
                break;
            case DARTS:
                // TODO
                space.point(this.getPosition().x, this.getPosition().y, this.getPosition().z);
                break;
        }
    }

    /**
     * A method that calculates a steering vector towards a target.
     * Takes a second argument, if true, it slows down as it approaches the target.
     *
     * @param target
     * @param slowdown
     * @return
     */
    public PVector steerTowards(PVector target, boolean slowdown) {
        PVector steer;
        // a vector pointing from the location to the target
        PVector desired = PVector.sub(target, position);
        // distance from the target is the magnitude of the vector
        float distance = desired.mag();
        // if the distance is greater than 0, calc steering (otherwise return zero vector)
        if (distance > 0) {
            // normalize desired
            desired.normalize();
            // two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
            if ((slowdown) && (distance < 100.0)) {
                desired.mult(maxSpeed * (distance / 100.0F));
            } else {
                desired.mult(maxSpeed);
            }
            // steering = dsired minus velocity
            steer = PVector.sub(desired, velocity);
            // limit to maximum steering force
            steer.limit(maxForce);
        } else {
            steer = new PVector(0, 0, 0);
        }
        return steer;
    }

    /**
     * Checks the boundaries of the {@link SwarmObject} in the space. If outside it will
     * teleport the {@link SwarmObject} back on the opposite wall of the constrained space.
     */
    public void checkBoundaries() {
        if (this.position.x >= DashboardVariables.screenWidth * .5F) {
            this.position.x = -DashboardVariables.screenWidth * .5F;
        } else if (this.position.x <= -DashboardVariables.screenWidth * .5F) {
            this.position.x = DashboardVariables.screenWidth * .5F;
        }
        if (this.position.y >= DashboardVariables.screenHeight * .5F) {
            this.position.y = -DashboardVariables.screenHeight * .5F;
        } else if (this.position.y <= -DashboardVariables.screenHeight * .5F) {
            this.position.y = DashboardVariables.screenHeight * .5F;
        }
        if (this.position.z <= -DashboardVariables.screenDepth * .5F) {
            this.position.z = DashboardVariables.screenDepth * .5F;
        } else if (this.position.z >= DashboardVariables.screenDepth * .5F) {
            this.position.z = -DashboardVariables.screenDepth * .5F;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    public Float getMaxForce() {
        return maxForce;
    }

    public void setMaxForce(Float maxForce) {
        this.maxForce = maxForce;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
