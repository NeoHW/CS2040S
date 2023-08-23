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
                    if (L % (temp%10) != 0) {
                        flag = false;
                        break;
                    }
                    temp /= 10;
                }
                if(flag == true) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static boolean checkValid(int num) {
        boolean[] used = new boolean[10];

        while (num != 0) {
            // if duplicated digit found, return 
            if (used[num%10]) return false;
            // update digit corresponding in arr
            used[num%10] = true;
            num /= 10;
        }
        
        return true;
    }
}
