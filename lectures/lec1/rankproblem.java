import java.util.*;

public class rankproblem {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTeams = sc.nextInt(), matches = sc.nextInt(); sc.nextLine();

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<=numTeams; ++i) {
            list.add(i); // adding 1,2,3...numteams
        }


        // for(int i=0; i<m; i++) { // repeat m times
        while(matches-- > 0) {
            String line = sc.nextLine();
            String[] token = line.split(" ");
            int i = Integer.parseInt(token[0].substring(1));
            int j = Integer.parseInt(token[1].substring(1));

            int n = list.indexOf(i);
            int m = list.indexOf(j);
            
            if(n > m) { // need to shuffle positions
                list.remove(m); // remove team i from index m, everybody moves +1 rank
                list.add(n,j); // slot team j at this position (behind team i)
            }
        }

        for (Integer i : list) {
            System.out.print("T" + i + " ");
        }
        System.out.println();

    }
}