package ucs;

import model.Coordinate;
import model.Graph;
import model.Node;

import java.util.*;

public class UcsImpl {
    private Stack<Node> solution;

    public Stack<Node> getSolution() {
        return solution;
    }

    private Map<Coordinate, Node> nodes;

    private Map<Node, List<Node>> adjacencyList;

    public UcsImpl(Graph graph) {
        solution = new Stack<>();
        this.nodes = new HashMap<>(graph.getNodes());
        this.adjacencyList = new HashMap<>(graph.getAdjacencyList());
    }

    public void traverse(Graph graph, Coordinate landingCoordinate, Coordinate targetCoordinate) {
        Map<Coordinate, Node> closedQueue = new HashMap<>();

        PriorityQueue<Node> openQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getCost() <= o2.getCost())
                    return -1;
                if (o1.getCost() > o2.getCost())
                    return 1;
                return 0;
            }
        });
        Node source = this.nodes.get(landingCoordinate);
        openQueue.add(source);
        while (true) {
            if (openQueue.isEmpty()) {
                return;
            }
            Node node = openQueue.remove();
            if (node.getCoordinate().equals(targetCoordinate)) {
                return;
            }

            //Getting the details of the neighbours of the current node
            List<Node> neighbours = new ArrayList<>(this.adjacencyList.get(node));

            for (Node neighbour : neighbours) {
                if (!openQueue.contains(neighbour) && !closedQueue.containsKey(neighbour.getCoordinate())) {
                    neighbour = new Node(/*neighbour.getNodeId(), */neighbour.getElevation(), neighbour.getCoordinate(), neighbour.getCost());
                    this.nodes.get(neighbour.getCoordinate()).setParent(node.getCoordinate());
                    this.nodes.get(neighbour.getCoordinate()).setCost(neighbour.getCost() + node.getCost());
                    neighbour.setParent(node.getCoordinate());
                    neighbour.setCost(neighbour.getCost() + node.getCost());
                    openQueue.add(neighbour);
                } else if (openQueue.contains(neighbour)) {
                    Node nodeInOpenQueue = this.nodes.get(neighbour.getCoordinate());
                    if (neighbour.getCost() + node.getCost() < nodeInOpenQueue.getCost()) {
                        this.nodes.get(neighbour.getCoordinate()).setParent(node.getCoordinate());
                        this.nodes.get(neighbour.getCoordinate()).setCost(neighbour.getCost() + node.getCost());
                        neighbour.setParent(node.getCoordinate());
                        neighbour.setCost(neighbour.getCost() + node.getCost());
                        openQueue.remove(nodeInOpenQueue);
                        openQueue.add(neighbour);
                    }
                } else if (closedQueue.containsKey(neighbour.getCoordinate())) {
                    Node nodeInClosedQueue = closedQueue.get(neighbour.getCoordinate());
                    if (neighbour.getCost() + node.getCost() < nodeInClosedQueue.getCost()) {
                        closedQueue.remove(nodeInClosedQueue);
                        neighbour.setParent(node.getCoordinate());
                        neighbour.setCost(neighbour.getCost() + node.getCost());
                        openQueue.add(neighbour);
                    }
                }
            }
            closedQueue.put(node.getCoordinate(), node);
        }
    }


    public void buildShortestPath(Graph graph, Coordinate target) {
        solution = new Stack<>();
        solution.add(this.nodes.get(target));
        while (true) {
            Node node = solution.peek();
            if (node == null || node.getCoordinate().equals(graph.getLandingCoordinate())) {
                return;
            }
            if (node.getParent() == null) {
                solution.clear();
                return;
            }
            solution.add(this.nodes.get(node.getParent()));
        }
    }
}