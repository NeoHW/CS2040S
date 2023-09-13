import java.util.*;

public class numbertree {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int N = (int) Math.pow(2,height+1) - 1;
        int num = 1;

        String[] arr = sc.nextLine().trim().split("");
        for (String i : arr) {
            if(i.equals("L")) {
                num = num<<1;
            } else if(i.equals("R")) {
                num = (num<<1) + 1;
            } else {
                continue;
            }
        }

        System.out.println(N + 1 - num);
    }
}