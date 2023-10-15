import java.util.*;

public class bokforing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        sc.nextLine();

        HashMap<Integer, Integer> hm = new HashMap<>();
        int currentWealth = 0;

        while(Q-- > 0) {
            String[] tok = sc.nextLine().split(" ");
            if (tok[0].equals("SET")) {
                hm.put(Integer.parseInt(tok[1]), Integer.parseInt(tok[2])); // keep track which id has changes
            } else if (tok[0].equals("PRINT")) {
                int index = Integer.parseInt(tok[1]);
                System.out.println(hm.get(index) == null ? currentWealth : hm.get(index));
            } else {
                currentWealth = Integer.parseInt(tok[1]);
                hm.clear();
            }
        }
    }
}
