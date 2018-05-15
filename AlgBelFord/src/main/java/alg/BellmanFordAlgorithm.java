package alg;

import graph.Edge;
import graph.Graph;
import graph.Result;

import java.util.*;
import java.util.stream.Stream;

public class BellmanFordAlgorithm<T> implements Algorithm<T> {

    static final int INF = Integer.MAX_VALUE;

    @Override
    public Result<T> execute(Graph<T> graph, T start) {
        return bellmanFord(graph,start);
    }

    public List<T> findNegativeCycle(Graph<T> graph) {

        List<Edge<T>>[] graph1 = Stream.generate(LinkedList::new).limit(graph.size()).toArray(List[]::new);
        graph.getGraph().forEach(tNode -> graph1[tNode.getNumber()].addAll(tNode.getEdges()));


        int n = graph1.length;
        int[] pred = new int[n];
        Arrays.fill(pred, -1);

        int[] dist = new int[n];

        int last = -1;
        for (int step = 0; step < n; step++) {
            last = -1;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) continue;
                for (Edge e : graph1[u]) {
                    int index = graph.indexOf((T) e.getVertex());
                    if (dist[index] > dist[u] + e.getCost()) {
                        dist[index] = Math.max(dist[u] + e.getCost(), -INF);
                        dist[index] = Math.max(dist[index], -INF);
                        pred[index] = u;
                        last = index;
                    }
                }
            }
            if (last == -1)
                return null;
        }
        for (int i = 0; i < n; i++) {
            last = pred[last];
        }

        int[] p = new int[n];
        int cnt = 0;
        for (int u = last; u != last || cnt == 0; u = pred[u]) {
            p[cnt++] = u;
        }

        int[] cycle = new int[cnt];
        for (int i = 0; i < cycle.length; i++) {
            cycle[i] = p[--cnt];
        }
        return normalize(cycle, graph);
    }

    public Result<T> bellmanFord(Graph<T> graph, T start) {

        List<Edge<T>>[] graph1 = Stream.generate(LinkedList::new).limit(graph.size()).toArray(List[]::new);
        graph.getGraph().forEach(tNode -> graph1[tNode.getNumber()].addAll(tNode.getEdges()));

        List<T> negativeCycle = findNegativeCycle(graph);
        if (negativeCycle != null)
            return new Result<T>(graph, negativeCycle);

        int[] dist = new int[graph.size()];
        int[] pred = new int[graph.size()];

        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
        dist[graph.indexOf(start)] = 0;
        int n = graph1.length;
        boolean updated = false;
        for (int step = 0; step < n; step++) {
            updated = false;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) continue;
                for (Edge e : graph1[u]) {
                    int index = graph.indexOf((T) e.getVertex());
                    if (dist[index] > dist[u] + e.getCost()) {
                        dist[index] = dist[u] + e.getCost();
                        dist[index] = Math.max(dist[index], -INF);
                        pred[index] = u;
                        updated = true;
                    }
                }
            }
            if (!updated)
                break;
        }

        return new Result<>(graph, dist, pred, start);
    }

    private List<T> normalize(int[] array, Graph<T> graph) {
        List<T> list = new LinkedList<>();
        Arrays.stream(array).forEach(i -> list.add(graph.getGraph().get(i).getVertex()));
        list.add(list.get(0));
        return list;
    }
}
