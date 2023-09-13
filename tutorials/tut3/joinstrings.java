import java.io.*;
import java.util.*;

public class joinstrings {

    static class Node {
        String word;
        Node tail;
        Node next;

        public Node(String word) {
            this.word = word;
            this.tail = this;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        FastInput fi = new FastInput(System.in);

        int N = fi.nextInt();
        Node[] nodes = new Node[N];
        int begin = 0; // finding which is the starting string


        for (int i=0; i< N; i++) {
            nodes[i] = new Node(fi.next());
            begin ^= i; // xor each element to cancel out later
            // 1 xor 2 xor 3 xor 4 xor 3 xor 2 xor 1 = 4, since 1 xor 1 cancels out
        }


        for (int i=0; i<N-1; i++) {    
            int a = fi.nextInt()-1;
            int b = fi.nextInt()-1;
            Node A = nodes[a];
            Node B = nodes[b];
            A.tail.next = B;
            A.tail = B.tail;
            begin ^= b;
        }

        StringBuilder sbd = new StringBuilder();

        for(Node n = nodes[begin]; n!=null; n = n.next) {
            sbd.append(n.word);
        }

        System.out.println(sbd);
    }
}

class FastInput {
    BufferedReader br;
    StringTokenizer tok;
    public FastInput(InputStream in) {
        br = new BufferedReader(new InputStreamReader(System.in));
        tok = new StringTokenizer("");
    }

    public String next() {
        while (!tok.hasMoreTokens()) {
            try {
                tok = new StringTokenizer(br.readLine());
            } catch (IOException e) {
            }
        }
        return tok.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}