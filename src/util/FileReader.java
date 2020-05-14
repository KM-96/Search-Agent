package util;

import model.Coordinate;
import model.Graph;
import model.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
    private File file;
    private BufferedReader bufferedReader;
    private String location;

    public FileReader(String location) throws SearchException {
        try {
            this.location = "G:\\Masters CS\\Study\\Fall 2019\\Artificial Intelligence\\Homework\\Homework1\\src\\input.txt";
            this.file = new File(this.location);
            this.bufferedReader = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            throw new SearchException(e.getMessage(), e);
        }
    }

    public Graph readFile() throws SearchException {
        Graph graph = new Graph();
        try {

            //Setting the algorithm to be used for search
            graph.setAlgorithm(this.bufferedReader.readLine());

            //Set rows and columns in the map topography
            String[] rowsAndColumns = this.bufferedReader.readLine().split(" ");
            graph.setColumns(Integer.parseInt(rowsAndColumns[0]));
            graph.setRows(Integer.parseInt(rowsAndColumns[1]));

            //Set landing coordinates of the rover
            String[] landingCoordinates = this.bufferedReader.readLine().split(" ");
            graph.setLandingCoordinate(landingCoordinates[0], landingCoordinates[1]);

            //Set max allowed elevation that the rover can move in any direction
            graph.setMaxAllowedElevation(Integer.parseInt(bufferedReader.readLine()));

            //Set the total number of target sites
            graph.setNumberOfTargetSites(Integer.parseInt(bufferedReader.readLine()));

            //Set coordinates for the all the target sites
            for (int j = 0; j < graph.getNumberOfTargetSites(); j++) {
                String[] targetCoordinates = this.bufferedReader.readLine().split(" ");
                graph.addTargetCoordinate(targetCoordinates[0], targetCoordinates[1]);
            }

            //Set nodes for the graph
            for (int x = 0; x < graph.getRows(); x++) {
                String[] elevations = this.bufferedReader.readLine().split(" ");
                for (int y = 0; y < graph.getColumns(); y++) {
                    Node node = new Node();
                    node.setElevation(Integer.parseInt(elevations[y]));
                    Coordinate coordinate = new Coordinate(y, x);
                    node.setCoordinate(coordinate);
                    graph.addNode(coordinate, node);
                }
            }
        } catch (FileNotFoundException e) {
            throw new SearchException(e.getMessage(), e);
        } catch (IOException e) {
            throw new SearchException(e.getMessage(), e);
        } finally {
            try {
                this.bufferedReader.close();
            } catch (IOException e) {
                throw new SearchException(e.getMessage(), e);
            }
        }
        return graph;
    }
}
