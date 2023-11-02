// 6% VA QQ 3 will be easier than this

import java.util.*;
import java.io.*;

public class racinggame {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int Q = Integer.parseInt(br.readLine());

        // do the comparison logic here
        PriorityQueue<Long> pq = new PriorityQueue<>();

        ArrayList<Long> storage = new ArrayList<>();

        while (Q-- > 0) {
            String[] tok = br.readLine().split(" ");
            int command = Integer.parseInt(tok[0]);
            long val = Long.parseLong(tok[1]);
            if (command == 1) {
                pq.add(val);
            } else if (command == 2) {
                while (!pq.isEmpty()) {
                    storage.add(pq.poll());
                }

                for (long s : storage) {
                    pq.add(s + val);
                }
                storage.clear();
            } else if (command == 3) {
                for (int i = 0 ; i < val-1; i++) {
                    storage.add(pq.poll());
                }
                pw.println(pq.peek());
                for (long s : storage) {
                    pq.add(s);
                }
                storage.clear(); // didn't put this leading to 15 marks instead of 60... 
            }
        }

        pw.flush();
        pw.close();
    }
    
    // static class Pair {
    //     public int index;
    //     public long value;

    //     public Pair(int a, long b) {
    //         this.index = a;
    //         this.value = b;
    //     }

    //     public String toString() {
    //         return "[" + index + "," + value + "]";
    //     }
    // }
}
