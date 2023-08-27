import java.util.Scanner;

public class coffeecupcombo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLectures = sc.nextInt();
        sc.nextLine();
        String CFavailbility = sc.nextLine();
        char[] arr = CFavailbility.toCharArray();
        int ans = 0;

        if(numLectures>3) {
            for(int i=0; i < arr.length-2; i++) {
                if(arr[i] == '1') {
                    // check for '11', +1
                    if(arr[i+1] == '1') {
                        ans++;
                    } else {
                        // check for '10' or '100', +2
                        if(arr[i+2] == '0') {
                            ans+=3;    
                        } else {
                            ans+=2;
                        }
                    }                
                }
            }

            // check last 2
            if(arr[arr.length-2] == '1') {
                ans+=2;
            } else {
                if(arr[arr.length-1] == '1') {
                    ans++;
                }
            }
        }
        
        if(numLectures<=3) {
            for(int i=0; i < arr.length; i++) {
                if(arr[i] == '1') {
                    ans = numLectures-i;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}