package pl.goralewski.artur.GIS;

import junit.framework.TestCase;
import pl.goralewski.artur.GIS.model.Edge;
import pl.goralewski.artur.GIS.model.Node;
import pl.goralewski.artur.GIS.model.TwoCoherentComponent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur Góralewski.
 */
public class ExtraDFSTest extends TestCase {

    ExtraDFS extraDFS;

    public void setUp() throws Exception {
        super.setUp();
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

        extraDFS = new ExtraDFS(nodes);
    }

    public void tearDown() throws Exception {

    }

//    public void testFindTwoCoherentComponents() throws Exception {
//
//    }
//
//
//    public void testVisitNode() throws Exception {
//
//    }
//
//    public void testCreateTwoCoherentComponentFromStack() throws Exception {
//
//    }

    public void testTrivialGraph(){
        //trywialny
        List<Node> nodes = new LinkedList<>();
        Node node = new Node("Only one node, zero edges");
        nodes.add(node);
        extraDFS = new ExtraDFS(nodes);
        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
        assertEquals(1, twoCoherentComponents.size());
    }

    public void testIncoherentGraph(){
        //{1,2} {2,3}, {4} - niespojny
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Edge.connectTwoNodes(node1, node2);
        Edge.connectTwoNodes(node2, node3);
        List<Node> nodes = new LinkedList<Node>(){{add(node1); add(node2); add(node3); add(node4);}};
        extraDFS = new ExtraDFS(nodes);
        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
        assertEquals(3, twoCoherentComponents.size());
    }

    public void testCoherentGraph(){
        //{1,2} {2,3}, {2,4} - spojny
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Edge.connectTwoNodes(node1, node2);
        Edge.connectTwoNodes(node2, node3);
        Edge.connectTwoNodes(node2, node4);
        List<Node> nodes = new LinkedList<Node>(){{add(node1); add(node2); add(node3); add(node4);}};
        extraDFS = new ExtraDFS(nodes);
        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
        assertEquals(3, twoCoherentComponents.size());
    }

    public void testTwoPartCompleteGraph(){
        //K2,3 - dwudzielny zupelny
        //{1,3} {1,4}, {1,5}, {2,3} {2,4}, {2,5},
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Edge.connectTwoNodes(node1, node3);
        Edge.connectTwoNodes(node1, node4);
        Edge.connectTwoNodes(node1, node5);
        Edge.connectTwoNodes(node2, node3);
        Edge.connectTwoNodes(node2, node4);
        Edge.connectTwoNodes(node2, node5);
        List<Node> nodes = new LinkedList<Node>(){{add(node1); add(node2); add(node3); add(node4); add(node5);}};
        extraDFS = new ExtraDFS(nodes);
        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
        assertEquals(1, twoCoherentComponents.size());
    }

    public void testCycleGraph(){
        //{1,3} {1,4}, {1,5}, {2,3} {2,4}, {2,5},
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Edge.connectTwoNodes(node1, node2);
        Edge.connectTwoNodes(node2, node3);
        Edge.connectTwoNodes(node3, node4);
        Edge.connectTwoNodes(node4, node1);
        List<Node> nodes = new LinkedList<Node>(){{add(node1); add(node2); add(node3); add(node4);}};
        extraDFS = new ExtraDFS(nodes);
        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
        assertEquals(1, twoCoherentComponents.size());
    }

//    public void testGraph(){
//        List<Node> nodes = new LinkedList<>();
//
//        extraDFS = new ExtraDFS(nodes);
//        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
//        assertEquals(, twoCoherentComponents.size());
//    }
//
//    public void testGraph(){
//        List<Node> nodes = new LinkedList<>();
//
//        extraDFS = new ExtraDFS(nodes);
//        List<TwoCoherentComponent> twoCoherentComponents = extraDFS.findTwoCoherentComponents();
//        assertEquals(, twoCoherentComponents.size());
//    }
}