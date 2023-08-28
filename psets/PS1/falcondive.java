// A0264683U
// Neo Haowei
// lab 07
// Rezwan Arefin

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class falcondive {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int numLines = Integer.parseInt(token[0]);
        int numChars = Integer.parseInt(token[1]);
        char c = token[2].charAt(1);

        char[][] mtx = new char[numLines][numChars];
        String str1 = "";
        String str2 = "";

        for(int i = 0; i < numLines; i++) {
            str1 = str1 + br.readLine();
        }
        br.readLine();

        for(int i = 0; i < numLines; i++) {
            str2 = str2 + br.readLine();
        }

        int f1r = -1;
        int f1c = -1;
        int f2r = -1;
        int f2c = -1;
        int f2count = -1;
        int f2countfinal = -1;

        int count = 0;
        // creating the full background matrix
        for(int i = 0; i < numLines; i++) {
            for(int j = 0; j < numChars; j++) {
                // getting first c in both frames
                if(str1.charAt(count) == c && f1r == -1) {
                    f1r = i;
                    f1c = j;
                }
                if(str2.charAt(count) == c) {
                    if(f2r == -1) {
                        f2r = i;
                        f2c = j;
                        f2count = count;
                    }
                    f2countfinal = count;
                }

                if(str1.charAt(count) != c) {
                    mtx[i][j] = str1.charAt(count);
                } else {
                    mtx[i][j] = str2.charAt(count);
                }
                count++;
            }
        }

        // finding displacement of falcon
        int dr = f2r - f1r;
        int dc = f2c - f1c;

        count = 0;

        while (f2count <= f2countfinal) {
            if(str2.charAt(f2count) == c) {
                int row = f2count/numChars + dr;
                int col = f2count%numChars + dc;
                if((row >= 0 && row <= numLines-1) && (col >= 0 && col <= numChars -1)) {
                    mtx[row][col] = c;
                }
            }
            f2count++;
        }

        for(int i=0; i < numLines; i++) {
            for(int j=0; j < numChars; j++) {
                writer.print(mtx[i][j]);
            }
            writer.println();
        }

        writer.flush(); // !!IMPT : to ensure output is printed
        writer.close();

    }    
}
