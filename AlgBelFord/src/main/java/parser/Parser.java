package parser;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import graph.Edge;
import graph.Graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class Parser {
    public Graph<String> parse(String path) {
        Graph<String> graph = new Graph<>();
        GraphParser parser = null;
        try {
            parser = new GraphParser(new FileInputStream(path));
            Map<String, GraphNode> nodes = parser.getNodes();
            Map<String, GraphEdge> edges = parser.getEdges();
            nodes.forEach((k, v) -> graph.addVertex(v.getId()));
            edges.forEach((k, v) -> graph
                    .getVertexNode(v.getNode1().getId())
                    .getEdges()
                    .add(new Edge<>(v.getNode2().getId(),
                            v.getAttribute("label") != null ? Integer.valueOf(v.getAttribute("label").toString()) : 0)));
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return graph;


    }
}
