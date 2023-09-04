import java.util.*;

public class gearchanging {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt(), P = sc.nextInt();

        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            C.add(sc.nextInt());
        }

        ArrayList<Integer> D = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            D.add(sc.nextInt());
        }

        ArrayList<Double> ratios = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                ratios.add((double)C.get(i) / D.get(j));
            }
        }
        Collections.sort(ratios); // sort in non-decreasing


        boolean rideOn = true;
        for(int i = 0; i < ratios.size() -1; i++) {
            if((ratios.get(i+1) - ratios.get(i)) / ratios.get(i) > P/100.0)
                rideOn = false;
        }

        System.out.println(rideOn ? "Ride On!" : "Time to change gears!");

        /*
        crank: 50 34
        back: 11 12 13 14 16 18 20 22 25 28 32
        
        50/11 50/12 50/13 ... 50/32 34/11 34/12 ... 34/32
        */



    }
}