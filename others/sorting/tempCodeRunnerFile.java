import java.util.*;
import java.io.*;

public class arraysmoothening {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<Integer,Integer> hm = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> b.second-a.second);

        String[] tok = br.readLine().split(" ");
        // int N = Integer.parseInt(tok[0]);
        int K = Integer.parseInt(tok[1]);

        String[] inputs = br.readLine().split(" ");
        
        for (String s : inputs) {
            int i = Integer.parseInt(s);
            if (hm.get(i) == null) {
                hm.put(i, 1);
            } else {
                int num = hm.get(i);
                hm.put(i, num+1);
            }
        }

        for (int key : hm.keySet()) {
            pq.offer(new Pair(key, hm.get(key)));
        }

        while (K-- > 0) {
            Pair p = pq.poll();
            p.second--;
            pq.offer(p);
        }

        pw.println(pq.poll().second);
        pw.flush();
        pw.close();

    }

    static class Pair {
        public int first;
        public int second;

        public Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }
    }
}

