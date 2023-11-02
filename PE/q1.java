// do subtask 1 first

import java.util.*;
import java.io.*;

public class q1 {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int idx = 0;
        int Q = Integer.parseInt(br.readLine());

        TreeMap<Integer,Long> tm = new TreeMap<>(); // map index to time

        // do the comparison logic here
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            
            long newAvalue;
            long newBvalue;

            if (tm.ceilingKey(a.index) == null) {
                newAvalue = a.value;
            } else {
                int Akey = tm.ceilingKey(a.index);
                newAvalue = a.value + tm.get(Akey);
            }

            if (tm.ceilingKey(b.index) == null) {
                newBvalue = b.value;
            } else {
                int Bkey = tm.ceilingKey(b.index);
                newBvalue = b.value + tm.get(Bkey);
            }
            
            
            if (newAvalue == newBvalue) {
                return 0;
            } else if (newAvalue < newBvalue) {
                    return -1;
            } else {
                return 1;
            }
        });
        ArrayList<Pair> storage = new ArrayList<>();

        while (Q-- > 0) {
            String[] tok = br.readLine().split(" ");
            int command = Integer.parseInt(tok[0]);
            long val = Integer.parseInt(tok[1]);
            if (command == 1) {
                pq.add(new Pair(idx, val));
                idx++;
            } else if (command == 2) {
                int curridx = idx;
                if(curridx < 0) {
                    continue;
                }
                if (tm.containsKey(idx)) {
                    long x = tm.get(idx);
                    x += val;
                    tm.put(idx,x);
                } else {
                    if (tm.ceilingKey(idx) == null) {
                        tm.put(idx, val);
                    }
                    int tempKey = tm.ceilingKey(idx);
                    val += tm.get(tempKey);
                    tm.put(idx, val);
                }
            } else if (command == 3) {
                for (int i = 0 ; i < val-1; i++) {
                    storage.add(pq.poll());
                }
                pw.println(pq.peek().value);
                for (Pair p : storage) {
                    pq.add(p);
                }
            }

        }

        pw.flush();
        pw.close();
    }
    
    static class Pair {
        public int index;
        public long value;

        public Pair(int a, long b) {
            this.index = a;
            this.value = b;
        }

        public String toString() {
            return "[" + index + "," + value + "]";
        }
    }
}
