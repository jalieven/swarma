package com.swarm.dashboard;

import com.swarm.BoundaryType;
import com.swarm.DrawType;
import com.swarm.StartPositionType;

/**
 * Object containing all the variables to tune in the swarm.
 */
public class DashboardVariables {

    public static int frameRate = 24;

    public static int screenWidth = 1000;

    public static int screenHeight = 600;

    public static int screenDepth = 600;

    public static boolean printSwirlCPUTime = false;

    public static boolean boidSwarmBehaviour = true;

    public static StartPositionType boidStartPositionType = StartPositionType.RANDOM;

    public static boolean predatorSwarmBehaviour = true;

    public static int boidCount = 500;

    public static int boidStroke = 1;

    public static int[] boidColor = new int[]{255, 255, 255};

    public static DrawType boidDrawType = DrawType.LINE;

    public static float boidDrawSize = .5F;

    public static float boidMaxSpeed = 18F;

    public static float boidMaxForce = 0.1F;

    public static float boidStartPositionX = 0F;

    public static float boidStartPositionY = 0F;

    public static float boidStartPositionZ = 0F;

    public static float boidSeparationSight = 40.0F;

    public static float boidSeparationWeight = 3.5F;

    public static float boidAlignmentSight = 90.0F;

    public static float boidAlignmentWeight = 1.2F;

    public static float boidCohesionSight = 80.0F;

    public static float boidCohesionWeight = 1.3F;

    public static float boidCautionSight = 150.0F;

    public static float boidCautionWeight = 1F;

    public static int predatorCount = 1;

    public static int predatorStroke = 4;

    public static int[] predatorColor = new int[]{255, 0, 0};

    public static DrawType predatorDrawType = DrawType.POINT;

    public static float predatorDrawSize = 0F;

    public static float predatorMaxSpeed = 25F;

    public static float predatorMaxForce = .9F;

    public static StartPositionType predatorStartPositionType = StartPositionType.FLOOR;

    public static float predatorSight = 250F;

    public static float predatorPrivateZone = 40F;

    public static float predatorOpponentRepulsion = 2F;

    public static boolean boxVisible = true;

    public static float boxInflation = 1.15F;

    public static int boxStroke = 255;

    public static int boxStrokeWeight = 1;

    public static BoundaryType boundaryType = BoundaryType.LINEAR_CENTRIC_FORCE;
    
    public static float centricForceWeight = 0.0008F;

    public static boolean wiiRemote = false;

    public static void setFrameRate(int frameRate) {
        DashboardVariables.frameRate = frameRate;
    }

    public static void setScreenWidth(int screenWidth) {
        DashboardVariables.screenWidth = screenWidth;
    }

    public static void setScreenHeight(int screenHeight) {
        DashboardVariables.screenHeight = screenHeight;
    }

    public static void setScreenDepth(int screenDepth) {
        DashboardVariables.screenDepth = screenDepth;
    }

    public static void setPrintSwirlCPUTime(boolean printSwirlCPUTime) {
        DashboardVariables.printSwirlCPUTime = printSwirlCPUTime;
    }

    public static void setBoidSwarmBehaviour(boolean boidSwarmBehaviour) {
        DashboardVariables.boidSwarmBehaviour = boidSwarmBehaviour;
    }

    public static void setBoidStartPositionType(StartPositionType boidStartPositionType) {
        DashboardVariables.boidStartPositionType = boidStartPositionType;
    }

    public static void setPredatorSwarmBehaviour(boolean predatorSwarmBehaviour) {
        DashboardVariables.predatorSwarmBehaviour = predatorSwarmBehaviour;
    }

    public static void setBoidCount(int boidCount) {
        DashboardVariables.boidCount = boidCount;
    }

    public static void setBoidStroke(int boidStroke) {
        DashboardVariables.boidStroke = boidStroke;
    }

    public static void setBoidColor(int[] boidColor) {
        DashboardVariables.boidColor = boidColor;
    }

    public static void setBoidDrawType(DrawType boidDrawType) {
        DashboardVariables.boidDrawType = boidDrawType;
    }

    public static void setBoidDrawSize(float boidDrawSize) {
        DashboardVariables.boidDrawSize = boidDrawSize;
    }

    public static void setBoidMaxSpeed(float boidMaxSpeed) {
        DashboardVariables.boidMaxSpeed = boidMaxSpeed;
    }

    public static void setBoidMaxForce(float boidMaxForce) {
        DashboardVariables.boidMaxForce = boidMaxForce;
    }

    public static void setBoidStartPositionX(float boidStartPositionX) {
        DashboardVariables.boidStartPositionX = boidStartPositionX;
    }

    public static void setBoidStartPositionY(float boidStartPositionY) {
        DashboardVariables.boidStartPositionY = boidStartPositionY;
    }

    public static void setBoidStartPositionZ(float boidStartPositionZ) {
        DashboardVariables.boidStartPositionZ = boidStartPositionZ;
    }

    public static void setBoidSeparationSight(float boidSeparationSight) {
        DashboardVariables.boidSeparationSight = boidSeparationSight;
    }

    public static void setBoidSeparationWeight(float boidSeparationWeight) {
        DashboardVariables.boidSeparationWeight = boidSeparationWeight;
    }

    public static void setBoidAlignmentSight(float boidAlignmentSight) {
        DashboardVariables.boidAlignmentSight = boidAlignmentSight;
    }

    public static void setBoidAlignmentWeight(float boidAlignmentWeight) {
        DashboardVariables.boidAlignmentWeight = boidAlignmentWeight;
    }

    public static void setBoidCohesionSight(float boidCohesionSight) {
        DashboardVariables.boidCohesionSight = boidCohesionSight;
    }

    public static void setBoidCohesionWeight(float boidCohesionWeight) {
        DashboardVariables.boidCohesionWeight = boidCohesionWeight;
    }

    public static void setBoidCautionSight(float boidCautionSight) {
        DashboardVariables.boidCautionSight = boidCautionSight;
    }

    public static void setBoidCautionWeight(float boidCautionWeight) {
        DashboardVariables.boidCautionWeight = boidCautionWeight;
    }

    public static void setPredatorCount(int predatorCount) {
        DashboardVariables.predatorCount = predatorCount;
    }

    public static void setPredatorStroke(int predatorStroke) {
        DashboardVariables.predatorStroke = predatorStroke;
    }

    public static void setPredatorColor(int[] predatorColor) {
        DashboardVariables.predatorColor = predatorColor;
    }

    public static void setPredatorDrawType(DrawType predatorDrawType) {
        DashboardVariables.predatorDrawType = predatorDrawType;
    }

    public static void setPredatorDrawSize(float predatorDrawSize) {
        DashboardVariables.predatorDrawSize = predatorDrawSize;
    }

    public static void setPredatorMaxSpeed(float predatorMaxSpeed) {
        DashboardVariables.predatorMaxSpeed = predatorMaxSpeed;
    }

    public static void setPredatorMaxForce(float predatorMaxForce) {
        DashboardVariables.predatorMaxForce = predatorMaxForce;
    }

    public static void setPredatorStartPositionType(StartPositionType predatorStartPositionType) {
        DashboardVariables.predatorStartPositionType = predatorStartPositionType;
    }

    public static void setPredatorSight(float predatorSight) {
        DashboardVariables.predatorSight = predatorSight;
    }

    public static void setPredatorPrivateZone(float predatorPrivateZone) {
        DashboardVariables.predatorPrivateZone = predatorPrivateZone;
    }

    public static void setPredatorOpponentRepulsion(float predatorOpponentRepulsion) {
        DashboardVariables.predatorOpponentRepulsion = predatorOpponentRepulsion;
    }

    public static void setBoxVisible(boolean boxVisible) {
        DashboardVariables.boxVisible = boxVisible;
    }

    public static void setBoxInflation(float boxInflation) {
        DashboardVariables.boxInflation = boxInflation;
    }

    public static void setBoxStroke(int boxStroke) {
        DashboardVariables.boxStroke = boxStroke;
    }

    public static void setBoxStrokeWeight(int boxStrokeWeight) {
        DashboardVariables.boxStrokeWeight = boxStrokeWeight;
    }

    public static void setBoundaryType(BoundaryType boundaryType) {
        DashboardVariables.boundaryType = boundaryType;
    }

    public static void setCentricForceWeight(float centricForceWeight) {
        DashboardVariables.centricForceWeight = centricForceWeight;
    }

    public static void setWiiRemote(boolean wiiRemote) {
        DashboardVariables.wiiRemote = wiiRemote;
    }
}
