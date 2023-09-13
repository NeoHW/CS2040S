import java.util.*;

public class numbertree {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int num = 1;

        char[] arr = sc.nextLine().toCharArray();
        for (int i = 1; i < arr.length; i++) { // i=0 is " "
            // if L: num = num << 1, if R: num = (num << 1) + 1    
            num = num<<1;
            if(arr[i] == 'R') {
                num++;
            }
        }
        System.out.println((1 << (height+1)) - num);
    }
}