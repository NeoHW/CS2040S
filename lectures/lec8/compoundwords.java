import java.util.*;

public class compoundwords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while(sc.hasNext()) {
            String[] arr = sc.nextLine().split(" ");
            for(String word: arr) {
                list.add(word);
            }
        }
        
        // put it into an AVL tree
        TreeSet<String> ts = new TreeSet<>();
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < list.size(); j++) {
                if(i == j) {
                    continue;
                }
                String newWord = list.get(i) + list.get(j);
                ts.add(newWord);
            }
        }

        Iterator<String> iterator = ts.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
