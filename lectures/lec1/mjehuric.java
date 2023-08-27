import java.util.*;

public class mjehuric {
    public static void main(String[] args) throws Exception {
        // time complexity is O(1) as n is constant, max is 125 steps (5^3)
        
        Scanner sc = new Scanner(System.in);
        int n = 5;
        int[] arr = new int[5];

        for(int i=0; i<n;i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=n-1; i>0; i--) { // O(n^3)??
            for(int j=0;j<i;j++) { // O(n^2)
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    for(int k=0; k<n; k++) { // O(n)
                        System.out.print(arr[k] + " ");
                    }
                    System.out.println();
                }       
            }
        }
    }
}
