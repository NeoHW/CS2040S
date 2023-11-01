import java.util.*;
import java.io.*;

public class modulosolitaire {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        String[] tok = br.readLine().split(" ");
        long m = Long.parseLong(tok[0]);
        long n = Long.parseLong(tok[1]);
        int s = Integer.parseInt(tok[2]);

        ArrayList<Long> a = new ArrayList<>(); 
        ArrayList<Long> b = new ArrayList<>(); 

        for (int i = 0; i < n; i++) {
            String[] tok2 = br.readLine().split(" ");
            a.add(Long.parseLong(tok2[0]));
            b.add(Long.parseLong(tok2[1]));
        }

        
        long INF = 1000000000L; // 1 billion , L behind to specify long to compiler
        ArrayList<Long> dist = new ArrayList<>();
        for (int i = 0; i < m+1; i++) {
            dist.add(INF); // setting all other vertex distances to INF
        }
        dist.set(s,0L); // source vertex distance is 0
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(s); // source vertex

        // coding out bfs
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < n; i++) { // for n operations
                // vertex it goes to
                int v = (int) ((long) (u*a.get(i) + b.get(i)) % m);
                if (dist.get(v).equals(INF)) { // remember! INTEGER uses .equals() and not ==
                    dist.set(v,dist.get(u) + 1);
                    q.offer(v);
                }
            }
        }

        pw.println(dist.get(0) == INF ? -1 : dist.get(0));

        pw.flush();
        pw.close();
    }
}
