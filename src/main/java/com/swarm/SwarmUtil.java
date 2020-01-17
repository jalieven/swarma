package com.swarm;

import com.swarm.dashboard.DashboardVariables;
import processing.core.PVector;

/**
 * TODO Insert JavaDoc!
 */
public class SwarmUtil {

    public static PVector createRandomPositionPVector() {
        float randX = -DashboardVariables.screenWidth * .5F + (float) Math.random() * DashboardVariables.screenWidth;
        float randY = -DashboardVariables.screenHeight * .5F + (float) Math.random() * DashboardVariables.screenHeight;
        float randZ = -DashboardVariables.screenDepth + (float) Math.random() * DashboardVariables.screenDepth;
        return new PVector(randX, randY, randZ);
    }

    public static PVector createRandomPositionFloorPVector() {
        float randX = -DashboardVariables.screenWidth * .5F + (float) Math.random() * DashboardVariables.screenWidth;
        float randY = -DashboardVariables.screenHeight * .5F + 1;
        float randZ = -DashboardVariables.screenDepth + (float) Math.random() * DashboardVariables.screenDepth;
        return new PVector(randX, randY, randZ);
    }

    public static PVector createRandomVelocityPVector() {
        float randX = -1 + (float) Math.random() * 2;
        float randY = -1 + (float) Math.random() * 2;
        float randZ = -1 + (float) Math.random() * 2;
        PVector pVector = new PVector(randX, randY, randZ);
        pVector.normalize();
        return pVector;
    }

    public static PVector getBoidStartPositionPVector() {
        return new PVector(DashboardVariables.boidStartPositionX, DashboardVariables.boidStartPositionY, DashboardVariables.boidStartPositionZ);
    }

    public static void printCPUTime(long id, long start, long end, String operation) {
        if(id == (long) 1){
            System.out.println(operation + " took: " + (end - start) + "milliseconds");
        }
    }
}
