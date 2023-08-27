import java.util.Scanner;

public class internationaldates {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] parts = input.split("/");
        if (Integer.valueOf(parts[0]) > 12) {
            System.out.println("EU");
        } else if (Integer.valueOf(parts[1]) > 12) {
            System.out.println("US");
        } else {
            System.out.println("either");
        }
    }
}