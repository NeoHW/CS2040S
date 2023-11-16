import java.util.*;
import java.io.*;

public class ads {
    static char[][] mtx;
    static ArrayList<Border> list = new ArrayList<>();
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] tok = br.readLine().split(" ");
        int H = Integer.parseInt(tok[0]);
        int W = Integer.parseInt(tok[1]);

        mtx = new char[H][W];

        // generating the matrix
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                mtx[i][j] = s.charAt(j);
            }
        }

        // doing the checking for invalid characters

        String allowed = "+?!,. ";

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                // upon hitting an invalid character
                // check all 4 directions to see if in an image
                // if u hit boundary it is NOT in an image
                if (!Character.isLetterOrDigit(mtx[i][j]) && allowed.indexOf(mtx[i][j]) == -1) {
                    Border c = findBorders(i,j,H,W);
                    if (c.tlh == -1) {
                        continue;
                    }
                    list.add(c);
                    i = c.brh;
                    j = c.brw;
                }
            }
        }

        // changing the ads to whitesspace
        for (Border b : list) {
            System.out.println(b);
            int tlh = b.tlh;
            int tlw = b.tlw;
            int brh = b.brh;
            int brw = b.brw;

            for(int i = tlh; i <= brh; i++) {
                for(int j = tlw; j <= brw; j++) {
                    mtx[i][j] = ' ';
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                pw.print(mtx[i][j]);
            }
            pw.println();
        }

        pw.flush();
        pw.close();
    }

    // find the top left and bottom right Border of current image 
    public static Border findBorders(int i, int j, int H, int W) {
        int top = i;
        int btm = i;
        int left = j;
        int right = j;

        // if top == -1, means its not in any image
        while (top >= 0) {
            if (mtx[top][j] == '+') {
                break;
            }
            top--;
        }
        if (top == -1) {
            return new Border(-1,-1,-1,-1);
        }

        // if bottom == H+1, means its not in any image
        while (btm <= H) {
            if (mtx[btm][j] == '+') {
                break;
            }
            btm++;
        }
        if (btm == H+1) {
            return new Border(-1,-1,-1,-1);
        }

        // if left == -1, means its not in any image
        while (left >= 0) {
            if (mtx[i][left] == '+') {
                break;
            }
            left--;
        }
        if (left == -1) {
            return new Border(-1,-1,-1,-1);
        }

        // if right == W+1, means its not in any image
        while (right <= W) {
            if (mtx[i][right] == '+') {
                break;
            }
            right++;
        }
        if (right == W+1) {
            return new Border(-1,-1,-1,-1);
        }

        return new Border(top,left,btm,right);
    }

    static class Border {
        public int tlh;
        public int tlw;
        public int brh;
        public int brw;

        public Border(int a, int b, int c, int d) {
            this.tlh = a;
            this.tlw = b;
            this.brh = c;
            this.brw = d;
        }

        public String toString() {
            return tlh + "," + tlw + "," + brh + "," + brw;
        }
    }
}
