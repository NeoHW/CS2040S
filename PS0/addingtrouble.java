import java.util.Scanner;

public class addingtrouble {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        String ans = (num1 + num2 == num3) ? "correct!" : "wrong!";
        System.out.println(ans);
    }
}