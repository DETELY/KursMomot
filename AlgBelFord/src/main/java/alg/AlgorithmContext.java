package alg;

import graph.Graph;
import graph.Result;

public class AlgorithmContext<T> {

    static final int INF = Integer.MAX_VALUE;

    private Algorithm strategy;

    public void setStrategy(Algorithm strategy) {
        this.strategy = strategy;
    }

    public Result<T> executeStrategy(Graph<T> graph, T start) {
        if (strategy != null)
            return strategy.execute(graph, start);
        else
            throw new NullPointerException();
    }


}
