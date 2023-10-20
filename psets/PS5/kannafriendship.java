import java.util.*;
import java.io.*;

public class kannafriendship {

    public static TreeSet<Pair> ts;
    public static long counter = 0;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        long N = Long.parseLong(tok[0]);
        int Q = Integer.parseInt(tok[1]);

        ts = new TreeSet<>((a,b) -> { // compares the lower element first, if same then compare higher element
            if ((a.first) - (b.first) == 0) {
                if(a.second - b.second < 0) {
                    return -1;
                } else if (a.second - b.second > 0) {
                    return 1;
                }
                return 0;
            } else if ((a.first) - (b.first) < 0) {
                return -1;
            } else {
                return 1;
            }
        });

        while (Q-- > 0) {
            String[] arr = br.readLine().split(" ");
            if (Long.parseLong(arr[0]) == 1) {
                Pair p = new Pair(Long.parseLong(arr[1]), Long.parseLong(arr[2]));
                if(!ts.contains(p)) {
                    ts.add(p);
                    counter += p.length;
                    unionDisjoint(p);
                }
            } else {
                System.out.println(counter);
            }
        }
    }

    public static void unionDisjoint(Pair p) {
        
        // check on higher element
        if(ts.higher(p) != null) {
            Pair higher = ts.higher(p);
            System.out.println("Comparing p " + p + " with higher " + higher);
            
            // case 1: p's range in higher's range
            if (p.first == higher.first && p.second <= higher.second) {
                ts.remove(p);
                counter -= p.length;
                System.out.println("Case 1: removing p: " + p);
            }
            // case 2: higher's range in p's range
            else if (higher.first >= p.first && higher.second <= p.second) {
                ts.remove(higher);
                counter -= higher.length;
                System.out.println("Case 2: removing higher: " + higher);
            }
            // case 3: p.second is in range of higher
            else if (p.second >= higher.first && p.second <= higher.second) {
                Pair newPair = new Pair(p.first, higher.second);
                ts.remove(p);
                ts.remove(higher);
                ts.add(newPair);
                counter += newPair.length - p.length - higher.length;
                System.out.println("Case 3: removing p: " + p + " ,removing higher: " + higher + " , adding newPair: " + newPair);
                // rebalance the avl (?)
                // unionDisjoint(newPair); // recursive call here to balance the avl (?)
            }
        }

        // check lower element
        if(ts.lower(p) != null) {
            Pair lower = ts.lower(p);
            System.out.println("Comparing p " + p + " with lower " + lower);

            // case 1: lower's range in p's range
            if (p.first == lower.first && lower.second <= p.second) {
                ts.remove(lower);
                counter -= lower.length;
                System.out.println("Case 1: removing lower: " + lower);
            }

            // case 2: p's range in lower's range
            else if (p.first >= lower.first && p.second <= lower.second) {
                ts.remove(p);
                counter -= p.length;
                System.out.println("Case 2: removing p: " + p);
            }

            // case 3: p.first is in range of lower
            else if (p.first >= lower.first && p.first <= lower.second) {
                Pair newPair = new Pair(lower.first, p.second);
                ts.remove(p);
                ts.remove(lower);
                ts.add(newPair);
                counter += newPair.length - p.length - lower.length;
                // rebalance the avl (?)
                // unionDisjoint(newPair); // recursive call here to balance the avl (?)
                System.out.println("Case 3: removing p: " + p + " ,removing lower: " + lower + " , adding newPair: " + newPair);
            }
        }
    }

    static class Pair {
        public long first;
        public long second;
        public long length;

        public Pair(long a, long b) {
            this.first = a;
            this.second = b;
            this.length = b - a + 1;
        }

        public String toString() {
            return "[" + first + "," + second + "]";
        }
    }
}