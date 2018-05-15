import alg.AlgorithmContext;
import alg.BellmanFordAlgorithm;
import graph.Graph;
import graph.Result;
import parser.Parser;
import printer.Printer;

public class Main {
    public static void main(String[] args) {

        String pathIn = args[0];
        String pathOut = args[1];
        String start = args[2];
        String end = args[3];


        Graph<String> graph = new Parser().parse(pathIn);
        AlgorithmContext context = new AlgorithmContext();
        context.setStrategy(new BellmanFordAlgorithm());

        Result<String> res = context.executeStrategy(graph, start);
        new Printer<String>().print(graph, res, end, pathOut);
    }
}
