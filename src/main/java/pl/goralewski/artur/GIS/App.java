package pl.goralewski.artur.GIS;

import pl.goralewski.artur.GIS.model.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur Góralewski.
 */
public class App {

    public static void main(String[] args) {
        List<Node> nodes = new LinkedList<>();

        Node nodeZero = new Node("nodeZero");
        Node nodeOne = new Node("nodeOne");
        Node nodeTwo = new Node("nodeTwo");
        Node nodeThree = new Node("nodeThree");
        Node nodeFour = new Node("nodeFour");
        Node nodeFive = new Node("nodeFive");
        Node nodeSix = new Node("nodeSix");
        Node nodeSeven = new Node("nodeSeven");

        // {0,1}
        nodeZero.addNeighbour(nodeOne);
        nodeOne.addNeighbour(nodeZero);
        // {1,2}
        nodeOne.addNeighbour(nodeTwo);
        nodeTwo.addNeighbour(nodeOne);
        // {2,3}
        nodeTwo.addNeighbour(nodeThree);
        nodeThree.addNeighbour(nodeTwo);
        // {1,3}
        nodeOne.addNeighbour(nodeThree);
        nodeThree.addNeighbour(nodeOne);
        // {3,4}
        nodeThree.addNeighbour(nodeFour);
        nodeFour.addNeighbour(nodeThree);
        // {1,4}
        nodeOne.addNeighbour(nodeFour);
        nodeFour.addNeighbour(nodeOne);
        // {4,5}
        nodeFour.addNeighbour(nodeFive);
        nodeFive.addNeighbour(nodeFour);
        // {5,6}
        nodeFive.addNeighbour(nodeSix);
        nodeSix.addNeighbour(nodeFive);

        nodes.add(nodeZero);
        nodes.add(nodeOne);
        nodes.add(nodeTwo);
        nodes.add(nodeThree);
        nodes.add(nodeFour);
        nodes.add(nodeFive);
        nodes.add(nodeSix);
        nodes.add(nodeSeven);

        ExtraDFS extraDFS = new ExtraDFS(nodes);
        extraDFS.findTwoCoherentComponents().forEach(twoCoherentComponent ->
                System.out.println(twoCoherentComponent)
        );

        System.out.println("number of twoCoherentComponents: " + extraDFS.findTwoCoherentComponents().size());
//        System.out.println(nodes);
    }

}
