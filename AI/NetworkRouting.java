package AI;
import java.util.*;

public class NetworkRouting {
    private int vertices;
    private LinkedList<Integer>[] adj;
    private String[] routerNames;

    NetworkRouting(int v) {
        vertices = v;
        adj = new LinkedList[v];
        routerNames = new String[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    void addRouter(int id, String name) {
        routerNames[id] = name;
    }

    void addConnection(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    void displayRouters() {
        System.out.println("\nRouters in Network:");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " - " + routerNames[i]);
        }
    }

    void displayNetwork() {
        System.out.println("\nNetwork Connections:");
        for (int i = 0; i < vertices; i++) {
            System.out.print(routerNames[i] + " -> ");
            for (int neighbor : adj[i]) {
                System.out.print(routerNames[neighbor] + " ");
            }
            System.out.println();
        }
    }

    void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.println("\nDFS Traversal starting from " + routerNames[start] + ":");
        DFSUtil(start, visited);
        System.out.println();
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(routerNames[v] + " ");

        for (int neighbor : adj[v]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<Integer>();

        visited[start] = true;
        queue.add(start);

        System.out.println("\nBFS Traversal starting from " + routerNames[start] + ":");

        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(routerNames[v] + " ");

            for (int neighbor : adj[v]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of routers: ");
        int n = sc.nextInt();
        sc.nextLine();

        NetworkRouting network = new NetworkRouting(n);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of router " + i + ": ");
            String name = sc.nextLine();
            network.addRouter(i, name);
        }

        System.out.print("\nEnter number of connections: ");
        int edges = sc.nextInt();

        network.displayRouters();

        for (int i = 0; i < edges; i++) {
            System.out.print("\nEnter first router index for connection " + (i + 1) + ": ");
            int a = sc.nextInt();

            System.out.print("Enter second router index for connection " + (i + 1) + ": ");
            int b = sc.nextInt();

            network.addConnection(a, b);
        }

        network.displayNetwork();
        network.displayRouters();

        System.out.print("\nEnter starting router index for traversal: ");
        int start = sc.nextInt();

        network.DFS(start);
        network.BFS(start);

        sc.close();
    }
}