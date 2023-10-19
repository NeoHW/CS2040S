import java.util.*;

public class baconeggsandspam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt(); sc.nextLine();

            if (n == 0) {
                return;
            }

            TreeMap<String, ArrayList<String>> tm = new TreeMap<>();

            while(n-- >0) {
                String[] arr = sc.nextLine().split(" ");
                for(int i = 1; i < arr.length; i++) {
                    if(!tm.containsKey(arr[i])) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(arr[0]);
                        tm.put(arr[i], list);
                    } else {
                        // we can do this as get returns a *reference* to it
                        tm.get(arr[i]).add(arr[0]);
                    }
                }
            }

            for (String key : tm.keySet()) {
                System.out.print(key + " ");
                ArrayList<String> al = tm.get(key);
                al.sort((a,b) -> a.toLowerCase().compareTo(b.toLowerCase()));
                for(String s : al) {
                    System.out.print(s + " ");
                }
                System.out.println();    
            }
            System.out.println();
        }
    }    
}