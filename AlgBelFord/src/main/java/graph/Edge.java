package graph;

import java.util.Objects;

public class Edge<T> {
    private int cost;
    private T vertex;

    //region get/set
    public int getCost() {
        return cost;
    }


    public void setCost(int cost) {
        this.cost = cost;
    }

    public T getVertex() {
        return vertex;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }
    //endregion

    public Edge(T vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(vertex, edge.vertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", vertex=" + vertex +
                "}\n";
    }
}
