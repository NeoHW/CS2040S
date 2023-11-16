import java.util.*;
import java.io.*;
import java.awt.Point;

public class knightfly {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = 2000000000;
        int n = 2000000000;

        int N = Integer.parseInt(br.readLine());
        String[] tok = br.readLine().split(" ");
        int sx = Integer.parseInt(tok[0]);
        int sy = Integer.parseInt(tok[1]);
        int tx = Integer.parseInt(tok[2]);
        int ty = Integer.parseInt(tok[3]);

        Point source = new Point(sy,sx);
        Point end = new Point(ty,tx);

        HashMap<Point, Boolean> avail = new HashMap<>();

        for (int i = 0; i < N; i++) {
            tok = br.readLine().split(" ");
            avail.put(new Point(Integer.parseInt(tok[1]), Integer.parseInt(tok[0])), false);
        }

        for(Point p : avail.keySet()) {
            System.out.println(p + "," + avail.get(p));
        }

        // bfs problem
        int count = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(source);
        avail.put(source, true);
        int[][] directions = {{-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {0,-1}, {0,1}, {-1,0}, {1,0}, {0,-2}, {0,2}, {-2,0}, {2,0}};

        while (!q.isEmpty()) {
            int currSize = q.size();
            System.out.println("count: " + count);
            while (currSize-- >0) {
                Point p = q.poll();
                if (p.equals(end)) {
                    System.out.println(count);
                    return;
                }
                for (int[] d : directions) {
                    int row = (int)p.getX() + d[0];
                    int col = (int)p.getY() + d[1];
                    Point temp = new Point(row,col);
                    System.out.print(temp);

                    System.out.println();
                    if (col > 0 && row > 0 && col <= n && row <= m && avail.containsKey(temp) && !avail.get(temp)) {
                        System.out.println("adding temp:" + temp);
                        q.add(temp);
                        avail.put(temp, true);
                    }
                }
            }
            count++;
        }

        System.out.println("-1");
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
        if (this.first != o.first)
            return this.first - o.first;
        else
            return this.second - o.second;
    }

    int first() {
        return this.first;
    }

    int second() {
        return this.second;
    }

    boolean equals(Pair o) {
        return this.first == o.first && this.second == o.second;
    }

    public int hashCode() {
        return this.first * 31 + this.second;
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