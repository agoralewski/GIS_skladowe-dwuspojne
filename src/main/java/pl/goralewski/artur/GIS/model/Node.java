package pl.goralewski.artur.GIS.model;

import java.util.LinkedHashSet;

/**
 * Created by Artur Góralewski.
 */
public class Node {

    String name;
    int num;
    int lowpt;
    LinkedHashSet<Node> neighbours;

    public Node(String name) {
        this.name = name;
        this.num = 0;
        this.lowpt = 0;
        this.neighbours = new LinkedHashSet<>();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLowpt() {
        return lowpt;
    }

    public void setLowpt(int lowpt) {
        this.lowpt = lowpt;
    }

    public LinkedHashSet<Node> getNeighbours() {
        return neighbours;
    }

    public boolean addNeighbour(Node neighbour) {
        return neighbours.add(neighbour);
    }

    public int numberOfNeighbours(){
        return neighbours.size();
    }

    public boolean isNotAlreadyVisited() {
        return num==0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Node{name='");
        stringBuilder.append(name).append("'");
        stringBuilder.append(", num=");
        stringBuilder.append(num);
        stringBuilder.append(", lowpt=");
        stringBuilder.append(lowpt);
        stringBuilder.append(", neighbours=[");
        int index = 0;
        for (Node node : neighbours) {
            stringBuilder.append((++index==1?"":", ")).append(node.getName());
        }
        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}
