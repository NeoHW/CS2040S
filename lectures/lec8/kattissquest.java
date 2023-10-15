import java.util.*;

public class kattissquest {
    public static void main(String[] args) {
        // dynamic structure and maintain sorted order : AVL tree
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        // to handle duplicates energy with different gold: use PQ
        TreeMap<Integer, PriorityQueue<Integer>> avl = new TreeMap<>();

        while(N-- > 0) {
            String[] tok = sc.nextLine().split(" ");
            if(tok[0].equals("add")) {
                int key = Integer.parseInt(tok[1]);
                int value = Integer.parseInt(tok[2]);
                if (avl.get(key) == null) {
                    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
                    pq.add(value);
                    avl.put(key, pq);
                } else {
                    PriorityQueue<Integer> pq = avl.get(key);
                    pq.add(value);
                    avl.put(key, pq);
                }
            } else { // query int
                long gold = 0;
                int energyLevel = Integer.parseInt(tok[1]);
                
                while(energyLevel > 0) {
                    if(avl.lowerKey(energyLevel+1) == null) break;

                    int key = avl.lowerKey(energyLevel+1); // because lowerKey returns strictly less than given number
                    PriorityQueue<Integer> pq = avl.get(key);
                    gold += pq.poll();
                    if(pq.isEmpty()) {
                        avl.remove(key);
                    }
                    energyLevel -= key;
                    
                }
                System.out.println(gold);
            }
        }
    }
}
