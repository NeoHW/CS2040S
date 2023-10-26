import java.util.*;
import java.io.*;

public class nicknames {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<String, Integer> hm = new HashMap<>();

        int A = Integer.parseInt(br.readLine());
        while (A-- > 0) {
            String s = br.readLine();

            for (int i = 1 ; i  <= s.length(); i++) {
                String substring = s.substring(0, i);
                if (hm.get(substring) == null) {
                    hm.put(substring, 1);
                } else {
                    int num = hm.get(substring);
                    hm.put(substring, num + 1);
                }
            }
        }

        int B = Integer.parseInt(br.readLine());
        while (B-- > 0) {
            String nn = br.readLine();
            
            if(hm.get(nn) == null) {
                pw.println(0);
            } else {
                pw.println(hm.get(nn));
            }
        }
        pw.flush();
        pw.close();
    }
}