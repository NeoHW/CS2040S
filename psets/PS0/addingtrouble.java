import java.util.Scanner;

public class addingtrouble {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt(), num2 = sc.nextInt(), num3 = sc.nextInt();
        //String ans = (num1 + num2 == num3) ? "correct!" : "wrong!";
        // System.out.println(ans);
        System.out.println((num1+num2==num3) ? "correct!" : "wrong!");
    }
}