import java.io.*;

public class heirsdilemma {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int L = Integer.parseInt(token[0]);
        int R = Integer.parseInt(token[1]);
        int ans = 0;

        for (;L <= R; L++) {
            
            
            // rule 1: cannot have 0 or 5
            if (String.valueOf(L).contains("0") || String.valueOf(L).contains("5")) {
                continue;
            }

            // cannot have repeated digits
            if(!checkValid(L)) {
                continue;
            }

            // rule 2 : must end with 2/4/6/8
            if (L%10 == 2 || L%10 == 4 || L%10 == 6 || L%10 == 8) {
                boolean flag = true;
                int temp = L;
                while(temp != 0) {
                    //System.out.println("temp%10: " + temp%10);
                    if (L % (temp%10) != 0) {
                        //System.out.println("L: " + L);
                        //System.out.println("temp: " + temp);
                        flag = false;
                        break;
                    }
                    temp /= 10;
                    //System.out.println(flag);
                    //System.out.println("temp: " + temp);
                }
                if(flag == true) {
                    ans++;
                    // System.out.println(L);
                }
            }
        }
        System.out.println(ans);
    }

    public static boolean checkValid(int num) {
        boolean[] used = new boolean[10];

        while (num != 0) {
            if (used[num%10]) return false;
            used[num%10] = true;
            num /= 10;
        }
        
        return true;
    }
}
