import java.util.*;
import java.io.*;

public class islands3 {
    public static int r;
    public static int c;
    public static int cc;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);

        char[][] grid = new char[r][c];
        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        
        /* testing that grid is correct
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                pw.print(grid[i][j]);
            }
            pw.println();
        }
        */
        cc = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != 'L' || visited[i][j]) {
                    continue;
                }
                bfs(new Pair(i,j),grid,visited);
                cc++;
            }
        }

        pw.println(cc);

        pw.flush();
        pw.close();
    }

    public static void bfs(Pair node, char[][] grid, boolean[][] visited) {
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        visited[node.first][node.second] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int[] d : directions) {
                int row = p.first + d[0];
                int col = p.second + d[1];

                if (row >= 0 && col >= 0 && row < r && col < c && grid[row][col] != 'W' && !visited[row][col]) {
                    q.add(new Pair(row,col));
                    visited[row][col] = true;
                }
            }
        }
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