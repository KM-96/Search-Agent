package model;

public class Node {

    //The elevation of the node
    private int elevation;

    //Coordinates of the node
    private Coordinate coordinate;

    //Coordinates of the parent node
    private Coordinate parent;

    //The cost to travel to this node from the parent node
    private long cost;

    private long heuristicValue;

    private long costFunction;

    public Node() {

    }

    public Node(int elevation, Coordinate coordinate, long cost) {
        this.elevation = elevation;
        this.coordinate = coordinate;
        this.cost = cost;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getParent() {
        return parent;
    }

    public void setParent(Coordinate parent) {
        this.parent = parent;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", elevation=" + elevation +
                ", coordinate=" + coordinate +
                ", parent=" + parent +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return coordinate != null ? coordinate.equals(node.coordinate) : node.coordinate == null;
    }

    @Override
    public int hashCode() {
        return coordinate != null ? coordinate.hashCode() : 0;
    }

    public long getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(long heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public long getCostFunction() {
        return costFunction;
    }

    public void setCostFunction(long costFunction) {
        this.costFunction = costFunction;
    }
}