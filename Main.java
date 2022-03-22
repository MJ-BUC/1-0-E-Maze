import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Maze {

    public static void main(String args[]) {
        String[] arr = new String[20];
        int index = 0;

        File mazeFile = new File("C:\\Users\\lunke\\OneDrive\\Documents\\Github\\10EMaze\\maze.txt");

        try {
            Scanner maze = new Scanner(mazeFile);
            while(maze.hasNext()) {
                String item = maze.next();
                arr[index] = item;
                index++;
            }
            maze.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("file not found!");
        }

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

        //debug
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        //debug


        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i]);
            System.out.print("\n");
        }
    }
}