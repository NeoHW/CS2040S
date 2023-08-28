import java.io.*;
import java.util.*;

public class FastInputTemplate {
  private static FastInput in = new FastInput(System.in);

  public static void main(String[] args) {

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
