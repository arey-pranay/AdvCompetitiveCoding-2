import java.util.*;

public class Main {

    static class CreateGraph {
        int V, E;
        LinkedList<Edge> edgeList[];

        CreateGraph(int v, int e) {
            V = v;
            E = e;
            edgeList = new LinkedList[v];
            for (int i = 0; i < v; i++)
                edgeList[i] = new LinkedList<>();
        }

        // Function to add edges to the graph
        void addEdge(int src, int dest, int weight) {
            edgeList[src].add(new Edge(src, dest, weight));
        }

        // Bellman-Ford algorithm implementation
        void BellmanFord(int src) {
            int dist[] = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Relax all edges |V| - 1 times
            for (int i = 1; i < V; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = edgeList[j].getFirst().src;
                    int v = edgeList[j].getFirst().dest;
                    int w = edgeList[j].getFirst().weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                        dist[v] = dist[u] + w;
                }
            }

            // Check for negative-weight cycles
            for (int j = 0; j < E; ++j) {
                int u = edgeList[j].getFirst().src;
                int v = edgeList[j].getFirst().dest;
                int w = edgeList[j].getFirst().weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }

            // Print the distances
            printSolution(dist);
        }

        void printSolution(int dist[]) {
            System.out.println("Vertex \t Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println(i + "\t\t" + dist[i]);
        }
    }

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 7;
        CreateGraph graph = new CreateGraph(V, E);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 1, 6);
        graph.addEdge(3, 2, 2);
        graph.addEdge(1, 4, -4);
        graph.addEdge(4, 2, 2);
        graph.BellmanFord(0); // 0 is the source vertex
    }
}
