import java.util.Scanner;

public class sifferprodukt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int ans = 1;
        boolean flag = true;

        while (flag) {
            while (num > 0) {
                int lastDigit = num % 10;
                lastDigit = (lastDigit == 0) ? 1 : lastDigit;
                ans *= lastDigit;
                num/=10;
            }
            flag = false;
            if (ans >= 10) {
                flag = true;
                num = ans;
                ans = 1;
            }
        }
        System.out.println(ans);
    }
}