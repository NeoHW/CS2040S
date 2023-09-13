import java.util.*;
import java.io.*;

public class janeeyre {
    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int unread = Integer.parseInt(token[0]);
        int given = Integer.parseInt(token[1]);
        int JEnumPages = Integer.parseInt(token[2]);

        ArrayList<Book> toBeAdded = new ArrayList<>();
        PriorityQueue<Book> pq = new PriorityQueue<>(1, (a, b) -> a.name.compareTo(b.name));
        // PriorityQueue<Book> pq = new PriorityQueue<>(1, (a, b) -> a.name.replaceAll("\\s", "").compareTo(b.name.replaceAll("\\s", ""))); (if dont compare spaces)
        pq.add(new Book("Jane Eyre", JEnumPages));

        for(int i = 0; i < unread; i++) {
            String[] parts = br.readLine().split("\"");
            String name = parts[1];
            int pages = Integer.parseInt(parts[2].trim());
            pq.add(new Book(name, pages));
        }

        for(int i = 0; i < given; i++) {
            String[] parts = br.readLine().split("\"");
            int time = Integer.parseInt(parts[0].trim());
            String name = parts[1];
            int pages = Integer.parseInt(parts[2].trim());
            toBeAdded.add(new Book(time, name, pages));
        }

        // sort the toBeAdded books by earliest time
        Collections.sort(toBeAdded, (a, b) -> a.time - b.time);

        //debuggin
        for(Book b : toBeAdded) {
            System.out.println("book in tobeadded: " + b.name);
        }

        int time = 0;
        boolean done = false;
        while(!done) {
            // check time add books
            boolean booksAddedDone = false;
            while(!booksAddedDone) {
                if(!toBeAdded.isEmpty() && toBeAdded.get(0).time > time) {
                    booksAddedDone = true;
                } else {
                    pq.add(toBeAdded.remove(0));
                }
            }

            // get pq first book and add time
            Book b = pq.poll();
            System.out.println("Book: " + b.name);
            time += b.pages;

            if(b.name.equals("Jane Eyre")) {
                done = true;
            }
        }
        writer.println(time);
        writer.flush();

    }
}

class Book {
    public int time;
    public String name;
    public int pages;

    public Book(int time, String name, int pages) {
        this.time = time;
        this.name = name;
        this.pages = pages;
    }

    public Book(String name, int pages) {
        this.time = 0;
        this.name = name;
        this.pages = pages;
    }
}