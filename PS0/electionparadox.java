import java.util.Scanner;
import java.util.Arrays;

public class electionparadox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numRegions = sc.nextInt();
        int[] arr = new int[numRegions];
        for(int i=0; i < numRegions; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int totalVotes = 0;
        
        // 3 lose 2 , 5 lose 3, 7 lose 4
        for(int i=0; i <= numRegions/2; i++) {
            totalVotes = totalVotes + (arr[i] / 2);
        }
        
        for(int i=numRegions/2 +1; i < numRegions; i++) {
            totalVotes = totalVotes + arr[i];
        }

        System.out.println(totalVotes);
    }
}