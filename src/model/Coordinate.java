    package model;

public class Coordinate {

    //This denotes the column
    private int xCoordinate;

    //This denotes the row
    private int yCoordinate;

    public Coordinate (Coordinate coordinate) {
        this.xCoordinate = coordinate.getxCoordinate();
        this.yCoordinate = coordinate.getyCoordinate();
    }

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }

    //Overriding the methods to make sure that the key in the nodes matches with the coordinates
    //Comparing the x and y coordinate in the equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (xCoordinate != that.xCoordinate) return false;
        return yCoordinate == that.yCoordinate;
    }

    @Override
    public int hashCode() {
        int result = xCoordinate;
        result = 31 * result + yCoordinate;
        return result;
    }
}
