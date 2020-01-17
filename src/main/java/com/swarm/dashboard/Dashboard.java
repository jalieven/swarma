package com.swarm.dashboard;

import com.swarm.Space;
import com.swarm.SwarmUtil;
import com.swarm.domain.Predator;
import processing.core.PVector;


public class Dashboard {

    private Space space;

    public Dashboard(Space space) {
        this.space = space;
    }

    public void control() {
        if (space.getPii().isExtensionControllerConnected()) {
            double[] analogStickData = this.space.getPii().getNunchukAnalogStickData();
            //DashboardVariables.setBoidAlignmentSight(this.space.map((float)analogStickData[0], 33, 233, 0, 100));
            //DashboardVariables.setBoidCohesionSight(this.space.map((float)analogStickData[1], 33, 233, 0, 100));
        }
        if (space.getPii().isButtonAPressed()) {
            PVector position = SwarmUtil.createRandomPositionPVector();
            PVector velocity = SwarmUtil.createRandomVelocityPVector();
            PVector acceleration = new PVector(0, 0, 0);
            this.space.getSwarm().addSwarmObject(new Predator((long) 0, position, velocity, acceleration, DashboardVariables.predatorMaxForce, DashboardVariables.predatorMaxSpeed));
        } else if (space.getPii().isButtonBPressed()) {
            this.space.getSwarm().removeAllPredators();
        }
    }
}
