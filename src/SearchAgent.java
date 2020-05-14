import astar.AStarImpl;
import bfs.BfsImpl;
import model.Coordinate;
import model.Graph;
import model.Node;
import ucs.UcsImpl;
import util.FileReader;
import util.FileWriter;
import util.SearchException;

public class SearchAgent {

    public static void main(String[] args) {
        try {
//            FileReader fileReader = new FileReader();
            FileReader fileReader = new FileReader("input.txt");
            Graph graph = fileReader.readFile();

            graph.createAdjacencyList();

            FileWriter fileWriter = new FileWriter();

            if ("BFS".equals(graph.getAlgorithm())) {
                StringBuilder outputString = new StringBuilder();
                for (Coordinate target : graph.getTargetCoordinates()) {
                    BfsImpl bfs = new BfsImpl();
                    bfs.traverse(graph, target);
                    bfs.buildShortestPath(graph, target);
                    if (bfs.getSolution().isEmpty()) {
                        outputString.append("FAIL");
                    } else {
                        while (!bfs.getSolution().empty()) {
                            Coordinate coordinate = bfs.getSolution().pop();
                            outputString.append(coordinate.getxCoordinate() + "," + coordinate.getyCoordinate() + " ");
                        }
                        outputString.deleteCharAt(outputString.lastIndexOf(" "));
                    }
                    outputString.append("\n");
                }
                fileWriter.writeToFile(outputString.toString());
            } else if ("UCS".equals(graph.getAlgorithm())) {
                StringBuilder outputString = new StringBuilder();
                for (Coordinate target : graph.getTargetCoordinates()) {
                    UcsImpl ucs = new UcsImpl(graph);
                    ucs.traverse(graph, new Coordinate(graph.getLandingCoordinate()), target);
                    ucs.buildShortestPath(graph, target);
                    if (ucs.getSolution().isEmpty()) {
                        outputString.append("FAIL");
                    } else {
                        while (!ucs.getSolution().empty()) {
                            Node node = ucs.getSolution().pop();
                            outputString.append(node.getCoordinate().getxCoordinate() + "," + node.getCoordinate().getyCoordinate() + " ");
                        }
                        outputString.deleteCharAt(outputString.lastIndexOf(" "));
                    }
                    outputString.append("\n");
                }
                fileWriter.writeToFile(outputString.toString());
            } else if ("A*".equals(graph.getAlgorithm())) {
                StringBuilder outputString = new StringBuilder();
                for (Coordinate target : graph.getTargetCoordinates()) {
                    AStarImpl aStar = new AStarImpl(graph);
                    aStar.traverse(graph, new Coordinate(graph.getLandingCoordinate()), target);
                    aStar.buildShortestPath(graph, target);
                    if (aStar.getSolution().isEmpty()) {
                        outputString.append("FAIL");
                    } else {
                        while (!aStar.getSolution().empty()) {
                            Node node = aStar.getSolution().pop();
                            outputString.append(node.getCoordinate().getxCoordinate() + "," + node.getCoordinate().getyCoordinate() + " ");
                        }
                        outputString.deleteCharAt(outputString.lastIndexOf(" "));
                    }
                    outputString.append("\n");
                }
                fileWriter.writeToFile(outputString.toString());
            }
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }
}