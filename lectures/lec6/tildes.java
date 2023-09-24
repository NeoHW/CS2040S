import java.util.*;
import java.io.*;

public class tildes {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int q = Integer.parseInt(token[1]);
        UnionFind uf = new UnionFind(n);

        while (q-- >0) {
            String[] parts = br.readLine().split(" ");
            if(parts[0].equals("t")) {
                uf.unionSet(Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]) - 1);
            } else {
               writer.println(uf.sizeOfSet(uf.findSet(Integer.parseInt(parts[1]) - 1)));
            }
        }

        writer.flush();
        writer.close();
    }    
}





class UnionFind {  // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new ArrayList<>(N);
    rank = new ArrayList<>(N);
    setSize = new ArrayList<>(N);
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
				p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
				if (rank.get(x) == rank.get(y)) {
					rank.set(y, rank.get(y) + 1); 
				}
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