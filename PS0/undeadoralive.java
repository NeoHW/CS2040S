import java.util.Scanner;

public class undeadoralive {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.indexOf(":)") >= 0 && input.indexOf(":(") >= 0) {
            System.out.println("double agent");
        } else if (input.indexOf(":(") >= 0) {
            System.out.println("undead");
        } else if (input.indexOf(":)") >= 0) {
            System.out.println("alive");
        } else {
            System.out.println("machine");
        }
    }
}