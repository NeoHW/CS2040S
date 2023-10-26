import java.util.*;
import java.io.*;

public class teque {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> frontHalf = new HashMap<>();
        HashMap<Integer, Integer> backHalf = new HashMap<>();

        int frontHalfStart = 0; // where next element WILL BE  added if it is added to start
        int frontHalfEnd = 1;   // where next element WILL BE added if it is added to end
        int backHalfStart = 0;
        int backHalfEnd = 1;

        while (N-- > 0) {
            String tok[] = br.readLine().split(" ");
            String command = tok[0];
            int num = Integer.parseInt(tok[1]);

            if (command.equals("push_back")) {
                backHalf.put(backHalfEnd++, num);
            }
            else if (command.equals("push_front")) {
                frontHalf.put(frontHalfStart--, num);
            }
            else if (command.equals("push_middle")) {
                frontHalf.put(frontHalfEnd++, num);
            }
            else { // get
                if (num < frontHalf.size()) {
                    pw.println(frontHalf.get(num + frontHalfStart + 1));
                } else {
                    pw.println(backHalf.get(num - frontHalf.size() + backHalfStart + 1));
                }
            }

            
            System.out.println();
            System.out.println(command +" "+num + " before rebalancing");
            System.out.println("frontHalf");
            System.out.println("frontHalfStart: " + frontHalfStart);
            System.out.println("frontHalfEnd: " + frontHalfEnd);
            for(int  key : frontHalf.keySet()) {
                System.out.println(key + ": " + frontHalf.get(key));
            }

            System.out.println();

            System.out.println("backHalf");
            System.out.println("backHalfStart: " + backHalfStart);
            System.out.println("backHalfEnd: " + backHalfEnd);
            for(int  key : backHalf.keySet()) {
                System.out.println(key + ": " + backHalf.get(key));
            }

            // rebalancing the halfs so that add middle can be done using adding to back of frontHalf 
            
            if (frontHalf.size() - 1 > backHalf.size()) { // case 1 : fh max 1 more than bh
                backHalf.put(backHalfStart--, frontHalf.get(frontHalfEnd - 1));
                frontHalf.remove(--frontHalfEnd);
            } else if (frontHalf.size() < backHalf.size()) {
                frontHalf.put(frontHalfEnd++, backHalf.get(backHalfStart + 1));
                backHalf.remove(++backHalfStart);
            }

            //debugging
            System.out.println(command +" "+num + " after rebalancing");
            System.out.println("frontHalf");
            System.out.println("frontHalfStart: " + frontHalfStart);
            System.out.println("frontHalfEnd: " + frontHalfEnd);
            for(int  key : frontHalf.keySet()) {
                System.out.println(key + ": " + frontHalf.get(key));
            }

            System.out.println();

            System.out.println("backHalf");
            System.out.println("backHalfStart: " + backHalfStart);
            System.out.println("backHalfEnd: " + backHalfEnd);
            for(int  key : backHalf.keySet()) {
                System.out.println(key + ": " + backHalf.get(key));
            }

            System.out.println("==============");
        }

        pw.flush();
        pw.close();
    }
}