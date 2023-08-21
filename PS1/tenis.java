// A0264683U
// Neo Haowei
// lab 07
// Rezwan Arefin

import java.util.Scanner;

public class tenis {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split(" ");
        String a = parts[0], b = parts[1];
        int numGames = sc.nextInt();
        sc.nextLine();

        for(int i=0;i<numGames;i++) {
            boolean invalid = false;
            int aWins = 0, bWins = 0;

            String[] arr = sc.nextLine().split(" ");

            if(arr.length == 1 || arr.length > 3) {
                invalid = true;
            } else {
                for(int j = 0; j<arr.length;j++) {
                    if(setWinner(j,arr[j], aWins, bWins) == 0) {
                        if(b.equals("federer")) {
                            invalid = true;
                            break;
                        }
                        aWins++;
                    } else if(setWinner(j,arr[j], aWins, bWins) == 1) {
                        if(a.equals("federer")) {
                            invalid = true;
                            break;
                        }
                        bWins++;
                    } else {
                        invalid = true;
                        System.out.println("invalid at line 39");
                        break;
                    }
                }
            }

            // check if theres a winner
            if(aWins < 2 && bWins < 2) {
                invalid = true;
                System.out.println("invalid at line 48");
            }

            
            if(invalid == true) {
                System.out.println("ne");
            } else {
                System.out.println("da");
            }

        }

    }

    public static int setWinner(int matchNum, String results, int aWins, int bWins) {
        System.out.println("match " + matchNum);
        
        if (aWins == 2 || bWins == 2) {
            System.out.println("invalid at line 64");
            return -1;
        }

        // parse results
        String[] parts = results.split(":");
        int s1 = Integer.parseInt(parts[0]);
        int s2 = Integer.parseInt(parts[1]);

        // checking for both less than 6 games
        if((s1 < 6 && s2 < 6)) {
            System.out.println("invalid at line 76");
            return -1;
        }

        // return 0 if a wins, 1 if b wins, -1 if invalid 
        if (matchNum != 2) {
            // should nvr go to 8 games
            if(s1 > 7 || s2 > 7) {
                System.out.println("invalid at line 84");
                return -1;
            }

            // 7:5/7:6 is valid; below 7:4 is not ; 7:7 not valid
            if((s1 == 7 || s2 == 7)) {
                if(s1-s2 > 2 || s1-s2 < -2 || s1-s2 == 0) {
                    System.out.println("invalid at line 91");
                    return -1;
                }    
            }
        }
        
        // wins at least 2 games more than opp
        return (s1-s2 >= 2) ? 0 : 1;
    }
}