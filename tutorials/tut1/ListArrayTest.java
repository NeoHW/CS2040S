import java.util.*;

class ListArray<T> { // question 1a
    private int N;
    private Object[] A = new Object[100]; // question 1b

    public ListArray() {
        N = 0; /* N will max be 100 */
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) A[i]; // question 1c
    }

    public int indexOf(T v) {
        for (int i = 0; i < N; ++i)
            if (A[i].equals(v))
                return i;
        return -1;
    }

    public void add(T v) { // question 1d
        if (N == 100)
            return;
        A[N++] = v;
    }

    public void add(int i, T v) {
        if (N == 100 || i < 0 || i > N) // question 1e
            return;
        for (int j = N - 1; j >= i; --j)
            // for (int j = i; j <= N-1; ++j) // question 1f
            A[j + 1] = A[j];
        A[i] = v;
        ++N;
    }

    public void remove(int i) {
        for (int j = i; j < N - 1; ++j) // question 1g
            A[j] = A[j + 1];
        --N;
    }

    public void printList() {
        for (int i = 0; i < N; ++i)
            System.out.print((i > 0 ? " " : "") + A[i]); // question 1h
        System.out.println();
    }

    public void sortList() {
        // sort array A, question 1i

        // Arrays.sort(A,0,N);

        // bubble sort early termination implementation
        for (int i = N-1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                @SuppressWarnings("unchecked")
                Comparable<T> item1 = (Comparable<T>) A[j];
                T item2 = (T) A[j + 1];
            
                if (item1.compareTo(item2) > 0) {
                    T temp = (T) A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

class ListArrayTest {
    public static void main(String[] args) {
        ListArray<Integer> LA = new ListArray<>();
        LA.add(5);
        LA.add(0, 1);
        LA.add(0, 4);
        LA.add(0, 7);
        LA.add(0, 2); // we should have A = {2, 7, 4, 1, 5} by now
        System.out.println(LA.get(3)); // 1, A[3] = 1
        System.out.println(LA.indexOf(4)); // 2, A[2] = 4
        System.out.println(LA.indexOf(6)); // not found, -1
        LA.remove(1); // we should have A = {2, 4, 1, 5} by now
        System.out.println(LA.indexOf(4)); // 1, A[1] = 4 now
        System.out.println(LA.indexOf(7)); // not found, -1
        LA.printList(); // unsorted
        LA.sortList(); // we should have A = {1, 2, 4, 5} by now
        LA.printList(); // sorted
    }
} // please copy paste the code above, test compile, and run it yourself