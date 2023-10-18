import java.util.*;
import java.io.*;

public class weakvertices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt(); sc.nextLine();
            if (n == -1) {
                break;   
            }
            
            int[][] mtx = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    mtx[i][j] = sc.nextInt();
                }
                sc.nextLine();
            }

            for (int i = 0; i < n; i++) {
                boolean isWeak = true;
                for(int j = 0; j < n; j++) {
                    if(mtx[i][j] == 0) {
                        continue;
                    }
                    for(int k = j+1; k < n; k++) {
                        if(mtx[i][k] == 0) {
                            continue;
                        }
                        if(mtx[j][k] == 1) {
                            isWeak = false;
                        }
                    }
                }
                if(isWeak) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }
}
