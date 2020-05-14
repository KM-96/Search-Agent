package model;

import java.util.*;

public class Graph {

    private static int ADJACENT_COST = 10;
    private static int DIAGONAL_COST = 14;

    //Search algorithm to be implemented
    private String algorithm;

    //Number of columns
    private int columns;

    //Number of rows
    private int rows;

    //Maximum allowed difference in elevation between two adjacent cells
    private int maxAllowedElevation;

    //Number of target sites
    private int numberOfTargetSites;

    //The landing coordinates of the rover
    private Coordinate landingCoordinate;

    //List of target coordinates
    private List<Coordinate> targetCoordinates;

    //Elevations of each node
    private Map<Coordinate, Node> nodes;

    //Adjacency List that represents the graph
    private Map<Node, List<Node>> adjacencyList;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    public void setMaxAllowedElevation(int maxAllowedElevation) {
        this.maxAllowedElevation = maxAllowedElevation;
    }

    public int getNumberOfTargetSites() {
        return numberOfTargetSites;
    }

    public void setNumberOfTargetSites(int numberOfTargetSites) {
        this.numberOfTargetSites = numberOfTargetSites;
    }

    public Coordinate getLandingCoordinate() {
        return landingCoordinate;
    }

    public void setLandingCoordinate(String x, String y) {
        Coordinate coordinate = new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
        this.landingCoordinate = coordinate;
    }

    public List<Coordinate> getTargetCoordinates() {
        return targetCoordinates;
    }

    public void addTargetCoordinate(String x, String y) {
        if (targetCoordinates == null) {
            targetCoordinates = new ArrayList<>();
        }
        targetCoordinates.add(new Coordinate(Integer.parseInt(x), Integer.parseInt(y)));
    }

    public Map<Coordinate, Node> getNodes() {
        return nodes;
    }

    public Map<Node, List<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public void addNode(Coordinate coordinate, Node node) {
        if (this.nodes == null) {
            this.nodes = new HashMap<>();
        }
        this.nodes.put(coordinate, node);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "algorithm='" + algorithm + '\'' +
                ", columns=" + columns +
                ", rows=" + rows +
                ", maxAllowedElevation=" + maxAllowedElevation +
                ", numberOfTargetSites=" + numberOfTargetSites +
                ", landingCoordinate=" + landingCoordinate +
                ", targetCoordinates=" + targetCoordinates +
                ", nodes=" + nodes +
                ", adjacencyList=" + adjacencyList +
                '}';
    }

    public void createAdjacencyListForBfs() {
        for (Map.Entry<Coordinate, Node> node : this.nodes.entrySet()) {
            List<Node> neighbours = new ArrayList<>();

            int x = node.getValue().getCoordinate().getxCoordinate();
            int y = node.getValue().getCoordinate().getyCoordinate();
            int elevation = node.getValue().getElevation();

            //Neighbour on the east
            if (x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) {
                Node neighbour = this.nodes.get(new Coordinate(x + 1, y));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }

            //Neighbour on the south
            if (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE) {
                Node neighbour = this.nodes.get(new Coordinate(x, y + 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the south-east
            if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) &&
                    (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
                Node neighbour = this.nodes.get(new Coordinate(x + 1, y + 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the west
            if (x - 1 >= 0 && x - 1 < this.columns) {
                Node neighbour = this.nodes.get(new Coordinate(x - 1, y));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north
            if (y - 1 >= 0 && y - 1 < this.rows) {
                Node neighbour = this.nodes.get(new Coordinate(x, y - 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north-west
            if ((x - 1 >= 0 && x - 1 < this.columns) && (y - 1 >= 0 && y - 1 < this.rows)) {
                Node neighbour = this.nodes.get(new Coordinate(x - 1, y - 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north-east
            if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) && (y - 1 >= 0 && y - 1 < this.rows)) {
                Node neighbour = this.nodes.get(new Coordinate(x + 1, y - 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the south-west
            if ((x - 1 >= 0 && x - 1 < this.columns) && (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
                Node neighbour = this.nodes.get(new Coordinate(x - 1, y + 1));
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbours.add(neighbour);
                }
            }

            this.adjacencyList.put(this.nodes.get(new Coordinate(x, y)), neighbours);
        }
    }

    public void createAdjacencyListForUcs() {
        for (Map.Entry<Coordinate, Node> node : this.nodes.entrySet()) {
            List<Node> neighbours = new ArrayList<>();

            int x = node.getValue().getCoordinate().getxCoordinate();
            int y = node.getValue().getCoordinate().getyCoordinate();
            int elevation = node.getValue().getElevation();
            node.getValue().setCost(0L);

            //Neighbour on the east
            if (x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) {
                Node n = this.nodes.get(new Coordinate(x + 1, y));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(ADJACENT_COST);
                    neighbours.add(neighbour);
                }
            }

            //Neighbour on the south
            if (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE) {
                Node n = this.nodes.get(new Coordinate(x, y + 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(ADJACENT_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the south-east
            if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) &&
                    (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
                Node n = this.nodes.get(new Coordinate(x + 1, y + 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(DIAGONAL_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the west
            if (x - 1 >= 0 && x - 1 < this.columns) {
                Node n = this.nodes.get(new Coordinate(x - 1, y));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(ADJACENT_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north
            if (y - 1 >= 0 && y - 1 < this.rows) {
                Node n = this.nodes.get(new Coordinate(x, y - 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(ADJACENT_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north-west
            if ((x - 1 >= 0 && x - 1 < this.columns) && (y - 1 >= 0 && y - 1 < this.rows)) {
                Node n = this.nodes.get(new Coordinate(x - 1, y - 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(DIAGONAL_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the north-east
            if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) && (y - 1 >= 0 && y - 1 < this.rows)) {
                Node n = this.nodes.get(new Coordinate(x + 1, y - 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(DIAGONAL_COST);
                    neighbours.add(neighbour);
                }
            }


            //Neighbour on the south-west
            if ((x - 1 >= 0 && x - 1 < this.columns) && (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
                Node n = this.nodes.get(new Coordinate(x - 1, y + 1));
                Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
                if (Math.abs(neighbour.getElevation() - elevation) <= this.maxAllowedElevation) {
                    neighbour.setCost(DIAGONAL_COST);
                    neighbours.add(neighbour);
                }
            }

            this.adjacencyList.put(this.nodes.get(new Coordinate(x, y)), neighbours);
        }
    }

    public void createAdjacencyList() {
        this.adjacencyList = new HashMap<>();
        if ("BFS".equalsIgnoreCase(this.getAlgorithm())) {
            this.createAdjacencyListForBfs();
        } else if ("UCS".equalsIgnoreCase(this.getAlgorithm())) {
            this.createAdjacencyListForUcs();
        }
    }


    public List<Node> getNeighbours(Node node, Coordinate target) {
        List<Node> neighbours = new LinkedList<>();
        int x = node.getCoordinate().getxCoordinate();
        int y = node.getCoordinate().getyCoordinate();
        int elevation = node.getElevation();

        //Neighbour on the east
        if (x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) {
            Node n = this.nodes.get(new Coordinate(x + 1, y));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(ADJACENT_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }

        //Neighbour on the south
        if (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE) {
            Node n = this.nodes.get(new Coordinate(x, y + 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(ADJACENT_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the south-east
        if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) &&
                (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
            Node n = this.nodes.get(new Coordinate(x + 1, y + 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(DIAGONAL_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the west
        if (x - 1 >= 0 && x - 1 < this.columns) {
            Node n = this.nodes.get(new Coordinate(x - 1, y));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(ADJACENT_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the north
        if (y - 1 >= 0 && y - 1 < this.rows) {
            Node n = this.nodes.get(new Coordinate(x, y - 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(ADJACENT_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the north-west
        if ((x - 1 >= 0 && x - 1 < this.columns) && (y - 1 >= 0 && y - 1 < this.rows)) {
            Node n = this.nodes.get(new Coordinate(x - 1, y - 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(DIAGONAL_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the north-east
        if ((x + 1 >= 0 && x + 1 < this.columns && x != Integer.MAX_VALUE) && (y - 1 >= 0 && y - 1 < this.rows)) {
            Node n = this.nodes.get(new Coordinate(x + 1, y - 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(DIAGONAL_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }


        //Neighbour on the south-west
        if ((x - 1 >= 0 && x - 1 < this.columns) && (y + 1 >= 0 && y + 1 < this.rows && y != Integer.MAX_VALUE)) {
            Node n = this.nodes.get(new Coordinate(x - 1, y + 1));
            Node neighbour = new Node(n.getElevation(), n.getCoordinate(), n.getCost());
            long cost = Math.abs(neighbour.getElevation() - elevation);
            if (cost <= this.maxAllowedElevation) {
                neighbour.setCost(DIAGONAL_COST + cost);
                neighbour.setHeuristicValue(getEuclideanDistance(n.getCoordinate(), target));
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    private long getEuclideanDistance(Coordinate c1, Coordinate c2) {
        int x1 = c1.getxCoordinate();
        int x2 = c2.getxCoordinate();
        int y1 = c1.getyCoordinate();
        int y2 = c2.getyCoordinate();
        return (long) ((Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) * 10));
    }
}
