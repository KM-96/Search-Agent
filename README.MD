# Search Agent

## Objective
* To develop search algorithms to find the optimal path given a terrain.

## Prerequisites

Before you begin, ensure you have met the following requirements:

* You have installed the lastest minor version of java 1.8, both JRE and JDK. You can use [this](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) for installation.

## Using the experiment

If you are using an IDE like IntelliJ or Eclipse, use the following steps to run the project
* Point the working directory to Search-Agent
* Main class: SearchAgent
* JRE: 1.8
* Run the project

If you are running the project on command line, follow these steps:<br/>
* Clone/Download the project to your local machine.
* Navigate to the folder with SearchAgent.java file.
* Run the following commands:
```
javac SearchAgent.java
```

```
java SearchAgent
```

## Problem Description
* The goal of this project is to send a sophisticated mobile lab to Mars to study the surface of the planet more closely. We develop algorithms to find the optimal path for navigation of the rover based on a particular objective.
* The input of our program includes a topographical map of the mission site, plus some information about intended landing site and target locations and some other quantities that control the quality of the solution. The surface of the planet can be imagined as a surface in a 3-dimensional space. One way to represent a surface in 3D space is using a mesh-grid with a Z value assigned to each cell that identifies the elevation of the planet at the location of the cell. At each cell, the rover can move to each of 8 possible neighbor  cells: North, North-East, East, South-East, South, South-West, West, and North-West. Actions are assumed to be deterministic and error-free (the rover will always end up at the intended neighbor cell).
* The rover is not designed to climb across steep hills and thus moving to a neighboring cell which requires the rover to climb up or down a surface which is steeper than a particular threshold value is not allowed. This maximum slope (expressed as a difference in Z elevation between adjacent cells) will be given as an input along with the topographical map.
* elementary moves. Each elementary move consists of moving the rover to one of its 8 neighbors.
* To find the solution you will use the following algorithms:
	- Breadth-first search (BFS)
	- Uniform-cost search (UCS)
	- A* search (A*)

## Contact
If you want to contact me you can reach me at <km69564@usc.edu> or <krishnamanoj14@gmail.com>.
