import java.util.*;
import java.io.*;

public class builddeps {
    public static HashMap<String,Boolean> visited;
    public static HashMap<String, ArrayList<String>> AL;
    public static ArrayList<String> toposort;

    public static void dfsTopoSort(String d) {
        visited.put(d,true);
        if (AL.get(d) != null) { // takes care of special case when get(d) is null
            for (String vertex : AL.get(d)) { // for each neighbour vertex
                if (!visited.get(vertex)) {
                    dfsTopoSort(vertex);
                }
            }
        }
        toposort.add(d); // post order traversal of dfs
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        visited = new HashMap<>();
        AL = new HashMap<>();
        toposort = new ArrayList<>();
        
        while (n-- > 0) {
            String[] tok = br.readLine().split(" ");
            tok[0] = tok[0].substring(0, tok[0].length()-1);
            visited.put(tok[0], false);
            for (int i = 1; i < tok.length; i++) {
                visited.put(tok[i], false);
                if (AL.get(tok[i]) == null) {
                    AL.put(tok[i], new ArrayList<>());
                }
                AL.get(tok[i]).add(tok[0]);
            }
        }

        String changed = br.readLine();
        dfsTopoSort(changed);

        Collections.reverse(toposort);
        for (int i = 0; i < toposort.size(); i++) {
            System.out.println(toposort.get(i));
        }
    }
}
