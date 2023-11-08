import java.util.*;
import java.io.*;

public class cats_prim {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        
        while (TC-- > 0) {
            String[] tok = br.readLine().split(" ");
            int M = Integer.parseInt(tok[0]);
            int C = Integer.parseInt(tok[1]);
            int numEdges = (C*(C-1))/2;
            
            // setting up what is needed for Prim's
            boolean visited[] = new boolean[C+1];
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            ArrayList<Pair> mst = new ArrayList<>();
            
            // instantiating the AL
            ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
            for (int i = 0; i < C; i++) {
                AL.add(new ArrayList<>());
            }

            // populating the AL
            for (int i = 0 ; i < numEdges; i++) {
                String[] tok2 = br.readLine().split(" ");
                int x = Integer.parseInt(tok2[0]);
                int y = Integer.parseInt(tok2[1]);
                int d = Integer.parseInt(tok2[2]);
                
                // adding in edges, bi-directional
                AL.get(x).add(new Pair(d,y));
                AL.get(y).add(new Pair(d,x)); 
            }

            // doing Prim's
            visited[0] = true;
            int num_taken = 0; // breaking when num_taken == E-1 reduces time from 6s -> 1.5s

            for (Pair p : AL.get(0)) { // for each neighbour of 0 
                pq.add(p); // add into pq a new pair(weight, vertex)
            }

            while (!pq.isEmpty()) {
                Pair p = pq.poll(); // this pair will be the smallest weight neighbour
                if (visited[p.second]) {
                    continue; // we want a non visited vertex
                }
                mst.add(p);
                visited[p.second] = true;
                num_taken++;
                // then we add in their neighbours
                for (Pair neighbour : AL.get(p.second)) {
                    pq.add(neighbour);
                }
                if(num_taken == C-1) break; // optimisation
            }

            // find shortest path length
            int totalLength = 0;
            for (Pair p : mst) {
                totalLength += p.first;
            }

            // add in each vertex requriing 1ml of milk
            totalLength += C;

            System.out.println(totalLength <= M ? "yes" : "no");
        }
    }

    static class Pair implements Comparable<Pair> {
        public int first; // weight
        public int second; // vertex

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair p) {
            if (this.first != p.first) {
                return this.first - p.first;
            } else {
                return this.second - p.second;
            }
        }

        public String toString() {
            return "[" + first + "," + second + "]";
        }
    }
}
