package AI;

import java.util.Scanner;

public class DijkstraApplication {
    static final int INF = 99999;

    static int findMinDistance(int distance[], boolean visited[], int vertices) {
        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static void dijkstra(int graph[][], int source, int vertices) {
        int distance[] = new int[vertices];
        boolean visited[] = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[source] = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = findMinDistance(distance, visited, vertices);

            if (u == -1) {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != INF
                        && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        System.out.println("City \t Shortest Distance from Source");

        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Application: Finding Shortest Distance from One City");

        System.out.print("Enter number of cities: ");
        int vertices = sc.nextInt();

        int graph[][] = new int[vertices][vertices];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source city: ");
        int source = sc.nextInt();

        dijkstra(graph, source, vertices);

        sc.close();
    }
}
