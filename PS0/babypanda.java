import java.util.Scanner;

public class babypanda {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        // note use long for large numbers
        long totalDays = sc.nextLong();
        long totalSlimes = sc.nextLong();
        int numSneezes = 1;

        // edge case
        if (totalSlimes == 0) {
            System.out.println(0);
            return;
        }

        while (totalSlimes != 0) {
            // finding largest num before 
            for (int i=1; i<=totalDays; i++) {
                if (slimesAfterDays(i) == totalSlimes) {
                    System.out.println(numSneezes);
                    return;
                }
                if (slimesAfterDays(i) > totalSlimes) {
                    totalSlimes -= slimesAfterDays(i-1);
                    numSneezes++;
                    break;
                }
            }
        }
    }

    public static long slimesAfterDays(int days) {
        return (long) Math.pow(2, days);
    }
}
