import java.util.Scanner;

public class bokhyllor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int smallBooks = sc.nextInt();
        int medBooks = sc.nextInt();
        int bigBooks = sc.nextInt();
        int width = sc.nextInt();
        int ans = 0;
        int maxBigBooks = width/3;

        while (smallBooks!=0 || medBooks!=0 || bigBooks!=0) {
            int copyWidth = width;
            int numBigBooksSlotted = bigBooks<=maxBigBooks ? bigBooks : maxBigBooks;
            copyWidth -= numBigBooksSlotted*3;
            bigBooks -= numBigBooksSlotted;

            if (copyWidth > 0 && medBooks > 0) {
                int maxMedBooks = copyWidth/2;
                int numMedBooksSlotted = medBooks<=maxMedBooks ? medBooks : maxMedBooks;
                copyWidth -= numMedBooksSlotted*2;
                medBooks -= numMedBooksSlotted;
            }

            if (copyWidth > 0 && smallBooks > 0) {
                int maxSmallBooks = copyWidth;
                int numSmallBooksSlotted = smallBooks<=maxSmallBooks ? smallBooks : maxSmallBooks;
                copyWidth -= numSmallBooksSlotted;
                smallBooks -= numSmallBooksSlotted;

            }
            ans++;
        }
        System.out.println(ans);
    }
}