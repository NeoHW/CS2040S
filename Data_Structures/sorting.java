import java.io.*;
import java.util.*;

class SortingDemo {
  private static void swap(int a[], int i, int j) { // swap array elements i and j
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }


  // https://visualgo.net/en/sorting?slide=7-1
  private static void bubbleSort(int a[], int N) { // the standard version
    for (; N > 0; --N) // N iterations
      for (int i = 0; i < N-1; ++i) // except the last, O(N)
        if (a[i] > a[i+1]) // not in non-decreasing order
          swap(a, i, i+1); // swap in O(1)
  }


  // https://visualgo.net/en/sorting?slide=11-2
  private static void merge(int a[], int low, int mid, int high) {
    // subarray1 = a[low..mid], subarray2 = a[mid+1..high], both sorted
    int N = high-low+1;
    int[] b = new int[N]; // discuss: why do we need a temporary array b?
    int left = low, right = mid+1, bIdx = 0;
    while (left <= mid && right <= high) // the merging
      b[bIdx++] = (a[left] <= a[right]) ? a[left++] : a[right++];
    while (left <= mid) b[bIdx++] = a[left++]; // leftover, if any
    while (right <= high) b[bIdx++] = a[right++]; // leftover, if any
    for (int k = 0; k < N; ++k) a[low+k] = b[k]; // copy back
  }

  // https://visualgo.net/en/sorting?slide=11-5
  private static void mergeSort(int a[], int low, int high) {
    // the array to be sorted is a[low..high]
    if (low < high) { // base case: low >= high (0 or 1 item)
      int mid = (low+high) / 2; 
      mergeSort(a, low  , mid ); // divide into two halves
      mergeSort(a, mid+1, high); // then recursively sort them
      merge(a, low, mid, high); // conquer: the merge routine
    }
  }


  // https://visualgo.net/en/sorting?slide=12-7, with two lines addition for https://visualgo.net/en/sorting?slide=13
  private static int partition(int a[], int i, int j) {
    // ================== the only addition for Randomized Quick Sort
    Random rand = new Random();
    int r = i + rand.nextInt(j-i+1); // a random index between [i..j]
    swap(a, i, r); // tada
    // ==================
    int p = a[i]; // p is the pivot
    int m = i; // S1 and S2 are initially empty
    for (int k = i+1; k <= j; ++k) { // explore the unknown region
      if ((a[k] < p) || ((a[k] == p) && (rand.nextInt(2) == 0))) { // case 2 (PATCHED solution to avoid TLE O(N^2) on input array with identical values)
        ++m;
        swap(a, k, m); // manual swap function
      } // notice that we do nothing in case 1: a[k] > p
    }
    swap(a, i, m); // final step, swap pivot with a[m]
    return m; // return the index of pivot, to be used by Quick Sort
  }

  // https://visualgo.net/en/sorting?slide=12-8
  private static void quickSort(int a[], int low, int high) {
    if (low < high) {
      int pivotIdx = partition(a, low, high); // O(N)
      // a[low..high] ~> a[low..pivotIdxâ€“1], pivot, a[pivotIdx+1..high]
      quickSort(a, low, pivotIdx-1); // recursively sort left subarray
      // a[pivotIdx] = pivot is already sorted after partition
      quickSort(a, pivotIdx+1, high); // then sort right subarray
    }
  }


  // to be used by various sorting algorithms
  private static void printArray(int a[], int n) {
    for (int i = 0; i < n; ++i) {
      if (i > 0) pw.print(" ");
      pw.print(a[i]);
    }
    pw.println();
  }


  private static final int MAX_N = 200000; // big enough for our demo to notice the difference
  // if you encounter runtime error/stack overflow, run this Java code using: java -Xss515m SortDemo to increase stack size (otherwise Stack Overflow during non-randomized Quicksort recursion)

  private static PrintWriter pw;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    pw = new PrintWriter(System.out);

    int[] a = new int[MAX_N];
    int n = MAX_N; // use smaller number if you intend to print the actual array before/after sorting or if you want to test any O(N^2) sorting algorithm
    Random r = new Random();
    for (int i = 0; i < n; ++i)
      a[i] = r.nextInt(1000000); // [0..1000000-1]

    long begin = System.currentTimeMillis();
    // printArray(a, n);
    // bubbleSort(a, n);
    // printArray(a, n);
    pw.printf("Elapsed time for Bubble Sort (uncomment the line above first, be careful, this is slow): %.3fs\n", (double)(System.currentTimeMillis()-begin) / 1000);

    begin = System.currentTimeMillis();
    // printArray(a, n);
    mergeSort(a, 0, n-1);
    // printArray(a, n);
    pw.printf("Elapsed time for Merge Sort: %.3fs\n", (double)(System.currentTimeMillis()-begin) / 1000);

    n = MAX_N;
    for (int i = 0; i < n; i++)
      // a[i] = i; // increasing input, one of the hardest test case for (non Randomized) Quick Sort
      // a[i] = 7; // constant input, also one of the hardest test case for (WRONGLY implemented or non Randomized) Quick Sort
      a[i] = r.nextInt(1000000); // [0..1000000-1]

    begin = System.currentTimeMillis();
    // printArray(a, n);
    quickSort(a, 0, n-1); // experiment with line 44-46 above
    // Arrays.sort(a); // in Java SE 18 (2022), Arrays.sort uses "Dual-Pivot Quicksort"
    // printArray(a, n);
    pw.printf("Elapsed time for Quick Sort: %.3fs\n", (double)(System.currentTimeMillis()-begin) / 1000);

    pw.close();
  }
}