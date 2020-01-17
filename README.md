# NOTES TO INSTALL:
-------------------

1) Install the directories under dependency into your Maven local repo

```
mvn install:install-file -Dfile=./dependency/org/processing/processing-core/1.0.3/processing-core-1.0.3.jar \
    -DgroupId=org.processing \
    -DartifactId=processing-core \
    -Dversion=1.0.3 -Dpackaging=jar

mvn install:install-file -Dfile=./dependency/net/sf/pii/0.2-SNAPSHOT/pii-0.2-SNAPSHOT.jar \
    -DgroupId=net.sf \
    -DartifactId=pii \
    -Dversion=0.2-SNAPSHOT -Dpackaging=jar
```

2) If you want to use a WiiRemote to make some virtual wind:
    - Make sure you installed Linux blueZ (tested with version 4.12)
    - Have your Bluetooth-dongle inserted
    - Set wiiRemote to true in the com.swarm.dashboard.DashboardVariables class
    - Run the Space class while leaving your Wii Remote perfectly horizontally on its back
5) Add com.swarma.Space to program arguments before running