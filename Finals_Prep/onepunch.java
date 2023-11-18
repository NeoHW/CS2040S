import java.util.*;
import java.io.*;

public class onepunch {
    public static char[][] grid;
    public static int n;
    public static int m;
    public static int[][] directions; // 4 directions array
    public static UnionFind uf;
    public static ArrayList<Integer> parents; // stores all the parents after bfs (walkable sets)
    public static HashMap<String, ArrayList<Integer>> joinablePaths; // key : the two parents box num concat as string, mapped : walls that break will join these 2 sets
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        int q = Integer.parseInt(tok[2]);

        grid = new char[n][m];

        // creating the grid from input
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // each square (r,c) will be given a number : (r*m) + c
        uf = new UnionFind(n*m);
        parents = new ArrayList<>();
        
        
        // doing the bfs to find connected components, and joining them in UFDS
        boolean[][] visited = new boolean[n][m];
        directions = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '#' || visited[i][j]) continue;
                bfs(i,j,grid,visited);
                parents.add(uf.findSet(boxNum(i, j)));
            }
        }

        joinablePaths = new HashMap<>();

        // set up the hashmap so that we can know if 2 parents will be reachable by breaking a wall to be computed later
        for (int i = 0 ; i < parents.size(); i++) {
            for (int j = i+1; j < parents.size(); j++) {
                String s1 = "" + parents.get(i) + parents.get(j);
                String s2 = "" + parents.get(j) + parents.get(i);
                joinablePaths.put(s1, new ArrayList<>());
                joinablePaths.put(s2, new ArrayList<>());
            }
        }

        // now, we go through entire grid, and virtually break each wall
        // and populate our hashmap if breaking a wall can connect ANY 2 parents (rmb that breaking a wall can connect up to 4 parents)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '#') continue;

                HashSet<Integer> linkable = new HashSet<>();
                for (int[] d : directions) {
                    int row = i + d[0];
                    int col = j + d[1];
                    if (row >= 0 && col >= 0 && row < n && col < m && grid[row][col] == '.') {
                        linkable.add(uf.findSet(boxNum(row,col))); // linkable contains parents of joinable sets if curr wall is broken
                    }
                }
                if (linkable.size() < 2) continue; // we want to link 2 or more CCs

                // putting in current wall that breaks that will join every 2 parents together
                for (int k : linkable) {
                    for (int l : linkable){
                        if (k == l) continue;
                        String s1 = "" + k + l;
                        String s2 = "" + l + k; 
                        joinablePaths.get(s1).add(boxNum(i, j));
                        joinablePaths.get(s2).add(boxNum(i, j));
                    }
                }
            }
        }

        for(String key : joinablePaths.keySet()) {
            System.out.println(key + ": " + joinablePaths.get(key));
        }


        while (q-- > 0) {
            tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]) - 1;
            int b = Integer.parseInt(tok[1]) - 1;
            int c = Integer.parseInt(tok[2]) - 1;
            int d = Integer.parseInt(tok[3]) - 1;
            int k = Integer.parseInt(tok[4]);

            System.out.println(boxNum(a, b));
            System.out.println(boxNum(c,d));

            int start = uf.findSet(boxNum(a, b));
            int end = uf.findSet(boxNum(c, d));
            boolean sameCC = (start == end);

            if (k == 0) {
                pw.println(sameCC ? "yes" : "no");
            } else {
                // can break one wall
                String s = "" + start + end;
                pw.println( sameCC || joinablePaths.get(s).size() > 0 ? "yes" : "no");
            }
        }


        pw.flush();
        pw.close();
    }

    public static void bfs(int i, int j, char[][] grid, boolean[][] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i,j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Pair top = queue.poll();
            for (int[] d : directions) {
                int row = top.first + d[0];
                int col = top.second + d[1];
                if (row >= 0 && col >= 0 && row < n && col < m && grid[row][col] == '.' && !visited[row][col]) {
                    visited[row][col] = true;
                    uf.unionSet(boxNum(i,j), boxNum(row,col));
                    queue.add(new Pair(row, col));
                }
            }
        }
    }

    public static int boxNum(int i, int j) {
        // each square (r,c) will be given a number : (r*m) + c
        return (i*m) + j;
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