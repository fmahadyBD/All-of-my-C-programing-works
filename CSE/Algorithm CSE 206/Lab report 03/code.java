import java.util.Scanner;

public class SecondBestMST {
    private static final int V = 5;

    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    void primMST(int graph[][]) {
        int parent[] = new int[V];
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        SecondBestMST t = new SecondBestMST();
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.println("Enter the number of vertices:");
        n = sc.nextInt();
        int graph[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Enter the weight of edge from vertex " + i + " to vertex " + j + ":");
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("First Best Minimum Spanning Tree:");
        t.primMST(graph);

        int firstBestWeight = 0;
        for (int i = 1; i < V; i++) {
            firstBestWeight += graph[i][t.minKey(graph[i], new Boolean[V])];
        }

        int secondBestWeight = Integer.MAX_VALUE;
        int[] secondBestTree = new int[V];
        for (int i = 1; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (graph[i][j] != 0) {
                    int temp = graph[i][j];
                    graph[i][j] = graph[j][i] = 0;

                    SecondBestMST t2 = new SecondBestMST();
                    t2.primMST(graph);

                    int weight = 0;
                    for (int k = 1; k < V; k++) {
                        weight += graph[k][t2.minKey(graph[k], new Boolean[V])];
                    }

                    if (weight > firstBestWeight && weight < secondBestWeight) {
                        secondBestWeight = weight;
                        secondBestTree = graph[i];
                    }

                    graph[i][j] = graph[j][i] = temp;
                }
            }
        }

        System.out.println("Second Best Minimum Spanning Tree:");
        t.primMST(secondBestTree);
    }
}
