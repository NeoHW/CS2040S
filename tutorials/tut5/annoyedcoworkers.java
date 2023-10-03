import java.io.*;
import java.util.*;

public class annoyedcoworkers {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        int h = Integer.parseInt(tok[0]);
        int c = Integer.parseInt(tok[1]);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> (a.first + a.second) - (b.first + b.second));

        while(c-- > 0) {
            String[] input = br.readLine().split(" ");
            pq.add(new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        while(h-- > 0) {
            Pair temp = pq.poll();
            temp.first += temp.second;
            pq.add(temp);
        }

        int max = 0;
        for(Pair pair : pq) {
            if(pair.first > max) {
                max = pair.first;
            }
        }


        System.out.println(max);
    }

    public static class Pair {
        public int first;
        public int second;

        public Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }

        public String toString() {
            return "annoyance: " + first + "; increase: " + second;
        }
    }
}
