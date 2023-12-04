import java.util.*;
import java.io.*;

public class conquest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] tok = br.readLine().split(" ");
        int n = Integer.parseInt(tok[0]);
        int e = Integer.parseInt(tok[1]);
        
        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        
        // creating 1-based index EL
        for (int i = 0; i <= n; i++) {
            AL.add(new ArrayList<>());
        }

        // setting up the AL, storing the neighbours of each island
        for (int i = 0 ; i < e; i++) {
            tok = br.readLine().split(" ");
            int u = Integer.parseInt(tok[0]);
            int v = Integer.parseInt(tok[1]);
            AL.get(u).add(v);
            AL.get(v).add(u);
        }

        // hashmap of island num -> army strength
        HashMap<Integer,Integer> armySize = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            armySize.put(i, Integer.parseInt(br.readLine()));
        }

        int currStrength = armySize.get(1);
        visited[1] = true;

        PriorityQueue<Pair> pq = new PriorityQueue<>(); // distance, vertex (to compare by dist)
        
        // adding source vertex neighbours into pq
        for (int neighbour : AL.get(1)) {
            pq.add(new Pair(armySize.get(neighbour), neighbour));
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (visited[p.second]) continue;

            if (p.first >= currStrength) break;

            visited[p.second] = true;
            currStrength += p.first;

            for (int neighbour : AL.get(p.second)) {
                pq.add(new Pair(armySize.get(neighbour), neighbour));
            }
        }

        pw.println(currStrength);

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