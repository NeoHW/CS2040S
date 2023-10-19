import java.util.*;

public class baconeggsandspam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt(); sc.nextLine();

            if (n == 0) {
                return;
            }

            TreeMap<String, PriorityQueue<String>> tm = new TreeMap<>();

            while(n-- >0) {
                String[] arr = sc.nextLine().split(" ");
                for(int i = 1; i < arr.length; i++) {
                    if(!tm.containsKey(arr[i])) {
                        PriorityQueue<String> pq = new PriorityQueue<>();
                        pq.add(arr[0]);
                        tm.put(arr[i], pq);
                    } else {
                        PriorityQueue<String> pq = tm.get(arr[i]);
                        pq.add(arr[0]);
                        tm.put(arr[i], pq);
                    }
                }
            }

            for (String key : tm.keySet()) {
                System.out.print(key + " ");
                PriorityQueue<String> pq = tm.get(key);
                Iterator<String> it = pq.iterator();
                while(it.hasNext()) {
                    System.out.print(it.next() + " ");
                }
                System.out.println();    
            }
            System.out.println();
        }
    }    
}
