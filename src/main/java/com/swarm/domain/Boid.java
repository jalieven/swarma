package com.swarm.domain;

import com.swarm.BoundaryType;
import com.swarm.dashboard.DashboardVariables;
import com.swarm.Space;
import processing.core.PVector;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO Insert JavaDoc!
 */
public class Boid extends SwarmObject {

    public Boid(Long id, PVector position, PVector velocity, PVector acceleration, Float maxForce, Float maxSpeed) {
        super(id, position, velocity, acceleration, maxForce, maxSpeed);
    }

    /**
     * {@inheritDoc}
     */
    public void fly(ConcurrentLinkedQueue<SwarmObject> otherSwarmObjects, Space space, int[] wind) {
        // prepare the space to render this Boid
        space.stroke(DashboardVariables.boidColor[0], DashboardVariables.boidColor[1], DashboardVariables.boidColor[2]);
        space.strokeWeight(DashboardVariables.boidStroke);
        // calculate the swarming behaviour
        if (DashboardVariables.boidSwarmBehaviour) {
            swarmBehaviour(otherSwarmObjects, DashboardVariables.boidSeparationWeight,
                    DashboardVariables.boidAlignmentWeight,
                    DashboardVariables.boidCohesionWeight,
                    DashboardVariables.boidCautionWeight, wind,
                    new PVector((space.getWindCalibrationVector() != null) ? space.getWindCalibrationVector().x : 0,
                            (space.getWindCalibrationVector() != null) ? space.getWindCalibrationVector().y : 0,
                            (space.getWindCalibrationVector() != null) ? space.getWindCalibrationVector().z : 0));
        }
        // update the position with the newly acquired acceleration and velocity
        updatePosition();
        // check if we have to teleport the Boid
        if (DashboardVariables.boundaryType == BoundaryType.TELEPORT) {
            checkBoundaries();
        }
        // sketch the Boid into the space
        sketch(space, DashboardVariables.boidDrawType, DashboardVariables.boidDrawSize);

    }

    /**
     * This method will calculate the acceleration vector on 3 behavioural treats:
     * - Separation: checks for nearby Boids and steers away
     * - Alignment: for every nearby Boid in the system, calculate the average velocity
     * - Cohesion: for the average location (i.e. center) of all nearby Boids, calculate steering vector towards that location
     *
     * @param otherSwarmObjects
     * @param separationWeight
     * @param alignmentWeight
     * @param cohesionWeight
     * @param cautionWeight
     * @param wind
     * @param windVector
     */
    private void swarmBehaviour(ConcurrentLinkedQueue<SwarmObject> otherSwarmObjects,
                                float separationWeight, float alignmentWeight, float cohesionWeight, float cautionWeight, int[] wind, PVector windVector) {
        PVector separationSteer = new PVector(0, 0, 0);
        PVector alignmentSteer = new PVector(0, 0, 0);
        PVector cohesionSteer = new PVector(0, 0, 0);
        PVector predatorSteer = new PVector(0, 0, 0);
        int separationNeighborCount = 0;
        int alignmentNeighborCount = 0;
        int cohesionNeighborCount = 0;
        // for cohesion start with empty vector to accumulate all locations
        PVector cohesionSum = new PVector(0, 0, 0);
        // for every boid in the system, check if it's too close
        for (SwarmObject otherSwarmObject : otherSwarmObjects) {
            float distance = PVector.dist(this.getPosition(), otherSwarmObject.getPosition());
            // if the Boid is of its own kind but not itself
            if (otherSwarmObject instanceof Boid && !otherSwarmObject.getId().equals(this.getId())) {
                // -------------------------------------------------------------------------------------------------
                //        SEPARATION - PART
                // -------------------------------------------------------------------------------------------------

                // if the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
                if ((distance > 0) && (distance < DashboardVariables.boidSeparationSight)) {
                    // calculate vector pointing away from neighbor
                    PVector diff = PVector.sub(this.getPosition(), otherSwarmObject.getPosition());
                    diff.normalize();
                    // weight by distance
                    diff.div(distance);
                    separationSteer.add(diff);
                    // keep track of how many
                    separationNeighborCount++;
                }
                // -------------------------------------------------------------------------------------------------
                //        ALIGNMENT - PART
                // -------------------------------------------------------------------------------------------------
                if ((distance > 0) && (distance < DashboardVariables.boidAlignmentSight)) {
                    alignmentSteer.add(otherSwarmObject.getVelocity());
                    alignmentNeighborCount++;
                }
                // -------------------------------------------------------------------------------------------------
                //        COHESION - PART
                // -------------------------------------------------------------------------------------------------
                if ((distance > 0) && (distance < DashboardVariables.boidCohesionSight)) {
                    cohesionSum.add(otherSwarmObject.getPosition()); // add location
                    cohesionNeighborCount++;
                }
            } else {
                // this means we encountered a predator and should steer away from it
                if((distance > 0) && (distance < DashboardVariables.boidCautionSight)) {
                    predatorSteer = PVector.sub(this.getPosition(),otherSwarmObject.getPosition());
                }
            }
        }
        // average -- divide by how many
        if (separationNeighborCount > 0) {
            separationSteer.div((float) separationNeighborCount);
        }
        if (alignmentNeighborCount > 0) {
            alignmentSteer.div((float) alignmentNeighborCount);
        }
        if (cohesionNeighborCount > 0) {
            cohesionSum.div((float) cohesionNeighborCount);
            // steer towards the location
            cohesionSteer = steerTowards(cohesionSum, true);
        }
        // as long as the vectors are greater than 0
        if (separationSteer.mag() > 0) {
            // implement Reynolds: Steering = Desired - Velocity
            separationSteer.normalize();
            separationSteer.mult(this.getMaxSpeed());
            separationSteer.sub(this.getVelocity());
            separationSteer.limit(this.getMaxForce());
        }
        if (alignmentSteer.mag() > 0) {
            // implement Reynolds: Steering = Desired - Velocity
            alignmentSteer.normalize();
            alignmentSteer.mult(this.getMaxSpeed());
            alignmentSteer.sub(this.getVelocity());
            alignmentSteer.limit(this.getMaxForce());
        }
        // apply weights
        separationSteer.mult(separationWeight);
        alignmentSteer.mult(alignmentWeight);
        cohesionSteer.mult(cohesionWeight);
        predatorSteer.mult(cautionWeight);
        // add the force vector to acceleration
        this.getAcceleration().add(separationSteer);
        this.getAcceleration().add(alignmentSteer);
        this.getAcceleration().add(cohesionSteer);
        this.getAcceleration().add(predatorSteer);
        // wind behaviour
        if (wind != null) {
            PVector windSteer = new PVector((float) wind[0], (float) wind[1], (float) wind[2]);
            windVector.sub(windSteer);
            //System.out.println(noAcceleration(windVector));
            if (!noAcceleration(windVector)) {
                this.getAcceleration().add(windVector);
            }
        }
        // boundary bahaviour
        if (DashboardVariables.boundaryType != BoundaryType.TELEPORT) {
            PVector repulsionSteer;
            if (DashboardVariables.boundaryType == BoundaryType.LINEAR_CENTRIC_FORCE) {
                // make the "walls" repulsive linear-centric-force style
                repulsionSteer = new PVector(-getPosition().x, -getPosition().y, -getPosition().z);
                repulsionSteer.normalize();
                getPosition().mag();
                repulsionSteer.mult(getPosition().mag() * DashboardVariables.centricForceWeight);
            } else if (DashboardVariables.boundaryType == BoundaryType.ASYMPTOTIC_CENTRIC_FORCE) {
                // make the "walls" repulsive in a asymptotic-to-the-wall-force style
                repulsionSteer = new PVector(-getPosition().x, -getPosition().y, -getPosition().z);
                repulsionSteer.normalize();
                getPosition().mag();
                repulsionSteer.mult(getPosition().mag() * 0.0011F);
            } else {
                repulsionSteer = new PVector(0, 0, 0);
            }
            this.getAcceleration().add(repulsionSteer);
        }
    }

    private boolean noAcceleration(PVector wind) {
        //System.out.println(wind);
        return (wind.x < 30 && wind.x > -30 && wind.y < 30 && wind.y > -30 && wind.z < 60 && wind.z > -10);
    }

}
