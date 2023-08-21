import java.util.*;

public class falcondive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLines = sc.nextInt(), numChars = sc.nextInt();
        String input = sc.nextLine().trim();
        char c = input.charAt(1);

        char[][] mtx = new char[numLines][numChars];
        String str1 = "";
        String str2 = "";

        for(int i = 0; i < numLines; i++) {
            str1 = str1 + sc.nextLine();
        }
        sc.nextLine();

        for(int i = 0; i < numLines; i++) {
            str2 = str2 + sc.nextLine();
        }

        int f1r = -1;
        int f1c = -1;
        int f2r = -1;
        int f2c = -1;

        int count = 0;
        // creating the full background matrix
        for(int i = 0; i < numLines; i++) {
            for(int j = 0; j < numChars; j++) {
                // getting first c in both frames
                if(str1.charAt(count) == c && f1r == -1) {
                    f1r = i;
                    f1c = j;
                }
                if(str2.charAt(count) == c && f2r == -1) {
                    f2r = i;
                    f2c = j;
                }

                if(str1.charAt(count) != c) {
                    mtx[i][j] = str1.charAt(count);
                } else {
                    mtx[i][j] = str2.charAt(count);
                }
                count++;
            }
        }

        for(int i = 0; i < numLines; i++) {
            for(int j = 0; j < numChars; j++) {
                System.out.print(mtx[i][j]);
            }
            System.out.println();
        }

        System.out.println("f1r "+ f1r);
        System.out.println("f1c "+ f1c);
        System.out.println("f2r "+ f2r);
        System.out.println("f2c "+ f2c);

    }    
}
