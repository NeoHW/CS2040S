
// turn on screen recording, this is a close internet PE

import java.util.*;
import java.io.*;

public class q1 {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        pw.flush();
        pw.close();
    }
    
    static class Pair {
        public int first;
        public int second;

        public Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }

        public String toString() {
            return "[" + first + "," + second + "]";
        }
    }
}
