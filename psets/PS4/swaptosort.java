import java.util.*;
import java.io.*;

public class swaptosort {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]);
        int K = Integer.parseInt(token[1]);

        UnionFind uf = new UnionFind(N);
        while (K-- > 0) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            uf.unionSet(a-1, b-1);
        }

        // check if number and their position is joint set
        for (int i = 0 ; i < N ; i++) {
            if(uf.findSet(i) == uf.findSet(N-1-i)) {
                continue;
            } else {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
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