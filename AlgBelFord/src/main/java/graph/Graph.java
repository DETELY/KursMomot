package graph;

import java.util.*;


public class Graph<T> {

    private List<Node<T>> graph;

    {
        graph = new LinkedList<>();
    }

    public List<Node<T>> getGraph() {
        return graph;
    }

    public List<Edge<T>> getEdges(T vertex) {
        List<Edge<T>> edges = new LinkedList<>();
        if (vertex != null)
            graph.stream()
                    .filter(node -> node.getVertex().equals(vertex))
                    .findFirst()
                    .ifPresent(node -> edges.addAll(node.getEdges()));
        return edges;
    }

    public int size() {
        return graph.size();
    }

    public void addVertex(T vertex, List<Edge<T>> edges) {
        if (edges != null && vertex != null)
            graph.add(new Node<T>(graph.size(), vertex, edges));
    }

    public void addVertex(T vertex) {
        if (vertex != null)
            graph.add(new Node<T>(graph.size(), vertex));
    }

    public void addEdges(T vertex, Edge<T> edge) {
        if (vertex != null)
            graph.get(graph.indexOf(vertex)).getEdges().add(edge);
    }

    public void addEdges(T vertex, T endVertex, int cost) {
        if (vertex != null)
            graph.get(indexOf(vertex)).getEdges().add(new Edge<>(endVertex, cost));
    }

    public Node<T> getVertexNode(T vertex){
        return graph.get(indexOf(vertex));
    }


    public int indexOf(T vertex) {
        return graph.indexOf(new Node<>(vertex));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{");
        sb.append("graph=\n");
        graph.forEach(node -> sb.append(node.getVertex()).append(node.getEdges()).append("\n"));
        sb.append('}');
        return sb.toString();
    }


}

