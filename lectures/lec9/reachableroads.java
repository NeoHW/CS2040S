import java.util.*;

public class reachableroads {

    // declaring global variables
    public static ArrayList<ArrayList<Integer>> AL;
    public static ArrayList<Boolean> visited;

    public static void dfs(int i) {
        visited.set(i, true);
        for(int v : AL.get(i)) { // for each neighbour v of vertex i
            if(!visited.get(v)) { // if my neighbour v is not yet visited
                dfs(v); // go there
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        while(tc-- > 0) {
            int v = sc.nextInt();
            int e = sc.nextInt();
            sc.nextLine();

            // creating the visited arraylist
            visited = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                visited.add(false);
            }

            // creating the AL
            AL = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                AL.add(new ArrayList<Integer>());
            }

            // populating the AL
            for (int i = 0; i < e; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                AL.get(a).add(b);
                AL.get(b).add(a);
            }

            int cc = 0;
            for (int i = 0 ; i < v; i++) {
                if(!visited.get(i)) {
                    dfs(i);
                    cc++;
                }
            }
            System.out.println(cc-1);
        }
    }
}
