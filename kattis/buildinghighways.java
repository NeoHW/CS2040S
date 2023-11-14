import java.util.*;
import java.io.*;

public class buildinghighways {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        String[] tok = br.readLine().split(" ");
        int[] arr = new int[n+1];

        // setting up array for nodes and their corresponding problematic level
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(tok[i-1]);
        }

        ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
        for (int i = 0 ; i < n+1; i++) {
            AL.add(new ArrayList<>());
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (i == j) continue;
                AL.get(i).add(new Pair(arr[i]+arr[j], j)); // weight, node
            }
        }

        boolean visited[] = new boolean[n+1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        visited[1] = true;
        int numTaken = 0;
        int totalWeight = 0;

        for (Pair p : AL.get(1)) {
            pq.add(p);
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (visited[p.second]) continue;
            visited[p.second] = true;
            totalWeight += p.first;
            numTaken++;

            // adding in their neighbours into pq
            for (Pair neigh : AL.get(p.second)) {
                pq.add(neigh);
            }

            if (numTaken == n-1) break;
        }   

        pw.println(totalWeight);


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