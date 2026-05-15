package AI;

import java.util.Scanner;

public class PrimsApplication {
    static final int INF = 99999;

    static int findMinVertex(int key[], boolean visited[], int vertices) {
        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static void prims(int graph[][], int vertices) {
        int parent[] = new int[vertices];
        int key[] = new int[vertices];
        boolean visited[] = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            key[i] = INF;
            visited[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {
            int u = findMinVertex(key, visited, vertices);

            if (u == -1) {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        int totalCost = 0;

        System.out.println("Cable connections selected:");

        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
            totalCost = totalCost + graph[i][parent[i]];
        }

        System.out.println("Total minimum cable cost = " + totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Application: Connecting Computer Labs with Minimum Cable Cost");

        System.out.print("Enter number of labs: ");
        int vertices = sc.nextInt();

        int graph[][] = new int[vertices][vertices];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        prims(graph, vertices);

        sc.close();
    }
}
