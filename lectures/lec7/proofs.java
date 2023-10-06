import java.util.*;
import java.io.*;

class proofs {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        HashSet<String> hs = new HashSet<>();
        boolean proved = true;
        
        int lineNum = 0;
        while(n-- > 0) {
            lineNum++;
            String[] tok = br.readLine().split("->");
            String[] a = tok[0].split(" ");
            if (tok[0].isEmpty()) {
                hs.add(tok[1].trim());
            } else {
                for(String s : a) {
                    if(!hs.contains(s.trim())) {
                        proved = false;
                        break;
                    }
                }
                hs.add(tok[1].trim());
            }
            if(!proved) {
                break;
            }
        }

        if(proved) {
            System.out.println("correct");
        } else {
            System.out.println(lineNum);
        }
    }
}