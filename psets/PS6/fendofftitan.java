import java.util.*;
import java.io.*;

public class fendofftitan {
    private static final int INF = 1000000000;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");

        int N = Integer.parseInt(tok[0]); // N nodes
        int M = Integer.parseInt(tok[1]); // M edges
        int X = Integer.parseInt(tok[2]); // source
        int Y = Integer.parseInt(tok[3]); // dest

        ArrayList<ArrayList<Node>> AL = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            AL.add(new ArrayList<Node>());
        }


        for (int i = 0; i < M; i++) {
            String[] tok2 = br.readLine().split(" ");
            int titan = 0;
            int shaman = 0;

            int A = Integer.parseInt(tok2[0]);
            int B = Integer.parseInt(tok2[1]);
            int W = Integer.parseInt(tok2[2]);
            int C = Integer.parseInt(tok2[3]);

            if (C == 1) {
                shaman = 1;
            } else if (C == 2) {
                titan = 1;
            }

            AL.get(A).add(new Node(B, W, shaman, titan));
            AL.get(B).add(new Node(A, W, shaman, titan)); // bidirectional
        }

        ArrayList<Node> info = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            info.add(new Node(i, INF, INF, INF));
        }

        info.set(X, new Node(X,0,0,0));

        // modified dijsktra with titans and shamans
        PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(X,0,0,0));

        while (!pq.isEmpty()) {                      // main loop
            Node top = pq.poll();
            int d = top.vertex;
            long distance = top.distance;
            int shamans = top.shaman;
            int titans = top.titan;

            if (top.compareTo(info.get(d)) > 0) {
                continue;
            }
            
            // all edges from d
            for (Node neighbour : AL.get(d)) {
                Node newNode = new Node(neighbour.vertex, distance + neighbour.distance, shamans + neighbour.shaman, titans + neighbour.titan);
                if (newNode.compareTo(info.get(neighbour.vertex)) < 0) {;
                    info.set(neighbour.vertex, newNode); // relax operation
                    pq.add(newNode); // enqueue better pair
                }

            }
        }

        if (info.get(Y).distance == INF) {
            System.out.println("IMPOSSIBLE");
        } else {
            Node pi = info.get(Y);
            System.out.println(pi.distance + " " + pi.shaman + " " + pi.titan);
        }

    }

    // edge
    static class Node implements Comparable<Node>{
        public int vertex;
        public long distance;
        public int shaman;
        public int titan;

        public Node(int vertex, long d, int s, int t) {
            this.vertex = vertex;
            this.distance = d;
            this.shaman = s;
            this.titan = t;
        }

        public int compareTo(Node r) {
            if (this.titan != r.titan) {
                return this.titan - r.titan;
            }
            else if (this.shaman != r.shaman) {
                return this.shaman - r.shaman;
            }
            else if (this.distance != r.distance) {
                return this.distance - r.distance > 0 ? 1 : -1;
            }
            else {
                return this.vertex - r.vertex;
            }
        }

        public String toString() {
            return vertex + ": [" + distance + "," + shaman + "," + titan + "]";
        }
    }

    // basically distance and other info
    /*
    static class Node{
        public int distance;
        public int shamanCount;
        public int titanCount;

        public Node(int d, int s, int t) {
            this.distance = d;
            this.shamanCount = s;
            this.titanCount = t;
        }

        public String toString() {
            return "[" + distance + "," + shamanCount + "," + titanCount + "]";
        }
    }
     */
}
