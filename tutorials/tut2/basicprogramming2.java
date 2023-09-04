import java.io.*;
import java.util.*;

public class basicprogramming2 {
    public static void main(String[] args) {
        FastInput in = new FastInput(System.in);
        int N = in.nextInt(), t = in.nextInt();
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = in.nextInt();
        }
        
        // sort array
        Arrays.sort(arr);

        switch (t) {
            case 1:
                twoSum(arr, N);
                break;
            case 2:
                checkUnique(arr, N);
                break;
            case 3:
                q3(arr, N); // sliding window?
                break;
            case 4:
                printMedian(arr, N);
                break;
            case 5:
                printInRange(arr, N);
                break;
        }


    }

    public static void twoSum(int[] arr, int N) {
        int start = 0;
        int end = N-1;
        boolean flag = false;

        while(start < end) {
            if(arr[start] == arr[end]) {
                break;
            }
            int sum = arr[start]+arr[end]; 
            if (sum == 7777) {
                flag = true;
                break;
            } else if (sum > 7777) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(flag ? "yes" : "No");
    }

    public static void checkUnique(int[] arr, int N) {
        boolean flag = true;

        for(int i = 0; i < N-1; i++) {
            if(arr[i] == arr[i+1]) {
                flag = false;
                break;  
            } 
        }

        System.out.println(flag ? "Unique" : "Contains duplicate");
    }

    public static void q3(int[] arr, int N) {
        int ans = -1;
        int numReq = N/2 + 1;

        for(int i = 0; i < N-numReq; i++) {
            if(arr[i] == arr[i+numReq-1]) {
                ans = arr[i];
                break;
            } 
        }

        System.out.println(ans);
    }

    public static void printMedian(int[] arr, int N) {
        if (N%2!=0) {
            System.out.println(arr[N/2]);
        } else {
            System.out.println(arr[N/2 - 1] + " " + arr[N/2]);
        }
    }

    public static void printInRange(int[] arr, int N) {
        int l = search(arr, 100);
        int h = search(arr, 1000);

        for(int i=l; i<h;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // why does this binary search work even though it is lower-bound?
    // lower_bound: Find lowest i, such that a[i] >= v
    public static int search(int[] arr, int v) {
        int low = 0, high = arr.length -1, ans = arr.length;
        
        while(low <= high) {
            int mid = (low+high)/2;
            if(arr[mid] >= v) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
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