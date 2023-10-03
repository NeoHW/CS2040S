import java.util.*;

// as long as we do the task that finished first for each available time, we will do max num of tasks

public class speedrun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt(); int N = sc.nextInt();
        sc.nextLine();

        ArrayList<Pair> list = new ArrayList<>();

        while (N-- > 0) {
            list.add(new Pair(sc.nextInt(), sc.nextInt()));
            sc.nextLine();
        }
        list.sort((a, b) -> a.second - b.second);

        int countMaxTasks = 0;
        int time = 0;

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).first >= time) {
                countMaxTasks++;
                time = list.get(i).second;
            }
        }

        System.out.println(countMaxTasks >= G ? "YES" : "NO");

    }

    static class Pair {
        public int first;
        public int second;
        
        public Pair(int x, int y) {
            first = x;
            second = y;
        }
    }
}

