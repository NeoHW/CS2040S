import java.util.Scanner;

public class codetosavelives {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTestCases = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < numTestCases; i++) {
            String s1 = sc.nextLine();
            s1 = s1.replaceAll("\\s", "");
            int num1 = Integer.valueOf(s1);

            String s2 = sc.nextLine();
            s2 = s2.replaceAll("\\s", "");
            int num2 = Integer.valueOf(s2);
            String ans = String.valueOf(num1+ num2);
            
            char[] digits = ans.toCharArray();
            String finalAns = "";
            for (int j = 0; j < digits.length; j++) {
                finalAns = finalAns + digits[j] + " ";
            }
            System.out.println(finalAns.trim());
        }
    }
}
