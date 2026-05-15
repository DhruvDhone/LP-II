package AI;

import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class MinimumSpanningTree {
    static int findParent(int parent[], int vertex) {
        if (parent[vertex] == vertex)
            return vertex;

        return findParent(parent, parent[vertex]);
    }

    static void union(int parent[], int u, int v) {
        int parentU = findParent(parent, u);
        int parentV = findParent(parent, v);

        parent[parentU] = parentV;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edgesCount = sc.nextInt();

        Edge edges[] = new Edge[edgesCount];

        for (int i = 0; i < edgesCount; i++) {
            System.out.println("Enter source, destination and weight of edge " + (i + 1) + ":");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            edges[i] = new Edge(src, dest, weight);
        }

        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        int parent[] = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        System.out.println("Edges in Minimum Spanning Tree:");

        for (int i = 0; i < edgesCount; i++) {
            int srcParent = findParent(parent, edges[i].src);
            int destParent = findParent(parent, edges[i].dest);

            if (srcParent != destParent) {
                System.out.println(edges[i].src + " - " + edges[i].dest + " : " + edges[i].weight);
                totalCost += edges[i].weight;
                union(parent, edges[i].src, edges[i].dest);
            }
        }

        System.out.println("Total cost of MST = " + totalCost);
    }
}
