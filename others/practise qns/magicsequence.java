// subtask 1 & 2 : library sort
// subtask 3 : counting sort
// full soln : radix sort



// runtime error for this solution
import java.util.*;
import java.io.*;

public class magicsequence {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int TC = Integer.parseInt(br.readLine());
        
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            String[] tok = br.readLine().split(" ");
            int A = Integer.parseInt(tok[0]);
            int B = Integer.parseInt(tok[1]);
            int C = Integer.parseInt(tok[2]);

            String[] tok2 = br.readLine().split(" ");
            int X = Integer.parseInt(tok2[0]);
            int Y = Integer.parseInt(tok2[1]);

            arr[0] = A;
            int max = A;
            for (int i = 1; i < N; i++) {
                arr[i] = (arr[i-1] * B + A) % C;
                max = Math.max(max, arr[i]);
            }

            // sorting
            // Arrays.sort(arr);

            // counting sort
            int[] countArray = new int[max+1];
            
            // mapping each eleement of arr as an index of count_array
            for (int i : arr) {
                countArray[i] += 1;
            }

            // calculating prefix sum at every index of countArray
            for(int i = 1; i <= max; i++) {
                countArray[i] += countArray[i-1]; 
            }

            // output array
            int[] outputArray = new int[N];

            for(int i = N-1; i >=0; i--) {
                outputArray[countArray[arr[i]] -1]  = arr[i];
                countArray[arr[i]]--;
            }

            int v = 0;
            for (int i = 0; i < N; i++) {
                v = (v * X + outputArray[i]) % Y;
            }

            pw.println(v);
        }
        
        
        pw.flush();
        pw.close();
    }
}
