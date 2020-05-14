package bfs;

import model.Coordinate;
import model.Graph;
import model.Node;

import java.util.*;

public class BfsImpl {
    private Stack<Coordinate> solution;
    private Set<Coordinate> visited;

    public BfsImpl() {
        visited = new HashSet<>();
    }

    public Stack<Coordinate> getSolution() {
        return solution;
    }

    public void traverse(Graph graph, Coordinate targetCoordinate) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(graph.getLandingCoordinate());
        visited.add(graph.getLandingCoordinate());
        while (true) {
            if (queue.isEmpty()) {
                return;
            }
            Coordinate coordinate = queue.remove();
            if (coordinate.equals(targetCoordinate)) {
                return;
            }
            Node node = graph.getNodes().get(coordinate);
            List<Node> neighbours = graph.getAdjacencyList().get(node);
            for (Node neighbour : neighbours) {
                if (!visited.contains(neighbour.getCoordinate())) {
                    queue.add(neighbour.getCoordinate());
                    graph.getNodes().get(neighbour.getCoordinate()).setParent(coordinate);
                    visited.add(neighbour.getCoordinate());
                }
            }
        }
    }

    public void buildShortestPath(Graph graph, Coordinate target) {
        solution = new Stack<>();
        solution.add(target);
        while (true) {
            Coordinate coordinate = solution.peek();
            if (coordinate == null || coordinate.equals(graph.getLandingCoordinate())) {
                return;
            }
            if (graph.getNodes().get(coordinate).getParent() == null) {
                solution.clear();
                return;
            }
            solution.add(graph.getNodes().get(coordinate).getParent());
        }
    }
}