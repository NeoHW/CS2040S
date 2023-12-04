import java.util.*;
import java.io.*;

public class colorland {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            AL.add(new ArrayList<>());
        }
        
        HashMap<String, Integer> hm = new HashMap<>();
        String[] colours = {"Blue", "Orange", "Pink", "Green", "Red", "Yellow"};

        String[] board = new String[n+1];
        for (int i = 1 ; i <= n ; i++) {
            board[i] = (br.readLine());
        }

        // we go from end to start
        for (int i = n; i >= 1; i--) {;
            String curr = board[i];

            // linking own colours
            if (!hm.containsKey(curr)) { // first time seeing the colour
                hm.put(curr, i);
            } else {
                int prevIdx = hm.get(curr);
                AL.get(i).add(prevIdx);
                hm.put(curr, i);
            }

            // link different colours in front to current colour
            for (String c : colours) {
                if (c.equals(curr)) continue;

                if (!hm.containsKey(c)) {
                    continue;
                } else {
                    int prevIdx = hm.get(c);
                    AL.get(i).add(prevIdx);
                }
            }
        }

        // have to include source node to all the front colours
        for (String key : hm.keySet()) {
            AL.get(0).add(hm.get(key));
        }

        // now do bfs on starting node to find shortest level
        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n+1, Integer.MAX_VALUE));
        Queue<Integer> q = new LinkedList<>();
        
        q.add(0);
        dist.set(0, 0);

        while (!q.isEmpty()) {
                int u = q.poll();

                if (u == n) {
                    break;
                }

                for (int v : AL.get(u)) {
                    if (dist.get(v) == Integer.MAX_VALUE) {
                        dist.set(v, dist.get(u)+1);
                        q.add(v);
                    }
                }
        }

        pw.println(dist.get(n));

        pw.flush();
        pw.close();
    }
}

class Pair implements Comparable<Pair> {
    public int first;
    public int second;

    public Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }

    public int compareTo(Pair o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else
            return this.second() - o.second();
    }

    Integer first() {
        return this.first;
    }

    Integer second() {
        return this.second;
    }

    public String toString() {
        return "[" + first() + "," + second() + "]";
    }
}

class IntegerTriple implements Comparable<IntegerTriple> {
    public int first;
    public int second;
    public int third;

    public IntegerTriple(int f, int s, int t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }

    public int compareTo(IntegerTriple o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else if (!this.second().equals(o.second()))
            return this.second() - o.second();
        else
            return this.third() - o.third();
    }

    Integer first() {
        return first;
    }

    Integer second() {
        return second;
    }

    Integer third() {
        return third;
    }

    public String toString() {
        return "[" + first() + "," + second() + "," + third() + "]";
    }
}

// Union-Find Disjoint Sets Library written in OOP manner, using both path
// compression and union by rank heuristics
class UnionFind { // OOP style
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public UnionFind(int N) {
        p = new ArrayList<Integer>(N);
        rank = new ArrayList<Integer>(N);
        setSize = new ArrayList<Integer>(N);
        numSets = N;
        for (int i = 0; i < N; i++) {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }

    public int findSet(int i) {
        if (p.get(i) == i)
            return i;
        else {
            int ret = findSet(p.get(i));
            p.set(i, ret);
            return ret;
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank.get(x) > rank.get(y)) {
                p.set(y, x);
                setSize.set(x, setSize.get(x) + setSize.get(y));
            } else {
                p.set(x, y);
                setSize.set(y, setSize.get(y) + setSize.get(x));
                if (rank.get(x) == rank.get(y))
                    rank.set(y, rank.get(y) + 1);
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }

    public int sizeOfSet(int i) {
        return setSize.get(findSet(i));
    }
}