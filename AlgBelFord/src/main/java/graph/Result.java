package graph;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Result<T> {
    private int[] distation;
    private int[] vertex;
    private T start;
    private Graph<T> graph;

    private List<T> negativeCircle;

    public Result(Graph<T> graph, int[] dist, int[] pred, T start) {
        distation = Arrays.copyOf(dist, dist.length);
        vertex = Arrays.copyOf(pred, pred.length);
        this.graph = graph;
        this.start = start;
    }

    public Result(Graph<T> graph, List<T> negativeCircle) {
        this.negativeCircle = new LinkedList<>();
        this.negativeCircle.addAll(negativeCircle);
        this.graph = graph;
    }

    public List<T> getWay(T end) {
        if (negativeCircle != null)
            return negativeCircle;
        int startIndex = graph.indexOf(start);
        int endIndex = -1;

        if (end == null)
            endIndex = vertex.length - 1;
        else
            endIndex = graph.indexOf(end);

        List<T> list = new LinkedList<>();
        int tmp = endIndex;
        do {
            list.add(graph.getGraph().get(tmp).getVertex());
            tmp = vertex[tmp];
        } while (tmp != startIndex && tmp != -1);
        list.add(graph.getGraph().get(startIndex).getVertex());
        Collections.reverse(list);
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Result{\n")
                .append(getWay(graph.getGraph().get(graph.size() - 1).getVertex()))
                .append("\n")
                .append(Arrays.toString(vertex))
                .append("\n}");
        return sb.toString();
    }
}

