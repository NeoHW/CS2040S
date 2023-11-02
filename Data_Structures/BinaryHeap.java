import java.io.*;
import java.util.*;

class BinaryHeap {
  private Vector<Integer> compact_vector;
  private int heap_size;

  // three helper navigation function
  private int p(int i) { return i>>1; } // i/2
  private int l(int i) { return i<<1; } // i*2
  private int r(int i) { return (i<<1)+1; } // i*2+1

  private void swap(Vector<Integer> a, int i, int j) {
    int temp = a.get(i);
    a.set(i, a.get(j));
    a.set(j, temp);
  }

  private void shift_up(int i) {
    if (i == 1) return; // at root, do nothing
    if (compact_vector.get(i) > compact_vector.get(p(i))) { // violate property with parent
      swap(compact_vector, i, p(i)); // swap upwards
      shift_up(p(i)); // recurse
    }
  }

  private void shift_down(int i) {
    if (i > heap_size) return; // beyond last element, do nothing
    int swap_id = i;
    if (l(i) <= heap_size && compact_vector.get(i) < compact_vector.get(l(i))) swap_id = l(i); // compare with left child, if exists
    if (r(i) <= heap_size && compact_vector.get(swap_id) < compact_vector.get(r(i))) swap_id = r(i); // compare with right child, if exists
    if (swap_id != i) { // need to swap with the larger of the two children
      swap(compact_vector, i, swap_id); // swap downwards with the larger of the two children
      shift_down(swap_id); // recurse
    }
  }

  public BinaryHeap() {
    compact_vector = new Vector<Integer>(); // clear the vector
    compact_vector.add(-1); // remember to set index 0 to be 'dummy'
    heap_size = 0; // an empty Binary Heap
  }

  public void offer(int x) {
    if (heap_size+1 >= compact_vector.size()) compact_vector.add(0); // enlarge the vector by one if needed
    compact_vector.set(++heap_size, x); // the only possible insertion point
    shift_up(heap_size); // shift upwards
  }

  public int poll() {
    int taken = compact_vector.get(1); // this is the maximum value
    swap(compact_vector, 1, heap_size--); // swap with the last existing leaf
    shift_down(1); // fix heap property downwards
    return taken; // return the maximum value
  }

  public int peek() {
    return compact_vector.get(1); // this is the root
  }

  public Boolean isEmpty() {
    return heap_size == 0; // the condition for an empty compact_vector
  }

  public int size() {
    return heap_size;
  }
};


class BinaryHeapDemo {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    pw.println("Our own Binary Heap");
    BinaryHeap pq = new BinaryHeap();
    pw.println(pq.isEmpty()); // should be true as pq content is {}

    pq.offer(5);
    pw.println(pq.isEmpty()); // should be false as pq content is {5 (max)}

    pq.offer(2);
    pq.offer(7);
    pq.offer(3);
    pw.println(pq.poll()); // should be 7 as pq content is {7 (max),5,3,2} -> {5 (max),3,2}

    pq.offer(1);
    pw.println(pq.peek()); // should be 5 as pq content is {5 (max),3,2,1}
    pw.println(pq.poll()); // should still be 5 as pq content is {5 (max),3,2,1} -> {(3 (max),2,1)}

    pq.offer(8);
    pq.offer(8); // duplicates is ok
    while (!pq.isEmpty()) // will print 8, 8, 3, 2, 1, in that order
      pw.println(pq.poll());


    pw.println();
    pw.println("Java PriorityQueue version (ignore the -)"); // this is a MIN heap, to get a max heap, we negate all numbers
    PriorityQueue<Integer> PQ_java = new PriorityQueue<>();
    pw.println(PQ_java.isEmpty()); // should be 1/true as PQ_java content is {}

    PQ_java.offer(-5);
    pw.println(PQ_java.isEmpty()); // should be 0/false as PQ_java content is {-5 (min)}

    PQ_java.offer(-2);
    PQ_java.offer(-7);
    PQ_java.offer(-3);
    pw.println(PQ_java.poll()); // should be -7 as PQ_java content is {-7 (min),-5,-3,-2} -> {-5 (min),-3,-2}

    PQ_java.offer(-1);
    pw.println(PQ_java.peek()); // should be -5 as PQ_java content is {-5 (min),-3,-2,-1}
    pw.println(PQ_java.poll()); // should still be 5 as PQ_java content is {-5 (min),-3,-2,-1} -> {-3 (min),-2,-1}

    PQ_java.offer(-8);
    PQ_java.offer(-8); // duplicates is ok
    while (!PQ_java.isEmpty()) // will print -8, -8, -3, -2, -1, in that order
      pw.println(PQ_java.poll());


    pw.println();
    pw.println("Equivalency testing on a very large test case");

    // large random test
    long begin = System.currentTimeMillis();

    Random rand = new Random();
    BinaryHeap ours = new BinaryHeap();
    PriorityQueue<Integer> theirs = new PriorityQueue<>();
    assert ours.isEmpty() && theirs.isEmpty(); // both empty at the start
    int N = 1000000; // usually just a few seconds
    for (int i = 0; i < N; ++i) { // insert N random integers to both data structures
      int value = rand.nextInt();
      ours.offer(value);
      theirs.offer(value); // yes you can use comma like this... or just split this into two lines
    }
    assert !ours.isEmpty() && !theirs.isEmpty(); // both not empty (has N entries) by now
    assert ours.size() == theirs.size(); // heap size should match, note that ours.size() returns int, theirs.size() returns size_t (depends on implementation, may not be really int)
    while (!ours.isEmpty()) {
      assert ours.peek() == -theirs.peek(); // max value should match (note that theirs contains negative values)
      ours.poll();
      theirs.poll();
      assert ours.size() == theirs.size(); // heap size should match
    }
    assert ours.isEmpty() && theirs.isEmpty(); // both empty at the end

    pw.printf("Test time = %fs\n", (System.currentTimeMillis()-begin)/1000.0);
    pw.println("If there is no assertion (Run Time Error), then all is good");

    pw.close();
  }
}