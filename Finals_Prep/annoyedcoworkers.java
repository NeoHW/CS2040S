import java.util.*;
import java.io.*;

public class annoyedcoworkers {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        int h = Integer.parseInt(tok[0]);
        int c = Integer.parseInt(tok[1]);

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i=0; i<c; i++) {
            tok = br.readLine().split(" ");
            pq.add(new Pair(Long.parseLong(tok[0]), Long.parseLong(tok[1])));
        }

        while (h-- > 0) {
            // Pair p = pq.poll();
            // pq.add(new Pair(p.first+p.second, p.second));
            Pair temp = pq.poll();
            temp.first += temp.second;
            pq.add(temp);
        }

        long max = 0;
        for(Pair pair : pq) {
            if(pair.first > max) {
                max = pair.first;
            }
        }

        System.out.println(max);
    }
}

class Pair implements Comparable<Pair> {
    public long first;
    public long second;

    public Pair(long f, long s) {
        this.first = f;
        this.second = s;
    }

    public int compareTo(Pair p) {
        return (int) ((this.first + this.second) - (p.first + p.second));
    }

    public String toString() {
        return "[" + first + "," + second + "]";
    }
}
