import java.util.*;
import java.io.*;

public class kaploeb {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int l = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        int s = Integer.parseInt(inputs[2]);

        HashMap<Integer,Pair> hm = new HashMap<>();

        // populate hashmap with total laps ran and final timing for all laps input
        while(l-- >0) {
            String[] tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]);

            // the period (.) is a special character in regular expressions and is used to match any character
            // we need to escape it with 2 backslahes as backslash is also an escape character
            String[] time = tok[1].split("\\."); 

            int mins = Integer.parseInt(time[0]) * 60;
            int seconds = Integer.parseInt(time[1]);
            
            if(hm.containsKey(a)) {
                Pair temp = hm.get(a);
                temp.first++;
                // convert all to seconds 
                temp.second = temp.second + mins + seconds;
                hm.put(a, temp);
            } else {
                hm.put(a, new Pair(1, mins + seconds));
            }
        }

        ArrayList<Pair> list = new ArrayList<>();

        for (Integer key : hm.keySet()) {
            if(hm.get(key).first == k) {
                list.add(new Pair(key, hm.get(key).second));
            }
        }

        list.sort((a,b) -> {
            if (a.second == b.second) {
                return a.first - b.first;
            } else if (a.second - b.second < 0){
                return -1;
            } else {
                return 1;
            }
        });

        for(Pair i : list) {
            System.out.println(i.first);
        }
    }

    public static class Pair {
        public int first; // num laps ran
        public long second; // total time in seconds

        public Pair(int a, long b) {
            this.first = a;
            this.second = b;
        }
    }
}
