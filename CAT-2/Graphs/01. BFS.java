import java.util.*;

public class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    // Create a graph with V vertices
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Add an edge to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // BFS algorithm to traverse the graph
    void BFS(int s) {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue and print it
            s = queue.poll();
            System.out.print(s + " ");
            // Get all adjacent vertices of the dequeued vertex s
            // If an adjacent vertex has not been visited, mark it visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        // Create a graph with 4 vertices
        Graph g = new Graph(4);
        // Add edges to the graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        // Perform BFS traversal starting from vertex 2
        System.out.println("Following is Breadth First Traversal (starting from vertex 2):");
        g.BFS(2);
    }
}
