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


        int count = 0;

        // greedy?
        while (smallBooks!=0 || medBooks!=0 || bigBooks!=0) {
            System.out.println("loop count: " + count);

            int copyWidth = width;
            int numBigBooksSlotted = bigBooks<=maxBigBooks ? bigBooks : maxBigBooks;
            System.out.println("numBigBooksSlotted: " + numBigBooksSlotted);
            copyWidth -= numBigBooksSlotted*3;
            System.out.println("copyWidth: " + copyWidth);
            bigBooks -= numBigBooksSlotted;
            System.out.println("bigBooks: " + bigBooks);

            if (copyWidth > 0 && medBooks > 0) {
                int maxMedBooks = copyWidth/2;
                int numMedBooksSlotted = medBooks<=maxMedBooks ? medBooks : maxMedBooks;
                System.out.println("numMedBooksSlotted: " + numMedBooksSlotted);
                copyWidth -= numMedBooksSlotted*2;
                System.out.println("copyWidth: " + copyWidth);
                medBooks -= numMedBooksSlotted;
                System.out.println("medBooks: " + medBooks);
            }

            if (copyWidth > 0 && smallBooks > 0) {
                int maxSmallBooks = copyWidth;
                int numSmallBooksSlotted = smallBooks<=maxSmallBooks ? smallBooks : maxSmallBooks;
                System.out.println("numSmallBooksSlotted: " + numSmallBooksSlotted);
                copyWidth -= numSmallBooksSlotted;
                System.out.println("copyWidth: " + copyWidth);
                smallBooks -= numSmallBooksSlotted;
                System.out.println("smallBooks: " + smallBooks);

            }
            ans++;
            count++;
        }

        System.out.println(ans);
    }
}
