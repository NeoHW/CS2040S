import java.util.*;
import java.io.*;

public class shortestpath4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // dfs/bfs from the destination node up to max number of allowed vertices?
        // greedy backtracking?

        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            br.readLine();
            int n = Integer.parseInt(br.readLine()); // num vertices

            ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                AL.add(new ArrayList<>());
            }

            // populating AL
            for (int i = 0; i < n; i++) {
                String[] tok = br.readLine().split(" ");
                int e = Integer.parseInt(tok[0]);
                for (int j = 1; j < e+1; j++) {
                    int vertex = Integer.parseInt(tok[j*2-1]);
                    int weight = Integer.parseInt(tok[j*2]);
                    AL.get(i).add(new Pair(vertex,weight));
                }
            }
            
            // doing queries
            int queries = Integer.parseInt(br.readLine()); // num queries
            while (queries-- > 0) {
                
                String[] tok = br.readLine().split(" ");
                int s = Integer.parseInt(tok[0]);
                int t = Integer.parseInt(tok[1]);
                int k = Integer.parseInt(tok[2]);

                ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
                //bfs with distance array
                Queue<Integer> q = new LinkedList<>();
                q.add(s);
                dist.set(s,0);
                int count = 1;

                while (!q.isEmpty()) {
                    if(count >= k) break;
                    int currSize = q.size();
                    HashMap<Integer,Integer> hm = new HashMap<>(); // to delay the changes
                    while (currSize-- >0) {
                        int v = q.poll();

                        for (Pair neighbour : AL.get(v)) {
                            if (dist.get(v) + neighbour.second >= dist.get(neighbour.first)) {
                                continue;
                            }

                            q.add(neighbour.first);
                            if (!hm.containsKey(neighbour.first) || (hm.get(neighbour.first) > (dist.get(v) + neighbour.second))) {
                                hm.put(neighbour.first, dist.get(v) + neighbour.second);
                            }
                        }
                    }
                    // update the changes now instead of in the neighbour loop
                    for (int key : hm.keySet()) {
                        dist.set(key, hm.get(key));
                    }
                    count++;
                }
                pw.println(dist.get(t) == Integer.MAX_VALUE ? -1 : dist.get(t));
            }
            pw.println();
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