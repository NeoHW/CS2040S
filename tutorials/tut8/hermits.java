import java.io.*;
import java.util.*;

public class hermits {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        int[] ans = new int[N+1];

        String[] tok = br.readLine().split(" ");
        for (int i = 1; i < N+1; i++) {
            int temp = Integer.parseInt(tok[i-1]);
            arr[i] = temp;
            ans[i] = temp;
        }

        int M = Integer.parseInt(br.readLine());
        
        while (M-- > 0) {
            String[] tok2 = br.readLine().split(" ");
            int u = Integer.parseInt(tok2[0]);
            int v = Integer.parseInt(tok2[1]);
            ans[u] += arr[v];
            ans[v] += arr[u];
        }

        int min = 1;
        for (int i = 2 ; i < N+1; i++) {
            if(ans[i] < ans[min]) {
                min = i;
            }
        }

        System.out.println(min);
    }
}
