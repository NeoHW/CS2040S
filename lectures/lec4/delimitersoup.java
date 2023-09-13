import java.io.*;
import java.util.*;

public class delimitersoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); sc.nextLine();
        String string =sc.nextLine();
        
        Stack<Character> s = new Stack<>();
        String openBrackets = "([{";

        for (int i =0; i < n; i++) {
            char c = string.charAt(i);
            if (c == ' ') {
                continue;
            } else if (openBrackets.indexOf(c) != -1 ) {
                s.push(c);
            } else {
                if (s.isEmpty() || !checkMatch(c,s.peek())) {
                    System.out.println(c + " " + i);
                    return;
                } else {
                    s.pop();
                }
            }
        }
        System.out.println("ok so far");
    }

    public static boolean checkMatch(char c, char c2) {
        return ((c == ')' && c2 == '(') 
            || (c == ']' && c2 == '[') 
            || (c == '}' && c2 == '{') 
        );
    }
}

