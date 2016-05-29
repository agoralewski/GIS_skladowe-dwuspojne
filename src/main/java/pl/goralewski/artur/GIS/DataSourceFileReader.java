package pl.goralewski.artur.GIS;

import pl.goralewski.artur.GIS.model.Edge;
import pl.goralewski.artur.GIS.model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Artur Góralewski.
 */
public class DataSourceFileReader {

    //Input file which needs to be parsed
    String fileToParse;

    //Delimiter used in CSV file
    final String DELIMITER = " ";

    public DataSourceFileReader(String fileToParse) {
        this.fileToParse = fileToParse;
    }

    public Set<Node> makeNodesFromFile(){
        Map<String, Node> nodes = new HashMap<>();
        List<ArrayList<String>> edges = new LinkedList<>();
        BufferedReader fileReader = null;

        try{
            String line;
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));

            //Read the file line by line
            while ((line = fileReader.readLine()) != null && !line.isEmpty()){
                //Get all tokens available in line
                String[] edge = line.split(DELIMITER);
                nodes.put(edge[0], new Node(edge[0]));
                nodes.put(edge[1], new Node(edge[1]));
                edges.add(new ArrayList<String>(){{add(edge[0]); add(edge[1]);}});
            }
            edges.forEach(strings -> {
                if(!strings.get(0).equals(strings.get(1)))//node do not have self loop
                    Edge.connectTwoNodes(nodes.get(strings.get(0)), nodes.get(strings.get(1)));
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashSet<>(nodes.values());
    }
}
