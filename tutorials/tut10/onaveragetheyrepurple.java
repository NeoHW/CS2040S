import java.util.*;
import java.io.*;

public class onaveragetheyrepurple {
    private static final int INF = 1000000000;
    public static void main (String[] args) throws Exception{
        // intuition: 
        // alice wants to alternate colour as much as possible
        // Bob wants to take the SHORTEST possible path to prevent alice from spanning blue and red
        // so shortest path MUST be alternating

        // bfs shortest path?
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();

        String[] tok = br.readLine().split(" ");
        int N = Integer.parseInt(tok[0]);
        int M = Integer.parseInt(tok[1]);

        for (int i = 0 ; i <= N ; i++) {
            AL.add(new ArrayList<Integer>());
        }

        while (M-- > 0) {
            String[] tok2 = br.readLine().split(" ");
            AL.get(Integer.parseInt(tok2[0])).add(Integer.parseInt(tok2[1]));
            AL.get(Integer.parseInt(tok2[1])).add(Integer.parseInt(tok2[0])); // remember it is UNDIRECTED graph so we need do both ways
        }

        // bfs
        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(N+1, INF));
        dist.set(1, 0);
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int u = q.poll();
            int layer = dist.get(u);
            for (int v : AL.get(u)) {
                if (v == N) {
                    System.out.println(layer); // as colour changes only start after 2 edges
                    return;
                }
                
                if (dist.get(v) == INF) {
                    q.add(v);
                    dist.set(v, layer + 1);
                }
            }
        }
    }
}