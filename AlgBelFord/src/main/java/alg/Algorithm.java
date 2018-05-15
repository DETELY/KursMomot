package alg;

import graph.Graph;
import graph.Result;

public interface Algorithm<T> {
    Result<T> execute(Graph<T> graph, T start);
}
