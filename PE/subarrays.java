// 6% VA QQ 3 will be easier than this

import java.util.*;
import java.io.*;

public class subarrays {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tok = br.readLine().split(" ");
        int N = Integer.parseInt(tok[0]);
        int K = Integer.parseInt(tok[1]);
        long B = Long.parseLong(tok[2]);

        int smallestLeft = Integer.MAX_VALUE;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if (a.first == b.first) {
                return a.second - b.second;
            } else {
                return a.first - b.first;
            }
        });


        int[] arr = new int[N];
        String[] tok2 = br.readLine().split(" ");
        
        for(int i = 0; i < N ; i++) {
            arr[i] = Integer.parseInt(tok2[i]);
        }
        // formula : B = sum of elements - (k * length)

        // sliding window 
        for (int length = 0; length < N ; length++) {
            int left = 0;
            int right = length;
            long currSum = 0;

            for(int i = 0 ; i <= right; i++) {
                currSum += arr[i];
            }

            while(right < N) {
                
                if (left > smallestLeft) {
                    break;
                }

                long beauty = currSum - (right-left+1) * K;
                // if(length == 2) {
                //     // System.out.println("left:" + left);
                //     // System.out.println("right:" + right);
                //     // System.out.println("currSum:" + currSum);
                //     // System.out.println("beauty:" + beauty);
                // }
                
                if (beauty == B) {
                    pq.add(new Pair(left,right));
                    smallestLeft = Math.min(smallestLeft, left);
                    break;
                } else {
                    if(right == N-1) {
                        break;
                    }
                    currSum -= arr[left];
                    left++;
                    right++;
                    currSum += arr[right];
                }
            }
        }
        if (pq.isEmpty()) { 
            System.out.println("-1");
        } else {
            Pair p = pq.poll();
            System.out.println(p.first + " " + p.second);
        }
    }

    static class Pair {
        public int first;
        public int second;

        public Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }

        public String toString() {
            return "[" + first + "," + second + "]";
        }
    }
}
