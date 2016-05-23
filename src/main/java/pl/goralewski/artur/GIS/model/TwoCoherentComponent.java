package pl.goralewski.artur.GIS.model;

import java.util.HashSet;

/**
 * Created by Artur Góralewski.
 */
public class TwoCoherentComponent {
    HashSet<Node> twoCoherentComponentNodes;

    public TwoCoherentComponent() {
        this.twoCoherentComponentNodes = new HashSet<>();
    }

    public TwoCoherentComponent(Node node) {
        twoCoherentComponentNodes = new HashSet<>();
        twoCoherentComponentNodes.add(node);
    }

    public void addNodeToTwoCoherentComponent(Node node) {
        this.twoCoherentComponentNodes.add(node);
    }

    @Override
    public String toString() {
        return "TwoCoherentComponent{" +
                "twoCoherentComponentNodes=" + twoCoherentComponentNodes +
                '}';
    }
}
