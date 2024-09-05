import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

class Graph {
    private int V;
    private int edge;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        edge++;
    }

    void print() {
        String tmp = "";
        for (int i = 0; i <this.V; i++){
            tmp += i + "-->" + adj[i].toString() +"\n";
        }
        tmp += "vertex=" + V + " , edge=" + edge;
        System.out.println(tmp);

        System.out.println("--------------------------------------------");
    }

    void time(long start, long end) {
        System.out.println("spend " + (end - start) + "ms");
        System.out.println("--------------------------------------------");
    }

    // brute force
    void bruteForceVertexCover() {
        Set<Integer> minVertexCover = null;

        for (int i = 0; i < (1 << V); i++) {
            Set<Integer> currentVertexCover = new HashSet<>();
            boolean[] visited = new boolean[V];

            for (int j = 0; j < V; j++) {
                if ((i & (1 << j)) != 0) {
                    currentVertexCover.add(j);
                    visited[j] = true;

                    Iterator<Integer> iterator = adj[j].iterator();
                    while (iterator.hasNext()) {
                        int neighbor = iterator.next();
                        visited[neighbor] = true;
                    }
                }
            }

            if (isValidVertexCover(visited) && (minVertexCover == null || currentVertexCover.size() < minVertexCover.size())) {
                minVertexCover = new HashSet<>(currentVertexCover);
            }
        }

        printResult("Brute Force", minVertexCover);
    }

    // greedy
    void greedyVertexCover() {
        Set<Integer> greedyVertexCover = new HashSet<>();
        boolean[] visited = new boolean[V];

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                Iterator<Integer> iterator = adj[u].iterator();
                while (iterator.hasNext()) {
                    int v = iterator.next();
                    if (!visited[v]) {
                        visited[u] = true;
                        visited[v] = true;
                        greedyVertexCover.add(u);
                        greedyVertexCover.add(v);
                        break;
                    }
                }
            }
        }

        printResult("Greedy", greedyVertexCover);
    }

    // greedy approximation
    void greedyApproxVertexCover() {
        Set<Integer> approxGreedyVertexCover = new HashSet<>();
        boolean[] visited = new boolean[V];

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                Iterator<Integer> iterator = adj[u].iterator();
                while (iterator.hasNext()) {
                    int v = iterator.next();
                    if (!visited[v]) {
                        visited[u] = true;
                        visited[v] = true;
                        approxGreedyVertexCover.add(u);
                        approxGreedyVertexCover.add(v);
                        break;
                    }
                }
            }
        }

        printResult("Greedy Approximation", approxGreedyVertexCover);
    }

    // print results
    private void printResult(String algorithm, Set<Integer> vertexCover) {
        System.out.print(algorithm + " min vertex cover set: [");
        StringBuilder result = new StringBuilder();
        for (int vertex : vertexCover) {
            result.append(vertex).append(", ");
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 2);  
        }
        System.out.print(result.toString());
        System.out.println("]");
    }

    // check whether the vertex cover is valid or not
    private boolean isValidVertexCover(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

}