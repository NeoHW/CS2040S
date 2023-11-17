import java.util.*;
import java.io.*;

public class jetpack {
    static int m;
    static int n;
    static int[] end;
    static char[][] grid;
    static boolean[][] visited;
    static Pair[][] parent;
    static ArrayList<Pair> route;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        m = 10;
        n = Integer.parseInt(br.readLine());

        grid = new char[m][n];
        visited = new boolean[m][n];
        visited[9][0] = true;
        
        for (int i = 0 ; i < m; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        parent = new Pair[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j] = new Pair(-1, -1);
            }
        }

        
        
        // this looks like a dfs problem
        // source vertex = [9][0]
        dfs(9,0);

        route = new ArrayList<>();
        route.add(new Pair(9,0)); // initial starting point

        backtrack(end[0], end[1]);

        boolean hold = false;
        int holdingstart = 0;
        int count = 0;
        for (int i = 0; i < route.size()-1; i++) {
            Pair curr = route.get(i);
            Pair next = route.get(i+1);

            if((curr.first == 0 && next.first == 0) || (curr.first - 1 == next.first)) {
                if (!hold) {
                    hold = true;
                    holdingstart = curr.second;
                    pw.print(curr.second + " ");
                    continue;
                }
            }

            if (hold && curr.first + 1 == next.first) {
                count++;
                hold = false;
                pw.print((curr.second-holdingstart) + " ");
                pw.println();
                continue;
            }
        }
        System.out.println(count);
        pw.flush();
        pw.close();
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;

        // base case: last column
        if (c == n-1 && grid[r][c] == '.') {
            end = new int[]{r,c};
            return;
        }

        int[][] directions;
        
        if (r == 9) {
            // means can go right or diagonally up
            directions = new int[][]{{0,1}, {-1,1}};
        } else if (r == 0) {
            // means can go right or diagonally down
            directions = new int[][]{{0,1}, {1,1}};
        } else {
            // means can go diagonally up or diagonally down
            directions = new int[][]{{-1,1}, {1,1}};
        }

        for (int[] d : directions) {
            int row = r + d[0];
            int col = c + d[1];
            if (grid[row][col] == '.' && !visited[row][col]) {
                parent[row][col] = new Pair(r,c);
                dfs(row,col);
            }
        }
    }

    public static void backtrack(int r, int c) {
        Pair p = parent[r][c];
        if (p.first == -1 && p.second == -1) return;
        backtrack(p.first,p.second);
        route.add(new Pair(r,c));
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
