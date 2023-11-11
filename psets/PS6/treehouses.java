import java.util.*;
import java.io.*;
import java.awt.geom.*;

public class treehouses {
    static ArrayList<Point2D> coordinateTable = new ArrayList<>();
    static ArrayList<IntegerTriple> EL = new ArrayList<>();
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] tok = br.readLine().split(" ");
        int n = Integer.parseInt(tok[0]);
        int e = Integer.parseInt(tok[1]);
        int p = Integer.parseInt(tok[2]);

        for (int i = 0; i < n; ++i) {
            tok = br.readLine().split(" ");
            coordinateTable.add(new Point2D.Float(Float.parseFloat(tok[0]), Float.parseFloat(tok[1])));   
        }

        // adding first e with 0 weight
        for (int i = 0; i < e-1; i++) {
            for (int j = i+1; j < e; j++) {
                EL.add(new IntegerTriple(0.0F,i,j));
                EL.add(new IntegerTriple(0.0F,j,i));
            }
        }

        // adding p lines with exisiting cable (0 weight)
        for (int i = 0; i < p; i++) {
            tok = br.readLine().split(" ");
            int u = Integer.parseInt(tok[0]);
            int v = Integer.parseInt(tok[1]);
            EL.add(new IntegerTriple(0.0F,u,v));
            EL.add(new IntegerTriple(0.0F,v,u));
        }

        // adding all possible edges between remaining points (n^2??)
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                float l = computeLength(i,j);
                EL.add(new IntegerTriple(l,i, j));
                EL.add(new IntegerTriple(l,j, i));
            }
        }
        
        // sorting the EL
        Collections.sort(EL);  

        float mst_cost = 0;
        int num_taken = 0;                           // no edge has been taken
        UnionFind UF = new UnionFind(n);             // all V are disjoint sets
        for (int i = 0; i < EL.size(); ++i) {
            IntegerTriple front = EL.get(i);
            if (UF.isSameSet(front.second(), front.third())) continue; // check
            mst_cost += front.first();                 // add w of this edge
            UF.unionSet(front.second(), front.third());// link them
            ++num_taken;                               // 1 more edge is taken
            if (num_taken == n-1) break;               // optimization
        }

        System.out.println(mst_cost);
        
    }

    public static float computeLength(int i, int j) {
        return (float) Math.abs(coordinateTable.get(i).distance(coordinateTable.get(j)));
    }
}

class IntegerTriple implements Comparable<IntegerTriple> {
  Float _first; 
  Integer _second, _third;

  public IntegerTriple(float f,Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return (this.first() - o.first() > 0 ? 1 : -1);
    else if (!this.second().equals(o.second()))
      return (this.second() - o.second() > 0 ? 1 : -1);
    else
      return (this.third() - o.third() > 0 ? 1 : -1);
  }

  Float first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }

  public String toString() { return first() + " " + second() + " " + third(); }
}

// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
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
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret;
    }
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { numSets--; 
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

