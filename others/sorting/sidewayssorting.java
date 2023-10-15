import java.util.*;

class Pair {
    public int a;
    public char b;

    public Pair(int a, char b) {
      this.a = a;
      this.b = b;
    }
}



public class sidewayssorting {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (r == 0 && c == 0) {
                return;
            }
            sc.nextLine();

            // sorting
            char[] arr = sc.nextLine().toCharArray();
            // char[] sorted = arr;
            char[] sorted = Arrays.copyOf(arr, c);
            mergeSort(sorted, 0, c-1);
            
            // populating indexes array
            int[] indexes = new int[c];
            for(int i = 0; i < c; i++) {
                indexes[i] = Arrays.binarySearch(sorted, arr[i]);
            }
            
            // printing out indexes
            for (int i = 0 ; i < c; i++) {
                System.out.print(indexes[i]);
            }
            System.out.println();

            // printing out first line
            for (int i = 0 ; i < c; i++) {
                System.out.print(sorted[i]);
            }
            System.out.println();
            r--;

            while(r-- > 0) {
                char[] arr2 = sc.nextLine().toCharArray();
                for (int i = 0 ; i < c; i++) {
                    System.out.print(arr2[indexes[i]]);
                }   
                System.out.println();
            }
            System.out.println();
        }
    }


    public static void mergeSort(char a[], int low, int high) {
        // the array to be sorted is a[low..high]
        if (low < high) { // base case: low >= high (0 or 1 item)
          int mid = (low+high) / 2;	
          mergeSort(a, low, mid ); // divide into two halves
          mergeSort(a, mid+1, high); // then recursively sort them
          merge(a, low, mid, high); // conquer: the merge subroutine
        }
    }
      
    public static void merge(char a[], int low, int mid , int high) {
        // subarray1 = a[low...mid], subarray2 = a[mid+1...high], both sorted
        int N = high-low+1;
        char[] b = new char[N]; // discuss why do we need temp array b
        int left = low, right = mid+1, bIdx = 0;
        while (left <= mid && right <= high) { // the merging
            b[bIdx++] = (Character.toLowerCase(a[left]) <= Character.toLowerCase(a[right])) ? a[left++] : a[right++];
        }
        while (left <= mid) b[bIdx++] = a[left++]; // leftover, if any
        while (right <= high) b[bIdx++] = a[right++]; // leftover, if any
        for (int k =0; k < N; k++) a[low+k] = b[k]; // copy back
    }
}

