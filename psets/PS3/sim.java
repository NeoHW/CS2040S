import java.io.*;
import java.util.*;

public class sim {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int T = sc.nextInt(); sc.nextLine();
        while(T-- > 0) {
            String s = sc.nextLine();

            LinkedList<Character> ll = new LinkedList<>();
            LinkedList<Character> temp = new LinkedList<>();
            boolean usingTemp = false;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '<') {
                    if (usingTemp) {
                        temp.pollLast();
                    }  else {
                        ll.pollLast();
                    }
                } else if (s.charAt(i) == '[') {
                    if(usingTemp) {
                        // temp.addAll(ll);
                        ll.addAll(0,temp);
                        // ll = temp;
                        temp = new LinkedList<>();
                        // usingTemp = false; // don't have to change this as we will be using another temp
                    } else {
                        usingTemp = true;
                    }
                } else if (s.charAt(i) == ']') {
                    if(usingTemp) {
                        // temp.addAll(ll);
                        ll.addAll(0,temp);
                        // ll = temp;
                        temp = new LinkedList<>();
                        usingTemp = false;
                    }
                } else {
                    if(usingTemp) {
                        temp.addLast(s.charAt(i));
                    } else {
                        ll.addLast(s.charAt(i));
                    }
                }
            }

            // check for if there is still temp 
            if(usingTemp) {
                ll.addAll(0,temp);
            }

            Iterator<Character> it = ll.iterator();
            while (it.hasNext()) {
	            pw.print(it.next());
            }
            pw.println();

            pw.flush();
        }
    }
}