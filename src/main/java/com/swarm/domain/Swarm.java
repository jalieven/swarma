package com.swarm.domain;

import com.swarm.dashboard.DashboardVariables;
import com.swarm.Space;
import com.swarm.SwarmUtil;
import processing.core.PApplet;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Swarm extends PApplet {

    private ConcurrentLinkedQueue<SwarmObject> swarmObjects = new ConcurrentLinkedQueue<SwarmObject>();

    public void swirl(Space space, int[] wind){
        long startSwirl = 0;
        if (DashboardVariables.printSwirlCPUTime) {
            startSwirl = System.currentTimeMillis();
        }
        for (SwarmObject swarmObject : this.swarmObjects) {
            swarmObject.fly(this.swarmObjects, space, wind);
        }
        if (DashboardVariables.printSwirlCPUTime) {
            SwarmUtil.printCPUTime((long) 1, startSwirl, System.currentTimeMillis(), "SWIRL");
        }
    }

    public void addSwarmObject(SwarmObject swarmObject){
        this.swarmObjects.add(swarmObject);
    }

    public void addAllSwarmObject(List<SwarmObject> swarmObjects){
        this.swarmObjects.addAll(swarmObjects);
    }

    public void removeAllPredators() {
        if (!this.swarmObjects.isEmpty()) {
            for (SwarmObject swarmObject : swarmObjects) {
                if(swarmObject instanceof Predator){
                    this.swarmObjects.remove(swarmObject);
                }
            }
        }
    }
}
