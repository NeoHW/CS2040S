import java.util.*;

public class laptopstickers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt(), height = sc.nextInt(), numStickers = sc.nextInt();

        char[][] mtx = new char[height][length];

        for(int i=0; i<height;i++) {
            for(int j =0; j< length; j++) {
                mtx[i][j] = '_';
            }
        }

        char alphabet = 97; // 97 is ascii for a

        for(int i=0; i < numStickers; i++) {
            int sl = sc.nextInt(), sh = sc.nextInt(), tlcc = sc.nextInt(), tlcr = sc.nextInt();
            
            for(int x = tlcr; x < tlcr + sh; x++) {
                if(x >= height) break;
                for(int y = tlcc; y < tlcc + sl; y++) {
                    if(y >= length) break;
                    mtx[x][y] = alphabet;
                    // mtx[x][y] = (char)('a' + i);
                }
            }
            alphabet++;
        }

        for(int i=0; i<height;i++) {
           for(int j =0; j< length; j++) {
                System.out.print(mtx[i][j]);
            }
            System.out.println();
        }
    }
}
