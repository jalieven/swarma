package com.swarm.domain;

import com.swarm.BoundaryType;
import com.swarm.dashboard.DashboardVariables;
import com.swarm.Space;
import processing.core.PVector;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO Insert JavaDoc!
 */
public class Predator extends SwarmObject {
    
    public Predator(Long id, PVector position, PVector velocity, PVector acceleration, Float maxForce, Float maxSpeed) {
        super(id, position, velocity, acceleration, maxForce, maxSpeed);
    }

    /**
     * {@inheritDoc}
     */
    public void fly(ConcurrentLinkedQueue<SwarmObject> otherSwarmObjects, Space space, int[] wind) {
        // prepare the space to render this Predator
        space.stroke(DashboardVariables.predatorColor[0], DashboardVariables.predatorColor[1], DashboardVariables.predatorColor[2]);
        space.strokeWeight(DashboardVariables.predatorStroke);
        // calculate the swarming behaviour
        if (DashboardVariables.predatorSwarmBehaviour) {
            swarmBehaviour(otherSwarmObjects);
        }
        // update the position with the newly acquired acceleration and velocity
        updatePosition();
        // check if we have to teleport the Predator
        if (DashboardVariables.boundaryType == BoundaryType.TELEPORT) {
            checkBoundaries();
        }
        // sketch the Predator into the space
        sketch(space, DashboardVariables.predatorDrawType, DashboardVariables.predatorDrawSize);
    }

    /**
     * This method will calculate the acceleration vector on the behavioural treats of a Predator
     *
     * @param otherSwarmObjects
     */
    private void swarmBehaviour(ConcurrentLinkedQueue<SwarmObject> otherSwarmObjects) {
        PVector boidAttractionSteer = new PVector(0,0,0);
        PVector opponentRepulsionSteer = new PVector(0,0,0);
        int victimCount = 0;
        for (SwarmObject otherSwarmObject : otherSwarmObjects) {
            float distance = PVector.dist(this.getPosition(), otherSwarmObject.getPosition());
            if(otherSwarmObject instanceof Boid) {
                // calculate the vector from the predator to the overall average of the boids within its sight
                if(distance > 0 && distance <= DashboardVariables.predatorSight) {
                    boidAttractionSteer.add(otherSwarmObject.getPosition());
                    victimCount++;
                }
            } else {
                // we encountered a opponent in the game of hunt so slightly repulse from him
                if((distance > 0) && (distance < DashboardVariables.predatorPrivateZone)) {
                    opponentRepulsionSteer = PVector.sub(this.getPosition(), otherSwarmObject.getPosition());
                }
            }
        }
        if (victimCount > 0) {
            boidAttractionSteer.div((float) victimCount);
        }
        opponentRepulsionSteer.mult(DashboardVariables.predatorOpponentRepulsion);
        // add the force vector to acceleration
        this.getAcceleration().add(opponentRepulsionSteer);
        this.getAcceleration().add(steerTowards(boidAttractionSteer, true));
    }

}
