package pl.goralewski.artur.GIS;

import pl.goralewski.artur.GIS.model.Edge;
import pl.goralewski.artur.GIS.model.Node;
import pl.goralewski.artur.GIS.model.TwoCoherentComponent;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Artur Góralewski.
 */
public class ExtraDFS {

    int counter;
    Stack<Edge> edgeStack;
    List<Node> nodes;
    List<TwoCoherentComponent> twoCoherentComponents;

    public ExtraDFS(List<Node> nodes) {
        this.counter=0;
        this.edgeStack = new Stack<>();
        this.nodes = nodes;
        this.twoCoherentComponents = new LinkedList<>();
    }

    public List<TwoCoherentComponent> findTwoCoherentComponents(){
        nodes.stream().
                filter(Node::isNotAlreadyVisited).
                forEach(node -> {
                    visitNode(node, null);
                });
        return twoCoherentComponents;
    }

    public void visitNode(Node currentNode, Node neighbour){
        counter++;
        currentNode.setNum(counter);
        currentNode.setLowpt(counter);
        if(currentNode.numberOfNeighbours()>0) {
            currentNode.getNeighbours().forEach(node -> {
                if (node.isNotAlreadyVisited()) {
                    //(currentNode,node) jest krawêdzi¹ drzewa
                    edgeStack.push(new Edge(currentNode, node));
                    visitNode(node, currentNode);
                    currentNode.setLowpt(Integer.min(currentNode.getLowpt(), node.getLowpt()));
                    if (node.getLowpt() >= currentNode.getNum()) {
                        //wierzcholek currentNode jest albo korzeniem drzewa
                        //albo punktem rozcinajacym
                        twoCoherentComponents.add(createTwoCoherentComponentFromStack(new Edge(currentNode, node)));
                    }
                } else {
                    if (node.getNum() < currentNode.getNum() && node != neighbour) {
                        //(currentNode,node) jest krawêdzi¹ zwrotn¹
                        edgeStack.push(new Edge(currentNode, node));
                        currentNode.setLowpt(Integer.min(currentNode.getLowpt(), node.getNum()));
                    }
                }
            });
        }else{
            twoCoherentComponents.add(new TwoCoherentComponent(currentNode));
        }
    }

    //wszystkie krawêdzie nad krawêdzi¹ bêd¹c¹ argumentem funcji wraz z ni¹
    //zdejmuje ze stosu i robi skladow¹ dwuspójn¹
    public TwoCoherentComponent createTwoCoherentComponentFromStack(Edge popWithThisEdge) {
        TwoCoherentComponent twoCoherentComponent = new TwoCoherentComponent();
        edgeStack.forEach(System.out::println);

        System.out.println(edgeStack.search(popWithThisEdge)+"\n");
        int howManyEdgesToPop = edgeStack.search(popWithThisEdge);
        for (int i = 1; i <= howManyEdgesToPop; i++) {
            System.out.println(edgeStack.search(popWithThisEdge));
            Edge edge = edgeStack.pop();
            System.out.println(i + "------" + edge);
            twoCoherentComponent.addNodeToTwoCoherentComponent(edge.getBeginning());
            twoCoherentComponent.addNodeToTwoCoherentComponent(edge.getEnd());
        }
        System.out.println("--" + twoCoherentComponent);
        return twoCoherentComponent;
    }
}
