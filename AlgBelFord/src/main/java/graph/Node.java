package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node<T> {
    private int number = -1;
    private T vertex;
    private List<Edge<T>> edges;

    {
        edges = new LinkedList<>();
    }

    public Node(int number, T vertex, List<Edge<T>> edges) {
        this.number = number;
        this.vertex = vertex;
        this.edges.addAll(edges);
    }

    public Node(int number, T vertex) {
        this.number = number;
        this.vertex = vertex;
    }

    public Node(T vertex) {
        this.vertex = vertex;
    }

    public int getNumber() {
        return number;
    }

    public T getVertex() {
        return vertex;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(vertex, node.vertex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vertex);
    }
}
