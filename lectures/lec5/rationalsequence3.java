import java.util.*;

public class rationalsequence3 {

    public static int p (int i) {return i>>1; };
    public static int l (int i) {return i<<1; };
    public static int r (int i) {return (i<<1) + 1;};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        while(P-- > 0) {
            int K = sc.nextInt(), N = sc.nextInt();
            int p = 1, q = 1;
            Stack<Character> s = new Stack<>();

            // finding sequence of L or R to reach index N
            while (N > 1) {
                if (l(p(N)) == N) {
                    s.push('l');
                } else {
                    s.push('r');
                }
                N = p(N);
            }

            while(!s.isEmpty()) {
                if(s.peek() == 'l') {
                    q += p;
                } else {
                    p += q;
                }
                s.pop();
            }

            System.out.println(K + " " + p + "/" + q);
        }
    }
}