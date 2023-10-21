import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'perfectSubstring' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static int perfectSubstring(String s, int k) {
        int ans = 0;
        int distinct = 0;
        boolean[] have = new boolean[10];
        
        for (int i = 0; i < s.length(); i++) {
            have[Integer.parseInt(String.valueOf(s.charAt(i)))] = true;
        }
        
        // count number of distinct ints
        for (int i = 0; i < 10; i++) {
            if(have[i]) {
                distinct++;
            }
        }
        
        for (int length = 1; length <= distinct; length++) { // varying the window length
            int window_length = length*k; //length of substring multiple of k
            int freq[] = new int[10];
            int start = 0;
            int end = start+window_length-1;
            
            for (int i = start; i <= Math.min(end, s.length() -1); i++) {
                freq[Integer.parseInt(String.valueOf(s.charAt(i)))]++;
            }
            
            // slides the window across the string
            while(end < s.length()) {
                if(all_same_freq(freq, k)) {
                    ans++;
                }
                freq[Integer.parseInt(String.valueOf(s.charAt(start)))]--; // removing from start of window
                start++;
                end++;
                if(end < s.length()) {
                    freq[Integer.parseInt(String.valueOf(s.charAt(end)))]++; // adding to end of window
                }
            }
        }
        return ans;
    }
    
    public static boolean all_same_freq(int[] freq, int k) {
        for(int i = 0 ; i < 10; i++) {
            if(freq[i] != 0 && freq[i] != k) {
                return false;
            }
        }
        return true;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.perfectSubstring(s, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
