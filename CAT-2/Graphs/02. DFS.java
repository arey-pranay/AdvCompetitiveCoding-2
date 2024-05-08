import java.util.*;

class Graph {
    private LinkedList<Integer> adjLists[];
    private boolean visited[];

    // Graph creation
    Graph(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<>();
    }

    // Add edges to the graph
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // DFS algorithm
    void DFS(int vertex) {
        // Mark the current vertex as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all vertices adjacent to this vertex
        Iterator<Integer> ite = adjLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj);
        }
    }

    public static void main(String args[]) {
        // Create a graph with 4 vertices
        Graph g = new Graph(4);
        // Add edges to the graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        // Perform DFS traversal starting from vertex 2
        System.out.println("Following is Depth First Traversal:");
        g.DFS(2);
    }
}
