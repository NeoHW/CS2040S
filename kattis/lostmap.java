import java.util.*;
import java.io.*;

public class lostmap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] tok = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(tok[j]);
            }
        }

        ArrayList<IntegerTriple> EL = new ArrayList<>();

        // i need edge list for kruskal's
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                if (i ==j) continue;
                EL.add(new IntegerTriple(grid[i][j], i, j));
            }
        }

        Collections.sort(EL);

        UnionFind uf = new UnionFind(n);
        int numTaken = 0;

        for (int i = 0; i < EL.size(); i++) {
            IntegerTriple top = EL.get(i);
            if (uf.isSameSet(top.second(), top.third())) {
                continue;
            }
            pw.println((top.second()+1) + " " + (top.third() + 1));
            numTaken++;
            uf.unionSet(top.second(), top.third());
            if (numTaken == n-1) break;
        }

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
        return first() + " " + second() + " " + third();
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