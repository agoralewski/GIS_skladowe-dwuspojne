package pl.goralewski.artur.GIS.model;

import java.util.HashSet;

/**
 * Created by Artur Góralewski.
 */
public class TwoCoherentComponent {
    HashSet<Node> twoCoherentComponentNodes;
    Node cutNode;

    public TwoCoherentComponent() {
        this.twoCoherentComponentNodes = new HashSet<>();
    }

    //used only for TwoCoherentComponents which consist one node
    public TwoCoherentComponent(Node node) {
        twoCoherentComponentNodes = new HashSet<>();
        twoCoherentComponentNodes.add(node);
        cutNode = node;
    }

    public void addNodeToTwoCoherentComponent(Node node) {
        this.twoCoherentComponentNodes.add(node);
    }

    public void setCutNode(Node cutNode) {
        this.cutNode = cutNode;
    }

    @Override
    public String toString() {
        return "TwoCoherentComponent{" +
                "Nodes=" + twoCoherentComponentNodes +
                ", cutNode="+cutNode+
                '}';
    }
}
