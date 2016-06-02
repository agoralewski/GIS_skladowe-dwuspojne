package pl.goralewski.artur.GIS;

import pl.goralewski.artur.GIS.model.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Artur Góralewski.
 */
public class App {

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("Podaj nazwę pliku z listą krawędzi grafu");
        }else {
            String path = args[0];
            DataSourceFileReader dataSourceFileReader = new DataSourceFileReader(path);
            Set<Node> nodes = dataSourceFileReader.makeNodesFromFile();
            ExtraDFS extraDFS = new ExtraDFS(nodes);
            extraDFS.findTwoCoherentComponents().forEach(System.out::println);
            System.out.println("number of twoCoherentComponents: " + extraDFS.findTwoCoherentComponents().size());
        }
    }

}
