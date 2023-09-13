import java.io.*;
import java.util.*;

public class jobbyte {
    public static void main (String[] args) {
        FastInput fi = new FastInput(System.in);
        int N = fi.nextInt();
        
        // populating arr
        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++) {
            arr[i] = fi.nextInt();
        }

        // boolean arr
        boolean[] done = new boolean[N+1];

        // algo
        int swaps = 0;
        int cycles = 0;

        // loop through boolean array until all is true from idx 1
        for(int i = 1; i <= N; i++) {
            
            if(arr[i] == i) {
                done[i] = true;
            }

            if(done[i]) {
                continue;
            }
            
            int startingIndex = i;
            int nextIndex = arr[i];
            
            // one cycle
            while(done[nextIndex] != true) {
                swaps++;
                done[nextIndex] = true;
                startingIndex = arr[startingIndex];
                nextIndex = arr[startingIndex];
            }
            // account for one cycle
            cycles++;
        }


        // if entire array 1 cycle, num of swaps : N-1 
        // if entire array 2 cycle, num of swaps : N-2
        // if entire array k cycle, num of swaps : N-k 
        // count how many cycles in the array

        System.out.println(swaps - cycles);

    }
}

class FastInput {
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