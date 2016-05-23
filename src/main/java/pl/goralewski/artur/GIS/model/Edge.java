package pl.goralewski.artur.GIS.model;

import java.util.Objects;

/**
 * Created by Artur Góralewski.
 */
public class Edge {
    Node beginning;
    Node end;

    public Edge(Node beginning, Node end) {
        this.beginning = beginning;
        this.end = end;
    }

    public Node getBeginning() {
        return beginning;
    }

    public Node getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "beginning=" + beginning +
                ", end=" + end +
                '}';
    }

    public static void connectTwoNodes(Node nodeOne, Node nodeTwo){
        nodeOne.addNeighbour(nodeTwo);
        nodeTwo.addNeighbour(nodeOne);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return beginning.equals(edge.beginning) && end.equals(edge.end);

    }

    @Override
    public int hashCode() {
        int result = beginning.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
