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
        
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        int count = 0;
        // creating the full background matrix
        for(int i = 0; i < numLines; i++) {
            for(int j = 0; j < numChars; j++) {
                if(arr1[count] != c) {
                    mtx[i][j] = arr1[count];
                } else {
                    mtx[i][j] = arr2[count];
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
    }    
}
