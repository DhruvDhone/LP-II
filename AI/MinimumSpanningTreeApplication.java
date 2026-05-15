package AI;

import java.util.*;

class RoadEdge {
    int source;
    int destination;
    int cost;

    RoadEdge(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }
}

public class MinimumSpanningTreeApplication {

    static int findParent(int parent[], int vertex) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return findParent(parent, parent[vertex]);
    }

    static void union(int parent[], int u, int v) {
        int parentU = findParent(parent, u);
        int parentV = findParent(parent, v);
        parent[parentU] = parentV;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Application: Connecting Cities with Minimum Road Cost");

        System.out.print("Enter number of cities: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of roads: ");
        int edgesCount = sc.nextInt();

        RoadEdge edges[] = new RoadEdge[edgesCount];

        System.out.println("Enter source city, destination city and road cost:");
        for (int i = 0; i < edgesCount; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new RoadEdge(source, destination, cost);
        }

        Arrays.sort(edges, (a, b) -> a.cost - b.cost);

        int parent[] = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int selectedEdges = 0;

        System.out.println("Roads selected for minimum cost connection:");

        for (int i = 0; i < edgesCount && selectedEdges < vertices - 1; i++) {
            int sourceParent = findParent(parent, edges[i].source);
            int destinationParent = findParent(parent, edges[i].destination);

            if (sourceParent != destinationParent) {
                System.out.println(edges[i].source + " - " + edges[i].destination + " : " + edges[i].cost);

                totalCost = totalCost + edges[i].cost;
                selectedEdges++;

                union(parent, edges[i].source, edges[i].destination);
            }
        }

        System.out.println("Total minimum road cost = " + totalCost);
    }
}
