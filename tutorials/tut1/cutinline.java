import java.util.*;

public class cutinline {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); sc.nextLine();

        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i < N; i++) {
            list.add(sc.nextLine());
        }

        int numEvents = sc.nextInt(); sc.nextLine();
        for (int i=0; i < numEvents; i++) {
            String[] parts = sc.nextLine().split(" ");
            if (parts.length == 2) {
                list.remove(parts[1]);
            } else {
                list.add(list.indexOf(parts[2]), parts[1]);
            }
        }

        for(String name:list) {
            System.out.println(name);
        }
    }
}
