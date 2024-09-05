
import java.util.Scanner;
public class test {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of vertices");
        int numVert = scanner.nextInt();
        Graph g = new Graph(numVert);
        System.out.println("Please enter the number of edges");
        int numEdges = scanner.nextInt();

        System.out.println("Please enter the value of(vertex1 vertex2(separate with blank space)):");
        for (int i = 0; i < numEdges; i++) {
            int vertex1 = scanner.nextInt();
            int vertex2 = scanner.nextInt();
            g.addEdge(vertex1, vertex2);
        }

        g.print();

        long start = System.currentTimeMillis();
        g.bruteForceVertexCover();
        long end = System.currentTimeMillis();
        g.time(start,end);

        long start_g = System.currentTimeMillis();
        g.greedyVertexCover();
        long end_g = System.currentTimeMillis();
        g.time(start_g,end_g);

        long start_a = System.currentTimeMillis();
        g.greedyApproxVertexCover();
        long end_a = System.currentTimeMillis();
        g.time(start_a,end_a);


        
    }





}
