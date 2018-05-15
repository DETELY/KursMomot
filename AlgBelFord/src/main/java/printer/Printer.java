package printer;

import graph.Edge;
import graph.Result;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Label;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

public class Printer<T> {
    public void print(graph.Graph<T> gr, Result<T> result, T end, String path) {
        try {
            Graph g = graph("example1").directed();
            List<T> list = result.getWay(end);
            Map<T, T> map = new HashMap<>();
            for (int i = 0; i < list.size() - 1; i++) {
                map.put(list.get(i), list.get(i + 1));
            }
            for (graph.Node<T> node : gr.getGraph()) {
                Node n = node(node.getVertex().toString());
                for (Edge edge : node.getEdges()) {
                    if (map.get(node.getVertex()) != null && map.get(node.getVertex()).equals(edge.getVertex()))
                        n = n.link(to(node(edge.getVertex().toString()))
                                .with(Color.RED, Label.of(String.valueOf(edge.getCost()))));
                    else
                        n = n.link(to(node(edge.getVertex().toString()))
                                .with(Label.of(String.valueOf(edge.getCost()))));
                }
                g = g.with(n);
            }
            Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
