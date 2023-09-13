import java.io.*;
import java.util.*;
import java.lang.Math;

public class universityzoning {
    public static void main (String[] args) {
        FastInput sc = new FastInput(System.in);
        int R = sc.nextInt(), C = sc.nextInt(), F = sc.nextInt(), S = sc.nextInt(), G = sc.nextInt();

        ArrayList<ArrayList<Point>> faculties = new ArrayList<>();
        ArrayList<Long> finalAnsList = new ArrayList<>();

        // next F lines
        for (int i = 0; i < F; i++) {
            ArrayList<Point> list = new ArrayList<>();
            int num = sc.nextInt();
            for(int j = 0; j < num; j++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                list.add(new Point(r,c));
            }
            // sort the points by row, if tie then column
            Collections.sort(list, new Comparator<Point>() {
                public int compare(Point p, Point q) {
                    if (p.r == q.r) {
                        return p.c-q.c;
                    } 
                    return p.r-q.r;
                }
            });
            faculties.add(list);
        }

        ArrayList<Student> studentList = new ArrayList<>();
        // next S lines
        for (int i = 0; i < S; i++) {
            long r = sc.nextLong();
            long c = sc.nextLong();
            int sn = sc.nextInt();
            int f = sc.nextInt();
            studentList.add(new Student(r,c,sn,f));
        }

        // T values for each faculty
        int[] tValues = new int[F];
        for (int i = 0; i < F; i++) { 
            tValues[i] = sc.nextInt();
        }

        // for each faculty, sort the student by id, calculate distance
        for (int i=0; i<F; i++) {
            ArrayList<Point> list = faculties.get(i);
            
            // creating facStuList
            ArrayList<Student> facStuList = new ArrayList<>();
            for(int j = 0; j < studentList.size(); j++) {
                if(studentList.get(j).f == i+1) {
                    facStuList.add(studentList.get(j));
                }
            }

            // sorting
            Collections.sort(facStuList, new Comparator<Student>() {
                public int compare(Student a, Student b) {
                    return a.sn - b.sn;
                }
            });

            ArrayList<Long> stepsToTake = new ArrayList<>();
            for(int k = 0; k < facStuList.size(); k++) {
                // distance for student to assigned area 
                stepsToTake.add(Math.abs(facStuList.get(k).r - list.get(k).r) + Math.abs(facStuList.get(k).c -list.get(k).c));
            }
            Collections.sort(stepsToTake);

            // finding minumum step for each faculty
            long totalSteps = 0;
            for(int x = 0; x < tValues[i]; x++) {
                totalSteps += stepsToTake.get(x);
            }

            finalAnsList.add(totalSteps);
        }

        long finalAns = 0;

        Collections.sort(finalAnsList);
        for(int i = 0; i < G; i++) {
            finalAns += finalAnsList.get(i);
        }

        System.out.println(finalAns);

    }
}

class Point {
    public int r, c;
    public Point(int r, int c) {
      this.r = r;
      this.c = c;
    }
}

class Student {
    public long r,c;
    public int sn,f;
    public Student(long r, long c, int sn, int f) {
        this.r = r;
        this.c = c;
        this.sn = sn;
        this.f = f;
    }
}

class FastInput {
    BufferedReader br;
    StringTokenizer tok;
    public FastInput(InputStream in) {
        br = new BufferedReader(new InputStreamReader(System.in));
        tok = new StringTokenizer("");
    }

    public String next() {
        while (!tok.hasMoreTokens()) {
            try {
                tok = new StringTokenizer(br.readLine());
            } catch (IOException e) {
            }
        }
        return tok.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}