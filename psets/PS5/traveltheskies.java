import java.util.*;

import javax.print.DocFlavor.STRING;

public class traveltheskies {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt(); // num airports
        int n = sc.nextInt(); // num days
        int m = sc.nextInt(); // num flights

        long[] numPeople = new long[k+1]; // for total num of ppl at each airport
        long[] peopleFromFlights = new long[k+1]; // for people arriving from flights
        TreeMap<Integer, ArrayList<FlightData>> EL = new TreeMap<>();
        TreeMap<Integer, ArrayList<ArrivalData>> data = new TreeMap<>();

        for (int i = 1; i < n+1; i++) { // use n as we need get(day) below
            EL.put(i, new ArrayList<FlightData>());
        }

        for (int i = 1; i < n+1; i++) {
            data.put(i, new ArrayList<ArrivalData>());
        }


        while (m-- > 0) {
            int u = sc.nextInt(); // depart
            int v = sc.nextInt(); // arrival
            int d = sc.nextInt(); // day
            int z = sc.nextInt(); // capacity
            
            ArrayList<FlightData> list = EL.get(d);
            list.add(new FlightData(u,v,z));
        }
        
        int kn = k*n;
        while(kn -- > 0) {
            int a = sc.nextInt(); // airport
            int b = sc.nextInt(); // day they arrive 
            int c = sc.nextInt(); // number of cust who wants to leave
            
            ArrayList<ArrivalData> list = data.get(b);
            list.add(new ArrivalData(a,c));
        }
        
        for (int day = 1; day <= n+1; day++) { 
            // add in people who arrives from their homes
            if (data.get(day) != null) {
                for (ArrivalData ad : data.get(day)) {
                    numPeople[ad.airport] += ad.num;
                }
            }

            // doing the flights capacity checking
            if (EL.get(day) != null) {
                for (FlightData fd : EL.get(day)) {
                    if (fd.capacity > numPeople[fd.depart]) {
                        System.out.println("suboptimal");
                        return;
                    }
                    numPeople[fd.depart] -= fd.capacity;
                    // customer can only take one flight per day, so use temp array to store first
                    peopleFromFlights[fd.arrival] += fd.capacity;
                }
            }

            // update total number of people at end of data in numPeople array
            for (int i = 0; i < k+1; i++) {
                numPeople[i] += peopleFromFlights[i];
                peopleFromFlights[i] = 0;
            }
        }

        System.out.println("optimal");

    }


    static class FlightData {
        public int depart;
        public int arrival;
        public int capacity;

        public FlightData(int b, int c, int d) {
            this.depart = b;
            this.arrival = c;
            this.capacity = d;
        }
    }

    static class ArrivalData {
        public int airport;
        public int num;

        public ArrivalData(int b, int c) {
            this.airport = b;
            this.num = c;
        }
    }
}
