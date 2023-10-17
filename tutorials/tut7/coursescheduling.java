import java.util.*;

public class coursescheduling {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        TreeMap<String, HashSet<String>> tm = new TreeMap<String, HashSet<String>>();
        while(n-- > 0) {
            String s = sc.nextLine();
            int index = s.lastIndexOf(" ") + 1;
            String name = s.substring(0, index);
            String course = s.substring(index);

            if(!tm.containsKey(course)) {
                HashSet<String> hs = new HashSet<String>();
                hs.add(name);
                tm.put(course, hs);
            } else {
                HashSet<String> hs = tm.get(course);
                hs.add(name);
                tm.put(course, hs);
            }
        }

        for(String key : tm.keySet()) {
            System.out.println(key + " " + tm.get(key).size());
        }
    }    
}
