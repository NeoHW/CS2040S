import java.util.*;
import java.io.*;

public class jurassicjigsaw {
    public static int n;
    public static int k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]); // num nodes
        k = Integer.parseInt(tok[1]);

        ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
        ArrayList<Pair> mst = new ArrayList<>();
        HashMap<Integer, String> hm = new HashMap<>();

        // the nodes
        for (int i = 0 ; i < n; i++) {
            String s = br.readLine();
            AL.add(new ArrayList<>());
            hm.put(i,s);
        }

        // creating the edges between everyone
        for (int u : hm.keySet()) {
            for (int v : hm.keySet()) {
                if (u == v) continue;
                int length = computeDiff(hm.get(u), hm.get(v));
                AL.get(u).add(new Pair(length, v));
            }
        }

        // this is a MST problem
        boolean[] visited = new boolean[n];
        
        PriorityQueue<IntegerTriple> pq = new PriorityQueue<>();
        visited[0] = true;
        int numTaken = 0;
        int cost = 0;

        for (Pair p : AL.get(0)) {
            pq.add(new IntegerTriple(p.first, p.second, 0));  // weight, next node, prev node
        }

        while (!pq.isEmpty()) {
            IntegerTriple top = pq.poll();
            if(visited[top.second]) continue;
            
            visited[top.second] = true;
            cost += top.first;
            numTaken++;
            mst.add(new Pair(top.second, top.third));

            // then we add in their neighbours
            for (Pair neighbour : AL.get(top.second)) {
                pq.add(new IntegerTriple(neighbour.first, neighbour.second, top.second));
            }
            if(numTaken == n-1) break; // optimisation
        }

        pw.println(cost);
        for (Pair p : mst) {
            pw.println(p);
        }

        pw.flush();
        pw.close();
    }

    public static int computeDiff(String u, String v) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (u.charAt(i) != v.charAt(i)) {
                count++;
            }
        }
        return count;
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
        return first() + " " + second();
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