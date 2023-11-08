import java.util.*;
import java.io.*;

public class shortestpath1 {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while (true) {
            String[] tok = br.readLine().split(" ");
            int n = Integer.parseInt(tok[0]);
            int e = Integer.parseInt(tok[1]);
            int q = Integer.parseInt(tok[2]);
            int s = Integer.parseInt(tok[3]);

            if (n == 0 && e == 0 && q == 0 && s ==0) break;

            ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
            for (int i =0; i < n; i++) {
                AL.add(new ArrayList<>());
            }

            for (int i = 0 ; i < e; i++) {
                tok = br.readLine().split(" ");
                int u = Integer.parseInt(tok[0]);
                int v = Integer.parseInt(tok[1]);
                int w = Integer.parseInt(tok[2]);
                AL.get(u).add(new Pair(v,w));
            }

            // run dijkstra
            ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n,Integer.MAX_VALUE));
            dist.set(s,0);

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.offer(new Pair(0,s));

            while (!pq.isEmpty()) {
                Pair top = pq.poll();
                int d = top.first; 
                int vert = top.second;
                if(d > dist.get(vert)) continue; // this is what makes dijsktra dijsktra
                
                for (Pair neighbour : AL.get(vert)) {
                    if (dist.get(vert) + neighbour.second < dist.get(neighbour.first)) {
                        dist.set(neighbour.first, dist.get(vert) + neighbour.second);
                        pq.add(new Pair(neighbour.second, neighbour.first));
                    }
                }
            }

            while (q-- > 0) {
                int minDist = dist.get(Integer.parseInt(br.readLine()));
                if (minDist == Integer.MAX_VALUE) {
                    pw.println("Impossible");
                } else {
                    pw.println(minDist);
                }
            }
            pw.println();
        }

        pw.flush();
        pw.close();
    }

    static class Pair implements Comparable<Pair> {
        public int first;
        public int second;

        public Pair(int f, int s) {
            this.first = f;
            this.second = s;
        }

        public int compareTo(Pair p) {
            if (this.first != p.first) {
                return this.first - p.first;
            } else {
                return this.second - p.second;
            }
        }
    }
}
