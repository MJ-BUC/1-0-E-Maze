import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Maze {

    public static void main(String args[]) {
        // reading file
        String[] lineByLineArray = new String[20];
        char[] charByCharArray = new char[400];

        int index = 0;

        File mazeFile = new File("C:\\Users\\lunke\\OneDrive\\Documents\\Github\\10EMaze\\maze.txt");

        try {
            Scanner maze = new Scanner(mazeFile);
            while(maze.hasNext()) {
                String item = maze.next();
                lineByLineArray[index] = item;
                index++;
            }
            maze.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("file not found!");
        }

        // for loop to insert each character of each line into a single array; one char per index
        index = 0;
        for (String line:lineByLineArray) {
            for (int i = 0; i < 20; i++) {
                charByCharArray[index] = line.charAt(i);
                index++;
            }
        }


        // using a stack in java
        Stack mystack = new Stack<>();
        mystack.push(0);
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        mystack.push(4);
        mystack.push(5);

        // for (int i = 0; i < 6; i ++) {
        //     System.out.println(mystack.pop());
        // }

        //debug
        // System.out.println(lineByLineArray[0]);
        // System.out.println(lineByLineArray[1]);
        //debug


        for (int i = 0; i < 400; i++) {
            System.out.print(charByCharArray[i]);
            // System.out.print("\n");
        }
    }
}