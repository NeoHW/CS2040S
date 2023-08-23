import java.util.Scanner;

public class height {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numStu = 20;
        int[] arr = new int[numStu];
        int n = sc.nextInt();

        while (n-- > 0) {
            int ans = 0;
            int k = sc.nextInt();

            // populating arr
            for (int i = 0; i < numStu; i++) {
                arr[i] = sc.nextInt();
            }

            // sorting
            for (int i = 1; i < numStu; i++) {
                int key = arr[i];
                int j = i-1;
                while (j >= 0 && arr[j] > key) {
                    arr[j+1] = arr[j];
                    j--;
                    ans++;
                }
                arr[j + 1] = key;
            }

            System.out.println(k + " " + ans);
        }
    }
}
