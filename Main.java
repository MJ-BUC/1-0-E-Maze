import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Maze {

    public static void main(String args[]) {
        System.out.println("Hello World");
        Stack mystack = new Stack<>();
        mystack.push(0);
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        mystack.push(4);
        mystack.push(5);

        for (int i = 0; i < 6; i ++) {
            System.out.println(mystack.pop());
        }
    }
}