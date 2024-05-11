import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    private int vertices;

    // Constructor to initialize the graph with a given number of vertices
    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList.put(i, new ArrayList<>());
        }
    }

    // Method to create an edge between two vertices
    public void createEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
    }

    // Method to perform topological sort
    public void topologicalSort() {
        int[] totalIndegree = new int[vertices];

        // Calculate the total indegree for each vertex
        for (int i = 0; i < vertices; i++) {
            for (int j : adjacencyList.get(i)) {
                totalIndegree[j]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Add vertices with 0 indegree to the queue
        for (int i = 0; i < vertices; i++) {
            if (totalIndegree[i] == 0) {
                queue.add(i);
            }
        }

        int visitedNodes = 0;
        List<Integer> order = new ArrayList<>();

        // Perform BFS to traverse the graph and update indegrees
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int i : adjacencyList.get(u)) {
                totalIndegree[i]--;
                if (totalIndegree[i] == 0) {
                    queue.add(i);
                }
            }
            visitedNodes++;
        }

        // If not all vertices are visited, there's a cycle
        if (visitedNodes != vertices) {
            System.out.println("There's a cycle present in the Graph.\nGiven graph is not a DAG");
        } else {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.createEdge(0, 1);
        graph.createEdge(0, 2);
        graph.createEdge(1, 3);
        graph.createEdge(1, 5);
        graph.createEdge(2, 3);
        graph.createEdge(2, 5);
        graph.createEdge(3, 4);
        graph.createEdge(5, 4);
        graph.topologicalSort();
    }
}
