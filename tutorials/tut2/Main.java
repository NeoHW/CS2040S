import java.io.*;
import java.util.*;

public class Main {
  private static FastInput in = new FastInput(System.in);

  public static int search(int[] a, int v) {
    int lo = 0, hi = a.length - 1, ans = a.length;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (a[mid] >= v) {
        ans = mid;
        hi = mid - 1;
      }
      else lo = mid + 1;
    }
    return ans;
  }

  public static void main(String[] args) {
    int N = in.nextInt();
    int t = in.nextInt();

    int[] a = new int[N];
    for (int i = 0; i < N; ++i) {
      a[i] = in.nextInt();
    }
    Arrays.sort(a, 0, N);

    if (t == 5) {
      int l = search(a, 100);
      int r = search(a, 1000);
      for (int i = l; i < r; ++i) {
        System.out.printf("%d ", a[i]);
      }
      System.out.println();
    }
  }

  static class FastInput {
    BufferedReader br;
    StringTokenizer tok;
    public FastInput(InputStream in) {
      br = new BufferedReader(new InputStreamReader(System.in));
      tok = new StringTokenizer("");
    }

    public String next() {
      while (!tok.hasMoreTokens()) {
        try {
          tok = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      }
      return tok.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

    public long nextLong() {
      return Long.parseLong(next());
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
