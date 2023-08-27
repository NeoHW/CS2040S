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
                        break;
                    }
                }
            }

            // check if theres a winner
            if(aWins < 2 && bWins < 2) {
                invalid = true;
            }

            
            if(invalid == true) {
                System.out.println("ne");
            } else {
                System.out.println("da");
            }

        }

    }

    public static int setWinner(int matchNum, String results, int aWins, int bWins) {
        // note that matches are 0,1,2

        if (aWins == 2 || bWins == 2) {
            return -1;
        }

        // parse results
        String[] parts = results.split(":");
        int s1 = Integer.parseInt(parts[0]);
        int s2 = Integer.parseInt(parts[1]);

        // checking for both less than 6 games
        if((s1 < 6 && s2 < 6)) {
            return -1;
        }

        // return 0 if a wins, 1 if b wins, -1 if invalid 
        if (matchNum != 2) {
            // should nvr go to 8 games
            if(s1 > 7 || s2 > 7) {
                return -1;
            }

            // 7:5/7:6 is valid; below 7:4 is not ; 7:7 not valid (check all 7s combi)
            if((s1 == 7 || s2 == 7)) {
                if(s1-s2 > 2 || s1-s2 < -2 || s1-s2 == 0) {
                    return -1;
                } else {
                    return s1-s2 > 0 ? 0 : 1;
                }
            }
        }


        // 7:4 is not valid 3rd match
        if(matchNum == 2 && ((s1 > 6 && s2 < 5) || (s2 > 6 && s1 < 5))) {
            return -1;
        }
        
        // checking if there is a 2 point diff
        if(s1-s2 < 2 && s1-s2 > -2) {
            return -1;
        }

        return (s1-s2 >= 2) ? 0 : 1;
    }
}