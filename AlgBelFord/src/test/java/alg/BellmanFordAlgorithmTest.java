package alg;

import graph.Edge;
import graph.Graph;
import graph.Result;
import junit.framework.TestCase;
import parser.Parser;
import printer.Printer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BellmanFordAlgorithmTest extends TestCase {

    public void testBellmanParser() {
        Graph<String> graph = new Parser().parse("example/pol.dot");

        BellmanFordAlgorithm<String> bfa = new BellmanFordAlgorithm<>();

        Result res = new Result(graph,new LinkedList());

        new Printer<String>().print(graph, res, "6", "example/testParser.png");
    }

    public void testBellmanFord() {

        Graph<Integer> graph = new Graph<>();


        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);

        graph.addEdges(1, 5, 1);
        graph.addEdges(1, 3, 2);
        graph.addEdges(1, 4, 0);
        graph.addEdges(1, 2, 4);

        graph.addEdges(2, 5, 6);

        graph.addEdges(4, 2, -3);
        graph.addEdges(4, 3, 4);
        graph.addEdges(4, 7, -5);
        graph.addEdges(4, 6, 10);

        graph.addEdges(5, 6, 3);
        graph.addEdges(5, 3, 2);

        graph.addEdges(7, 6, 10);
        graph.addEdges(7, 3, 7);
        graph.addEdges(7, 5, -1);


        System.out.println(graph);
        System.out.println("\n\n\n\n");

        BellmanFordAlgorithm<Integer> bfa = new BellmanFordAlgorithm<>();

        System.out.println(bfa.findNegativeCycle(graph));
        System.out.println("\n\n\n\n");

        Result res = bfa.bellmanFord(graph, 1);

        new Printer<Integer>().print(graph, res, 6, "example/test1.png");
    }

    public void testBellmanFord1() {

        Graph<Integer> graph = new Graph<>();


        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdges(1, 3, 2);

        graph.addEdges(3, 2, -1);


        System.out.println(graph);
        System.out.println("\n\n\n\n");

        BellmanFordAlgorithm<Integer> bfa = new BellmanFordAlgorithm<>();

        System.out.println(bfa.findNegativeCycle(graph));
        System.out.println("\n\n\n\n");


        Result res = bfa.bellmanFord(graph, 1);
        new Printer<Integer>().print(graph, res, 2, "example/test2.png");

    }

    public void testBellmanFord2() {

        Graph<Integer> graph = new Graph<>();


        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdges(1, 2, -2);
        graph.addEdges(1, 3, -3);
        graph.addEdges(1, 4, 10);

        graph.addEdges(2, 3, -2);
        graph.addEdges(2, 4, 12);

        graph.addEdges(3, 4, 5);


        System.out.println(graph);
        System.out.println("\n\n\n\n");

        BellmanFordAlgorithm<Integer> bfa = new BellmanFordAlgorithm<>();

        System.out.println(bfa.findNegativeCycle(graph));
        System.out.println("\n\n\n\n");


        int[] dist = new int[graph.size()];
        int[] pred = new int[graph.size()];

        System.out.println(bfa.bellmanFord(graph, 4));
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(pred));

    }

    public void testBellmanFord3() {

        Graph<Integer> graph = new Graph<>();


        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 4, 8);
        graph.addEdges(1, 3, 1);

        graph.addEdges(2, 6, 5);

        graph.addEdges(3, 6, 6);

        graph.addEdges(4, 6, 4);
        graph.addEdges(4, 5, 2);

        graph.addEdges(5, 2, 5);

        graph.addEdges(6, 1, 10);
        graph.addEdges(6, 5, 17);


        System.out.println(graph);
        System.out.println("\n\n\n\n");

        BellmanFordAlgorithm<Integer> bfa = new BellmanFordAlgorithm<>();

        System.out.println(bfa.findNegativeCycle(graph));
        System.out.println("\n\n\n\n");

        Result res = bfa.bellmanFord(graph, 1);
        //System.out.println();
        new Printer<Integer>().print(graph, res, 5, "example/test4.png");


    }

    public void testBellmanFordNeg4() {
        Graph<String> graph = new Graph<>();

        List<Edge<String>> edges = new LinkedList();

        edges.add(new Edge<>("b", 6));
        edges.add(new Edge<>("c", 7));
        graph.addVertex("a", edges);
        edges.clear();

        edges.add(new Edge<>("d", 5));
        edges.add(new Edge<>("e", -4));
        edges.add(new Edge<>("c", -8));
        graph.addVertex("b", edges);
        edges.clear();


        edges.add(new Edge<>("d", -3));
        edges.add(new Edge<>("e", 9));
        graph.addVertex("c", edges);
        edges.clear();


        edges.add(new Edge<>("b", -2));
        graph.addVertex("d", edges);
        edges.clear();


        edges.add(new Edge<>("a", 2));
        edges.add(new Edge<>("d", 7));
        graph.addVertex("e", edges);
        edges.clear();

        System.out.println(graph);
        System.out.println("\n\n\n\n");

        BellmanFordAlgorithm<String> bfa = new BellmanFordAlgorithm<>();

        System.out.println(bfa.findNegativeCycle(graph));
        System.out.println("\n\n\n\n");

        Result res = bfa.bellmanFord(graph, "a");
        System.out.println(res);

        new Printer<String>().print(graph, res, "e", "example/test5Neg.png");
    }
}